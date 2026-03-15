package xs1;

public class Main {
    public static void main(String[] args) {
        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 8);
        TicketPool roomC = new TicketPool("C", 12);

        TicketPool[] pools = {roomA, roomB, roomC};

        Thread c1 = new Thread(new BookingCounter("Quầy 1", pools));
        Thread c2 = new Thread(new BookingCounter("Quầy 2", pools));
        Thread c3 = new Thread(new BookingCounter("Quầy 3", pools));
        Thread c4 = new Thread(new BookingCounter("Quầy 4", pools));
        Thread c5 = new Thread(new BookingCounter("Quầy 5", pools));

        Thread timeout = new Thread(new TimeoutManager(pools));

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();

        timeout.start();

    }
}
