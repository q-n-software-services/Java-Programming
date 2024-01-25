package week_4;

import java.util.ArrayList;
import java.util.List;



public class Question_1_Breakfast {

    public static void main(String[] args) {
        breakfast();
    }

    public static List<String> breakfast() {


        List<String> myCereals = new ArrayList<>();


        myCereals.add("Special K");
        myCereals.add("Captain Crunch");
        myCereals.add("Oatmeal");


        myCereals.remove("Oatmeal");


        myCereals.add("Pancakes");


        myCereals.add("Cornflakes");


        for (String cereal : myCereals) {
            System.out.println(cereal);
        }


        if (myCereals.contains("Special K")) {
            System.out.println("Special K is in the list.");
        } else {
            System.out.println("Special K is not in the list.");
        }


        System.out.println("The number of cereals in the list are: " + myCereals.size());

        return myCereals;
    }
}
