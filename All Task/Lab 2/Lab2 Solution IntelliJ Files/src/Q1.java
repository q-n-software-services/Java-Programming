// Program prints all the integers in a given range using for loop
package week_1;

public class Q1 {
    public static void main(String[] args) {
        printNumbers(1, 10); // calling the method with 1 and 10 as arguments
    }

    public static void printNumbers(int from, int to) {
        for (int i = from; i <= to; i++) {
            System.out.print(i + " ");
        }
    }
}
