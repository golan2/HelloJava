package exceptions.arcane;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    21/03/14 08:34
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class CheckedException {

    public static void main(String[] args) {

        Map<Integer, String> map1 = new HashMap<Integer, String>();
        map1.put(new Integer(1), "111a");
        map1.put(new Integer(1), "111b");
        map1.put(new Integer(2), "222");

        System.out.println("map1 size = " + map1.size());
    }

    interface Type1 {

        void f() throws CloneNotSupportedException;

    }

    interface Type2 {

        void f() throws InterruptedException;

    }

    interface Type3 extends Type1, Type2 {

    }

    public static class Arcane3 implements Type3 {

        public void f() {

            System.out.println("Hello world");

        }

    }




}
