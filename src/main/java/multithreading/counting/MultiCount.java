package multithreading.counting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    06/08/14 10:39
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class MultiCount {
    static int i = 0;

    public static void main(String[] args) {
        new MultiCount().doSomething();
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        //executorService.submit(new Runnable() {
        //    @Override
        //    public void run() {
        //        MultiCount.func();
        //    }
        //});
        //executorService.submit(new Runnable() {
        //    @Override
        //    public void run() {
        //        MultiCount.func();
        //    }
        //});
    }

    static void func() {
        try {
            System.out.println("i=" + i);
            for (int j = 1; j < 5; j++) {
                i = i + j;
                Thread.sleep(100);
            }
            System.out.println("i=" + i);
        }
        catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public int doSomething() {

        try {
            System.out.println("doSomething is running...");
            doSomethingElse();
            return 1;
        }
        catch (Exception ex) {
            System.out.println("Cannot execute ....");
        }
        finally {
            System.out.println("Finally is running...");
        }

        return -1;
    }

    private void doSomethingElse() throws Exception {
        throw new RuntimeException();
    }


}
