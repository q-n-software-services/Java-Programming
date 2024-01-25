import java.util.LinkedList;

public class Question_6_Fix_Code_No_Exception_Handling {

    public static void main(String[] args) {

        Question_6_Fix_Code_No_Exception_Handling q6 = new Question_6_Fix_Code_No_Exception_Handling();

        // Test the printLanguageList() method
        q6.printLanguageList();

        // Test the wordCount() method
        String sentence1 = "This is an example sentence.";
        String sentence2 = null;
        int words1 = q6.wordCount(sentence1);
        int words2 = q6.wordCount(sentence2);

        System.out.println(sentence1 + " has this many words: " + words1);
        System.out.println(sentence2 + " has this many words: " + words2);

    }

    /* Adds some example programming languages to a LinkedList, and then prints them in reverse order.
     */

    public void printLanguageList() {

        LinkedList<String> languages = new LinkedList<>();

        languages.push("JavaScript");
        languages.push("Python");
        languages.push("C#");

        while (!languages.isEmpty()) {
            String oneLanguage = languages.pop();
            System.out.println(oneLanguage);
        }
    }

    /* A very simple word count function.

    This function should return the number of words in a string.
    For this program, each word is assumed to be separated by a single space character.
    If the String sentence is null, this method should return 0.

    Counting words in real-world situations can be a much trickier problem,
    with various special cases to consider.
    For example, is "sugar-free" one word, or two? How many words in "D.B. Cooper" ? */

    public int wordCount(String sentence) {

        if (sentence == null) {
            return 0;
        }

        String[] words = sentence.split(" ");
        return words.length;
    }

}
