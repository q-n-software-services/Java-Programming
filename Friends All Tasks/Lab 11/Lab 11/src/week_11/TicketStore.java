package week_11;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class TicketStore {
    private static String dbURI = "jdbc:sqlite:tickets.sqlite";
    
    TicketStore(String databaseURI) {
        this.dbURI = databaseURI;

        try (Connection dbConnection = DriverManager.getConnection(dbURI);
             Statement myStatement = dbConnection.createStatement()) {
            // Create ticket table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS ticket (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "description TEXT NOT NULL," +
                    "reporter TEXT NOT NULL," +
                    "priority INTEGER NOT NULL CHECK (priority >= 1 AND priority <= 5)," +
                    "dateReported INTEGER NOT NULL," +
                    "resolution TEXT," +
                    "dateResolved INTEGER," +
                    "status TEXT NOT NULL CHECK (status IN ('OPEN', 'RESOLVED'))" +
                    ")";
            myStatement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            System.err.println("Error! Creating Ticket table: " + e.getMessage());
        }

    }

    public static List searchTicketsByDescription(String description) {

        List<Ticket> matchingTicketsList = new ArrayList<>();
        String query = "SELECT * FROM ticket WHERE LOWER(description) LIKE ?";

        try (Connection dbConnection = DriverManager.getConnection(dbURI);
             PreparedStatement queryStatement = dbConnection.prepareStatement(query)) {

            queryStatement.setString(1, "%" + description.toLowerCase() + "%");

            try (ResultSet resultSet = queryStatement.executeQuery()) {
                while (resultSet.next()) {
                    int ticketId = resultSet.getInt("id");
                    String ticketDescription = resultSet.getString("description");
                    String reporter = resultSet.getString("reporter");
                    int priority = resultSet.getInt("priority");
                    long dateReported = resultSet.getLong("dateReported");
                    String resolution = resultSet.getString("resolution");
                    long dateResolved = resultSet.getLong("dateResolved");
                    String status = resultSet.getString("status");

                    Ticket ticket = new Ticket(ticketId, ticketDescription, reporter, priority, Instant.ofEpochSecond(dateReported), resolution, dateResolved, status);
                    matchingTicketsList.add(ticket);
                }
            }

            matchingTicketsList.sort(Comparator.comparingInt(Ticket::getPriority));
        } catch (SQLException e) {
            System.err.println("Error! searching by description: " + e.getMessage());
            return null;
        }

        return matchingTicketsList;
    }


    public static List getAllOpenTickets() {

        String query = "SELECT * FROM ticket WHERE status = 'OPEN' ORDER BY priority ASC";
        List<Ticket> openTicketsList = new ArrayList<>();

        try (Connection dbConnection = DriverManager.getConnection(dbURI);
             Statement myStatement = dbConnection.createStatement();
             ResultSet resultSet = myStatement.executeQuery(query)) {

            while (resultSet.next()) {
                Ticket ticket = new Ticket(
                        resultSet.getString("description"),
                        resultSet.getInt("priority"),
                        resultSet.getString("reporter"),
                        resultSet.getLong("dateReported"),
                        resultSet.getString("resolution"),
                        resultSet.getLong("dateResolved"),
                        resultSet.getString("status")
                );

                openTicketsList.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving open tickets from the database: " + e.getMessage());
            return null;
        }

        return openTicketsList;
    }


    public void add(Ticket newTicket) throws SQLException {


        String insertSQL = "INSERT INTO ticket (description, priority, reporter, dateReported, resolution, dateResolved, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection dbConnection = DriverManager.getConnection(dbURI);
             PreparedStatement queryStatement = dbConnection.prepareStatement(insertSQL)) {

            queryStatement.setString(1, newTicket.getDescription());
            queryStatement.setInt(2, newTicket.getPriority());
            queryStatement.setString(3, newTicket.getReporter());

            try {
                queryStatement.setLong(4, newTicket.getDateReported().getTime());
            } catch (Exception e){
                queryStatement.setNull(4, Types.INTEGER);
            }
            queryStatement.setString(5, newTicket.getResolution());

            try {
                queryStatement.setLong(6, newTicket.getDateResolved().getTime());
            } catch (Exception e){
                queryStatement.setNull(6, Types.INTEGER);
            }
            queryStatement.setString(7, newTicket.getStatus().toString());


            queryStatement.executeUpdate();

            ResultSet keys = queryStatement.getGeneratedKeys();  // ask the database for generated primary key values
            keys.next();   // move to first row of the result set
            int id = keys.getInt(1);   // get value from first column

            newTicket.setTicketID(id);

        } catch (SQLException sqle) {
            throw sqle;
        }

    }


    public static Ticket getTicketById(int id) {


        String query = "SELECT * FROM ticket WHERE id = ?";
        Ticket ticket = null;

        try (Connection dbConnection = DriverManager.getConnection(dbURI);
             PreparedStatement queryStatement = dbConnection.prepareStatement(query)) {

            queryStatement.setInt(1, id);
            ResultSet resultSet = queryStatement.executeQuery();

            if (resultSet.next()) {
                ticket = new Ticket(
                        resultSet.getString("description"),
                        resultSet.getInt("priority"),
                        resultSet.getString("reporter"),
                        resultSet.getLong("dateReported"),
                        resultSet.getString("resolution"),
                        resultSet.getLong("dateResolved"),
                        resultSet.getString("status")
                );
            }

            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error! Fetching the Ticket from Database: " + e.getMessage());
            return null;
        }

        return ticket;

    }
    
    
    public void updateTicket(Ticket ticket) {

        String updateSQL = "UPDATE ticket SET description = ?, reporter = ?, priority = ?, dateReported = ?, resolution = ?, dateResolved = ?, status = ? WHERE id = ?";

        try (Connection dbConnection = DriverManager.getConnection(dbURI);
             PreparedStatement queryStatement = dbConnection.prepareStatement(updateSQL)) {

            queryStatement.setString(1, ticket.getDescription());
            queryStatement.setString(2, ticket.getReporter());
            queryStatement.setInt(3, ticket.getPriority());
            queryStatement.setLong(4, ticket.getDateReported().getTime());
            queryStatement.setString(5, ticket.getResolution());
            queryStatement.setLong(6, ticket.getDateResolved().getTime());
            queryStatement.setString(7, ticket.getStatus().toString());
            queryStatement.setInt(8, ticket.getTicketID());

            queryStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error! updating ticket: " + e.getMessage());
        }

    }


    public List<Ticket> searchByDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            return Collections.emptyList();
        }

        List<Ticket> tickets = new ArrayList<>();
        String updateSQL = "UPDATE ticket SET description = ?, reporter = ?, priority = ?, dateReported = ?, resolution = ?, dateResolved = ?, status = ? WHERE id = ?";

        try (Connection dbConnection = DriverManager.getConnection(dbURI);
             PreparedStatement queryStatement = dbConnection.prepareStatement(updateSQL)){
             String query = "SELECT * FROM tickets WHERE description LIKE ?";
             PreparedStatement statement = dbConnection.prepareStatement(query);
             statement.setString(1, "%" + description + "%");
             ResultSet resultSet = statement.executeQuery();


             while (resultSet.next()) {
                 Integer id = resultSet.getInt("id");
                 Integer priority = resultSet.getInt("priority");
                 String ticketDescription = resultSet.getString("description");
                 String ticketReporter = resultSet.getString("reporter");
                 Integer ticketDateReported = resultSet.getInt("dateReported");
                 String ticketResolution = resultSet.getString("resolution");
                 String ticketStatus = resultSet.getString("status");
                 Integer ticketDateResolved = resultSet.getInt("dateResolved");
                  Ticket ticket = new Ticket(id, ticketDescription, ticketReporter, priority,  ticketDateReported, ticketResolution, ticketDateResolved, ticketStatus);
                  tickets.add(ticket);
             }


        } catch (SQLException e) {
            System.err.println("Error! Searching by Description: " + e.getMessage());
            return null;
        }

        return tickets;
    }


}
