package tools;

/**
 *
 * @author Geir Haugen
 * This program converts a binary number input (String) to a decimal number (int).
 * The algorithm checks if any of the positions of the binary number is a "one",
 * if it is, adder is added to the total (decimal). Adder is incremented with the
 * proceeding binary values.
 * 
 */
public class BinaryToDecimal {
    
    private int decimal;
    private String binary;
    private int adder;
    
    // Constructor
    public BinaryToDecimal() {
        adder = 1;
    }
    
    // This method performs the conversion.
    public void convert(String binary) {
        this.binary = binary;
        int index = this.binary.length() - 1;
        
        while (index >= 0) {
            if (this.binary.charAt(index) == '1') {     // When a "one" is found in the binary number, the current binary value is added to the total.
                decimal = decimal + adder;
            }
            
            adder = adder * 2;                          // The adder is multiplied to update the current binary value.
            index --;
        }
    }
    
    // Get method for the calculated total (the decimal number)
    public int getDecimal() {
        return this.decimal;
    }
}
