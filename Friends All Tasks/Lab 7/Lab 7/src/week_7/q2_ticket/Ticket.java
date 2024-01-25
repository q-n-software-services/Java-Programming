package week_7.q2_ticket;

import java.time.LocalDateTime;
import java.util.Date;

public class Ticket implements Comparable<Ticket> {

    private int priority;
    private String reporter;     // Stores person or department who reported problem
    private String description;
    private static String resolution;
    private Date dateReported;
    private Date dateResolved;
    private final int ticketID;    // The ID for each ticket - an instance variable. Each Ticket will have it's own ticketID variable
    private boolean resolved;

    public Ticket(String desc, int p, String rep, Date date) {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = date;
        this.ticketID = TicketCounter.getNextCounterValue();
    }


    public int getTicketID() {
        return ticketID;
    }

    protected int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() { return description; }
    public static String getResolution() { return resolution; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResolution(String resolution) {
        Ticket.resolution = resolution;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public Date getDateReported() {
        return dateReported;
    }

    public Date getDateResolved() {
        return dateResolved;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

    public void setDateResolved(LocalDateTime dateResolved) {
        this.dateResolved = dateResolved;
    }

    public String toString(){
        if (this.resolved) {
            return "ID: " + this.ticketID
                    + " Description: " + this.description
                    + " Priority: " + this.priority
                    + " Reported by: " + this.reporter
                    + " Reported on: " + this.dateReported
                    + " Resolution: " + this.resolution
                    + " Date resolved: " + this.dateResolved;
        } else {
            return "ID: " + this.ticketID
                    + " Description: " + this.description
                    + " Priority: " + this.priority
                    + " Reported by: " + this.reporter
                    + " Reported on: " + this.dateReported;
        }
    }


    @Override
    public int compareTo(Ticket otherTicket) {

        // Sort by priority and then by date in reverse
        if (this.getPriority() == otherTicket.getPriority()){
            return this.getDateReported().compareTo(otherTicket.getDateReported());
        }

        else {
            // sorting by priority in ascending order
            return this.getPriority() - otherTicket.getPriority();
        }
    }
}
