package multithreading.concurrent;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  We have 100 arrays.
 *  We populate (random numbers) all of them in parallel.
 *  Use a {@link CountDownLatch} to wait for all of them to finish populate phase.
 *  Only then we start sorting them.
 *
 *  @see
 */
public class HelloCountDownLatch {

    private static final int            ARRAYS  = 100;
    private static       int [][]       matrix  = new int[ARRAYS][100000];

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(4);

        System.out.println("Populating...");
        CountDownLatch populateLatch = new CountDownLatch(ARRAYS);
        AtomicInteger populateIndexer = new AtomicInteger();
        executorService.execute(new Populator(1, populateLatch, populateIndexer));
        executorService.execute(new Populator(2, populateLatch, populateIndexer));
        executorService.execute(new Populator(3, populateLatch, populateIndexer));
        executorService.execute(new Populator(4, populateLatch, populateIndexer));
        populateLatch.await();
        System.out.println("Done populating!");

        System.out.println("Sorting...");
        CountDownLatch sortLatch = new CountDownLatch(ARRAYS);
        AtomicInteger sortIndexer = new AtomicInteger();
        executorService.execute(new Sorter(1, sortLatch, sortIndexer));
        executorService.execute(new Sorter(2, sortLatch, sortIndexer));
        executorService.execute(new Sorter(3, sortLatch, sortIndexer));
        executorService.execute(new Sorter(4, sortLatch, sortIndexer));
        sortLatch.await();
        System.out.println("Done sorting!");


    }

    public abstract static class Handler implements Runnable {

        protected final int name;
        private final CountDownLatch cdl;
        private final AtomicInteger indexer;

        Handler(int name, CountDownLatch cdl, AtomicInteger indexer) {
            this.name = name;
            this.cdl = cdl;
            this.indexer = indexer;
        }

        @Override
        public void run() {

            while (indexer.get()<100) {
                int index = indexer.getAndIncrement();
                if (index>=100) {
                    print("Got index ["+index+"] to process. Bye...");
                    return;
                }
                print("Working on index ["+index+"]");

                doYourThing(index);
                cdl.countDown();
                print("Done with index ["+index+"]");
            }
        }

        protected abstract void doYourThing(int index);

        protected void print(String s) {
            System.out.println("\t["+Thread.currentThread().getId()+"]["+this.name+"] "+s);
        }
    }

    private static class Populator extends Handler {

        Populator(int name, CountDownLatch cdl, AtomicInteger indexer) { super(name, cdl, indexer); }

        @Override
        protected void doYourThing(int index) {
            int[] arr = matrix[index];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = new Random().nextInt(5000)+1;
            }
        }
    }

    private static class Sorter extends Handler {

        Sorter(int name, CountDownLatch cdl, AtomicInteger indexer) { super(name, cdl, indexer); }

        @Override
        protected void doYourThing(int index) {
            int[] arr = matrix[index];
            Arrays.sort(arr);
        }
    }





}
