import java.util.*;

public class WeightedGraph<T> {
    private final boolean directed;
    private final Map<Vertex<T>, List<Edge<T>>> adjacencyList = new HashMap<>();

    public WeightedGraph(boolean directed) {
        this.directed = directed;
    }

    public void addEdge(T sourceData, T destData, double weight) {
        Vertex<T> source = new Vertex<>(sourceData);
        Vertex<T> dest = new Vertex<>(destData);
        Edge<T> edge = new Edge<>(source, dest, weight);
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(edge);

        if (!directed) {
            Edge<T> backEdge = new Edge<>(dest, source, weight);
            adjacencyList.computeIfAbsent(dest, k -> new ArrayList<>()).add(backEdge);
        } else {
            adjacencyList.putIfAbsent(dest, new ArrayList<>());
        }
    }

    public List<Edge<T>> getEdges(Vertex<T> vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<Vertex<T>> getVertices() {
        return adjacencyList.keySet();
    }
}
