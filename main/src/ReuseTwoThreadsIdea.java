import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Example demonstrating the idea how threads are reused in implementations of {@link java.util.concurrent.ExecutorService}
 */
public class ReuseTwoThreadsIdea {

    // Interface which does something very computationally heavy with hashes
    interface HashExecutor { // (1)

        // Submits the specified parameter to be scheduled for execution by executor
        void submit(String hash);
    }

    // Executor of heavy computations with hashes with 2 threads
    static class TwoThreadsHashExecutor implements HashExecutor { // (2)

        // List of hashes that need to be processed, will be passed to workers
        // Synchronized collections will be covered later in the course
        List<String> hashesToProcess = Collections.synchronizedList(new LinkedList<>());
        // Explicitly create threads
        Thread workerOne = new Thread(new WorkerHashThread(hashesToProcess));
        Thread workerTwo = new Thread(new WorkerHashThread(hashesToProcess));

        TwoThreadsHashExecutor() {
            workerOne.start();
            workerTwo.start();
        }

        @Override
        public void submit(String hash) {
            hashesToProcess.add(hash);
        }
    }

    // Worker thread executing hash computations
    static class WorkerHashThread implements Runnable { // (3)

        // List of hashes that need to be processed, passed by TwoThreadsHashExecutor
        List<String> hashesToProcess;

        WorkerHashThread(List<String> hashesToProcess) {
            this.hashesToProcess = hashesToProcess;
        }

        @Override
        public void run() {
            // (4) Main idea
            while (true) {
                if (!hashesToProcess.isEmpty()) {
                    processHash(hashesToProcess.remove(0)); // Remove first element from the list and process it
                }
            }
        }

        // Function executing computations
        private void processHash(String hash) {
            System.out.println("Start processing hash " + hash);
            for (int i = 0; i < 10_000_000; i++) {
                hash = Integer.toString(hash.hashCode());
            }
            System.out.println("Finished processing with resulting hash " + hash);
        }
    }

    public static void main(String[] args) {
        HashExecutor hashExecutor = new TwoThreadsHashExecutor();

        for (int i = 0; i < 10; i++) {
            hashExecutor.submit(UUID.randomUUID().toString());
        }
    }
}
