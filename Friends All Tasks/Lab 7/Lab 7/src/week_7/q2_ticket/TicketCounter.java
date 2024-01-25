package week_7.q2_ticket;


public class TicketCounter {

    private static int countVariable = 1;

    public static int getNextCounterValue() {
        return countVariable++;
    }

    public static void setCounter(int value) {
        countVariable = value;
    }
}