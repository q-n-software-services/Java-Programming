package week_9;

import static input.InputUtils.*;
import java.sql.SQLException;
import java.util.*;

import static com.example.utils.InputUtils.intInput;
import static com.example.utils.InputUtils.stringInput;
import com.example.models.Product;
import com.example.database.ProductDB;


class ProductManager {

    ProductDB database;

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

        List<String> allProducts = database.fetchAll();
        Collections.sort(allProducts);

        for (String item : allProducts) {
            System.out.println(item );
        }

        String name = stringInput("Enter product name: ");
        Integer quantity = intInput("Enter product quantity: ");
    }



    protected void addProduct() {
        String name = stringInput("Enter Name: ");
        Integer quantity = intInput("Enter Quantity: ");

        // Check if product already exists
        List myProduct = database.getProduct(name);
        if (myProduct != null) {
            System.out.println("Product already EXISTS!");
            return;
        }

        boolean added = database.addProduct(name, quantity);
        if (added) {
            System.out.println("Product added!");
        } else {
            System.out.println("Product already EXISTS!");
        }
    }


    protected void editProductQuantity() {
        String productName = stringInput("Enter the name: ");
        Integer newQuantity = intInput("Enter new quantity: ");

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
        String productName = stringInput("Enter the name of the product to delete: ");


        boolean isDeleted = database.deleteProduct(productName);

        if (isDeleted) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product to delete not found.");
        }
    }




}