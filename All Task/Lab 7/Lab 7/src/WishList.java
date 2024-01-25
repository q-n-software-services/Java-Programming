package week_7.q1_travel_wish_list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

/**
 Program to create and display a list of places a user wishes to travel to.
 */


public class WishList {

    public static void main(String[] args) {

        // Create an ArrayList to store Place objects
        List<Place> wishList = new ArrayList<>();

        // Prompt the user to add a new place to their wish list
        do {

            String name = stringInput("Enter the name of the place");
            String reason = stringInput("Why do you want to visit " + name + "?");

            // Call the newPlace method to create a new Place object with the given name and reason
            Place newPlace = newPlace(name, reason);

            // Add the new Place object to the wishList
            wishList.add(newPlace);

        } while (yesNoInput("More places to add to your wish list?"));

        // Call the displayPlacesInNameOrder method to print the list of places sorted by name
        displayPlacesInNameOrder(wishList);

    }

    public static Place newPlace(String placeName, String reason) {

        // Create a new Place object with the given name and reason
        Place place = new Place(placeName, reason);

        // Return the new Place object
        return place;
    }


    public static void displayPlacesInNameOrder(List<Place> places) {

        // Sort the list of Place objects in name order, ignoring case
        Collections.sort(places, new Comparator<Place>() {
            @Override
            public int compare(Place p1, Place p2) {
                return p1.getName().compareToIgnoreCase(p2.getName());
            }
        });

        // Print each Place object, one per line
        for (Place place : places) {
            System.out.println(place);
        }
    }

}
