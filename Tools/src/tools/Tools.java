package tools;

/**
 *
 * @author Geir Haugen
 */
public class Tools {
    public static void main(String[] args) {
        DecimalToBinary dtb = new DecimalToBinary();
        int decimalNumber = 4352346;
        dtb.convert(decimalNumber);
        System.out.println(dtb.getBinary().toString());
        System.out.println(decimalNumber + " = " + dtb.toString());
        
        BinaryToDecimal btd = new BinaryToDecimal();
        String binaryNumber = "10101100111";
        btd.convert(binaryNumber);
        System.out.println(binaryNumber + " = " + btd.getDecimal());
    }
}
