package xs1;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    String roomName;
    List<Ticket> tickets = new ArrayList<>();
    int nextId = 1;

    public TicketPool(String roomName, int capacity) {

        this.roomName = roomName;

        for (int i = 0; i < capacity; i++) {
            String id = roomName + "-" + String.format("%03d", nextId++);
            tickets.add(new Ticket(id, roomName));
        }
    }

    public synchronized Ticket holdTicket(boolean vip) {

        long now = System.currentTimeMillis();

        for (Ticket t : tickets) {

            if (!t.isSold && !t.isHeld) {

                t.isHeld = true;
                t.isVIP = vip;
                t.holdExpiryTime = now + 5000;

                System.out.println("Đã giữ vé " + t.ticketId +
                        (vip ? " (VIP)" : "") +
                        ". Vui lòng thanh toán trong 5s");

                return t;
            }
        }

        return null;
    }

    public synchronized boolean sellHeldTicket(Ticket t) {

        if (t != null && t.isHeld && !t.isSold) {

            t.isSold = true;
            t.isHeld = false;

            return true;
        }

        return false;
    }

    public synchronized void releaseExpiredTickets() {

        long now = System.currentTimeMillis();

        for (Ticket t : tickets) {

            if (t.isHeld && !t.isSold && now > t.holdExpiryTime) {

                t.isHeld = false;

                System.out.println(
                        "TimeoutManager: Vé " + t.ticketId +
                                " hết hạn giữ, đã trả lại kho"
                );
            }
        }
    }
}
