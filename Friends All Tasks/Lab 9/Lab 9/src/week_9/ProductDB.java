package week_9;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ProductDB {

    private Connection conn;

    public ProductDB(String dbName) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:products.sqlite");
    }

    public void close() throws SQLException {
        conn.close();
    }

    public static void main(String[] args) {
        String url = "jdbc:sqlite:products.sqlite";
        String testUrl = "jdbc:sqlite:products_test.sqlite";


        try (Connection conn = DriverManager.getConnection(url);
             Statement myStatement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS inventory (\n"
                    + "    name TEXT UNIQUE,\n"
                    + "    quantity INTEGER\n"
                    + ");";
            myStatement.execute(sql);
            System.out.println("Inventory Table Successfully Dropped in products.sqlite");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(testUrl);
             Statement myStatement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS inventory (\n"
                    + "    name TEXT UNIQUE,\n"
                    + "    quantity INTEGER\n"
                    + ");";
            myStatement.execute(sql);
            System.out.println("Inventory Table Successfully Dropped in products_test.sqlite");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> fetchAll() throws SQLException {
        Statement myStatement = conn.createStatement();
        String sql = "SELECT * FROM inventory ORDER BY name ASC";
        ResultSet myResult = myStatement.executeQuery(sql);
        List<String> products = new ArrayList<>();
        while (myResult.next()) {
            String name = myResult.getString("name");
            Integer quantity = myResult.getInt("quantity");
            products.add(name + ": " + quantity);
        }
        myResult.close();
        myStatement.close();
        return products;
    }

    public boolean addProduct(String name, int quantity) {
        try {
            // Check does product already exists!
            PreparedStatement checkStatement = conn.prepareStatement("SELECT COUNT(*) AS count FROM inventory WHERE name = ?");
            checkStatement.setString(1, name);
            ResultSet checkResult = checkStatement.executeQuery();
            checkResult.next();
            Integer count = checkResult.getInt("count");
            if (count > 0) {
                // If the product priorly exists in the database, return false
                return false;
            }

            // If the Product does not exist, insert it into the database Table!
            PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO inventory (name, quantity) VALUES (?, ?)");
            insertStatement.setString(1, name);
            insertStatement.setInt(2, quantity);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    public List getProduct(String name) {
        List<List> products = new ArrayList<List>();
        try (PreparedStatement myStatement = conn.prepareStatement("SELECT * FROM inventory WHERE name = ?")) {
            myStatement.setString(1, name);
            ResultSet myResult = myStatement.executeQuery();
            while (myResult.next()) {
                String productName = myResult.getString("name");
                Integer quantity = myResult.getInt("quantity");
                products.add(List.of(productName, quantity));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public boolean editProductQuantity(String productName, int newQuantity) {
        try {
            if (checkProductExists(productName)) {

                String sql = "UPDATE inventory SET quantity = ? WHERE name = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, newQuantity);
                statement.setString(2, productName);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    return true;
                }
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
        }

        return false;
    }

    public boolean checkProductExists(String productName) {
        try {
            String sql = "SELECT COUNT(*) FROM inventory WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, productName);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteProduct(String productName) {
        try {
            if (!checkProductExists(productName)) {

                return false;
            }

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


