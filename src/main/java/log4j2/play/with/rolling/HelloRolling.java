package log4j2.play.with.rolling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.UUID;

public class HelloRolling {

    private static Logger LOGGER = LoggerFactory.getLogger(HelloRolling.class);


    public static void main(String[] args) throws InterruptedException {
        final String uuid = UUID.randomUUID().toString();
        char[] chars = new char[1024*32];
        Arrays.fill(chars, '@');
        final String s = new String(chars);
        for (int i = 0; i < 15; i++) {
            System.out.println(i);
            LOGGER.info(uuid+ " - " + i);
            LOGGER.info(s);
            Thread.sleep(100);
        }
    }
}
