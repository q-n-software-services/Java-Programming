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

/**
 * For saving and fetching lists of places from files
 */

public class Storage {
    
    public void writeListToFile(List<String> places, String fileName) {
        // TODO finish this method.  See Lab 10 Questions.md.

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(fileName), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String place : places) {
                writer.write(place);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
        
    }
    
    
    public static List<String> readListFromFile(String fileName) {
        // TODO complete this method. See Lab 10 Questions.md.

        List<String> places = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                places.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            return places;
        }
        return places;  // TODO replace with your own code.

    }
}
