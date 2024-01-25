package week_7;

public class Place {
    private final String name;
    private final String reason;

    public Place(String name, String reason) {
        this.name = name;
        this.reason = reason;
    }

    public String getName() {
        return this.name;
    }
}
