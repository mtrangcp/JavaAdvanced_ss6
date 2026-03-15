package xs2;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector implements Runnable{
    public void run() {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();

        while (true) {

            long[] ids = bean.findDeadlockedThreads();

            if (ids != null) {

                System.out.println("Phát hiện DEADLOCK!");

                ThreadInfo[] infos = bean.getThreadInfo(ids);

                for (ThreadInfo ti : infos)
                    System.out.println(ti.getThreadName());
            }

            try {
                Thread.sleep(5000);
            } catch (Exception e) {}
        }
    }

}
