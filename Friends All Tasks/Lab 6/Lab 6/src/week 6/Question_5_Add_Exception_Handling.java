
import java.util.LinkedList;
import java.util.NoSuchElementException;


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

    public void printLanguageList() {


        LinkedList<String> languages = new LinkedList<>();

        languages.push("Python");     // push() adds a new element to the end of the list.
        languages.push("Swift");
        languages.push("C#");

        try {
            while (true) {
                String oneLanguage = languages.pop();
                System.out.println(oneLanguage);
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
