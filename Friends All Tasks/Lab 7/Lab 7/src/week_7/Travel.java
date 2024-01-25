package week_7;

import java.util.Date;


public class Travel<Place> implements Comparable<Place> {

    // Private fields
    private String name;    // A String for the place name (for example, Hawaii)
    private String reason;  // A String reason (a reason for visiting, for example, to go surfing)
    private Date created;   // A Date created (when the Place object was created)


    public void Place(String name, String reason) {
        this.name = name;
        this.reason = reason;
        this.created = new Date(); // Set the current date/time as the creation date/time
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getReason() {
        return reason;
    }


    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreated() {
        return created;
    }


    public void setCreated(Date created) {
        this.created = created;
    }


    @Override
    public int compareTo(Place other) {
        return this.name.compareTo(other.name);
    }


    @Override
    public String toString() {
        return "Place to visit: " + name + ". Reason: " + reason + ". Date created: " + created.toString();
    }
}
