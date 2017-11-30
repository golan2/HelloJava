package multithreading.concurrent;


import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.stream.Collectors;

/**
 *  We have 100 arrays.
 *  We populate (random numbers) all of them in parallel.
 *  Use a {@link java.util.concurrent.CyclicBarrier } to wait for all of them to finish populate phase.
 *  Only then we start sorting them.
 *
 *  @see HelloCountDownLatch
 */
public class HelloCyclicBarrier implements Serializable {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException, CloneNotSupportedException, IOException, ClassNotFoundException {
        final Integer[] arr = {1, 2, 3};
        final List<Integer> list = Arrays.asList(arr);
        System.out.println(list.stream().map(Object::toString).collect(Collectors.joining(",")));
    }

    private class SerSon extends Ser implements Serializable{
        int son;

        private SerSon(int son) {
            super(son);
            this.son = son;
        }
    }

    private class Ser {
        int a;
        transient NonSer b;

        private Ser(int a) {
            this.a = a;
            this.b = new NonSer(a*2);
        }

        @Override
        public String toString() {
            return "Ser{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    private static class NonSer {
        int b;

        private NonSer(int b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "NonSer{" +
                    "b=" + b +
                    '}';
        }
    }

}
