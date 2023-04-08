import java.util.UUID;


/**
 * Example demonstrating the idea of daemon and non-daemon threads
 */
public class DaemonThreadsIdea {

    // Example with main thread spinning another thread
    public static void main(String[] args) {
        Thread busyThread = new BusyThread();
//        busyThread.setDaemon(true); // When uncommented, JVM will exit without waiting for busy thread to finish
        busyThread.start();

        System.out.println("Main thread is done");
    }

    // Thread emulating busy work
    static class BusyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Busy thread started working");
            String a = UUID.randomUUID().toString();
            String b = UUID.randomUUID().toString();
            for (int i = 0; i < 100_000_000; i++) {
                a = Integer.toString(a.hashCode() * b.hashCode() + 1);
            }
            System.out.println("Busy thread stopped working with result " + a);
        }
    }
}


