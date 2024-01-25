package week_10;

import java.util.List;


public class Main {
    
    // You don't need to modify this file.

    public static String wishListFile = "wishlist.txt";
    public static String visitedFile = "visited.txt";
    
    static Storage storage = new Storage();
    
    static WishListGUI gui;
    
    public static void main(String[] args) {
        List<String> wishListPlaces = Storage.readListFromFile(wishListFile);
        List<String> visitedPlaces = Storage.readListFromFile(visitedFile);

        System.out.println(visitedPlaces);
        System.out.println(wishListPlaces);
        gui = new WishListGUI(wishListPlaces, visitedPlaces);
    }
    
    public static void quit(List<String> wishList, List<String> visitedList) {
        storage.writeListToFile(wishList, wishListFile);
        storage.writeListToFile(visitedList, visitedFile);
    }
}
