package week_9;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Talk to your database from here.

 Use the db_url variable in DBConfig to create your database connection
 This is necessary so the tests can replace your database with a test database.

 You can access this variable with

 DBConfig.db_url

 */

public class ProductDB {

    private Connection conn;

    public ProductDB(String dbName) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:products.sqlite");
    }

    public void close() throws SQLException {
        conn.close();
    }

    // TODO add methods here to work with the database.
    public static void main(String[] args) {
        String url = "jdbc:sqlite:products.sqlite";
        String testUrl = "jdbc:sqlite:products_test.sqlite";


        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // Create inventory table in products.db
            String sql = "CREATE TABLE IF NOT EXISTS inventory (\n"
                    + "    name TEXT UNIQUE,\n"
                    + "    quantity INTEGER\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("Inventory table created in products.sqlite");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(testUrl);
             Statement stmt = conn.createStatement()) {
            // Create inventory table in products_test.db
            String sql = "CREATE TABLE IF NOT EXISTS inventory (\n"
                    + "    name TEXT UNIQUE,\n"
                    + "    quantity INTEGER\n"
                    + ");";
            stmt.execute(sql);
            System.out.println("Inventory table created in products_test.sqlite");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> fetchAll() throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM inventory ORDER BY name ASC";
        ResultSet rs = stmt.executeQuery(sql);
        List<String> products = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            products.add(name + ": " + quantity);
        }
        rs.close();
        stmt.close();
        return products;
    }

    public boolean addProduct(String name, int quantity) {
        try {
            // Check if product already exists in the database
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) AS count FROM inventory WHERE name = ?");
            checkStmt.setString(1, name);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();
            int count = checkResult.getInt("count");
            if (count > 0) {
                // Product already exists in the database, return false
                return false;
            }

            // Product does not exist, insert it into the database
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO inventory (name, quantity) VALUES (?, ?)");
            insertStmt.setString(1, name);
            insertStmt.setInt(2, quantity);
            insertStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    public List getProduct(String name) {
        List<List> products = new ArrayList<List>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM inventory WHERE name = ?")) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String productName = rs.getString("name");
                int quantity = rs.getInt("quantity");
                products.add(List.of(productName, quantity));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public boolean editProductQuantity(String productName, int newQuantity) {
        try {
            // Check if the product exists in the database
            if (checkProductExists(productName)) {
                // Update the product's quantity
                String sql = "UPDATE inventory SET quantity = ? WHERE name = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, newQuantity);
                statement.setString(2, productName);

                // Execute the update statement
                int rowsAffected = statement.executeUpdate();

                // Check if the update was successful
                if (rowsAffected > 0) {
                    return true; // Quantity edited successfully
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Product not found or quantity not edited
    }

    public boolean checkProductExists(String productName) {
        try {
            String sql = "SELECT COUNT(*) FROM inventory WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, productName);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Returns true if count is greater than 0 (product exists)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Product not found
    }

    public boolean deleteProduct(String productName) {
        try {
            // Check if the product exists in the database
            if (!checkProductExists(productName)) {

                return false;
            }

            // TODO: Write code to delete the product's record from the database
            // Assuming you have a database connection object named "conn" and a table named "products"
            String query = "DELETE FROM products WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, productName);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {

                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the product: " + e.getMessage());
            return false;
        }
    }



}


