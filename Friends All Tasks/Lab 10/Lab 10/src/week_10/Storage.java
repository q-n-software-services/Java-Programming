package week_10;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Storage {
    
    public void writeListToFile(List<String> listOfPlaces, String fileName) {

        try (BufferedWriter fileHandler = Files.newBufferedWriter(Path.of(fileName), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String place : listOfPlaces) {
                fileHandler.write(place);
                fileHandler.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error! writing to the file: " + e.getMessage());
        }
        
    }
    
    
    public static List<String> readListFromFile(String fileName) {

        List<String> listOfPlaces = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listOfPlaces.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            return listOfPlaces;
        }
        return listOfPlaces;

    }
}
