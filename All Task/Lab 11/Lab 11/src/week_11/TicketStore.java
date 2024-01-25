package week_11;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * For add, edit, search operations on the Ticket database.
 * Use try-with-resources exception handing in all the methods to ensure the
 * database connection is closed at the end of the method.
 */


public class TicketStore {
    
    private static String dbURI = "jdbc:sqlite:tickets.sqlite";


    
    TicketStore(String databaseURI) {
        this.dbURI = databaseURI;
        
        /* TODO create a ticket table in the database given by databaseURI.
             It should have these columns, types and constraints, in this order:
              id, the primary key  (you will let SQLite autogenerate the values for this column for you)
              description, text, Can't be null.
              reporter, text. Can't be null.
              priority, a number in the range 1-5. Can't be null. Add constraints to prohibit values outside the range 1-5.
              dateReported, number, the long time value of a Date object, representing the date the ticket was created. Can't be null.
              resolution, text. May be null.
              dateResolved, number, the long time value of a Date object, representing the date the ticket was marked as resolved. May be null.
              status, text, either "OPEN" or "RESOLVED". These are the only acceptable values. Can't be null.

              You will use the autogenerated ID values as the ticket ID.
        */

        try (Connection connection = DriverManager.getConnection(dbURI);
             Statement statement = connection.createStatement()) {
            // Create the ticket table
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
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            System.err.println("Error creating ticket table: " + e.getMessage());
        }

    }

    public static List searchTicketsByDescription(String description) {

        List<Ticket> matchingTickets = new ArrayList<>();
        String query = "SELECT * FROM ticket WHERE LOWER(description) LIKE ?";

        try (Connection conn = DriverManager.getConnection(dbURI);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, "%" + description.toLowerCase() + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
                    matchingTickets.add(ticket);
                }
            }

            matchingTickets.sort(Comparator.comparingInt(Ticket::getPriority));
        } catch (SQLException e) {
            System.err.println("Error while searching tickets by description: " + e.getMessage());
            return null;
        }

        return matchingTickets;
    }


    public static List getAllOpenTickets() {
        
        // TODO Query database, get all tickets which have the status "OPEN"
        //  order the tickets by priority - tickets with priority = 1 first, priority = 5 last
        //  Remember the database can order tickets for you
        //  Create Ticket object for each row in the ResultSet
        //  Return a List of these Ticket objects
        //  If there are no Tickets in the database, return an empty List.
        //  Catch any SQL errors and use System.err.print to print an error message.
        //  If an SQL exception is thrown, return null.

        String query = "SELECT * FROM ticket WHERE status = 'OPEN' ORDER BY priority ASC";
        List<Ticket> openTickets = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbURI);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

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

                openTickets.add(ticket);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving open tickets from the database: " + e.getMessage());
            return null;
        }

        return openTickets;
    }

    
    
    /** Add ticket to the database. */
    public void add(Ticket newTicket) throws SQLException {
        
        // TODO insert a new row in the database for this ticket.
        //  Write the data from the fields in the newTicket object.
        //  Write all of the fields a Ticket could have:
        //  description, priority, reporter, dateReported, resolution, dateResolved and status.
    
        // TODO Re-throw any SQLExceptions due to constraint violations from this method. The rest of the
        //  code will interpret a SQLException as meaning that the data in the ticket
        //  was not valid, for example the priority was -10 or the status was "CAT"



        // From a catch block, use the throw keyword to throw the exception, for example,

        //        String insertSQL = ""; //  write SQL statement here
        //
        //        try (Connection conn = DriverManager.getConnection(dbURI);
        //            PreparedStatement preparedStatement = conn.prepareStatement(insertSQL)) {
        //
        //            // insert data about ticket into database
        //
        //        }
        //        catch (SQLException sqle) {
        //            // use the throw keyword to throw the exception for the caller to handle
        //            throw sqle;
        //        }

        String insertSQL = "INSERT INTO ticket (description, priority, reporter, dateReported, resolution, dateResolved, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbURI);
             PreparedStatement preparedStatement = conn.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, newTicket.getDescription());
            preparedStatement.setInt(2, newTicket.getPriority());
            preparedStatement.setString(3, newTicket.getReporter());

            try {
                preparedStatement.setLong(4, newTicket.getDateReported().getTime());
            } catch (Exception e){
                preparedStatement.setNull(4, Types.INTEGER);
            }
            preparedStatement.setString(5, newTicket.getResolution());

            try {
                preparedStatement.setLong(6, newTicket.getDateResolved().getTime());
            } catch (Exception e){
                preparedStatement.setNull(6, Types.INTEGER);
            }
            preparedStatement.setString(7, newTicket.getStatus().toString());

            // create a prepared statement to insert data
            // set placeholders

            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();  // ask the database for generated primary key values
            keys.next();   // move to first row of the result set
            int id = keys.getInt(1);   // get value from first column

            newTicket.setTicketID(id);



// use the ticket object's setTicketID(id); method to set the ticket's ID value.
        } catch (SQLException sqle) {
            throw sqle;
        }

    }


    public static Ticket getTicketById(int id) {
       
        // TODO query the database to find the ticket with this id.
        //  if the ticket is found, then create a Ticket object and return it
        //  if the ticket is not found, return null.
        //  Catch any SQL errors and use System.err.print to print an error message.
        //  If an SQL exception is thrown, return null.

        String query = "SELECT * FROM ticket WHERE id = ?";
        Ticket ticket = null;

        try (Connection conn = DriverManager.getConnection(dbURI);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

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
            System.err.println("Error retrieving ticket from the database: " + e.getMessage());
            return null;
        }

        return ticket;

    }
    
    
    public void updateTicket(Ticket ticket) {
        
        // TODO Use the Ticket's ID to modify the row in the database with this ID
        //  modify row in the database to set the values contained in the Ticket object
        //  this method can be used to resolve a ticket.
        //  Catch any SQLException errors. Use System.err.print to print an error message if an exception if thrown.

        String updateSQL = "UPDATE ticket SET description = ?, reporter = ?, priority = ?, dateReported = ?, resolution = ?, dateResolved = ?, status = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(dbURI);
             PreparedStatement preparedStatement = conn.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, ticket.getDescription());
            preparedStatement.setString(2, ticket.getReporter());
            preparedStatement.setInt(3, ticket.getPriority());
            preparedStatement.setLong(4, ticket.getDateReported().getTime());
            preparedStatement.setString(5, ticket.getResolution());
            preparedStatement.setLong(6, ticket.getDateResolved().getTime());
            preparedStatement.setString(7, ticket.getStatus().toString());
            preparedStatement.setInt(8, ticket.getTicketID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error while updating ticket: " + e.getMessage());
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