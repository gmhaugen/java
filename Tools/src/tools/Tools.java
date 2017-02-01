/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author Geir Haugen
 */
public class Tools {
    public static void main(String[] args) {
        DecimalToBinary dtb = new DecimalToBinary();
        dtb.convert(578);
        System.out.println(dtb.getBinary().toString());
    }
}
