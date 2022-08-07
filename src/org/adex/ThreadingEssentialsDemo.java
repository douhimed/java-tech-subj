package org.adex;


import java.time.LocalTime;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 *  Concurrency vs Parallelism
 *
 *      Concurrency : being able to possibly doing two things in the same time
 *          via : new Thread(), ExecutorService
 *
 *      Parallelism : using multiple ressources (CPUs) to solve one task faster
 *          via : ForkJoinPool, Parallel Stream
 *
 */
public class ThreadingEssentialsDemo {

    public static void main(String[] args) {
        exp04ThreadSafe();
    }

    private static void exp04ThreadSafe() {
    }

    private static void exp03SortingSequentialVsParallel() {
        /**
         *  if the input is unsorted, the parallel sorting is *3, *4 faster than the sequential sorting
         *
         *  but in case of an already sorted input, the parallel sorting is worse, it doesn't know if it's already sorted, so the algo will be applied
         *
         */

        final int[] ints = ThreadLocalRandom.current().ints(10_000_000).toArray(); // int[] are not sorted
        final int[] sorteInts = IntStream.range(0, 10_000_000).toArray();

        sortInts("sequential unsorted array", ints.clone(), Arrays::sort);
        sortInts("parallel unsorted array", ints.clone(), Arrays::parallelSort);
        sortInts("sequential sorted array", sorteInts.clone(), Arrays::sort);
        sortInts("parallel sorted array", sorteInts.clone(), Arrays::parallelSort);
    }

    private static void sortInts(String type, int[] ints, Consumer<int[]> sortALgo) {
        long time = System.currentTimeMillis();
        sortALgo.accept(ints);
        System.out.println(type + " time : " + (System.currentTimeMillis() - time) + " ms");
    }

    private static void exp02ExecutorService() {
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        timer.scheduleAtFixedRate(() -> System.out.println(LocalTime.now()), 0, 1, TimeUnit.SECONDS);
        timer.scheduleAtFixedRate(() -> System.out.println("Hello world"), 0, 200, TimeUnit.MILLISECONDS);
    }

    private static void exp01Thread() {
        Thread timeThread = new Thread(() -> {
            while (true) {
                System.out.println(LocalTime.now());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }, "TImedThread");
        timeThread.start();
        Thread helloThread = new Thread(() -> {
            while (true) {
                System.out.println("Hello word");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }, "HelloThread");
        helloThread.start();
    }

    class Counter {
        // Invariant : value >= 0
        private int value;
    }
}
