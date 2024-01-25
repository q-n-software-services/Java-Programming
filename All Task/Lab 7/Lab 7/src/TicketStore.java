package week_7.q2_ticket;

import java.util.*;


/**
 * A data structure to store Tickets in memory as the program runs
 * Stores tickets in priority order, so tickets with priority 5 are at the start
 * If more than one ticket with same priority, oldest tickets are before newer tickets
 * Supports add, delete, search operations on the list of Tickets
 *
 *
 * Implemented as a Singleton - there can only ever be one TicketStore.
 * If you want to work with the TicketStore, call TicketStore.getInstance()
 *
 */


public class TicketStore {

    private LinkedList<Ticket> ticketQueue;

    private static TicketStore instance;

    private TicketStore() {
        ticketQueue = new LinkedList<>();
    }

    public static TicketStore getInstance() {
        if (instance == null) {
            instance = new TicketStore();
        }
        return instance;
    }

    /** Add all of the tickets in a list to the Ticket Queue, in priority order.
     * @param tickets a list of tickets. */
    public void addAll(LinkedList<Ticket> tickets) {
        ticketQueue.addAll(tickets);
        Collections.sort(ticketQueue);
    }


    /** Add ticket, and then sort list to keep the highest-priority at the top of the queue.
     * @param newTicket the new Ticket to add. */
    public void add(Ticket newTicket) {
        ticketQueue.add(newTicket);
        Collections.sort(ticketQueue);
    }


    /** Returns all tickets in the queue.
     * @return All the tickets.  */
    public LinkedList<Ticket> getAllTickets() {
        return ticketQueue;
    }


    /** Returns, but does not remove, the tickets from the top of the TicketQueue
     * @return the ticket at the top of the queue */
    public Ticket peekNextTicket() {
        return ticketQueue.peek();
    }


    /** How many currently open tickets in the list?
     * @return the number of open tickets */
    public int ticketsInQueue() {
        return ticketQueue.size();
    }


    /** Searches store for ticket with given ID.
     * @param id The ticket ID
     * @return The ticket with this ID, if found; null otherwise */
    public Ticket getTicketById(int id) {
        for (Ticket t : ticketQueue) {
            if (t.getTicketID() == id) {
                return t;
            }
        }

        return null; // If ticket with this ID is not found
    }


    /** Delete a single ticket by ticket ID.
     *  @return true if a ticket was found and deleted, false if a ticket with this ID is not in the queue */
    public boolean deleteTicketById(int deleteID) {

        //Loop over all tickets. Delete the one with this ticket ID
        for (Ticket ticket : ticketQueue) {
            if (ticket.getTicketID() == deleteID) {
                ticketQueue.remove(ticket);
                return true;
            }
        }

        // Not found? Return false
        return false;
    }

    /** Create a list of tickets, with a description containing
     * the given String. The search is not case sensitive.
     * @param description Text to search for in Ticket descriptions
     * @return a list of matching Tickets. If no matches, return an empty list.
     */
    public List<Ticket> searchByDescription(String description) {
        List<Ticket> matchingTickets = new ArrayList<>();

        if (description == null || description.isEmpty()) {
            return matchingTickets; // Return an empty list if the search string is null or empty
        }

        for (Ticket ticket : ticketList) {
            if (ticket.getDescription().toLowerCase().contains(description.toLowerCase())) {
                matchingTickets.add(ticket); // Add the ticket to the matching list if the description contains the search string
            }
        }

        return matchingTickets;
    }



    /** Remove all tickets from the Ticket queue */
    public void removeAll() {
        ticketQueue.clear();
    }

}