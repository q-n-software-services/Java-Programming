package week_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static input.InputUtils.positiveIntInput;
import static input.InputUtils.yesNoInput;

/**
 * Finish this program to roll a set of dice. Generate a random number between 1 and 6 for
 * each dice to be rolled, and save the values in a list.
 *
 * Display the total of all the dice rolled.
 *
 * In some games, rolling the same number on all dice has a special meaning.
 * In your program, check if all dice have the same value, and print a message
 * if all the dice show the same value.  In other words, you'll need to write a method that
 * checks if all of the values in a list are the same.
 *
 */

public class Question_2_Dice_Roll {

    public static final String SAME_VALUES = "All the dice have the same value!";

    static Random rnd = new Random();   // You will use this Random number generator in your roll method.

    public static void main(String[] args) {

        // How many dice to roll?
        int numberOfDice = positiveIntInput("How many dice would you like to roll?");

        // A do loop is similar to a while loop, but the condition is
        // checked at the end of one loop iteration.
        // So the set of dice are always rolled at least one time.

        do {
            // Roll the dice
            List<Integer> diceValues = roll(numberOfDice);

            // Print the dice values rolled
            System.out.println("The dice have the values: " + diceValues);
            System.out.println("The total of all dice: " + diceTotal(diceValues));

            if (allSameValue(diceValues)) {
                System.out.println(SAME_VALUES);
            }

        } while (yesNoInput("Do you want to roll again?"));
    }


    /**
     * This method rolls a given number of dice.
     *
     * @param numberOfDice number of dice to roll.
     * @return a list containing the values of each rolled dice.
     *         If the numberOfDice is 0 or negative, return an empty list.
     */
    public static List<Integer> roll(int numberOfDice) {

        // Use the Random rnd variable declared on line 28 to generate random numbers.
        // Don't create another Random object.

        // Create an ArrayList of Integer values.
        List<Integer> diceValues = new ArrayList<>();

        // Use a loop to roll the given number of dice. Store the values in an ArrayList and return it.
        for (int i = 0; i < numberOfDice; i++) {
            diceValues.add(rnd.nextInt(6) + 1);
        }

        // If the numberOfDice is 0 or negative, return an empty List.
        if (numberOfDice <= 0) {
            return new ArrayList<>();
        }

        return diceValues;
    }


    /**
     * This method calculates the total value of all dice rolled.
     *
     * @param diceValues a list containing the values of each rolled dice.
     * @return the total value of all dice rolled.
     *         If the diceValues List is null or empty, return 0.
     */
    public static int diceTotal(List<Integer> diceValues) {
        // Check if the diceValues list is null or empty
        if (diceValues == null || diceValues.isEmpty()) {
            return 0;
        }

        int total = 0;
        // Calculate the total sum of dice values
        for (int value : diceValues) {
            total += value;
        }
        return total;
    }

    public static boolean allSameValue(List<Integer> diceValues) {
        // Check if the diceValues list is null or empty
        if (diceValues == null || diceValues.isEmpty()) {
            return false;
        }

        int firstValue = diceValues.get(0);
        // Check if all values in the diceValues list are the same
        for (int i = 1; i < diceValues.size(); i++) {
            if (diceValues.get(i) != firstValue) {
                return false;
            }
        }
        return true;
    }

}
