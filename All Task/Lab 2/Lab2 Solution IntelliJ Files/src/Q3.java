// Validation of college code using While Loop where code is b/w 1000 & 2999
import static input.InputUtils.intInput;

public class Q3 {
    public static void main(String[] args) {
        int classCode = intInput("Enter a college class code (between 1000 and 2999): ");
        while (classCode < 1000 || classCode > 2999) {
            System.out.println("Invalid class code. Please enter a code between 1000 and 2999.");
            classCode = intInput("Enter a college class code (between 1000 and 2999): ");
        }
        System.out.println("Valid class code entered: " + classCode);
    }
}
