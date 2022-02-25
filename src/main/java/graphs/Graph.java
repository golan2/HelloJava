package graphs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<T> {
    private final Map<T, Vertex<T>> vertices = new HashMap<>();     //map will easily find a vertex given its value

    public Vertex<T> addV(T t) {
        final Vertex<T> v = new Vertex<>(t);
        vertices.put(t, v);
        return v;
    }

    public void addE(T from, T to) {
        final Vertex<T> fv = vertices.get(from);
        if (fv == null) throw new IllegalArgumentException();
        final Vertex<T> tv = vertices.get(to);
        if (tv == null) throw new IllegalArgumentException();
        fv.edgeTo(tv);
    }

    public Vertex<T> get(T t) {
        return vertices.get(t);
    }

    @Getter
    @RequiredArgsConstructor
    public static class Vertex<T> {
        private final T data;
        private final Set<Vertex<T>> outE = new HashSet<>();

        public void edgeTo(Vertex<T> to) {
            outE.add(to);
        }

    }
}
