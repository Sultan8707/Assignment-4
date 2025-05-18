import java.util.*;

public class DijkstraSearch<T> implements Search<T> {
    private final Map<T, Double> distTo = new HashMap<>();
    private final Map<T, T> edgeTo = new HashMap<>();
    private final T start;

    public DijkstraSearch(WeightedGraph<T> graph, T startData) {
        this.start = startData;
        Vertex<T> startVertex = new Vertex<>(startData);

        PriorityQueue<Vertex<T>> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo.getOrDefault(v.getData(), Double.POSITIVE_INFINITY)));
        distTo.put(startData, 0.0);
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Vertex<T> current = pq.poll();

            for (Edge<T> edge : graph.getEdges(current)) {
                T neighbor = edge.getDestination().getData();
                double newDist = distTo.get(current.getData()) + edge.getWeight();

                if (newDist < distTo.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    distTo.put(neighbor, newDist);
                    edgeTo.put(neighbor, current.getData());
                    pq.add(new Vertex<>(neighbor));
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(T key) {
        return distTo.containsKey(key);
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


