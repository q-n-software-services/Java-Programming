import java.util.Collections;
import java.util.Collections;
import java.util.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class Question_3_Read_Recycling_Report {


    public static void main(String[] args) {
        new Question_3_Read_Recycling_Report().recyclingReport();
    }

    public void recyclingReport(){

        // Read the file into your program
        String nameOfFile = "report.txt";

        List<String> inputLines = readLinesFromRecyclingDataFile(nameOfFile);
        List<Integer> crateCount = extractCrateQuantityData(inputLines);
        int max = calculateMax(crateCount);
        List<Integer> maxCountHouse = copyIndexesToNewList(crateCount, max);

        System.out.println("The maximum number of crates is " + max);
        System.out.println("The houses with this max number of crates is " + maxCountHouse);
    }


    public List<String> readLinesFromRecyclingDataFile(String nameOfFile) {
        List<String> inputLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nameOfFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                inputLines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + nameOfFile);
            e.printStackTrace();
            return null;
        }

        return inputLines;
    }


    public List<Integer> extractCrateQuantityData(List<String> inputLines) {
        List<Integer> crateCount = new ArrayList<>();

        for (String line : inputLines) {
            String[] parts = line.split(" ");
            int quantity = Integer.parseInt(parts[1]);
            crateCount.add(quantity);
        }

        return crateCount;
    }



    public int calculateMax(List<Integer> crates) {
        return Collections.max(crates);
    }

    public List<Integer> copyIndexesToNewList(List<Integer> quantities, int value) {
        List<Integer> myIndexes = new ArrayList<>();

        for (int i = 0; i < quantities.size(); i++) {
            if (quantities.get(i) == value) {
                myIndexes.add(i);
            }
        }

        return myIndexes;
    }
}
