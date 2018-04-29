package multithreading.executors;

import golan.utils.MyLog;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    17/05/2015 09:54
 * <B>Since:</B>       Neptune 1.0
 * <B>Description:</B>
 *
 * </pre>
 */
public class PlayWithScheduledExecutor {

  public static void main(String[] args) throws InterruptedException {
    final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    final ScheduledFuture<?> future = executor.scheduleAtFixedRate(new MyRunnable(), 2, 2, TimeUnit.SECONDS);

    for (int i = 0; i < 20 ; i++) {
      MyLog.log(MyLog.LogLevel.DEBUG, "["+i+"] isCancelled=["+future.isCancelled()+"] isDone=["+future.isDone()+"]");
      Thread.sleep(500);
    }
    executor.shutdown();
  }

  private static class MyRunnable implements Runnable {
    private int counter = 0;

    @Override
    public void run() {
      MyLog.log(MyLog.LogLevel.DEBUG, "["+counter+++"]");
    }
  }
}
