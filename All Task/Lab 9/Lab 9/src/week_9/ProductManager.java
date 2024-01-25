package week_9;

//import static input.InputUtils.*;
import java.sql.SQLException;
import java.util.*;

import static com.example.utils.InputUtils.intInput;
import static com.example.utils.InputUtils.stringInput;
import com.example.models.Product;
import com.example.database.ProductDB;


class ProductManager {

    ProductDB database;    // TODO use this object to access instance methods in the ProductDB class

    private final String menu = "" +
            "1. Show all products\n" +
            "2. Add a product\n" +
            "3. Edit a product's quantity\n" +
            "4. Delete a product\n" +
            "9. Quit";


    public static void main(String[] args) throws SQLException {
        // You do not need to modify this method.
        ProductManager productManager = new ProductManager();
        productManager.start();
    }


    public void start() throws SQLException {
        // You do not need to modify this method.
        database  = new ProductDB("products.sqlite");
        showMenuDoAction();
    }


    private void showMenuDoAction() throws SQLException {
        // You do not need to modify this method.

        int choice;

        while (true) {

            System.out.println(menu);
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter choice");
            choice = myObj.nextInt();


            if (choice == 1) {
                showAll();
            } else if (choice == 2) {
                addProduct();
            } else if (choice == 3) {
                editProductQuantity();
            } else if (choice == 4) {
                deleteProduct();
            } else if (choice == 9) {
                break;   // end the loop
            } else {
                System.out.println("Invalid choice, please try again");
            }
        }
    }


    protected void showAll() throws SQLException {
        // Fetch all products from the database, sorted by name
        List<String> products = database.fetchAll();
        Collections.sort(products);

        // Display all product names and quantities
        for (String product : products) {
            System.out.println(product );
        }

        // Ask the user for the name and quantity of a product
        String name = stringInput("Enter product name: ");
        int quantity = intInput("Enter product quantity: ");
    }



    protected void addProduct() {
        // Ask the user for the product name and quantity
        String name = stringInput("Enter product name: ");
        int quantity = intInput("Enter product quantity: ");

        // Check if the product already exists in the database
        List product = database.getProduct(name);
        if (product != null) {
            System.out.println("Product already in database");
            return;
        }

        // Add the product to the database
        boolean added = database.addProduct(name, quantity);
        if (added) {
            System.out.println("Product added successfully");
        } else {
            System.out.println("Product already in database");
        }
    }


    protected void editProductQuantity() {
        // Ask the user for the name of the product to edit and the new quantity
        String productName = stringInput("Enter the name of the product to edit: ");
        int newQuantity = intInput("Enter the new quantity: ");

        // Call the method in ProductDB to edit the product's quantity
        boolean isEdited = database.editProductQuantity(productName, newQuantity);

        // Display confirmation message if product found and quantity edited
        if (isEdited) {
            System.out.println("Quantity of product '" + productName + "' edited successfully.");
        } else {
            System.out.println("Product to edit not found.");
        }
    }


    protected void deleteProduct() {
        // Ask user for the name of a product to delete
        String productName = stringInput("Enter the name of the product to delete: ");

        // Call method in ProductDB to delete the product's record from the database
        boolean isDeleted = database.deleteProduct(productName);

        // Display confirmation message or error message if product not found
        if (isDeleted) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product to delete not found.");
        }
    }




}