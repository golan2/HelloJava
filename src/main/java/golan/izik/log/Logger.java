package golan.izik.log;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* <pre>
* <B>Copyright:</B>   Izik Golan
* <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
* <B>Creation:</B>    20/03/14 22:27
* <B>Since:</B>       BSM 9.21
* <B>Description:</B>
*
* </pre>
*/
public class Logger {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS", Locale.US);
    private static final GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
    private static final String CLASS_NAME = Logger.class.getCanonicalName();


    private static String generatePrefix() {
        StringBuilder buf = new StringBuilder();
        buf.append("[").append(getCurrentDateAndTime()).append("] ");
        buf.append("[").append(Thread.currentThread().getName()).append("] ");
        buf.append("[").append(getFileNameAndLineNumber()).append("] ");
        return  buf.toString();
    }

    /**
     * The [stackTrace] will contain a few callers but eventually we will have "Logger.java" there.
     * So we iterate the [stackTrace] in order to find it.     *
     * But it might appear more than once (once for generatePrefix and once for log) so we iterate all "Logger.java" until we find the next one
     * This next one is the place in the [stackTrace] who is the caller to [log]
     * @return the file name and the line number of the caller
     */
    private static String getFileNameAndLineNumber() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean found = false;
        for (StackTraceElement ste : stackTrace) {
            if (CLASS_NAME.equals(ste.getClassName())) {
                found = true;
            } else {
                if (found) {
                    return ste.getFileName() + ":" + ste.getLineNumber();
                }
            }
        }
        return "?";
    }

    private static String getCurrentDateAndTime() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        return sdf.format(calendar.getTime());
    }

    public static void log(String s) {
        System.out.println(generatePrefix() + s);
    }

    public static void log(Throwable t) {
        t.printStackTrace(System.out);
    }
}
