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
public class Array {

    private String[] arr;

    /**
     * The constructor.
     *
     * @param n Number of elements
     */
    public Array(int n) {
        arr = new String[n];

    }

    /**
     * Returns the array.
     *
     * @return the array of strings
     */
    public String[] getArr() {
        return arr;
    }

    /**
     * Sets the array.
     *
     * @param arr the array of strings to set
     */
    public void setArr(String[] arr) {
        this.arr = arr;
    }

    /**
     * Enter name into array.
     */
    public void addName() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = MyLib.getName("Name cannot contain numbers and special characters");
        }
    }

    /**
     * toString method, return the array in format.
     *
     * @return the string format of array
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str += (i + 1) + ". " + arr[i] + "\n";
        }
        return str;
    }

    /**
     * Sorts the array using bubble sort algorithm.
     */
    public void bubbleSort() {
        // Outer loop iterates from the first element to the second-to-last element
        for (int i = 0; i < arr.length - 1; i++) {
            // Inner loop starts from the element i + 1 to compare with element i
            for (int j = i + 1; j < arr.length; j++) {
                // Compare two strings: if arr[i] > arr[j] alphabetically, swap them
                if (arr[i].compareToIgnoreCase(arr[j]) > 0) {
                    // Swap arr[i] and arr[j]
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
