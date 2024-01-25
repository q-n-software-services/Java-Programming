package week_7.q2_ticket;

import java.util.Date;

public class Ticket implements Comparable<Ticket> {

    private int priority;
    private String reporter;     // Stores person or department who reported problem
    private String description;
    private String resolution;
    private Date dateReported;
    private Date dateResolved;
    private final int ticketID;    // The ID for each ticket - an instance variable. Each Ticket will have it's own ticketID variable

    // TODO Problem 5: Tickets need to store the resolution Date in a variable called dateResolved
    // TODO Problem 5: and a String describing the resolution, in a variable called resolution.
    //  Add two new fields (variables) here.
    //  You do not need to modify the constructor. When a new tickets is created,
    //  the user will not know the data resolved or the resolution.

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
    public String getResolution() { return resolution; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
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

    public void setDateResolved(Date dateResolved) {
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

        // Joint equal priorities, sort with oldest first
        if (this.getPriority() == otherTicket.getPriority()){
            return this.getDateReported().compareTo(otherTicket.getDateReported());
        }

        else {
            // Sort with smallest priority number at the start
            return this.getPriority() - otherTicket.getPriority();
        }
    }
}
