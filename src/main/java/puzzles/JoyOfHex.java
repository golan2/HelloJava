package puzzles;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    08/12/13 23:03
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class JoyOfHex {


    public static void main(String[] args) {
        long a1 = 0x100000000L;
        printValue(a1);
        long a2 = 0xcafebabe;
        printValue(a2);

        //System.out.println(Long.toHexString(0x100000000L + 0xcafebabe));

    }

    private static void printValue(long val) {
        String bin = Long.toBinaryString(val);
        String hex = Long.toHexString(val);
        System.out.println("0x" + hex + " | " + val + " | " + bin);
    }

}
