package newyear.joke.day;

import newyear.joke.day.morning.WakeUpHandler;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    31/12/13 22:08
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class DayScheduler {

    public static void main(String[] args) throws Exception {
        new DayScheduler().start();
    }

    private void start() throws Exception {

        new WakeUpHandler().hadnle();
    }
}
