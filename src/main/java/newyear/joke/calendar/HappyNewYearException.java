package newyear.joke.calendar;

import newyear.joke.calendar.validation.AlcoholThresholdException;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    31/12/13 22:01
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class HappyNewYearException extends Exception {
    public HappyNewYearException(String message, AlcoholThresholdException e) {
        super(message, e);
    }
}
