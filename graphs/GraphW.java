import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.HashMap;

public class GraphW<T> {

    HashMap<T, ArrayList<AbstractMap.SimpleEntry<T, Double>>> edges = new HashMap<>();

    GraphW(Edge[] edges) {
        this.convertEdges(edges);
    }

    private void convertEdges(Edge[] edges) {
        for(int i = 0; i < edges.length; i++) {
            T v1 = (T) edges[i].vertex1;
            T v2 = (T) edges[i].vertex2;
            double cost = edges[i].cost;

            // Check if Key exist
            if (!this.edges.containsKey(v1))
                this.edges.put(v1, new ArrayList<AbstractMap.SimpleEntry<T, Double>>());
            if (!this.edges.containsKey(v2))
                this.edges.put(v2, new ArrayList<AbstractMap.SimpleEntry<T, Double>>());

            this.edges.get(v1).add(new AbstractMap.SimpleEntry<>(v2, cost));
            this.edges.get(v2).add(new AbstractMap.SimpleEntry<>(v1, cost));
        }
    }

    private boolean allKnown(boolean[] known) {
        boolean all = true;
        for(int i = 0; i < known.length; i++) {
            all = all && known[i];
        }
        return all;
    }

    private T minVertex(HashMap<T, Integer> vertices, boolean[] known, double[] distance) {
        T vertex = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for(T v: vertices.keySet()) {
            if (distance[vertices.get(v)] < minDistance && !known[vertices.get(v)]) {
                vertex = v;
                minDistance = distance[vertices.get(v)];
            }
        }
        return vertex;
    }

    public void dijstras(T startVertex) {

        //set up
        HashMap<T, Integer> vertices = new HashMap<>();
        int counter = 0;
        for (T v : this.edges.keySet()) {
            vertices.put(v, counter++);
        }
        boolean[] known = new boolean[vertices.size()];
        double[] distances = new double[vertices.size()];
        T[] previous = (T[])(new Object[vertices.size()]);

        for(int i = 0; i < vertices.size(); i++) {
            known[i] = false;
            distances[i] = Double.POSITIVE_INFINITY;
        }

        T currentVertex = startVertex;
        known[vertices.get(startVertex)] = true;
        distances[vertices.get(startVertex)] = 0;

        while(!allKnown(known)) {

            // update table
            ArrayList<AbstractMap.SimpleEntry<T, Double>> currentEdges = this.edges.get(currentVertex);
            for(int i = 0; i < currentEdges.size(); i++) {
                T destination = currentEdges.get(i).getKey(); // vertex
                double weight = currentEdges.get(i).getValue(); // cost

                if (!known[vertices.get(destination)]) {
                    if ((distances[vertices.get(currentVertex)] + weight) < distances[vertices.get(destination)]) {
                        distances[vertices.get(destination)] = distances[vertices.get(currentVertex)] + weight;
                        previous[vertices.get(destination)] = currentVertex;
                    }
                }
            }

            currentVertex = this.minVertex(vertices, known, distances);
            known[vertices.get(currentVertex)] = true;
        }
    }
}
