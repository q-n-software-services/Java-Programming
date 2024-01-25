package week_11;

import java.time.Instant;
import java.util.Date;

public class Ticket {
    public Ticket(Integer ticketID, String description, String reporter, Integer priority, Instant dateReported, String resolution, long dateResolved, String status) {
        this.ticketID = ticketID;
        this.description = description;
        this.reporter = reporter;
        this.priority = priority;
        this.dateReported = dateReported;
        this.resolution = resolution;
        this.dateResolved = Date.from(Instant.ofEpochSecond(dateResolved));
        this.status = TicketStatus.valueOf(status);
    }

    public Ticket(String description, Integer priority, String reporter, long dateReported, String resolution, long dateResolved, String status) {
        this.ticketID = 121212;
        this.description = description;
        this.reporter = reporter;
        this.priority = priority;
        this.dateReported = Instant.ofEpochSecond(dateReported);
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
        this.status = TicketStatus.valueOf(String.valueOf(status));
    }

    enum TicketStatus {
        OPEN, RESOLVED
    }
    
    private int ticketID;
    
    private int priority;
    private String reporter;
    private String description;
    private Instant dateReported;
    private String resolution;
    private Date dateResolved;
    private TicketStatus status;
    
    public Ticket(String desc, int p, String rep, Date date) {
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = date.toInstant();
        this.status = TicketStatus.OPEN;
    }

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
    
        String ResultTemplate = String.format("Id: %d, Description: %s, Priority: %d, Reported by: %s, Reported on: %s.",
                this.ticketID, this.description, this.priority, this.reporter, this.dateReported);
        
        if (dateResolved != null && dateResolved.getTime() != 0) {
            ResultTemplate += " Date resolved: " + dateResolved;
        }

        if (resolution != null)  {
            ResultTemplate += " Resolution: " + resolution;
        }
        
        ResultTemplate += " Status: " + status.name();
        
        return ResultTemplate;
    }
}

