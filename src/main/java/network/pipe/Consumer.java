package network.pipe;

import golan.izik.log.Logger;

import java.util.concurrent.BlockingQueue;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    11/03/2015 23:42
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class Consumer implements Runnable{

  private final BlockingQueue<String> sharedQueue;


  public Consumer (BlockingQueue<String> sharedQueue) {
    this.sharedQueue = sharedQueue;
  }

  @Override
  public void run() {
    StringBuilder b = new StringBuilder();
    String s=null;
    while(!Coordinator.END_OF_SEND.equals(s)) {
      try {
        s = sharedQueue.take();
        b.append(s);
      } catch (InterruptedException ex) {
        Logger.log(ex);
      }
    }
    Logger.log(b.toString());
  }

}
