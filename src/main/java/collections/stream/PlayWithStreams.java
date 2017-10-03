package collections.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Play with streams
 */
public class PlayWithStreams {

    public static void main(String[] args) {
        sortedKeySet();
//        fromObjectsListToString();
//        fromMapToString();
    }

    private static void sortedKeySet() {
        final HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 100);
        map.put("B", 20);
        map.put("C", 13);
        map.put("D", 4);

        final List<String> a = map.keySet().stream().sorted().collect(Collectors.toList());
        for (String s : a) {
            System.out.println(s);
        }
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


        NameVal(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}
