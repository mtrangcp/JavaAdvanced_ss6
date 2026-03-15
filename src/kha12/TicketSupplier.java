package kha12;


// kha 2
public class TicketSupplier implements Runnable{
    TicketPool roomA;
    TicketPool roomB;

    int supplyCount;
    int interval;
    int rounds;

    public TicketSupplier(TicketPool a, TicketPool b, int supplyCount, int interval, int rounds) {
        this.roomA = a;
        this.roomB = b;
        this.supplyCount = supplyCount;
        this.interval = interval;
        this.rounds = rounds;
    }

    public void run() {

        for (int i = 0; i < rounds; i++) {

            try {
                Thread.sleep(interval);
            } catch (Exception e) {}

            roomA.addTickets(supplyCount);
            roomB.addTickets(supplyCount);
        }
    }

}
