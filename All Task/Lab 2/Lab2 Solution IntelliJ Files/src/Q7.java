/* program to calculate the average bill for all 12 monthly bills
 user inputs values of each month's bill for last year
program adds up all the bills and calculates, and displays the average. */
package week_2;

import static input.InputUtils.*;

public class Q7 {

    public static void main(String[] args) {

        // Create an array to store the 12 monthly bills
        double[] monthlyBills = new double[12];

        // Use a loop to ask the user for each month's bill and store it in the array
        for (int i = 0; i < monthlyBills.length; i++) {
            double bill = doubleInput("Enter the bill for " + getMonthName(i) + ": ");
            monthlyBills[i] = bill;
        }

        // Calculate the sum of all bills
        double sum = 0;
        for (double bill : monthlyBills) {
            sum += bill;
        }

        // Calculate the average bill
        double average = sum / monthlyBills.length;

        // Print the table of months and bill amounts
        System.out.println("Month\tBill");
        for (int i = 0; i < monthlyBills.length; i++) {
            System.out.printf("%s\t$%.2f%n", getMonthName(i), monthlyBills[i]);
        }

        // Print the average bill
        System.out.printf("The average bill is $%.2f%n", average);

    }

    // An array of month names to help ask for data and display data
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};

    /**
     * Returns the name of the month for the given index (0 = January, 1 = February, etc.)
     */
    private static String getMonthName(int index) {
        return MONTHS[index];
    }

}
