import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Question_2_Write_Recycling_Report {

    public static void main(String[] args) {
        new Question_2_Write_Recycling_Report().recycling();
    }

    public void recycling() {

        int numberOfHouses = 8;

        int[] averageCratesCount = getRecyclingPerHouse(numberOfHouses);
        int cratesTotal = calculateTotal(averageCratesCount);
        int maxCrates = calculateMax(averageCratesCount);
        int minCrates = calculateMin(averageCratesCount);
        int maxRecyclingHouse = calculateHouseWithMostRecycling(averageCratesCount);

        System.out.println("Total crates from all houses = " + cratesTotal);
        System.out.println("Max crates at any house = " + maxCrates);
        System.out.println("Min crates at any house = " + minCrates);
        System.out.println("House with most recycling = " + maxRecyclingHouse);

        String filename = "recycling_report.txt";

        writeReport(averageCratesCount, cratesTotal, filename);
    }

//    Inputs the number of crates recycled by each house
    public int[] getRecyclingPerHouse(int houses) {
        Scanner scanner = new Scanner(System.in);
        int[] averageCratesCount = new int[houses];
        for (int i = 0; i < houses; i++) {
            System.out.print("Enter how many crates are for this house " + i + ": ");
            averageCratesCount[i] = scanner.nextInt();
        }
        return averageCratesCount;
    }

//    Calculates the total number of crates
    public int calculateTotal(int[] averageCratesCount) {
        int total = 0;
        for (int i = 0; i < averageCratesCount.length; i++) {
            total += averageCratesCount[i];
        }
        return total;
    }

//    Calculates the maximum of all the crates values
    public int calculateMax(int[] averageCratesCount) {
        int max = averageCratesCount[0];
        for (int i = 1; i < averageCratesCount.length; i++) {
            if (averageCratesCount[i] > max) {
                max = averageCratesCount[i];
            }
        }
        return max;
    }

    //    Calculates the minimum of all the crates values
    public int calculateMin(int[] averageCratesCount) {
        int min = averageCratesCount[0];
        for (int i = 1; i < averageCratesCount.length; i++) {
            if (averageCratesCount[i] < min) {
                min = averageCratesCount[i];
            }
        }
        return min;
    }

    //    Detemines the house that recycled the most crates
    public int calculateHouseWithMostRecycling(int[] averageCratesCount) {
        int max = averageCratesCount[0];
        int maxRecyclingHouse = 0;
        for (int i = 1; i < averageCratesCount.length; i++) {
            if (averageCratesCount[i] > max) {
                max = averageCratesCount[i];
                maxRecyclingHouse = i;
            }
        }
        return maxRecyclingHouse;
    }

//    Writes data to the file
    public void writeReport(int[] averageCratesCount, int cratesTotal, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < averageCratesCount.length; i++) {
                String crateOrCrates = averageCratesCount[i] == 1 ? "crate" : "crates";
                writer.write("House " + i + " recycled " + averageCratesCount[i] + " " + crateOrCrates + "\n");
            }
            writer.write("Total crates recycled: " + cratesTotal + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }}
