package xs2;

import java.util.List;

public class Statistics {
    static void show(List<TicketPool> rooms) {

        System.out.println("=== THỐNG KÊ HIỆN TẠI ===");

        int totalSold = 0;

        for (TicketPool r : rooms) {

            int sold = r.soldCount();

            totalSold += sold;

            System.out.println(
                    "Phòng " + r.roomName +
                            ": Đã bán " + sold +
                            "/" + r.total()
            );
        }

        System.out.println("Tổng doanh thu: " + totalSold * 150000 + " VNĐ");
    }
}
