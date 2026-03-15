package kha12;

import java.util.Random;

public class BookingCounter implements Runnable {

    String counterName;
    TicketPool roomA;
    TicketPool roomB;
    int soldCount = 0;

    Random random = new Random();

    public BookingCounter(String name, TicketPool a, TicketPool b) {
        this.counterName = name;
        this.roomA = a;
        this.roomB = b;
    }

    @Override
    public void run() {

        while (true) {

            if (roomA.remainingTickets() == 0 && roomB.remainingTickets() == 0) {
                break;
            }

            Ticket ticket;

            if (random.nextBoolean()) {
                ticket = roomA.sellTicket();
            } else {
                ticket = roomB.sellTicket();
            }

            if (ticket != null) {
                soldCount++;
                System.out.println(counterName + " đã bán vé " + ticket.ticketId);
            }

            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
    }

    public int getSoldCount() {
        return soldCount;
    }
}
