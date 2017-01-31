package simplepaint;

/**
 *
 * @author Geir Haugen
 *  This program is a very simple and basic shape painting program. The program can draw circles, squares, lines.
 *  The program does hav a functionality for undoing and redoing as well as clearing the canvas.
 *  The canvas can be saved to a java serialized file which can be opened, edited and saved etc.
 *  This project was originally a part of an assignment on my second year as a bachelor student.
 *  Except from the PrintHandler class, i created this program by myself.
 *  Feel free to reuse/copy any of the code used in this project.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
 
@SuppressWarnings("serial")
public class SimplePaint extends JFrame {
 
    private int type;
    private Vector<Shape> shapes;
    private CenterPanel centerPanel;
    private ButtonPanel buttonPanel;
    private PaintMenu menu;
    private Stack<Shape> undoStack;
    private Stack<Shape> redoStack;
 
    public SimplePaint() {
        super("SimplePaint");
        type = 0;
        undoStack = new Stack<Shape>();
        redoStack = new Stack<Shape>();
        menu = new PaintMenu();
        centerPanel = new CenterPanel();
        buttonPanel = new ButtonPanel();
        add(menu, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(600, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private class PaintMenu extends JPanel implements ActionListener {
        Shape tempShape = null;
 
        public PaintMenu() {
            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            fileMenu.setMnemonic('f');
            JMenuItem open = new JMenuItem("Open");
            JMenuItem save = new JMenuItem("Save");
            JMenuItem print = new JMenuItem("Print");
            JMenuItem exit = new JMenuItem("Exit");
 
            fileMenu.add(open);
            fileMenu.add(save);
            fileMenu.add(print);
            fileMenu.add(exit);
 
            open.addActionListener(this);
            save.addActionListener(this);
            print.addActionListener(this);
            exit.addActionListener(this);
            menuBar.add(fileMenu);
 
            JMenu editMenu = new JMenu("Edit");
            JMenuItem undo = new JMenuItem("Undo");
            JMenuItem redo = new JMenuItem("Redo");
            JMenuItem clear = new JMenuItem("Clear");
            undo.addActionListener(this);
            redo.addActionListener(this);
            clear.addActionListener(this);
            editMenu.add(undo);
            editMenu.add(redo);
            editMenu.add(clear);
            menuBar.add(editMenu);
            setJMenuBar(menuBar);
        }
 
        // Listener for the menu in the upper part of the window
        public void actionPerformed(ActionEvent evt) {
            String command = evt.getActionCommand();
 
            if (command.equals("Open")) {
                if (shapes.size() == 0) {
                    openFile();
                } else if (shapes.size() != 0) {
                    askToSave(1);
                }
            } else if (command.equals("Save")) {
                save();
            } else if (command.equals("Print")) {
                PrintHandler.printComponent(centerPanel);
            } else if (command.equals("Exit")) {
                if (shapes.size() != 0) {
                    askToSave(2);
                } else if (shapes.size() == 0) {
                    System.exit(0);
                }
            } else if (command.equals("Undo")) {
                undo();
            } else if (command.equals("Redo")) {
                redo();
            } else if (command.equals("Clear")) {
                clear();
            }
        }
 
        // Prompts the user about saving. Requests an int to determine what to do. 1 = If user opens a file. 2 = If the program is closed.
        private int askToSave(int criteria) {
            int result = JOptionPane.showConfirmDialog(null, "Would you like to save?", "Select an Option", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
            if (result == JOptionPane.YES_OPTION && criteria == 1) {
                save();
            } else if (result == JOptionPane.NO_OPTION && criteria == 1) {
                openFile();
            } else if (result == JOptionPane.YES_OPTION && criteria == 2) {
                save();
                System.exit(0);
            } else if (result == JOptionPane.NO_OPTION && criteria == 2) {
                System.exit(0);
            }
            return result;
        }
        
        // Saves Vector<Shape> shapes as a serialized file (The user have to add ".ser" at the end of the filename when saving).
        private void save() { 
            JFileChooser fileSaver = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("Java Serialized", "ser");
            fileSaver.setFileFilter(filter);
            fileSaver.setCurrentDirectory(new File("."));
            int userSelection = fileSaver.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    FileOutputStream fileOut = new FileOutputStream(fileSaver.getSelectedFile());
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(shapes);
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 
        // Open the chosen serialized file and updates the content on the panel.
        @SuppressWarnings("unchecked")
        private void openFile() {
            JFileChooser fileChooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("Java Serialized", "ser");
            fileChooser.setFileFilter(filter);
            fileChooser.setCurrentDirectory(new File("."));
            int userSelection = fileChooser.showOpenDialog(null);
 
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                try {
                    FileInputStream fileIn = new FileInputStream(fileChooser.getSelectedFile());
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    shapes = (Vector<Shape>) in.readObject();
                    fileIn.close();
                    centerPanel.repaint();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
 
        // Removes the last shape created and adding it to another stack.
        private void undo() {
            if (undoStack.size() != 0 && shapes.size() != 0) {
                tempShape = undoStack.pop();
                shapes.remove(shapes.size() - 1);
                redoStack.push(tempShape);
                centerPanel.repaint();
            }
        }
         
        // Redraw the last shape that was removed. The shape is removed from redoStack and is added to a new stack.
        // The shape is also added back into shapes.
        private void redo() {
            if (redoStack.size() != 0 && shapes.size() != 0) {
                tempShape = redoStack.pop();
                undoStack.push(tempShape);
                shapes.add(tempShape);
                centerPanel.repaint();
            }
        }
 
        // Clears the panel
        private void clear() {
            type = 0;
            shapes.clear();
            centerPanel.repaint();
        }
    }
 
    // Lower buttonpanel
    private class ButtonPanel extends JPanel implements ActionListener {
        private JButton btnCircle, btnEllipse, btnLine, btnRectangle,
                btnSquare, btnExit;
 
        public ButtonPanel() {
            setLayout(new GridLayout(1, 4, 0, 10));
            btnCircle = new JButton("Circle");
            btnEllipse = new JButton("Ellipse");
            btnLine = new JButton("Line");
            btnRectangle = new JButton("Rectangle");
            btnSquare = new JButton("Square");
            btnExit = new JButton("Exit");
            JButton[] buttons = { btnCircle, btnEllipse, btnLine, btnRectangle, btnSquare, btnExit };
            for (JButton button : buttons) {
                button.addActionListener(this);
                add(button);
            }
        }
 
        // Listener for the buttons in the bottom part of the window.
        public void actionPerformed(ActionEvent event) {
            JButton button = (JButton) event.getSource();
            if (button == btnCircle) {
                type = 1;
            } else if (button == btnEllipse) {
                type = 2;
            } else if (button == btnLine) {
                type = 3;
            } else if (button == btnRectangle) {
                type = 4;
            } else if (button == btnSquare) {
                type = 5;
            } else if (button == btnExit) {
                if (shapes.size() != 0) {
                    menu.askToSave(2);
                } else if (shapes.size() == 0) {
                    System.exit(0);
                }
            }
        }
    }
 
    // This class is the drawing panel.
    private class CenterPanel extends JPanel implements MouseListener, MouseMotionListener {
        private final int CIRCLE = 1;
        private final int ELLIPSE = 2;
        private final int LINE = 3;
        private final int RECTANGLE = 4;
        private final int SQUARE = 5;
        private Point startPoint, endPoint;
        private Graphics2D g2;
 
        public CenterPanel() {
            shapes = new Vector<Shape>();
            startPoint = null;
            endPoint = null;
            addMouseListener(this);
            addMouseMotionListener(this);
            setSize(50, 50);
            setVisible(true);
        }
 
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g2 = (Graphics2D) g;
 
            for (int i = 0; i < shapes.size(); i++) {
                Shape s = (Shape) shapes.get(i);
                if (s != null)
                    g2.draw(s);
            }
        }
 
        public void mouseClicked(MouseEvent mouseEvent) {
 
        }
 
        public void mouseEntered(MouseEvent mouseEvent) {
 
        }
 
        public void mouseExited(MouseEvent mouseEvent) {
 
        }
 
        // The startpoint is set when the mouse button is pressed.
        public void mousePressed(MouseEvent mouseEvent) {
            startPoint = mouseEvent.getPoint();
        }
 
        // End point is set when the mouse button is released.
        public void mouseReleased(MouseEvent mouseEvent) {
            endPoint = mouseEvent.getPoint();
            Shape s = null;
            int[] coordinates = null;
            
            switch (type) {
            // Draws a circle with a fixed width
            case CIRCLE:
                coordinates = getFixedCoordinates(getCoordinates(startPoint, endPoint));
                s = new Ellipse2D.Float(coordinates[0], coordinates[1], coordinates[2], coordinates[2]);
                break;
                 
            // Draws an ellipse (oval circle).
            case ELLIPSE:
                coordinates = getCoordinates(startPoint, endPoint);
                s = new Ellipse2D.Float(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                break;
 
            // Draws a line.
            case LINE:
                s = new Line2D.Float(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                break;
 
            // Draws a rectangle.
            case RECTANGLE:
                coordinates = getCoordinates(startPoint, endPoint);
                s = new Rectangle2D.Float(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                break;
 
            // Draws a square.
            case SQUARE:
                coordinates = getFixedCoordinates(getCoordinates(startPoint, endPoint));
                s = new Rectangle2D.Float(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                break;
            }
            undoStack.push(s); // s (shape) is added to a stack to make saving a possibility.
            shapes.add(s);
            startPoint = null;
            endPoint = null;
            repaint();
        }
 
        // This method returns an array containing start and end points for the figures.
        private int[] getCoordinates(Point startPoint, Point endPoint) {
            int[] coordinates = new int[4];
             
            // Down to the right.
            if (endPoint.x > startPoint.x && endPoint.y > startPoint.y) {
                coordinates[0] = startPoint.x;
                coordinates[1] = startPoint.y;
                coordinates[2] = endPoint.x - startPoint.x;
                coordinates[3] = endPoint.y - startPoint.y;
 
                // Up to the left.
            } else if (endPoint.x < startPoint.x && endPoint.y < startPoint.y) {
                coordinates[0] = endPoint.x;
                coordinates[1] = endPoint.y;
                coordinates[2] = startPoint.x - endPoint.x;
                coordinates[3] = startPoint.y - endPoint.y;
 
                // Up to the right.
            } else if (endPoint.x > startPoint.x && endPoint.y < startPoint.y) {
                coordinates[0] = startPoint.x;
                coordinates[1] = endPoint.y;
                coordinates[2] = endPoint.x - startPoint.x;
                coordinates[3] = startPoint.y - endPoint.y;
 
                // Down to the left.
            } else if (endPoint.x < startPoint.x && endPoint.y > startPoint.y) {
                coordinates[0] = endPoint.x;
                coordinates[1] = startPoint.y;
                coordinates[2] = startPoint.x - endPoint.x;
                coordinates[3] = endPoint.y - startPoint.y;
            }
            return coordinates;
        }
 
        // Returns an array of points for "Square" and "Circle".
        private int[] getFixedCoordinates(int[] coordinates) {
            int width = Math.min(coordinates[2], coordinates[3]); // Finds the smallest of the widths.
            if (coordinates[0] < startPoint.x)
                coordinates[0] = startPoint.x - width;
            if (coordinates[1] < startPoint.y)
                coordinates[1] = startPoint.y - width;
            coordinates[2] = width;
            coordinates[3] = width;
 
            return coordinates;
        }
 
        // The shapes are drawn whilst the mouse pointer slides across the "canvas".
        public void mouseDragged(MouseEvent event) {
            g2 = (Graphics2D) getGraphics();
            g2.setXORMode(Color.white);
            int[] coordinates = new int[4];
            switch (type) {
            case CIRCLE:
                if (endPoint != null) {
                    coordinates = getFixedCoordinates(getCoordinates(startPoint, endPoint));
                    g2.drawOval(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                }
                endPoint = event.getPoint();
                coordinates = getFixedCoordinates(getCoordinates(startPoint, endPoint));
                g2.drawOval(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                break;
 
            case ELLIPSE:
                if (endPoint != null) {
                    coordinates = getCoordinates(startPoint, endPoint);
                    g2.drawOval(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                }
                endPoint = event.getPoint();
                coordinates = getCoordinates(startPoint, endPoint);
                g2.drawOval(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                break;
 
            case LINE:
                if (endPoint != null)
                    g2.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                endPoint = event.getPoint();
                g2.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                break;
 
            case RECTANGLE:
                if (endPoint != null) {
                    coordinates = getCoordinates(startPoint, endPoint);
                    g2.drawRect(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                }
                endPoint = event.getPoint();
                coordinates = getCoordinates(startPoint, endPoint);
                g2.drawRect(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                break;
 
            case SQUARE:
                if (endPoint != null) {
                    coordinates = getFixedCoordinates(getCoordinates(startPoint, endPoint));
                    g2.drawRect(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                }
                endPoint = event.getPoint();
                coordinates = getFixedCoordinates(getCoordinates(startPoint, endPoint));
                g2.drawRect(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);
                break;
            }
        }
 
        public void mouseMoved(MouseEvent hendelse) {
 
        }
    }
 
    public static void main(String[] args) {
        new SimplePaint();
    }
}