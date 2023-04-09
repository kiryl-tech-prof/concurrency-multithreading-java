import java.util.UUID;


// An example demonstrating .join() method capabilities
public class JoinThread {

    // Let's imagine our main thread is Alice
    public static void main(String[] args) throws Exception {
        Thread bob = new BusyThread();
        bob.start();

        System.out.println("Alice decided to join (wait) for Bob");
//        System.out.println("Alice decided to not join (wait) for Bob");
        bob.join();
        System.out.println("Alice finished waiting Bob since Bob is done");
//        System.out.println("Alice is done without waiting for Bob");
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
