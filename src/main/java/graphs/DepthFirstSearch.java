package graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class DepthFirstSearch {

    public static void main(String[] args) {

        final Graph<Integer> g = new Graph<>();
        IntStream.range(0,6+1).forEach(g::addV);

        g.addE(0,1);
        g.addE(0,3);
        g.addE(0,2);
        g.addE(1,5);
        g.addE(1,6);
        g.addE(2,4);
        g.addE(3,2);
        g.addE(3,4);
        g.addE(4,1);
        g.addE(6,4);

        System.out.println("----");

        go(g, 0)
                .forEach(System.out::println);

    }

    private static List<Integer> go(Graph<Integer> g, Integer root) {

        final LinkedHashSet<Integer> visited = new LinkedHashSet<>();
        final Deque<Integer> stack = new LinkedList<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            final Integer value = stack.pop();
            visited.add(value);
            g.get(value)
                    .getOutE()
                    .stream()
                    .map(Graph.Vertex::getData)
                    .filter(o -> !visited.contains(o))
                    .forEach(stack::push);
        }
        return new ArrayList<>(visited);
    }
}
