package gioi1;

public class BookingCounter extends Thread {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private boolean reverseOrder;

    public BookingCounter(String name, TicketPool a, TicketPool b, boolean reverse) {
        this.counterName = name;
        this.roomA = a;
        this.roomB = b;
        this.reverseOrder = reverse;
    }

    public void run() {
//        sellComboDeadlock();

        sellComboSafe();
    }

    public void sellComboSafe() {

        TicketPool first = roomA;
        TicketPool second = roomB;

        synchronized (first) {

            synchronized (second) {

                String ticketA = roomA.getTicket();
                String ticketB = roomB.getTicket();

                if (ticketA != null && ticketB != null) {

                    System.out.println(counterName +
                            " bán combo thành công: "
                            + ticketA + " & " + ticketB);

                } else {

                    System.out.println(counterName +
                            ": Hết vé phòng A hoặc B, bán combo thất bại");

                    if (ticketA != null) roomA.returnTicket(ticketA);
                    if (ticketB != null) roomB.returnTicket(ticketB);
                }
            }
        }
    }

    public void sellComboDeadlock() {

        TicketPool first = reverseOrder ? roomB : roomA;
        TicketPool second = reverseOrder ? roomA : roomB;

        synchronized (first) {

            String ticket1 = first.getTicket();
            System.out.println(counterName + ": Đã lấy vé " + ticket1);

            try { Thread.sleep(100); } catch (Exception e) {}

            System.out.println(counterName + ": Đang chờ vé " + second.getRoomName());

            synchronized (second) {

                String ticket2 = second.getTicket();

                if (ticket1 != null && ticket2 != null) {
                    System.out.println(counterName + " bán combo thành công: "
                            + ticket1 + " & " + ticket2);
                } else {

                    System.out.println(counterName + " bán combo thất bại");

                    if (ticket1 != null) first.returnTicket(ticket1);
                    if (ticket2 != null) second.returnTicket(ticket2);
                }
            }
        }
    }

}
