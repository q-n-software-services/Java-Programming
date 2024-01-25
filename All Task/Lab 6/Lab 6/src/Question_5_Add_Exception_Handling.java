package week_6;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 *
 This program has two example methods. Both throw exceptions when you run the code.

 Fix by adding a try-catch block in each method.  Don't modify any of the existing code.
 Here, you will be adding new code.

 For the printLanguageList method, add try-catch statements.
 In the catch block, print a message that the LinkedList is empty. You don't need to do anything else, just let the method return.

 For the wordCount method, add try-catch statements so the NullPointerException thrown when the list is empty, is caught.
 In the catch block, return 0. So if the sentence is null, your method will return 0.

 */
public class Question_5_Add_Exception_Handling {

    public static void main(String[] args) {

        Question_5_Add_Exception_Handling q5 = new Question_5_Add_Exception_Handling();

        // Test the printLanguageList() method
        q5.printLanguageList();

        // Test the wordCount() method
        String sentence1 = "This is an example sentence.";
        String sentence2 = null;
        int words1 = q5.wordCount(sentence1);
        int words2 = q5.wordCount(sentence2);

        System.out.println(sentence1 + " has this many words: " + words1);
        System.out.println(sentence2 + " has this many words: " + words2);

    }

    /* Adds some example programming languages to a LinkedList, and then prints them in reverse order.
     */

    //Start of printLanguageList. Don't change or move this comment. The Autograder needs it.

    public void printLanguageList() {

        // TODO stop this code crashing by adding exception handling to the method.
        // Don't modify any of the code or change what is printed.
        // Add a try-catch block to catch the NoSuchElementException that's thrown.
        // Your code should still print all the languages, but not crash.

        LinkedList<String> languages = new LinkedList<>();

        languages.push("Python");     // push() adds a new element to the end of the list.
        languages.push("Swift");
        languages.push("C#");

        try {
            while (true) {
                String oneLanguage = languages.pop();   // pop() removes an element from the end of the list.
                System.out.println(oneLanguage);        // What order are the elements printed in?
            }
        } catch (NoSuchElementException e) {
            System.out.println("The LinkedList is empty.");
        }
    }

    //End of printLanguageList. Don't change or move this comment. The Autograder needs it.


    /* A very simple word count function.

    This function should return the number of words in a string.
    For this program, each word is assumed to be separated by a single space character.
    If the String sentence is null, this method should return 0.

    Counting words in real-world applications can be a much trickier
    problem, with various special cases to consider.
    For example, is "sugar-free" one word, or two? How many words in "D.B. Cooper" ? */

    //Start of wordCount. Don't change or move this comment.  The Autograder needs it.

    public int wordCount(String sentence) {
        try {
            String[] words = sentence.split(" ");
            return words.length;
        } catch (NullPointerException e) {
            return 0;
        }
    }
}
