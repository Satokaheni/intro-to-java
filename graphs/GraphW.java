import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Pair;

public class GraphW<T> {

    HashMap<T, ArrayList<Pair<T, int>>> edges = new HashMap<T, ArrayList<Pair<T, int>>>();

    GraphW(Edge[] edges) {
        this.convertEdges(edges);
    }

    private void convertEdges(Edge[] edges) {
        for(int i = 0; i < edges.length; i++) {
            T v1 = edges[i].vertex1;
            T v2 = edges[i].vertex2;
            double cost = edges[i].cost;

            // Check if Key exist
            if (!this.edges.containsKey(v1)) 
                this.edges.put(v1, new ArrayList<Pair<T, int>>());
            if (!this.edges.containsKey(v2))
                this.edges.put(v2, new ArrayList<Pair<T, int>>());

            this.edges.get(v1).add(new Pair(v2, cost));
            this.edges.get(v2).add(new Pair(v1, cost));
        }
    }

    private boolean allKnown(boolean[] known) {
        boolean all = true;
        for(int i = 0; i < known.length; i++) {
            all = all && known[i];
        }
        return all;
    }

    private T minVertex(HashMap<T, int> vertices, boolean[] known, double[] distance) {
        T vertex = null;
        int minDistance = Double.POSITIVE_INFINITY;

        for(T v: vertices.keySet().toArray()) {
            if (distance[vertices.get(v)] < minDistance && !known[vertices.get(v)]) {
                vertex = v;
                minDistance = distance[vertices.get(v)];
            }
        }
        return vertex;
    }

    public void dijstras(T startVertex) {

        //set up
        HashMap<T, int> vertices = new HashMap<T, int>();
        T[] vert = this.edges.keySet().toArray();
        for (int i = 0; i < vert.length; i++) {
            vertices.put(vert[i], i);
        }
        boolean[] known = new boolean[vertices.size()];
        double[] distances = new double[vertices.size()];
        T[] previous = (T[])(new Object[vertices.size()]);

        for(int = 0; i < vertices.size(); i++) {
            known[i] = false;
            distances[i] = Double.POSITIVE_INFINITY;
        }
        
        T currentVertex = startVertex;
        known[vertices.get(startVertex)] = true;
        distances[vertices.get(startVertex)] = 0;

        while(!allKnown(known)) {

            // update table
            ArrayList<Pair<T, double>> currentEdges = this.edges.get(currentVertex)
            for(int i = 0; i < currentEdges.size(); i++) {
                T destination = currentEdges.get(i).getKey(); // vertex
                double weight = currentEdges.get(i).getValue(); // cost

                if (!known[vertices.get(destination)]) {
                    if ((distance[vertices.get(currentVertex)] + weight) < distance[vertices.get(destination)]) {
                        distances[vertices.get(destination)] = distance[vertices.get(startVertex)] + weight;
                        previous[vertices.get(destination)] = currentVertex;
                    }
                }
            }

            currentVertex = this.minVertex(vertices, known, distances);
            known[vertice.get(currentVertex)] = true;
        }
    }

    public void dijstras(T startVertex) {

        //set up
        HashMap<T, int> vertices = new HashMap<T, int>();
        T[] vert = this.edges.keySet().toArray();
        for (int i = 0; i < vert.length; i++) {
            vertices.put(vert[i], i);
        }
        boolean[] known = new boolean[vertices.size()];
        double[] distances = new double[vertices.size()];
        T[] previous = (T[])(new Object[vertices.size()]);

        for(int = 0; i < vertices.size(); i++) {
            known[i] = false;
            distances[i] = Double.POSITIVE_INFINITY;
        }
        
        T currentVertex = startVertex;
        known[vertices.get(startVertex)] = true;
        distances[vertices.get(startVertex)] = 0;

        while(!allKnown(known)) {

            // update table
            ArrayList<Pair<T, double>> currentEdges = this.edges.get(currentVertex)
            for(int i = 0; i < currentEdges.size(); i++) {
                T destination = currentEdges.get(i).getKey(); // vertex
                double weight = currentEdges.get(i).getValue(); // cost

                if (!known[vertices.get(destination)]) {
                    if ((weight) < distance[vertices.get(destination)]) {
                        distances[vertices.get(destination)] = weight;
                        previous[vertices.get(destination)] = currentVertex;
                    }
                }
            }

            currentVertex = this.minVertex(vertices, known, distances);
            known[vertice.get(currentVertex)] = true;
        }
    }
}