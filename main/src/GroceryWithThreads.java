import java.util.Random;

/**
 * Demonstration of the grocery store using {@link Thread} objects
 */
public class GroceryWithThreads {

    // Class representing cashier functionality implemented as a Thread subclass
    static class CashierThread extends Thread {

        private int customersToServe;
        private String name;

        // Create thread which should work specific number of hours
        CashierThread(int workDurationHours, String name) {
            this.customersToServe = workDurationHours * 2; // 2 customers per hour
            this.name = name;
        }

        // Function emulating serving one customer by cashier
        private void serveCustomer() {
            int a = new Random().nextInt();
            int b = new Random().nextInt();

            for (int i = 0; i < 1_000_000_000; i++) {
                a = (a + b) * a;
            }

            System.out.println("Cashier " + name + " served the customer (receipt = " + a + ")");
        }

        @Override
        public void run() {
            System.out.println("!!! Cashier " + name + " started working");
            for (int i = 0; i < customersToServe; i++) {
                serveCustomer();
            }
            System.out.println("!!! Cashier " + name + " is done with the work for today");
        }


    }

    // Function emulating manager Jenny's work, and returning some kind of id proofing work
    private static int doManagerWork(int durationHours) {
        int a = new Random().nextInt();
        int b = new Random().nextInt();

        for (long i = 0; i < (2_000_000_000L * durationHours); i++) {
            a = (a + b) * a;
        }

        return a;
    }

    // Main method -> grocery store is opening
    public static void main(String[] args) {
        // At this point in time, main thread (manager Jenny) is in RUNNABLE state
        System.out.println("!!! Manager Jenny started working");

        Thread alice = new CashierThread(4, "Alice"); // Alice is in NEW state
        alice.start(); // Alice is in RUNNABLE state

        int jennyWorkPart1 = doManagerWork(2); // Jenny is doing manager work for 2 hours

        // At this time, lines are pretty long
        Thread bob = new CashierThread(4, "Bob");
        Thread chris = new CashierThread(4, "Chris");
        // Both Bob and Chris are NEW

        bob.start();
        chris.start();

        int jennyWorkPart2 = doManagerWork(4); // Somewhere in the middle, Alice will finish work, entering TERMINATED state

        System.out.println("!!! Manager Jenny is done with the work today (id = " + (jennyWorkPart1 + jennyWorkPart2) + ")");
        // In the end, all threads will finish, being in TERMINATED state
    }
}
