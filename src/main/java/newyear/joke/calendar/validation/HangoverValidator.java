package newyear.joke.calendar.validation;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    31/12/13 22:10
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class HangoverValidator {

    public void validate() throws AlcoholThresholdException {
        checkAlcoholThreshold();
    }

    private boolean checkAlcoholThreshold() throws AlcoholThresholdException {
        throw new AlcoholThresholdException("Too much bear last night");
    }
}
