import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Example demonstrating {@link java.util.concurrent.Callable} in action with food court scenario
 */
public class FoodCourtCallables {

    /**
     * Main thread needs to do 3 things:
     * 1. Get sushi
     * 2. Get coffee
     * 3. Write an article
     *
     * (regardless of the order)
     */
    public static void main(String[] args) throws Exception {
        // Using executor service which will do best to cache threads, but create new ones if no possibility to reuse
        ExecutorService executorService = Executors.newCachedThreadPool();

        SushiCallable sushiCallable = new SushiCallable();
        CoffeeCallable coffeeCallable = new CoffeeCallable();

        // Method 1: naive way
//        Future<String> futureSushi = executorService.submit(sushiCallable);
//        String sushi = futureSushi.get();
//        System.out.println("Main got sushi: " + sushi);
//
//        Future<String> futureCoffee = executorService.submit(coffeeCallable);
//        String coffee = futureCoffee.get();
//        System.out.println("Main got coffee: " + coffee);
//
//        String article = writeAnArticle();
//        System.out.println("Main is done with the article " + article);
//
//        executorService.shutdown();

        // Method 2: slightly improved version
        Future<String> futureSushi = executorService.submit(sushiCallable);
        Future<String> futureCoffee = executorService.submit(coffeeCallable);

        if (!futureSushi.isDone() && !futureCoffee.isDone()) {
            String article = writeAnArticle();
            System.out.println("Main is done with the article " + article);
        }

        String sushi = futureSushi.get();
        System.out.println("Main got sushi: " + sushi);

        String coffee = futureCoffee.get();
        System.out.println("Main got coffee: " + coffee);

        executorService.shutdown();
    }

    // Callable representing thread building sushi represented as String
    public static class SushiCallable implements Callable<String> {

        @Override
        public String call() {
            System.out.println("Order for sushi submitted");
            int duration = 10_000_000;
            String a = UUID.randomUUID().toString();
            String b = UUID.randomUUID().toString();

            for (int i = 0; i < duration; i++) {
                a = Integer.toString(a.hashCode());
                b = Integer.toString(b.hashCode());
            }

            System.out.println("Order for sushi is ready");
            return "Sushi " + a + b;
        }
    }

    // Callable representing thread preparing coffee represented as String
    public static class CoffeeCallable implements Callable<String> {

        @Override
        public String call() {
            System.out.println("Order for coffee submitted");
            int duration = 10_000_000;
            String a = UUID.randomUUID().toString();
            String b = UUID.randomUUID().toString();

            for (int i = 0; i < duration; i++) {
                a = Integer.toString(a.hashCode());
                b = Integer.toString(b.hashCode());
            }

            System.out.println("Order for coffee is ready");
            return "Coffee " + a + b;
        }
    }

    // Method simulating work of writing an article
    private static String writeAnArticle() {
        System.out.println("Main started writing an article");
        int duration = 10_000_000;
        String a = UUID.randomUUID().toString();
        String b = UUID.randomUUID().toString();

        for (int i = 0; i < duration; i++) {
            a = Integer.toString(a.hashCode());
            b = Integer.toString(b.hashCode());
        }

        System.out.println("Main finished writing an article");
        return "Article " + a + b;
    }
}
