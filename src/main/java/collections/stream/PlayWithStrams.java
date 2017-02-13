package collections.stream;

import org.omg.CORBA.NVList;
import org.omg.CORBA.StringHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by golaniz on 03/07/2016.
 */
public class PlayWithStrams {

    public static void main(String[] args) {
        fromObjectsListToString();
        fromMapToString();
    }

    private static void fromObjectsListToString() {
        List<NameVal> list = new ArrayList<>();
        list.add( new NameVal("one", 1) );
        list.add( new NameVal("two", 2) );
        list.add( new NameVal("six", 6) );

        System.out.println(  list.stream().map(nv -> nv.name).collect(Collectors.joining(","))  );
    }

    private static void fromMapToString() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(6, "six");

        System.out.println(  map.entrySet().stream().map(t -> "(" + t.getKey() + "=" + t.getValue() + ")").collect(Collectors.joining(","))  );
    }


    private static class NameVal {
        final String name;
        final int value;


        public NameVal(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}
