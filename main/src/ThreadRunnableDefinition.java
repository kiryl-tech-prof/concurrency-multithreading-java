/**
 * Example class demonstrating different ways of thread definitions
 */
public class ThreadRunnableDefinition {

    public static void main(String[] args) {
        // 1. Subclassing Thread
        Thread t1 = new HelloWorldThread();

        // 2. Anonymous class
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println("Hello, world from anonymous class!");
            }
        };

        // 3. Implementing Runnable interface
        Runnable r1 = new HelloWorldRunnable();

        // 4. Anonymous class (interface implementation)
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, world from anonymous interface implementation (class)");
            }
        };

        // 5. Anonymous class (simplified version of (4))
        Runnable r2_2 = () -> System.out.println("Hello, world from anonymous interface implementation (class)");

        // 6. Creating thread out of runnable
        Thread t3 = new Thread(r1);

        // 7. Creating thread out of another thread
        Thread t4 = new Thread(t1);
    }

    // Thread subclass which prints greeting message to the console
    static class HelloWorldThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello, world!");
        }
    }

    // Runnable implementation which prints greeting message to the console
    static class HelloWorldRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello, world!");
        }
    }
}
