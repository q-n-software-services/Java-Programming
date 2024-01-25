/* program prints suggested tip amounts for a food check in a restaurant for the bill amount that user inputs */
package week_1;

import static input.InputUtils.doubleInput;

public class Q2 {

    public static void main(String[] args) {

        double checkAmount = doubleInput("Enter the amount of the check: ");

        for (int tipPercentage = 10; tipPercentage <= 30; tipPercentage += 5) {
            double tipAmount = checkAmount * tipPercentage / 100;
            double totalAmount = checkAmount + tipAmount;

            System.out.printf("%d%% tip $%.2f total $%.2f\n", tipPercentage, tipAmount, totalAmount);
        }

    }

}
