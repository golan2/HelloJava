package network.pipe;

import golan.izik.log.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    11/03/2015 23:40
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class Coordinator {


  public  static final String          END_OF_SEND     = "END_OF_SEND";


  private final ExecutorService       executorService = Executors.newFixedThreadPool(2);
  private final BlockingQueue<String> sharedQueue     = new LinkedBlockingQueue<String>();

  public static void main(String[] args) {
    new Coordinator().run();
  }

  public void run() {
    try {
      executorService.execute(new Producer(sharedQueue));
      executorService.execute(new Consumer(sharedQueue));
      executorService.awaitTermination(5, TimeUnit.SECONDS);
      executorService.shutdown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
