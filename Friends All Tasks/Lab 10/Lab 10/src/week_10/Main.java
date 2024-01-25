package week_10;

import java.util.List;


public class Main {
    

    public static String myWishListFile = "myWishList.txt";
    public static String myVisitedFile = "myVisited.txt";
    
    static Storage storage = new Storage();
    
    static WishListGUI gui;
    
    public static void main(String[] args) {
        List<String> myWishListPlaces = Storage.readListFromFile(myWishListFile);
        List<String> myVisitedPlaces = Storage.readListFromFile(myVisitedFile);

        System.out.println(myVisitedPlaces);
        System.out.println(myWishListPlaces);
        gui = new WishListGUI(myWishListPlaces, myVisitedPlaces);
    }
    
    public static void quit(List<String> myWishList, List<String> myVisitedList) {
        storage.writeListToFile(myWishList, myWishListFile);
        storage.writeListToFile(myVisitedList, myVisitedFile);
    }
}
