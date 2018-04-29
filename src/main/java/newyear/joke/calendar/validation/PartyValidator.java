package newyear.joke.calendar.validation;

import newyear.joke.calendar.HappyNewYearException;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    31/12/13 22:02
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class PartyValidator {

    public void validate() throws Exception {
        try {
            new HangoverValidator().validate();
        }
        catch (AlcoholThresholdException e) {
            throw new HappyNewYearException("Failed to wake up due to a great party last night :-)", e);
        }

    }
}

