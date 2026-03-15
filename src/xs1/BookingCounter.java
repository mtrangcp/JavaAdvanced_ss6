package xs1;

import java.util.Random;

public class BookingCounter implements Runnable{
    String counterName;
    TicketPool[] pools;

    Random random = new Random();

    public BookingCounter(String name, TicketPool[] pools) {
        this.counterName = name;
        this.pools = pools;
    }

    public void run() {

        while (true) {

            TicketPool pool = pools[random.nextInt(pools.length)];

            boolean vip = random.nextBoolean();

            Ticket ticket = pool.holdTicket(vip);

            if (ticket != null) {

                System.out.println(counterName +
                        ": Đã giữ vé " + ticket.ticketId +
                        (vip ? " (VIP)" : ""));

                try {
                    Thread.sleep(3000);
                } catch (Exception e) {}

                if (pool.sellHeldTicket(ticket)) {

                    System.out.println(counterName +
                            ": Thanh toán thành công vé " +
                            ticket.ticketId);
                }
            }

            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }

}
