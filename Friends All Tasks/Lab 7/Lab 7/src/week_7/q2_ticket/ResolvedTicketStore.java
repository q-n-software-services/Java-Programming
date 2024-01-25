package week_7.q2_ticket;

import java.util.LinkedList;


public class ResolvedTicketStore {

    private static LinkedList<week_7.q2_ticket.Ticket> resolvedTickets;

    private static ResolvedTicketStore instance;

    private ResolvedTicketStore() {
        resolvedTickets = new LinkedList<>();
    }

    public static ResolvedTicketStore getInstance(){
        if (instance == null) {
            instance = new ResolvedTicketStore();
        }
        return instance;
    }

    public void addTicket(week_7.q2_ticket.Ticket t) {
        resolvedTickets.add(t);
    }

    public LinkedList<week_7.q2_ticket.Ticket> getAll() {
        return resolvedTickets;
    }

}