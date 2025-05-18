import java.util.*;

public class UnweightedGraph<T> {
    private final boolean directed;
    private final Map<Vertex<T>, List<Vertex<T>>> adjacencyList = new HashMap<>();

    public UnweightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(T sourceData, T destData) {
        Vertex<T> source = new Vertex<>(sourceData);
        Vertex<T> dest = new Vertex<>(destData);

        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(dest);
        if (!directed) {
            adjacencyList.computeIfAbsent(dest, k -> new ArrayList<>()).add(source);
        } else {
            adjacencyList.putIfAbsent(dest, new ArrayList<>());
        }
    }

    public List<Vertex<T>> getAdjacentVertices(Vertex<T> vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<Vertex<T>> getVertices() {
        return adjacencyList.keySet();
    }
}

