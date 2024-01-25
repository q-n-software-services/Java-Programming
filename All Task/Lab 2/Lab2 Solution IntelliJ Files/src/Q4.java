/*Program to create a new String from many copies of itself. The user decides how many copies to make */
package week_2;

import static input.InputUtils.*;

/**
 * Finish the multiplyString method to create a String from many copies of itself.
 * The user will decide how many copies to make.
 *
 * If the method is given the String "cat" and repeats = 3 copies, it will return "catcatcat".
 * If the method is given the String "Hello" and repeats = 5 copies, it will return "HelloHelloHelloHelloHello".
 *
 * If the method is given the String "Hello" and repeats = 1 copies, it will return "Hello".
 * If the method is given the String "Hello" and repeats = 0 copies, it will return "". (An empty String)
 * If the method is given the String "Hello" and repeats = -1 copies, or any negative number it will return "". (An empty String)

 */

public class Q4 {

    public static void main(String[] args) {

        // You don't need to modify the main method.

        String input = stringInput("Enter your string to multiply: ");
        int copies = intInput("How many copies to make?");

        String output = multiplyString(input, copies);

        System.out.printf("Your String %d times is: %s%n", copies, output);

    }

    public static String multiplyString(String userString, int repeats) {
        // return a String that is made of multiple copies of userString.
        // The variable called repeats contains the number of copies.
        // When userString = "Java" and repeats = 4 this method should return "JavaJavaJavaJava".
        // When userString = "Java" and repeats = 2 this method should return "JavaJava".
        // When userString = "Java" and repeats = 0, this method should return ""  (an empty String)
        // When userString = "Java" and repeats is negative, like -10, this method should return ""  (an empty String)

        // DON'T USE THE String repeat() LIBRARY METHOD.
        // For this lab, you should solve this problem using a loop.
        // Hint: Start with an empty string, then add userString to the end of that string, as many times as you need.

        if (repeats <= 0) {
            return "";
        }

        String result = "";
        for (int i = 0; i < repeats; i++) {
            result += userString;
        }

        return result;
    }

}
