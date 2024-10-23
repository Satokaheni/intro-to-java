import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T> {

    ArrayList<T> vertices;
    HashMap<T, ArrayList<T>> edges;

    Graph(Pair<Vertex, Vertex>[] edges, T[] vertices) {
        this.setEdges(edges);
        this.vertices = new ArrayList<>(vertices);
    }

    Graph(ArrayList<Pair<Vertex, Vertex>> edges, T[] vertices) {
        this.setEdges(edges.toArray());
        this.vertices = new ArrayList<>(vertices);
    }

    Graph(ArrayList<Pair<Vertex, Vertex>> edges; ArrayList<T> vertices) {
        this.setEdges(edges.toArray());
        this.vertices = vertices;
    }

    private void setEdges(Pair<T, T>[] edges) {
        for(int i = 0; i < edges.length; i++) {
            T v1 = edges[i].getKey();
            T v2 = edges[i].getValue();

            // Check if Key exist
            if (!this.edges.containsKey(v1)) 
                this.edges.put(v1, new ArrayList<T>());
            if (!this.edges.containsKey(v2))
                this.edges.put(v2, new ArrayList<T>());

            this.edges.get(v1).add(v2);
            this.edges.get(v2).add(v1);
        }
    }

    public boolean isPath(T start, T end) {
        // Check if both vertex exist
        if (edges.containsKey(start) && edges.containsKey(end)) {
            return this.depthFirstSearch(start, end, new ArrayList<T>());
        }
    }

    public boolean depthFirstSearch(T current, T objective, ArrayList<T> searched) {
        // base found
        if (current.equals(objective)) 
            return true;
        // base already searched
        else if (searched.contains(current)) 
            return false;
        // recursive case
        else {
            boolean found = false;
            for (int i = 0; i < edges.get(current).size(); i++) {
                found = depthFirstSearch(edges.get(current).get(i), objective, searched.add(current)) || found;
            }

            return found;
        }
        
    }
}
