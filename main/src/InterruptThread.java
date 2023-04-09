import java.util.UUID;


// Example demonstrating capabilities of thread interruption
public class InterruptThread {

    // Imaging that our main thread is Jenny
    public static void main(String[] args) {
        Thread alice;

        // Use case 1: interrupting thread in RUNNABLE state
        alice = new BusyThread();
        alice.start();

        System.out.println("Jenny recognized it's time for Alice to stop working, so Jenny is interrupting Alice");
        alice.interrupt();
        System.out.println("Jenny interrupted Alice and is done now");

        // Use case 2: interrupting thread in TIMED_WAITING state
        alice = new SleepThread();
        alice.start();

        System.out.println("Jenny recognized it's time for Alice to stop working, so Jenny is interrupting Alice");
        alice.interrupt();
        System.out.println("Jenny interrupted Alice and is done now");
    }

    // Thread emulating busy work
    static class BusyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Busy thread started working");

            String a = UUID.randomUUID().toString();
            String b = UUID.randomUUID().toString();
            for (int i = 0; i < 100_000_000; i++) {
                if (isInterrupted()) {
                    System.out.println("Busy thread was interrupted and finishing it's work");
                    return;
                }
                a = Integer.toString(a.hashCode() * b.hashCode() + 1);
            }
            System.out.println("Busy thread stopped working with result " + a);
        }
    }

    // Class sleeping for 10 seconds
    static class SleepThread extends Thread {

        @Override
        public void run() {
            System.out.println("Sleep thread started sleeping (state is TIMED_WAITING)");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Sleep thread's sleep was interrupted, exiting");
                return;
            }
            System.out.println("Sleep thread successfully finished sleeping");
        }
    }
}
