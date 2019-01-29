package interview.javajava;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesRunner {

    public static void main(String[] args) {
        final SimpleDateFormat DF = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss,SSS");

        for (long l=Long.MIN_VALUE ; l< Long.MAX_VALUE ; l+=1000_000) {
            System.out.println(DF.format(new Date(l)));
        }
    }
}
