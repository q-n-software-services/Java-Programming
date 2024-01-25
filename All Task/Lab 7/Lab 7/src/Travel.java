package week_7.q1_travel_wish_list;

import java.util.Date;

/**
 * Represents one place in a wish list of places to travel to.
 */
public class Travel implements Comparable<Place> {

    // Private fields
    private String name;    // A String for the place name (for example, Hawaii)
    private String reason;  // A String reason (a reason for visiting, for example, to go surfing)
    private Date created;   // A Date created (when the Place object was created)

    /**
     * Constructor that takes two arguments, the name and the reason.
     * The constructor creates and sets the Date created.
     *
     * @param name   A String for the place name
     * @param reason A String reason for visiting the place
     */
    public Place(String name, String reason) {
        this.name = name;
        this.reason = reason;
        this.created = new Date(); // Set the current date/time as the creation date/time
    }

    // Getters and setters

    /**
     * Get the name of the place
     *
     * @return A String representing the place name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the place
     *
     * @param name A String representing the new place name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the reason for visiting the place
     *
     * @return A String representing the reason for visiting the place
     */
    public String getReason() {
        return reason;
    }

    /**
     * Set the reason for visiting the place
     *
     * @param reason A String representing the new reason for visiting the place
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Get the date and time the Place object was created
     *
     * @return A Date object representing the creation date/time
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Set the date and time the Place object was created
     *
     * @param created A Date object representing the new creation date/time
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    // Comparable interface method

    /**
     * Compare two Place objects based on their name.
     *
     * @param other The other Place object to compare to
     * @return An integer representing the comparison result
     */
    @Override
    public int compareTo(Place other) {
        return this.name.compareTo(other.name);
    }

    // toString method

    /**
     * Return a human-readable String representation of the Place object
     *
     * @return A String with all the information about the Place object
     */
    @Override
    public String toString() {
        return "Place to visit: " + name + ". Reason: " + reason + ". Date created: " + created.toString();
    }
}
