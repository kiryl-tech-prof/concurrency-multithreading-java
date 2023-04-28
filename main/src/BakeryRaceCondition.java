import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// Example of race condition based on bakery real world scenario
public class BakeryRaceCondition {

    // Class representing bakery stock with croissants and bagels
    static class Stock {
        int croissantCount;
        int bagelCount;
    }

    // Class emulating customer behavior, represented as a separate thread
    static class Customer implements Runnable {

        private Stock stock;
        // The number of times this customer visits bakery
        private int numberOfVisits;
        // The number of croissants this customer purchases per visit
        private int croissantsPerVisit;
        // The number of bagels this customer purchases per visit
        private int bagelsPerVisit;

        // We pass reference to shared stock to each of customer threads
        Customer(Stock stock, int numberOfVisits, int croissantsPerVisit, int bagelsPerVisit) {
            this.stock = stock;
            this.numberOfVisits = numberOfVisits;
            this.croissantsPerVisit = croissantsPerVisit;
            this.bagelsPerVisit = bagelsPerVisit;
        }

        @Override
        public void run() {
            for (int i = 0; i < numberOfVisits; i++) {
                visitBakery();
            }
            System.out.println("Customer completed all visits to bakery");
        }

        // Method emulating visiting bakery, which is essentially buying (decreasing the amount) of bagels and croissants
        private void visitBakery() {
            stock.croissantCount = stock.croissantCount - croissantsPerVisit;
            stock.bagelCount = stock.bagelCount - bagelsPerVisit;
        }
    }

    // Main methods simulating bakery scenario -> first we bake croissants and bagels,
    // and after that customers are visiting our bakery
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // Stocked our bakery with 1500 croissants and 1500 bagels
        Stock stock = new Stock();
        stock.croissantCount = 1500;
        stock.bagelCount = 1500;
        // We will use 10 customers of our bakery -> 10 threads
        for (int i = 0; i < 10; i++) {
            // Each customer will visit bakery 100 times, purchasing 1 croissant and 1 bagel per visit
            Customer customer = new Customer(stock, 100, 1, 1);
            executorService.submit(customer);
        }
        executorService.shutdown();
        // 3 seconds to sleep and wait until all threads finish
        Thread.sleep(3000);
        System.out.println("In the end, there are " + stock.croissantCount + " croissants and " +
                            stock.bagelCount + " bagels");
    }
}
