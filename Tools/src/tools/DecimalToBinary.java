package tools;

import java.util.ArrayList;

/**
 *
 * @author Geir Haugen
 * 
 * This program converts an input decimal integer (int) and converts it to binary.
 * The program is based on the "arrow algorithm".
 * The algorithm divides the incoming number by 2 and check if the result is a
 * non-decimal number. If it is, a "zero" is added at the start. If it is a decimal
 * number, a "one" is added and the number is rounded down to the closest whole integer.
 * The algorithm perform these actions as long as the current divided/rounded number
 * is not zero.
 * 
 */
public class DecimalToBinary {
    private ArrayList<String> binary;
    private double trialNum;
    private StringBuilder strBuilder;
    
    // Constructor.
    public DecimalToBinary() {
        binary = new ArrayList<String>();
        strBuilder = new StringBuilder();
    }
    
    // This method performs the conversion.
    public void convert(int decimal) {
        this.trialNum = decimal;
        
        while (trialNum != 0) {
            System.out.println(trialNum);       // Shows the current value of trialNum.
            trialNum = trialNum / 2;
            
            if (trialNum % 1 == 0) {            // If current trialNum is of non-decimal, add a zero.
                binary.add(0, "0");
            } else {                            // If the current trialNum contains decimals, add a one and remove the decimals.
                binary.add(0, "1");
                trialNum = Math.floor(trialNum);
            }
        }
    }
    
    // Get method for "binary", the ArrayList containing the converted decimal number.
    public ArrayList<String> getBinary() {
        return binary;
    }
    
    // toString-method to give prettier output of the binary form.
    // Returns a String-version of the binary number.
    public String toString() {
        String strForm;
        
        for (int i = 0; i < binary.size(); i++) {
            strBuilder.append(binary.get(i));
        }
        
        strForm = strBuilder.toString();
        
        return strForm;
    }
}
