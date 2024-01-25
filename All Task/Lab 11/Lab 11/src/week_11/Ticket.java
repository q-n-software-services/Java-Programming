package week_11;

import java.time.Instant;
import java.util.Date;

public class Ticket {
    public Ticket(int ticketID, String description, String reporter, int priority, Instant dateReported, String resolution, long dateResolved, String status) {
        this.ticketID = ticketID;
        this.description = description;
        this.reporter = reporter;
        this.priority = priority;
        this.dateReported = dateReported;
        this.resolution = resolution;
        this.dateResolved = Date.from(Instant.ofEpochSecond(dateResolved));
        this.status = TicketStatus.valueOf(status);
    }

    public Ticket(Integer id, String ticketDescription, String ticketReporter, Integer priority, Integer ticketDateReported, String ticketResolution, Integer ticketDateResolved, String ticketStatus) {
        this.ticketID = ticketID;
        this.description = description;
        this.reporter = reporter;
        this.priority = priority;
        this.dateReported = dateReported;
        this.resolution = resolution;
        this.dateResolved = dateResolved;
        this.status = week_11.Ticket.TicketStatus.valueOf(String.valueOf(status));
    }

    public Ticket(String description, int priority, String reporter, long dateReported, String resolution, long dateResolved, String status) {
        this.ticketID = 121212;
        this.description = description;
        this.reporter = reporter;
        this.priority = priority;
        this.dateReported = Instant.ofEpochSecond(dateReported);
        this.resolution = resolution;
        this.dateResolved = Date.from(Instant.ofEpochSecond(dateResolved));
        this.status = TicketStatus.valueOf(status);
    }

    /*
    * An enumeration is a structure that can only take one of
    * given set of constant values. In this case, a TicketStatus can only be
    * OPEN or RESOLVED.
    *
    * Examples of working with an enum
    *   TicketStatus status = TicketStatus.OPEN;   // Create a TicketStatus
    *   status = TicketStatus.RESOLVED;      // Modify a TicketStatus
    *
    * To modify a TicketStatus in a Ticket object
    *   Ticket.setStatus(TicketStatus.RESOLVED);  // change to RESOLVED
    *
    * To turn a TicketStatus into a String, for example, to write to a database, use the TicketStatus name() method
    *  String statusAsString = status.name();
    *
    * To turn a String into a TicketStatus, use TicketStatus.valueOf(string), example
    *
    *  TicketStatus statusFromString = TicketStatus.valueOf("RESOLVED");       // OK - the String must be one of the exact strings
    *  TicketStatus anotherStatusFromString = TicketStatus.valueOf("OPEN");   // OK - the String must be exact
    *  TicketStatus invalidFromString = TicketStatus.valueOf("closed");  // Error - not a valid constant in the enum
    *
    */

    enum TicketStatus {
        OPEN, RESOLVED
    }
    
    private int ticketID;
    
    private int priority;
    private String reporter;    // Stores person or department who reported problem
    private String description;
    private Instant dateReported;
    private String resolution;
    private Date dateResolved;
    private TicketStatus status;
    
    // This constructor will be used to create a new, open ticket.
    public Ticket(String desc, int p, String rep, Date date) {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = date.toInstant();
        this.status = TicketStatus.OPEN;
    }

    // TODO create another constructor to create a Ticket from existing Ticket data read from a database
    //  it should be able to set the same fields as the previous constructor, plus set the
    //  ticketID, dateResolved value, resolution string, and status.


    public int getTicketID() {
        return ticketID;
    }
    
    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public String getReporter() {
        return reporter;
    }
    
    public void setReporter(String reporter) {
        this.reporter = reporter;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getDateReported() {
        return Date.from(dateReported);
    }
    
    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported.toInstant();
    }
    
    public String getResolution() {
        return resolution;
    }
    
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    
    public Date getDateResolved() {
        return dateResolved;
    }
    
    public void setDateResolved(Date dateResolved) {
        this.dateResolved = dateResolved;
    }

    public TicketStatus getStatus() {
        return status;
    }
    
    public void setStatus(TicketStatus status) {
        this.status = status;
    }
    
    public String toString(){
    
        String out = String.format("Id: %d, Description: %s, Priority: %d, Reported by: %s, Reported on: %s.",
                this.ticketID, this.description, this.priority, this.reporter, this.dateReported);
        
        if (dateResolved != null && dateResolved.getTime() != 0) {
            out += " Date resolved: " + dateResolved;
        }

        if (resolution != null)  {
            out += " Resolution: " + resolution;
        }
        
        out += " Status: " + status.name();
        
        return out;
    }
}

