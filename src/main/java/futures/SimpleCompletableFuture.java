package futures;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@Slf4j
public class SimpleCompletableFuture {

    private static CompletableFuture<Long> supplyAsync = CompletableFuture.supplyAsync(() -> {
        log.info("supplyAsync");
        return 1L;
    }) ;

    private static CompletableFuture<Long> thenApply = supplyAsync.thenApply(a -> {
        log.info("thenApply: {} -> {})", a, a+1);
        return a+1;
    }) ;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        allOf();
        thenCombine();
    }

    private static void allOf() {
        final CompletableFuture[] arr =
                Stream.of(1,2,3)
                        .sorted()
                        .map(SimpleCompletableFuture::cf)
                        .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(arr).join();
    }

    private static void thenCombine() {
        final Integer sum = Stream.of(1, 2, 3, 4, 5)
                .sorted()
                .map(SimpleCompletableFuture::cf)
                .reduce((l, r) -> l.thenCombine(r, SimpleCompletableFuture::sum))
                .orElse(cf(0))
                .join();


        log.info("Sum: {}", sum);
    }

    private static Integer sum(Integer a, Integer b) {
        log.info("{}+{}={}", a, b, a+b);
        return a+b;
    }

    private static CompletableFuture<Integer> cf(Integer num) {
        return CompletableFuture.supplyAsync( () -> {
            try {
                final long millis = (long) (Math.random() * 500);
                log.info("cf [{}] ({})", num, millis);
                Thread.sleep(millis);
                return num * 2;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalArgumentException("Interrupted", e);
            }
        });
    }



}
