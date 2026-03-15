package xs2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static ExecutorService executor;
    static List<TicketPool> rooms = new ArrayList<>();
    static List<BookingCounter> counters = new ArrayList<>();

    static boolean started = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Bắt đầu mô phỏng");
            System.out.println("2. Tạm dừng mô phỏng");
            System.out.println("3. Tiếp tục mô phỏng");
            System.out.println("4. Thêm vé vào phòng");
            System.out.println("5. Xem thống kê");
            System.out.println("6. Phát hiện deadlock");
            System.out.println("7. Thoát");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Số phòng: ");
                    int roomCount = sc.nextInt();

                    System.out.print("Vé/phòng: ");
                    int ticketCount = sc.nextInt();

                    System.out.print("Số quầy: ");
                    int counterCount = sc.nextInt();

                    for (int i = 0; i < roomCount; i++) {
                        char name = (char) ('A' + i);
                        rooms.add(new TicketPool("" + name, ticketCount));
                    }

                    executor = Executors.newCachedThreadPool();

                    for (int i = 1; i <= counterCount; i++) {

                        BookingCounter c =
                                new BookingCounter("Quầy " + i, rooms);

                        counters.add(c);

                        executor.execute(c);
                    }

                    executor.execute(new DeadlockDetector());

                    started = true;

                    System.out.println(
                            "Đã khởi tạo hệ thống với "
                                    + roomCount + " phòng"
                    );

                    break;

                case 2:

                    for (BookingCounter c : counters)
                        c.stop();

                    System.out.println("Đã tạm dừng tất cả quầy bán vé.");

                    break;

                case 3:

                    for (BookingCounter c : counters)
                        executor.execute(c);

                    System.out.println("Đã tiếp tục hoạt động.");

                    break;

                case 4:

                    System.out.print("Chọn phòng (A,B,C...): ");
                    String r = sc.next();

                    System.out.print("Số vé thêm: ");
                    int count = sc.nextInt();

                    for (TicketPool p : rooms)
                        if (p.roomName.equals(r))
                            p.addTickets(count);

                    break;

                case 5:

                    Statistics.show(rooms);

                    break;

                case 6:

                    System.out.println("Đang quét deadlock...");

                    break;

                case 7:

                    System.out.println("Đang dừng hệ thống...");

                    if (executor != null)
                        executor.shutdownNow();

                    System.out.println("Kết thúc chương trình.");

                    return;
            }
        }
    }

}
