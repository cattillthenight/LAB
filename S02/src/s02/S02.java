/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package s02;

/**
 * S02 - String Array Manipulations.
 *
 * @author QuyenTTTCE200993
 */
public class S02 {

    /**
     * The main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = MyLib.getPositiveInteger("Enter the value of n\n", "n must be a positive integer");
        Array arr = new Array(n);
        System.out.println("Enter " + n + " name");
        arr.addName();
        System.out.println("List input name:\n" + arr);
        arr.bubbleSort();
        System.out.println("List sort name:\n" + arr);
    }
}