import java.util.UUID;


// Example demonstrating the usage of ThreadLocal variables
public class ThreadLocalExample {

    // Thread that first sets the announcement and then periodically announcing that
    static class Announcer extends Thread {

        private final ThreadLocal<String> announcement;

        Announcer(ThreadLocal<String> announcement) {
            this.announcement = announcement;
        }

        @Override
        public void run() {
            try {
                // First, set the value of the announcement (thread local)
                announcement.set(UUID.randomUUID().toString());
                // Then, periodically announce that
                for (int i = 0; i < 10; i++) {
                    System.out.println(announcement.get());
                    Thread.sleep(10000);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        ThreadLocal<String> threadLocalAnnouncement = new ThreadLocal<>();
        new Announcer(threadLocalAnnouncement).start();
        new Announcer(threadLocalAnnouncement).start();
        new Announcer(threadLocalAnnouncement).start();
    }
}
