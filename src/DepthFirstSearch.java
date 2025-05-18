import java.util.*;

public class DepthFirstSearch<T> implements Search<T> {
    private Set<T> visited = new HashSet<>();
    private Map<T, T> edgeTo = new HashMap<>();
    private final T start;

    public DepthFirstSearch(UnweightedGraph<T> graph, T startData) {
        this.start = startData;
        dfs(graph, new Vertex<>(startData));
    }

    private void dfs(UnweightedGraph<T> graph, Vertex<T> current) {
        visited.add(current.getData());
        for (Vertex<T> neighbor : graph.getAdjacentVertices(current)) {
            if (!visited.contains(neighbor.getData())) {
                edgeTo.put(neighbor.getData(), current.getData());
                dfs(graph, neighbor);
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
