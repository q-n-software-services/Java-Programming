package week_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static input.InputUtils.positiveIntInput;
import static input.InputUtils.yesNoInput;



public class Question_2_Dice_Roll {

    public static final String SAME_VALUES = "All the dice have the same value!";

    static Random random = new Random();

    public static void main(String[] args) {


        int diceCount = positiveIntInput("Input the number of dice you would like to roll?");


        do {

            List<Integer> diceValues = roll(diceCount);


            System.out.println("The dice have the values: " + diceValues);
            System.out.println("The total of all dice: " + diceTotal(diceValues));

            if (allSameValue(diceValues)) {
                System.out.println(SAME_VALUES);
            }

        } while (yesNoInput("Do you want to roll again?"));
    }



    public static List<Integer> roll(int diceCount) {

        // Use the Random random variable declared on line 28 to generate random numbers.
        // Don't create another Random object.

        // Create an ArrayList of Integer values.
        List<Integer> diceValues = new ArrayList<>();

        // Use a loop to roll the given number of dice. Store the values in an ArrayList and return it.
        for (int i = 0; i < diceCount; i++) {
            diceValues.add(random.nextInt(6) + 1);
        }

        // If the diceCount is 0 or negative, return an empty List.
        if (diceCount <= 0) {
            return new ArrayList<>();
        }

        return diceValues;
    }


    public static int diceTotal(List<Integer> diceValues) {
        if (diceValues == null || diceValues.isEmpty()) {
            return 0;        }
        int total = 0;
        for (int value : diceValues) {
            total += value;
        }
        return total;
    }

    public static boolean allSameValue(List<Integer> diceValues) {

        if (diceValues == null || diceValues.isEmpty()) {
            return false;
        }
        int firstValue = diceValues.get(0);
        for (int i = 1; i < diceValues.size(); i++) {
            if (diceValues.get(i) != firstValue) {
                return false;
            }
        }
        return true;
    }
}
