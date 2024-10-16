package interview.multitimer;

import golan.izik.log.Logger;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    21/03/14 00:00
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class MultiTimerMain {
    public static void main(String[] args) throws InterruptedException {
        Task t1 = new LogOnlyTask(1);
        Task t2 = new LogOnlyTask(2);
        Task t3 = new LogOnlyTask(3);

//        Logger.log("========================================");
//        Logger.log("StaticTimer");
//        Logger.log("BEFORE");
//
//        StaticTimer.execTask(t2, 2000);
//        StaticTimer.execTask(t3, 2500);
//        StaticTimer.execTask(t1, 1500);
//
//        Logger.log("AFTER");
//
//        Logger.log("Waiting...");
//        Thread.sleep(5000);
//
//        Logger.log("FINISHED");


        Logger.log("========================================");
        Logger.log("MultiTimer");
        Logger.log("BEFORE");

        ListMultiTimer multiTimer = new ListMultiTimer();
        multiTimer.addTask(t3, 2500);
        multiTimer.addTask(t2, 2000);
        multiTimer.addTask(t1, 1500);

        Logger.log("AFTER");

        Logger.log("Waiting...");
        Thread.sleep(5000);

        Logger.log("FINISHED");

    }

}
