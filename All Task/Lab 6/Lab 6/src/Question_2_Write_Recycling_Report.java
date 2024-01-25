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

        int[] cratesPerHouse = getRecyclingPerHouse(numberOfHouses);
        int totalCrates = calculateTotal(cratesPerHouse);
        int maxCrates = calculateMax(cratesPerHouse);
        int minCrates = calculateMin(cratesPerHouse);
        int houseWithMostRecycling = calculateHouseWithMostRecycling(cratesPerHouse);

        System.out.println("Total crates from all houses = " + totalCrates);
        System.out.println("Max crates at any house = " + maxCrates);
        System.out.println("Min crates at any house = " + minCrates);
        System.out.println("House with most recycling = " + houseWithMostRecycling);

        String filename = "recycling_report.txt";

        writeReport(cratesPerHouse, totalCrates, filename);
    }

    public int[] getRecyclingPerHouse(int houses) {
        Scanner input = new Scanner(System.in);
        int[] cratesPerHouse = new int[houses];
        for (int i = 0; i < houses; i++) {
            System.out.print("Enter the number of crates for house " + i + ": ");
            cratesPerHouse[i] = input.nextInt();
        }
        return cratesPerHouse;
    }

    public int calculateTotal(int[] cratesPerHouse) {
        int total = 0;
        for (int i = 0; i < cratesPerHouse.length; i++) {
            total += cratesPerHouse[i];
        }
        return total;
    }

    public int calculateMax(int[] cratesPerHouse) {
        int max = cratesPerHouse[0];
        for (int i = 1; i < cratesPerHouse.length; i++) {
            if (cratesPerHouse[i] > max) {
                max = cratesPerHouse[i];
            }
        }
        return max;
    }

    public int calculateMin(int[] cratesPerHouse) {
        int min = cratesPerHouse[0];
        for (int i = 1; i < cratesPerHouse.length; i++) {
            if (cratesPerHouse[i] < min) {
                min = cratesPerHouse[i];
            }
        }
        return min;
    }

    public int calculateHouseWithMostRecycling(int[] cratesPerHouse) {
        int max = cratesPerHouse[0];
        int houseWithMostRecycling = 0;
        for (int i = 1; i < cratesPerHouse.length; i++) {
            if (cratesPerHouse[i] > max) {
                max = cratesPerHouse[i];
                houseWithMostRecycling = i;
            }
        }
        return houseWithMostRecycling;
    }

    public void writeReport(int[] cratesPerHouse, int totalCrates, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < cratesPerHouse.length; i++) {
                String crateOrCrates = cratesPerHouse[i] == 1 ? "crate" : "crates";
                writer.write("House " + i + " recycled " + cratesPerHouse[i] + " " + crateOrCrates + "\n");
            }
            writer.write("Total crates recycled: " + totalCrates + "\n");
        }
