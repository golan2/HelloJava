package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Phaser;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    25/11/13 08:25
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class StatisticalList extends ArrayList<Double> {

    public double getMinimumValue() {
        Collections.sort(this);
        return this.get(0);
    }

    public double getMaximumValue() {
        Collections.sort(this, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return (-1) * o1.compareTo(o2);  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        return this.get(0);
    }

    public double range(){

        double range = getMaximumValue() - getMinimumValue();

        return range;
    }


    public static void median(int odd[]) throws InterruptedException {

        Arrays.sort(odd);

        for (int i = 0; i < odd.length; i++) {
            System.out.println(odd[i]);
            Thread.sleep(500);
        }
        System.out.println("The median number of the previous list of numbers is: " + odd[5]);
    }





}