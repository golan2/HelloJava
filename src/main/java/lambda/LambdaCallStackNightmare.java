package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    05/05/14 21:06
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class LambdaCallStackNightmare {
    public static ArrayList<Integer> integers = initValues();

    public static void main(String[] args) {
        //integers.stream().filter(val -> val%2==0).forEach(LambdaCallStackNightmare::visit);
        integers.parallelStream().filter(val -> val%2==0).forEach(LambdaCallStackNightmare::visit);
    }

    private static ArrayList<Integer> initValues() {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
              integers.add(i);
        }
        return integers;
    }

    public static void doIt(String[] args) {
        //map names to lengths

        List<Integer> lengths = new ArrayList<>();

        Arrays.asList(args).stream().map(LambdaCallStackNightmare::check).forEach(lengths::add);
        //for (String name : Arrays.asList(args))
        //    lengths.add(check(name));

        System.out.println(lengths);
    }

    // simple check against empty strings
    public static int check(String s) {
        if (s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.length();
    }

    private static void visit(Integer i) {
        System.out.println(i+",");
    }


}
