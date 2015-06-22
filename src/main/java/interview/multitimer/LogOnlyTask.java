package interview.multitimer;

import golan.izik.log.Logger;

/**
* <pre>
* <B>Copyright:</B>   HP Software IL
* <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
* <B>Creation:</B>    20/03/14 22:29
* <B>Since:</B>       BSM 9.21
* <B>Description:</B>
*
* </pre>
*/
class LogOnlyTask extends Task{
    private final int index;

    LogOnlyTask(int index) {this.index = index;}

    @Override
    public void run() {
        Logger.log("LogOnlyTask - run [" + index + "]");
    }

    @Override
    public String toString() {
        return "LogOnlyTask{" +
            "index=" + index +
            '}';
    }
}
