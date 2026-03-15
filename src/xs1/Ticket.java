package xs1;

public class Ticket {
    String ticketId;
    String roomName;

    boolean isSold = false;
    boolean isHeld = false;
    boolean isVIP = false;

    long holdExpiryTime = 0;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
    }
}
