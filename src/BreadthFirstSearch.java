import java.util.*;

public class BreadthFirstSearch<T> implements Search<T> {
    private Map<T, T> edgeTo = new HashMap<>();
    private Set<T> visited = new HashSet<>();
    private final T start;

    public BreadthFirstSearch(UnweightedGraph<T> graph, T startData) {
        this.start = startData;
        bfs(graph, new Vertex<>(startData));
    }

    private void bfs(UnweightedGraph<T> graph, Vertex<T> startVertex) {
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.add(startVertex.getData());

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.poll();
            for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
                if (!visited.contains(neighbor.getData())) {
                    visited.add(neighbor.getData());
                    edgeTo.put(neighbor.getData(), current.getData());
                    queue.add(neighbor);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(T key) {
        return visited.contains(key);
    }

    @Override
    public List<T> pathTo(T key) {
        LinkedList<T> path = new LinkedList<>();
        if (!hasPathTo(key)) return path;

        for (T x = key; !x.equals(start); x = edgeTo.get(x)) {
            path.addFirst(x);
        }
        path.addFirst(start);
        return path;
    }
}
