package gioi2;

public class BookingCounter extends Thread {
    private String counterName;
    private TicketPool pool;

    public BookingCounter(String name, TicketPool pool) {
        this.counterName = name;
        this.pool = pool;
    }

    public void run() {

        while (true) {

            pool.sellTicket(counterName);

            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }

}
