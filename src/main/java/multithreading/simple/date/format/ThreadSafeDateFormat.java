package multithreading.simple.date.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeDateFormat {
    private static final int THREADS = 100;
    private static final int ITERATIONS = 50;
    private static final Date[] dates = new Date[ITERATIONS * THREADS];
    private static final String[] strings = new String[ITERATIONS * THREADS];
    private static final AtomicInteger datesIndex = new AtomicInteger();

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");


    public static void main(String[] args) throws InterruptedException {
        testParse();
        testFormat();

    }

    private static void testParse() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(THREADS);

        for (int i = 0; i < THREADS; i++) {
            executorService.submit(ThreadSafeDateFormat::parseDate);
        }

        executorService.awaitTermination(5, TimeUnit.SECONDS);

        int nullCount = 0;
        for (Date date : dates) {
            if (date == null) nullCount++;
        }
        System.out.println("There are ["+100*nullCount/dates.length+"] null PCT for parse");

        executorService.shutdownNow();
    }

    private static void parseDate() {
        for (int iteration = 0; iteration < ITERATIONS; iteration++) {
            final int ind = datesIndex.getAndIncrement();
            try {
                dates[ind] = sdf.parse("2020-51-18 22:01:749");     //due to non thread safety it will return null instead of a Date
            } catch (ParseException e) {
                dates[ind] = null;
            }
        }
    }

    private static void testFormat() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(THREADS);

        for (int i = 0; i < THREADS; i++) {
            executorService.submit(ThreadSafeDateFormat::formatDate);
        }

        executorService.awaitTermination(5, TimeUnit.SECONDS);

        int nullCount = 0;
        for (String string : strings) {
            if (string == null) nullCount++;
        }
        System.out.println("There are ["+100*nullCount/dates.length+"] null PCT for format");
        executorService.shutdownNow();
    }

    private static void formatDate() {
        for (int iteration = 0; iteration < ITERATIONS; iteration++) {
            final int ind = datesIndex.getAndIncrement();
            strings[ind] = sdf.format(new Date());     //due to non thread safety it will return null instead of a String
        }
    }

}
