import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Question_1_Write_Name_Color_Class_Code {

    public static void main(String[] args) {
        new Question_1_Write_Name_Color_Class_Code().fileIO();
    }

    public void fileIO() {
        String myFile = "testData.txt";
        String name = "Franklin";
        String favoriteColor = "green";
        int classCode = 2545;
        writeToFile(myFile, name, favoriteColor, classCode);
        printDataFromFile(myFile);
    }

    public void writeToFile(String myFile, String name, String favoriteColor, int classCode) {
        try {
            BufferedWriter fileHandler = new BufferedWriter(new FileWriter(myFile));
            fileHandler.write(name);
            fileHandler.newLine();
            fileHandler.write(favoriteColor);
            fileHandler.newLine();
            fileHandler.write(Integer.toString(classCode));
            fileHandler.close();
        } catch (IOException e) {
            System.err.println("Error occurred while writing data to the file: " + e.getMessage());
        }
    }

    public void printDataFromFile(String myFile) {
        try {
            BufferedReader fileInput = new BufferedReader(new FileReader(myFile));
            String line;
            while ((line = fileInput.readLine()) != null) {
                System.out.println(line);
            }
            fileInput.close();
        } catch (IOException e) {
            System.err.println("Error occurred while Reading data from the file: " + e.getMessage());
        }    }  }
