package week_11;

import java.sql.SQLException;
import java.util.List;


public class TicketController {
    
    TicketStore myTicketStore;
    
    TicketController(TicketStore myStore) {
        myTicketStore = myStore;
    }

    protected List loadAllOpenTicketsFromStore() {
        return myTicketStore.getAllOpenTickets();
    }
    
    
    protected boolean addTicket(Ticket newTicket) {
        
        try{
            myTicketStore.add(newTicket);
            return true;
        } catch (SQLException exp) {
            return false;
        }
    }

    protected Ticket searchById(int ticketId) {
        Ticket ticket = myTicketStore.getTicketById(ticketId);
        return ticket;
    }
    
    
    protected void updateTicket(Ticket ticket) {
        myTicketStore.updateTicket(ticket);
    }

    protected List<Ticket> searchByDescription(String searchTerm) {
        List<Ticket> matchingTickets = myTicketStore.searchByDescription(searchTerm);
        return matchingTickets;
    }

    protected void quitProgram() {
        Main.quit();
    }
    
}
