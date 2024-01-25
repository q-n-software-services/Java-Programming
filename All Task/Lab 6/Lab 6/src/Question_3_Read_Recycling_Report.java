package week_6;

import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**

 There is a file called recycling-report-main-street.txt in the root
 directory of this project.

 This file contains data for a much longer street. Again, the house numbers correspond to array or ArrayList indexes.
 Read it into your program, analyze the data, and then display the numbers of the house(s) that recycled the most crates

 Make sure you use try-catch blocks for IOException.

 */

public class Question_3_Read_Recycling_Report {


    public static void main(String[] args) {
        new Question_3_Read_Recycling_Report().recyclingReport();
    }

    public void recyclingReport(){

        // Read the file into your program
        String filename = "recycling-report-main-street.txt";

        List<String> lines = readLinesFromRecyclingDataFile(filename);
        List<Integer> crateQuantities = extractCrateQuantityData(lines);
        int max = calculateMax(crateQuantities);
        List<Integer> housesWithMax = copyIndexesToNewList(crateQuantities, max);

        System.out.println("The maximum number of crates is " + max);
        System.out.println("The houses with this max number of crates is " + housesWithMax);
    }


    public List<String> readLinesFromRecyclingDataFile(String filename) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
            e.printStackTrace();
            return null;
        }

        return lines;
    }


    public List<Integer> extractCrateQuantityData(List<String> lines) {
        List<Integer> crateQuantities = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(" ");
            int quantity = Integer.parseInt(parts[1]);
            crateQuantities.add(quantity);
        }

        return crateQuantities;
    }

    import java.util.Collections;

    public int calculateMax(List<Integer> crates) {
        return Collections.max(crates);
    }

    public List<Integer> copyIndexesToNewList(List<Integer> quantities, int value) {
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < quantities.size(); i++) {
            if (quantities.get(i) == value) {
                indexes.add(i);
            }
        }

        return indexes;
    }
}
