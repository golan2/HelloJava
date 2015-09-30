package newyear.joke.day.morning;

import newyear.joke.calendar.validation.WakeUpValidator;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    31/12/13 22:06
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class WakeUpHandler {

    public void hadnle() throws Exception {
        new WakeUpValidator().validate();
    }
}