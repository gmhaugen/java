package simplepaint;

/*
 * @Author: Geir Haugen 
*   This class handles printing within a java program. Not totally bug free.
*   Needs a printer present to work.
*/
import java.awt.Component;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;
import javax.swing.RepaintManager;
 
public class PrintHandler implements Printable {
 
    private Component comp;
 
    private PrintHandler(Component comp) {
        this.comp = comp;
    }
 
    public static void printComponent(Component comp) {
        new PrintHandler(comp).printComponent();
    }
 
    public void printComponent() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok)
            try {
                job.print();
            } catch (PrinterException e1) {
                JOptionPane.showMessageDialog(null, "Could not Print!", "Print error", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
    }
 
    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex)
            throws PrinterException {
        if (pageIndex > 0)
            return (NO_SUCH_PAGE);
        else {
            int x = (int) pf.getImageableX() + 1;
            int y = (int) pf.getImageableY() + 1;
            g.translate(x, y);
            RepaintManager rpm = RepaintManager.currentManager(comp);
            rpm.setDoubleBufferingEnabled(false);
            comp.paint(g);
            rpm.setDoubleBufferingEnabled(true);
        }
        return (PAGE_EXISTS);
    }
 
}
