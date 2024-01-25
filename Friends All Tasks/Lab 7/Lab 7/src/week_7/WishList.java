package week_7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;



public class WishList {

    public static <Place> void main(String[] args) {

        List<Place> wishList = new ArrayList<>();

        do {

            String name = stringInput("Enter the name of the place");
            String reason = stringInput("Why do you want to visit " + name + "?");

            Place newPlace = newPlace(name, reason);

            // Add the new Place to wishList
            wishList.add(newPlace);

        } while (yesNoInput("More places to add to your wish list?"));

        // Call the displayPlacesInNameOrder method to print the list of places sorted by name
        displayPlacesInNameOrder(wishList);

    }

    public static <Place> week_7.Place newPlace(String placeName, String reason) {

        // Create a new Place object with the given name and reason
        week_7.Place place = new week_7.Place(placeName, reason);

        // Return the new Place object
        return (week_7.Place) place;
    }


    public static <Place> void displayPlacesInNameOrder(List<Place> places) {

        // Sort the list of Place objects in name order, ignoring case
        Collections.sort(places, new Comparator<Place>() {
            @Override
            public int compare(Place o1, Place o2) {
                return 0;
            }

            @Override
            public int compare(week_7.Place p1, week_7.Place p2) {
                return p1.getName().compareToIgnoreCase(p2.getName());
            }
        });

        // Print each Place object, one per line
        for (Place place : places) {
            System.out.println(place);
        }
    }

}

