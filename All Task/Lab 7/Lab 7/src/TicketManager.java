package week_7.q2_ticket;

import java.util.LinkedList;

/**
 * This code controls the program. It shows the menu, asks user for their choice,
 * and carries out that action.
 * It delegates tasks to methods in TicketUI, TicketStore and ResolvedTicketStore.
 *
 * The instruction are in the Lab 7 Questions.md file  */

public class TicketManager {

    public static void main(String[] args) {
        new TicketManager().manage();
    }

    // Global objects - the data stores, and the user interface object
    // you can access these from any method in this class
    private TicketUI ticketUI = new TicketUI();  // delegate all user interaction to the ticketUI object
    private TicketStore ticketStore = TicketStore.getInstance(); // stores all the open tickets

    // TODO Problem 6: create a ResolvedTicketStore object here.
    private ResolvedTicketStore resolvedTickets = ResolvedTicketStore.getInstance();


    void manage() {

        boolean wantToQuit = false;

        while (!wantToQuit) {

            int userChoice = ticketUI.showMenuGetChoice();

            // TODO Problem 3: extend this if-else if-else statement to add the new
            //  choices for the two new menu options here. Call the appropriate method for each choice.

            if (userChoice == TicketUI.ADD_TICKET_MENU_OPTION_NUMBER) {
                menuOptionAddTicket();
            } else if (userChoice == TicketUI.SEARCH_BY_ID_MENU_OPTION_NUMBER) {
                menuOptionSearchById();
            } else if (userChoice == TicketUI.DELETE_BY_TICKET_ID_MENU_OPTION_NUMBER) {
                menuOptionDeleteById();
            } else if (userChoice == TicketUI.SHOW_NEXT_TICKET_MENU_OPTION_NUMBER) {
                menuOptionShowNextTicket();
            } else if (userChoice == TicketUI.SHOW_ALL_TICKETS_MENU_OPTION_NUMBER) {
                menuOptionDisplayAllTickets();
            } else if (userChoice == TicketUI.QUIT_MENU_OPTION_NUMBER) {
                wantToQuit = true;
            } else if (userChoice == week_7.q2_ticket.TicketUI.DELETE_BY_DESCRIPTION) {
                menuOptionDeleteTicketByDescription();
            } else if (userChoice == week_7.q2_ticket.TicketUI.SEARCH_BY_DESCRIPTION) {
                menuOptionSearchByDescription();
            }
        }
    }


    private void menuOptionAddTicket() {

        // Get ticket data. Use ticketUI method to ask for data.
        Ticket newTicket = ticketUI.getNewTicketInfo();

        // Add to the ticket store
        ticketStore.add(newTicket);
        ticketUI.userMessage("Ticket added to the ticket queue");
    }


    /* Ask for a ticket ID, request store searches for a ticket with that ID, displays the
     * ticket if one is found, or a message if the ticket with that ID is not found. */
    protected void menuOptionSearchById() {

        int ticketID = ticketUI.getTicketID();
        Ticket ticket = ticketStore.getTicketById(ticketID);
        if (ticket == null) {
            ticketUI.userMessage("No ticket found with that ID");
        } else {
            ticketUI.displayTicket(ticket);
        }
    }


    /* Ask for ticket ID, call deleteTicketByID to find ticket, confirm delete operation, and delete */
    protected void menuOptionDeleteById() {
        int ticketID = ticketUI.getTicketID();
        deleteTicketById(ticketID);
    }


    /* Display next ticket in the queue - the highest priority */
    protected void menuOptionShowNextTicket() {
        Ticket next = ticketStore.peekNextTicket();
        ticketUI.displayTicket(next);
    }


    /* Show all tickets, in priority order */
    protected void menuOptionDisplayAllTickets() {
        LinkedList<Ticket> allTickets = ticketStore.getAllTickets();
        ticketUI.displayTickets(allTickets);
    }


    /* Ask user for a search term, display all tickets matching that term */
    protected void menuOptionSearchByDescription() {
        String searchTerm = ticketUI.askUserQuestion("Enter search term: ");
        List<Ticket> matchingTickets = ticketStore.searchByDescription(searchTerm);
        if (matchingTickets.isEmpty()) {
            ticketUI.displayMessage("No matching tickets.");
        } else {
            ticketUI.displayTickets(matchingTickets);
        }
    }



    /* Ask user for a search term, display all matching tickets, ask for ID of ticket to delete */
    protected void menuOptionDeleteTicketByDescription() {
        // Ask user for search term
        String searchTerm = ticketUI.askUserQuestion("Enter a search term:");

        // Find matching tickets
        List<Ticket> matchingTickets = ticketStore.searchByDescription(searchTerm);

        // If there are no matching tickets, display a message and return
        if (matchingTickets.isEmpty()) {
            ticketUI.userMessage("No matching tickets.");
            return;
        }

        // Display list of matching tickets
        ticketUI.displayTickets(matchingTickets);

        // Ask user for ticket ID to delete or to cancel
        int ticketID = ticketUI.getTicketID();
        if (ticketID == -1) {
            // User cancelled, do nothing
            return;
        }

        // Delete the selected ticket
        deleteTicketById(ticketID);
    }



    /* Fetch a ticket with the given ID, verify user wishes to delete, gather resolution information.
    Remove ticket from store, and add to resolved tickets. */
    protected void deleteTicketById(int ticketID) {
        // Fetch the ticket
        Ticket ticketToDelete = ticketStore.getTicketById(ticketID);

        // Check if a ticket with this ID is in the store
        if (ticketToDelete == null) {
            ticketUI.userMessage("Ticket not found in the ticket store.");
        } else {
            // Display ticket info, and verify delete operation
            ticketUI.displayTicket(ticketToDelete);
            if (ticketUI.areYouSure("Delete the above ticket, are you sure?")) {
                // Ask for resolution and save it
                String resolution = ticketUI.getResolution();
                ticketToDelete.setResolution(resolution);

                // Save current date and time as the date resolved
                ticketToDelete.setDateResolved(LocalDateTime.now());

                // Add ticket to resolved ticket store
                ResolvedTicketStore.getInstance().addResolvedTicket(ticketToDelete);

                // Delete ticket from ticket store
                ticketStore.deleteTicketById(ticketID);

                // Display confirmation message
                ticketUI.userMessage("Ticket deleted.");
            } else {
                ticketUI.userMessage("Cancelled - ticket not deleted.");
            }
        }
    }


}