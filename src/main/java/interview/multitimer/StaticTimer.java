package interview.multitimer;

import golan.izik.log.Logger;

import java.util.Timer;

/**
* <pre>
* <B>Copyright:</B>   HP Software IL
* <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
* <B>Creation:</B>    20/03/14 22:27
* <B>Since:</B>       BSM 9.21
* <B>Description:</B>
*
* </pre>
*/
class StaticTimer {

    private static Timer t = null;




    /**
     * Submit the given task to be executed within the given timeToWait.
     * Previously submitted task(s) will be removed.
     * Only this last one will be invoked.
     * @param task to be run
     * @param timeToWait how much time (milliseconds) to wait
     */
    public static synchronized void execTask(Task task, long timeToWait){
        Logger.log("execTask - Task was submitted. timeToWait=[" + timeToWait + "] task=" + task);
        if (t!=null) t.cancel();
        t = new Timer(true);
        t.schedule(new TaskWrapper(task), timeToWait);
    }


    /**
     * For some stupid reason you can't submit the same instance of {@link Task} twice into {@link Timer}
     * It somehow remembers all the tasks you've submitted in the past.
     *
     * After you submit "A" and then submit it again you get:
     * IllegalStateException: Task already scheduled or cancelled
     *
     * In order to allow users of {@link StaticTimer} to submit the same {@link Task} again (different time for example) we wrap it with {@link interview.multitimer.StaticTimer.TaskWrapper}
     * This way the same task is submitted but with a different wrapper.
     */
    private static class TaskWrapper extends Task {
        private final Task t;

        private TaskWrapper(Task t) {this.t = t;}

        @Override
        public void run() {
            t.run();
        }
    }

}
