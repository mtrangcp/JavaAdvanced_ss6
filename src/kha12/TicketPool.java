package kha12;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    String roomName;
    List<Ticket> tickets = new ArrayList<>();
    int nextId = 1;

    public TicketPool(String roomName, int total) {
        this.roomName = roomName;

        for (int i = 1; i <= total; i++) {
            String id = roomName + "-" + String.format("%03d", i);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public synchronized Ticket sellTicket() {

        for (Ticket t : tickets) {
            if (!t.isSold) {
                t.isSold = true;
                return t;
            }
        }

        return null;
    }

    // kha 2
    public synchronized void addTickets(int count) {

        for (int i = 0; i < count; i++) {
            String id = roomName + "-" + String.format("%03d", nextId++);
            tickets.add(new Ticket(id, roomName));
        }

        System.out.println("Nhà cung cấp: Đã thêm " + count + " vé vào phòng " + roomName);
    }



    public int remainingTickets() {
        int count = 0;

        for (Ticket t : tickets) {
            if (!t.isSold) count++;
        }

        return count;
    }
}
