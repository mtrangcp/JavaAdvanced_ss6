package xs2;

import java.util.List;
import java.util.Random;

public class BookingCounter implements Runnable{
    String name;
    List<TicketPool> rooms;
    boolean running = true;

    Random rand = new Random();

    public BookingCounter(String name, List<TicketPool> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    public void run() {

        System.out.println(name + " bắt đầu bán vé...");

        while (running) {

            TicketPool room = rooms.get(rand.nextInt(rooms.size()));

            Ticket t = room.sellTicket();

            if (t != null) {
                System.out.println(name + " bán " + t.id);
            }

            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }

    public void stop() {
        running = false;
    }
}
