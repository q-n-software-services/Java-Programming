package week_7.q2_ticket;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class TicketManager {

    public static void main(String[] args) {
        new TicketManager().manage();
    }

    private TicketUI ticketUI = new TicketUI();  // delegate all user interaction to the ticketUI object
    private TicketStore ticketStore = TicketStore.getInstance(); // stores all the open tickets


    private ResolvedTicketStore resolvedTickets = ResolvedTicketStore.getInstance();


    void manage() {

        boolean Quit = false;

        while (!Quit) {

            int userChoice = ticketUI.showMenuGetChoice();


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
                Quit = true;
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
        ticketUI.userMessage("Ticket successfully added into the Ticket queue");
    }


    /* Ask for a ticket ID, request handled thereby */
    protected void menuOptionSearchById() {

        int ticketID = ticketUI.getTicketID();
        Ticket ticket = ticketStore.getTicketById(ticketID);
        if (ticket == null) {
            ticketUI.userMessage("TICKET Not Found");
        } else {
            ticketUI.displayTicket(ticket);
        }
    }


    /* Ask for ticket ID, call deleteTicketByID to delete */
    protected void menuOptionDeleteById() {
        int ticketID = ticketUI.getTicketID();
        deleteTicketById(ticketID);
    }


    /* Display Next Ticket */
    protected void menuOptionShowNextTicket() {
        Ticket further = ticketStore.peekNextTicket();
        ticketUI.displayTicket(further);
    }


    /* Show all tickets, in priority order */
    protected void menuOptionDisplayAllTickets() {
        LinkedList<Ticket> TicketsList = ticketStore.getAllTickets();
        ticketUI.displayTickets(TicketsList);
    }


    /* Ask user for a search term, display all tickets matching that term */
    protected void menuOptionSearchByDescription() {
        String query = ticketUI.askUserQuestion("Enter search term: ");
        List<Ticket> resultFound = ticketStore.searchByDescription(query);
        if (resultFound.isEmpty()) {
            System.out.println("No matching tickets.");
        } else {
            ticketUI.displayTickets(resultFound);
        }
    }



    /* Asking the user for a search term, display all matching tickets, ask for ID of ticket to delete */
    protected void menuOptionDeleteTicketByDescription() {

        String query = ticketUI.askUserQuestion("Enter a search term:");


        List<Ticket> resultFound = ticketStore.searchByDescription(query);


        if (resultFound.isEmpty()) {
            ticketUI.userMessage("No matching tickets.");
            return;
        }


        ticketUI.displayTickets(resultFound);


        int ticketID = ticketUI.getTicketID();
        if (ticketID == -1) {
            // User cancelled, do nothing
            return;
        }

        // Delete the selected ticket
        deleteTicketById(ticketID);
    }




    protected void deleteTicketById(int ticketID) {
        // Fetch the ticket
        Ticket ticketToDelete = ticketStore.getTicketById(ticketID);


        if (ticketToDelete == null) {
            ticketUI.userMessage("Ticket not found in the ticket store.");
        } else {

            ticketUI.displayTicket(ticketToDelete);
            if (ticketUI.areYouSure("Delete the above ticket, are you sure?")) {
                // Ask for resolution and save it
                String resolution = Ticket.getResolution();
                ticketToDelete.setResolution(resolution);

                // Save current date and time as the date resolved
                ticketToDelete.setDateResolved(LocalDateTime.now());

                // Add ticket to resolved ticket store
                ResolvedTicketStore.getInstance().addTicket(ticketToDelete);

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