package newyear.joke.calendar.validation;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    31/12/13 22:04
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class WakeUpValidator {


    public void validate() throws Exception {
        new PartyValidator().validate();
    }
}
