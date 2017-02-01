package tools;

import java.util.ArrayList;

/**
 *
 * @author Geir Haugen
 * 
 * This program converts an input decimal integer (int) and converts it to binary.
 * The program is based on the "arrow algorithm".
 * 
 */
public class DecimalToBinary {
    private ArrayList<String> binary = new ArrayList<String>();
    private double trialNum;
    
    // Constructor.
    public DecimalToBinary() {
        
    }
    
    // This method performs the conversion.
    public void convert(int decimal) {
        this.trialNum = decimal;
        
        trialNum = trialNum / 2;
        while (trialNum >= 0.5) {
            System.out.println(trialNum);       // Shows the current value of trialNum.
            if (trialNum % 1 == 0) {            // If current trialNum is of non-decimal, add a zero.
                binary.add(0, "0");
                trialNum = trialNum / 2;
            } else {                            // If the current trialNum contains decimals, add a one, divide the current trialNum by two and remove the decimals.
                binary.add(0, "1");
                trialNum = trialNum / 2;
                trialNum = Math.floor(trialNum);
            }
        }
    }
    
    // Get method for "binary", the ArrayList containing the converted decimal number.
    public ArrayList<String> getBinary() {
        return binary;
    }
}
