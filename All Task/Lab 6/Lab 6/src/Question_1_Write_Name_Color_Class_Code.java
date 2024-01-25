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
        String filename = "data.txt";
        String name = "John Doe";
        String favoriteColor = "blue";
        int classCode = 2545;
        writeToFile(filename, name, favoriteColor, classCode);
        printDataFromFile(filename);
    }

    public void writeToFile(String filename, String name, String favoriteColor, int classCode) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(name);
            writer.newLine();
            writer.write(favoriteColor);
            writer.newLine();
            writer.write(Integer.toString(classCode));
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void printDataFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }    }  }
