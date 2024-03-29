package week_5;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * Paint color HashMap - keys are room names, values are the color to paint that room.
 *
 * In this program, practice adding data to a HashMap, and analyzing values in a HashMap
 */
public class Question_1_Paint_Colors {

    public static void main(String[] args) {

        Map<String, String> paintColors = new HashMap<>();
        paintColors.put("Kitchen", "Light Blue");
        paintColors.put("Dining Room", "Yellow");
        paintColors.put("Living Room", "Yellow");
        paintColors.put("Bedroom 1", "Purple");
        paintColors.put("Bedroom 2", "Green");

        // Add another key-value pair to the map. "Bedroom 3" will be painted "Yellow".
        paintColors.put("Bedroom 3", "Yellow");
        // Should print "Kitchen color: Light Blue"
        System.out.println("Kitchen color: " + getKitchenColor(paintColors));
        // Should print "Number of rooms to be painted yellow: 3"
        System.out.println("Number of rooms to be painted yellow: " + howManyYellowRooms(paintColors));

    }

    public static String getKitchenColor(Map<String, String> paintColors) {
        // Return the value for the key "Kitchen" in the paintColors HashMap
        return paintColors.get("Kitchen");
    }

    public static int howManyYellowRooms(Map<String, String> paintColors) {
        int count = 0;
        // Count how many values are the String "Yellow" in the paintColors HashMap
        for (String color : paintColors.values()) {
            if (color.equals("Yellow")) {
                count++;
            }
        }
        return count;
    }
}
