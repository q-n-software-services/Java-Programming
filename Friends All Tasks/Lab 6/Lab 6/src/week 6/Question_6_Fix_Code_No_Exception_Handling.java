import java.util.LinkedList;

public class Question_6_Fix_Code_No_Exception_Handling {

    public static void main(String[] args) {

        Question_6_Fix_Code_No_Exception_Handling SixthQuestion = new Question_6_Fix_Code_No_Exception_Handling();

        // Test the printLanguageList() method
        SixthQuestion.printLanguageList();

        // Test the wordCount() method
        String sentence1 = "This is an example sentence.";
        String sentence2 = null;
        int words1 = SixthQuestion.wordCount(sentence1);
        int words2 = SixthQuestion.wordCount(sentence2);

        System.out.println(sentence1 + " has this many words: " + words1);
        System.out.println(sentence2 + " has this many words: " + words2);

    }

    public void printLanguageList() {

        LinkedList<String> languageList = new LinkedList<>();

        languageList.push("JavaScript");
        languageList.push("Python");
        languageList.push("C#");

        while (!languageList.isEmpty()) {
            String oneLanguage = languageList.pop();
            System.out.println(oneLanguage);
        }
    }


    public int wordCount(String sentence) {

        if (sentence == null) {
            return 0;
        }

        String[] words = sentence.split(" ");
        return words.length;
    }

}
