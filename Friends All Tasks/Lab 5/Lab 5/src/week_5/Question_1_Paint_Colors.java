package week_5;
import java.util.HashMap;
import java.util.Map;

public class Question_1_Paint_Colors {

    public static void main(String[] args) {

        Map<String, String> paintColors = new HashMap<>();
        paintColors.put("Kitchen", "Light Blue");
        paintColors.put("Dining Room", "Yellow");
        paintColors.put("Living Room", "Yellow");
        paintColors.put("Bedroom 1", "Purple");
        paintColors.put("Bedroom 2", "Green");

        // Adding key-value pair to  hashmap. "Bedroom 3" will be painted "Yellow".
        paintColors.put("Bedroom 3", "Yellow");
        // This Should output "Kitchen color: Light Blue"
        System.out.println("Kitchen color: " + getKitchenColor(paintColors));
        // This Should output "Number of rooms to be painted yellow: 3"
        System.out.println("Number of rooms to be painted yellow: " + howManyYellowRooms(paintColors));

    }

    public static String getKitchenColor(Map<String, String> paintColors) {
        // Returns value for the key "Kitchen" in the paintColors HashMap
        return paintColors.get("Kitchen");
    }

    public static int howManyYellowRooms(Map<String, String> paintColors) {
        int count = 0;
        // Counts that how many values are, text, "Yellow" in theHashMap paintColors
        for (String color : paintColors.values()) {
            if (color.equals("Yellow")) {
                count++;
            }
        }
        return count;
    }
}
