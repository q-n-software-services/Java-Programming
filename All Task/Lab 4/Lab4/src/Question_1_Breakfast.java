package week_4;

import java.util.ArrayList;
import java.util.List;

/**
 *  ArrayList practice.
 *
 *	Remove "Oatmeal" from the ArrayList.
 *	Add the name of your favorite breakfast food to the ArrayList.
 *	Add "Cornflakes" to the ArrayList.
 *	Display all of the items in the ArrayList.
 *	Print a message if the ArrayList contains “Special K”.
 *	Print a different message if it does not contain "Special K".
 *
 *	(optional) non-programming question: what does Captain Crunch have to do with computer hacking?
 *
 */

public class Question_1_Breakfast {

    public static void main(String[] args) {
        breakfast();
    }

    public static List<String> breakfast() {

        // Creating a new ArrayList.
        List<String> cereals = new ArrayList<>();

        // Don't modify these three lines
        cereals.add("Special K");
        cereals.add("Captain Crunch");
        cereals.add("Oatmeal");

        // Removing "Oatmeal" from the ArrayList.
        cereals.remove("Oatmeal");

        // Adding the name of your favorite breakfast food to the List.
        cereals.add("Pancakes");

        // Adding the String "Cornflakes" to the List.
        cereals.add("Cornflakes");

        // Printing all of the items in the ArrayList, one per line. Use a loop.
        for (String cereal : cereals) {
            System.out.println(cereal);
        }

        // Checking if the ArrayList contains "Special K".
        if (cereals.contains("Special K")) {
            System.out.println("Special K is in the list.");
        } else {
            System.out.println("Special K is not in the list.");
        }

        // Printing a message with the number of items in the list.
        System.out.println("The number of items in the list is: " + cereals.size());

        // (optional) non-programming question: what does Captain Crunch have to do with computer hacking?

        // This line needs to be the last line in this method,
        // so write all of your code before this line.
        // Don't modify this line.
        // The test needs the method to return your ArrayList after the modifications you make.
        return cereals;
    }
}
