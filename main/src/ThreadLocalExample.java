// Example demonstrating the usage of ThreadLocal variables
public class ThreadLocalExample {

    // Announcement class that stores announcement that is different per thread
    static class Announcement {
        // Thread local is used to store variable per thread
        ThreadLocal<String> announcement = new ThreadLocal<>();

        // Method printing the announcement
        void announce() {
            System.out.println(announcement.get());
        }
    }

    // Thread that first sets the announcement and then periodically announcing that
    static class Announcer extends Thread {
        private Announcement sharedAnnouncement;
        private String valueToAnnounce;

        Announcer(Announcement sharedAnnouncement, String valueToAnnounce) {
            this.sharedAnnouncement = sharedAnnouncement;
            this.valueToAnnounce = valueToAnnounce;
        }

        @Override
        public void run() {
            try {
                sharedAnnouncement.announcement.set(valueToAnnounce);
                for (int i = 0; i < 10; i++) {
                    sharedAnnouncement.announce();
                    Thread.sleep(10000);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Announcement announcement = new Announcement();
        new Announcer(announcement, "Pizza").start();
        new Announcer(announcement, "Broccoli").start();
        new Announcer(announcement, "Coffee").start();
    }
}
