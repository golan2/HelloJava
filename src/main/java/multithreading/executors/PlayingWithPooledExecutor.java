package multithreading.executors;

import EDU.oswego.cs.dl.util.concurrent.PooledExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    16/11/14 22:20
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class PlayingWithPooledExecutor {

  public PlayingWithPooledExecutor() {
    super();    //To change body of overridden methods use File | Settings | File Templates.
  }

  public static void main(String[] args) throws InterruptedException {
    final List<MyRun> runners = new ArrayList<>();
    PooledExecutor pooledExecutor = new PooledExecutor(100);

    //invoke runners
    for (int i=0 ; i<150 ; i++) {
      MyRun command = new MyRun();
      runners.add(command);
      pooledExecutor.execute(command);
    }


    //wait for last runner to finish
    boolean allDone = false;
    while (!allDone) {
      allDone = true;
      for (MyRun runner : runners) {
        if (runner.getEnd()==0) {
          allDone = false;
          break;
        }
      }
      Thread.sleep(1000);
    }


    //report runners
    for (MyRun runner : runners) {
      System.out.println(runner);
    }

    System.out.println("Done!");
  }

  private static class MyRun implements Runnable {
    private static long timeZero = System.currentTimeMillis();
    private static int counter = 0;

    private int  index = 0;
    private long born;
    private long begin;
    private long end;

    private MyRun() {
      this.index=++counter;
      this.born = getRelativeTime();
    }

    private long getRelativeTime() {
      return System.currentTimeMillis()-timeZero;
    }

    @Override public void run() {
      try {
        this.begin = getRelativeTime();
        Thread.sleep(100);
        this.end = getRelativeTime();
        System.out.println("run ended ["+index+"]");
      } catch (InterruptedException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
    }

    private int getIndex() {
      return index;
    }

    private long getBorn() {
      return born;
    }

    private long getBegin() {
      return begin;
    }

    private long getEnd() {
      return end;
    }

    @Override public String toString() {
      return "MyRun{" +
          "index=" + index +
          ", born=" + born +
          ", begin=" + begin +
          ", end=" + end +
          '}';
    }
  }

}
