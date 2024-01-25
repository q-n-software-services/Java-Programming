/* program prints only the program file names from an array. i.e., any file that ends with .py or .java or .cs */

public class Q5 {

    public static void main(String[] args) {
        String[] filenames = {"lab1.py", "assignment.docx", "music.mp3", "Website.cs", "Calculator.java"};

        // Loop through the array of filenames
        for (String filename : filenames) {
            // Check if the filename ends with .py, .java or .cs
            if (filename.endsWith(".py") || filename.endsWith(".java") || filename.endsWith(".cs")) {
                // If it does, print the filename
                System.out.println(filename);
            }
        }
    }

}
