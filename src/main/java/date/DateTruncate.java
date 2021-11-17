package date;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateTruncate {


    public static void main(String[] args) {
        truncate();
        System.out.println(
                Stream.of("1", "2", "3")
                        .collect(Collectors.joining("','", "'", "'"))
        );
    }

    private static void truncate() {
        final long epoch = Long.parseLong("1564130324970"); //Friday, July 26, 2019 8:38:44.970
        final Date d = new Date(epoch);
        final Date t = DateUtils.truncate(d, Calendar.YEAR);
        System.out.println();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(t));
    }
}
