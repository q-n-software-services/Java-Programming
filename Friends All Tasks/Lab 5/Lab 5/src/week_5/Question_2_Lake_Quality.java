package week_5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static input.InputUtils.*;


public class Question_2_Lake_Quality {

    public static void main(String[] args) {

        Map<String, Double> lakeClarities = new HashMap<>();
        lakeClarities.put("Bde Maka Ska", 7.2);
        lakeClarities.put("Harriet", 5.3);
        lakeClarities.put("Powderhorn", 1.8);
        lakeClarities.put("Nokomis", 2.4);


        while (yesNoInput("Add or update a lake clarity?")) {
            String lakeName = stringInput("Input lake name");
            double clarity = positiveDoubleInput("Input clarity, in feet");
            updateClarity(lakeClarities, lakeName, clarity);
        }

        // Deciding that which of the lakes are the best suitable for swimming

        double minSwimmingClarity = 4.0;   // four feet of clarity
        List<String> swimmingLakes = getSwimmingLakes(lakeClarities, minSwimmingClarity);

        System.out.println("These lakes are suitable for swimming: " + swimmingLakes);

    }

    public static void updateClarity(Map<String, Double> lakeClarities, String lake, double clarity) {


        lakeClarities.put(lake, clarity);
        System.out.println("Updated clarity for " + lake + " to " + clarity + " feet.");

    }

    public static List<String> getSwimmingLakes(Map<String, Double> lakeClarities, double minClarity) {

        List<String> suitableLakes = new ArrayList<>();

        for (String lake : lakeClarities.keySet()) {
            double clarity = lakeClarities.get(lake);
            if (clarity >= minClarity) {
                suitableLakes.add(lake);
            }
        }

        return suitableLakes;

    }
}
