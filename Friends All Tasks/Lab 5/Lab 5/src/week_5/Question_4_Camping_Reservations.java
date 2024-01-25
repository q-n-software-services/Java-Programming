package week_5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static input.InputUtils.*;


public class Question_4_Camping_Reservations {

    private static Map<String, String> site1 = Map.of("type", "RV", "has_water", "YES", "not_allowed", "TENT", "availability", "RESERVED", "all_year", "AVAILABLE");
    private static Map<String, String> site2 = Map.of("type", "RV", "has_water", "YES", "not_allowed", "TENT", "availability", "AVAILABLE", "all_year", "AVAILABLE");
    private static Map<String, String> site3 = Map.of("type", "TENT", "has_water", "YES", "not_allowed", "RV", "availability", "RESERVED", "all_year", "AVAILABLE");
    private static Map<String, String> site4 = Map.of("type", "TENT", "has_water", "NO", "not_allowed", "RV", "availability", "AVAILABLE", "all_year", "AVAILABLE");
    private static Map<String, String> site5 = Map.of("type", "TENT", "has_water", "NO", "not_allowed", "RV", "availability", "AVAILABLE", "all_year", "AVAILABLE");
    private static Map<String, String> site6 = Map.of("type", "TENT", "has_water", "NO", "not_allowed", "RV", "availability", "RESERVED", "all_year", "SUMMER_ONLY");
    private static Map<String, String> site7 = Map.of("type", "RV", "has_water", "NO", "not_allowed", "TENT", "availability", "AVAILABLE", "all_year", "SUMMER_ONLY");
    private static Map<String, String> site8 = Map.of("type", "RV", "has_water", "YES", "not_allowed", "TENT", "availability", "AVAILABLE", "all_year", "SUMMER_ONLY");
    private static Map<String, String> site9 = Map.of("type", "RV", "has_water", "NO", "not_allowed", "TENT", "availability", "RESERVED", "all_year", "SUMMER_ONLY");
    private static Map<String, String> site10 = Map.of("type", "RV", "has_water", "YES", "not_allowed", "TENT", "availability", "AVAILABLE", "all_year", "SUMMER_ONLY");


    static Map<String, Map<String, String>> siteInfo = Map.of("1", site1, "2", site2, "3", site3, "4", site4, "5", site5, "6", site6, "7", site7, "8", site8, "9", site9, "10", site10);


    public static void main(String[] args) {


        List<String> siteTypes = List.of("RV", "TENT");

        String siteType;

        do {
            siteType = stringInput("Do you want to reserve an RV or TENT site? Enter RV or TENT.").toUpperCase();
        } while (!siteTypes.contains(siteType));

        boolean needWater = yesNoInput("Do you want water at the site?");

        List<Integer> availableSiteNames = getMatchingSites(siteType, needWater);

        if (availableSiteNames.isEmpty()) {
            System.out.println("No sites match your search");
        } else {
            System.out.println("These sites are available: " + availableSiteNames);
        }

    }

    public static List<Integer> getMatchingSites(String type, boolean hasWater) {
        List<Integer> sitesFound = new ArrayList<>();

        for (int numSite = 1; numSite <= 10; numSite++) {
            Map<String, String> siteInfo = switch (numSite) {
                case 1 -> site1;
                case 2 -> site2;
                case 3 -> site3;
                case 4 -> site4;
                case 5 -> site5;
                case 6 -> site6;
                case 7 -> site7;
                case 8 -> site8;
                case 9 -> site9;
                case 10 -> site10;
                default -> null;
            };
            if (siteInfo != null && siteInfo.get("type").equals(type) && siteInfo.get("has_water").equals(hasWater ? "YES" : "NO") && siteInfo.get("availability").equals("AVAILABLE")) {
                sitesFound.add(numSite);
            }
        }
        return sitesFound;
    }
}
