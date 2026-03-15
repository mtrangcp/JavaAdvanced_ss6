package xs2;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    String roomName;
    List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int total) {
        this.roomName = roomName;

        for (int i = 1; i <= total; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i)));
        }
    }

    public synchronized Ticket sellTicket() {

        for (Ticket t : tickets) {
            if (!t.sold) {
                t.sold = true;
                return t;
            }
        }

        return null;
    }

    public synchronized void addTickets(int count) {

        int start = tickets.size() + 1;

        for (int i = 0; i < count; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", start + i)));
        }

        System.out.println("Đã thêm " + count + " vé vào phòng " + roomName);
    }

    public int soldCount() {

        int c = 0;

        for (Ticket t : tickets)
            if (t.sold) c++;

        return c;
    }

    public int total() {
        return tickets.size();
    }
}
