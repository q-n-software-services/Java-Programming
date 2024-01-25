package week_7.q2_ticket;

import java.util.*;

import static input.InputUtils.*;

/** User interaction - user input and printing - for the ticket manager  */

public class TicketUI {


    static final int ADD_TICKET_MENU_OPTION_NUMBER = 1;

    static final int SEARCH_BY_ID_MENU_OPTION_NUMBER = 2;
    static final int DELETE_BY_TICKET_ID_MENU_OPTION_NUMBER = 3;
    static final int SHOW_NEXT_TICKET_MENU_OPTION_NUMBER = 4;
    static final int SHOW_ALL_TICKETS_MENU_OPTION_NUMBER = 5;
    static final int DELETE_BY_DESCRIPTION = 6;
    static final int SEARCH_BY_DESCRIPTION = 7;


    static final int QUIT_MENU_OPTION_NUMBER = 99;

    private Map<Integer, String> menuOptions = new TreeMap<>();



    TicketUI() {
        menuOptions.put(ADD_TICKET_MENU_OPTION_NUMBER, "Add Ticket");
        menuOptions.put(SEARCH_BY_ID_MENU_OPTION_NUMBER, "Search Ticket by ID");
        menuOptions.put(DELETE_BY_TICKET_ID_MENU_OPTION_NUMBER, "Delete Ticket by ID");
        menuOptions.put(SHOW_NEXT_TICKET_MENU_OPTION_NUMBER, "Show Next Ticket");
        menuOptions.put(SHOW_ALL_TICKETS_MENU_OPTION_NUMBER, "Show All Tickets (Open)");



        menuOptions.put(QUIT_MENU_OPTION_NUMBER, "QUIT");

    }


    protected int showMenuGetChoice() {

        while (true) {

            for (int menuOption : menuOptions.keySet()) {
                System.out.printf("%d: %s\n", menuOption, menuOptions.get(menuOption));
            }

            int choice = intInput("Enter your selection");

            // If the user's menuOption found in map's key set. Return it.
            if (menuOptions.containsKey(choice)) {
                return choice;
            }

        }
    }

    protected Ticket getNewTicketInfo() {

        Date dateReported = new Date(); // Default Date constructor creates Date with current date & time

        String description = stringInput("Enter description of the problem: ");
        String reporter = stringInput("Who reported this problem? ");


        int priority = 0;
        while (priority < 1 || priority > 5) {
            priority = intInput("Enter priority of " + description + " (1-5): ");
        }

        return new Ticket(description, priority, reporter, dateReported);
    }

    /* Methods for getting information from the user */



    protected int getTicketID() {
        return intInput("Enter Ticket ID: ");
    }



    protected String askUserQuestion(String question) {
        return stringInput(question);
    }



    protected boolean areYouSure(String question) {
        return yesNoInput(question);
    }



    public void userMessage(String s) {
        System.out.println(s);
    }


    protected void displayTickets(List<Ticket> tickets) {

        if (tickets.isEmpty()) {
            System.out.println(" ******** No tickets ******** ");
        } else {
            System.out.println(" --------- All tickets --------- ");

            for (Ticket t : tickets) {
                displayTicket(t);
            }

            System.out.println(" --------- End of ticket list --------- ");
        }
    }

    public void displayTicket(Ticket ticket) {
        System.out.println(ticket);
    }

}
