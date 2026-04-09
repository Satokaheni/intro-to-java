import java.util.*;

public class GenericGraph<T> {

    // All vertices stored in order so we can reference them by index
    private ArrayList<T> vertices;

    // adjList.get(i) holds a list of edges from vertex i.
    // Each edge is an int[] of {neighborIndex, weight}.
    private ArrayList<ArrayList<int[]>> adjList;

    private boolean directed;

    // -----------------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------------

    // Default: undirected graph
    public GenericGraph() {
        this(false);
    }

    public GenericGraph(boolean directed) {
        this.vertices = new ArrayList<>();
        this.adjList  = new ArrayList<>();
        this.directed = directed;
    }

    // -----------------------------------------------------------------------
    // Adding vertices and edges
    // -----------------------------------------------------------------------

    public void addVertex(T vertex) {
        vertices.add(vertex);
        adjList.add(new ArrayList<>());
    }

    // Unweighted edge — defaults to weight 1
    public void addEdge(T from, T to) {
        addEdge(from, to, 1);
    }

    public void addEdge(T from, T to, int weight) {
        int fromIdx = vertices.indexOf(from);
        int toIdx   = vertices.indexOf(to);

        if (fromIdx == -1 || toIdx == -1) {
            System.out.println("Vertex not found. Add vertices before adding edges.");
            return;
        }

        adjList.get(fromIdx).add(new int[]{toIdx, weight});

        // For undirected graphs add the edge in both directions
        if (!directed) {
            adjList.get(toIdx).add(new int[]{fromIdx, weight});
        }
    }

    // -----------------------------------------------------------------------
    // Breadth-First Search (BFS)
    //
    // Explores level by level using a queue.
    // Good for finding the shortest path by number of edges.
    // -----------------------------------------------------------------------

    public List<T> bfs(T start) {
        int startIdx = vertices.indexOf(start);
        if (startIdx == -1) return new ArrayList<>();

        boolean[] visited = new boolean[vertices.size()];
        List<T> result    = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        visited[startIdx] = true;
        queue.add(startIdx);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(vertices.get(current));

            for (int[] edge : adjList.get(current)) {
                int neighbor = edge[0];
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }

    // -----------------------------------------------------------------------
    // Depth-First Search (DFS)
    //
    // Goes as deep as possible down each branch before backtracking.
    // -----------------------------------------------------------------------

    public List<T> dfs(T start) {
        int startIdx = vertices.indexOf(start);
        if (startIdx == -1) return new ArrayList<>();

        boolean[] visited = new boolean[vertices.size()];
        List<T> result    = new ArrayList<>();
        dfsHelper(startIdx, visited, result);
        return result;
    }

    private void dfsHelper(int current, boolean[] visited, List<T> result) {
        visited[current] = true;
        result.add(vertices.get(current));

        for (int[] edge : adjList.get(current)) {
            int neighbor = edge[0];
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited, result);
            }
        }
    }

    // -----------------------------------------------------------------------
    // Dijkstra's Algorithm
    //
    // Finds the shortest weighted path from one vertex to all others.
    // Uses a simple scan instead of a priority queue so the steps are clear.
    //
    //   dist[i]  = shortest known distance from start to vertex i
    //   prev[i]  = index of the previous vertex on that shortest path
    // -----------------------------------------------------------------------

    public void dijkstra(T start) {
        int startIdx = vertices.indexOf(start);
        if (startIdx == -1) {
            System.out.println("Start vertex not found.");
            return;
        }

        int n = vertices.size();
        int[]     dist    = new int[n];
        int[]     prev    = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[startIdx] = 0;

        for (int i = 0; i < n; i++) {

            // Pick the unvisited vertex with the smallest known distance
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || dist[j] < dist[u])) {
                    u = j;
                }
            }

            // If the smallest distance is still MAX_VALUE the rest are unreachable
            if (dist[u] == Integer.MAX_VALUE) break;
            visited[u] = true;

            // Relax each edge out of u
            for (int[] edge : adjList.get(u)) {
                int v      = edge[0];
                int weight = edge[1];
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    prev[v] = u;
                }
            }
        }

        // Print the results
        System.out.println("Shortest distances from " + start + ":");
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("  to " + vertices.get(i) + " = unreachable");
            } else {
                System.out.println("  to " + vertices.get(i) + " = " + dist[i]
                        + "   path: " + buildPath(prev, startIdx, i));
            }
        }
    }

    // Walks backwards through prev[] to reconstruct the path as a string
    private String buildPath(int[] prev, int startIdx, int endIdx) {
        if (prev[endIdx] == -1 && endIdx != startIdx) return "none";

        List<T> path = new ArrayList<>();
        for (int at = endIdx; at != -1; at = prev[at]) {
            path.add(0, vertices.get(at)); // insert at front to get correct order
        }
        return path.toString();
    }

    // -----------------------------------------------------------------------
    // Cycle Detection
    //
    // Directed:   track which vertices are currently on the DFS call stack.
    //             A back-edge to a stack vertex means a cycle.
    //
    // Undirected: track the parent so we don't mistake the edge we came from
    //             as a cycle. Any other visited neighbor is a cycle.
    // -----------------------------------------------------------------------

    public boolean hasCycle() {
        boolean[] visited = new boolean[vertices.size()];

        if (directed) {
            boolean[] inStack = new boolean[vertices.size()];
            for (int i = 0; i < vertices.size(); i++) {
                if (!visited[i] && cycleDirected(i, visited, inStack)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < vertices.size(); i++) {
                if (!visited[i] && cycleUndirected(i, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Returns true if a cycle is found in a directed graph starting at 'current'
    private boolean cycleDirected(int current, boolean[] visited, boolean[] inStack) {
        visited[current] = true;
        inStack[current] = true;

        for (int[] edge : adjList.get(current)) {
            int neighbor = edge[0];
            if (inStack[neighbor]) {
                return true; // back-edge found — this is a cycle
            }
            if (!visited[neighbor] && cycleDirected(neighbor, visited, inStack)) {
                return true;
            }
        }

        inStack[current] = false; // done with this vertex, remove from stack
        return false;
    }

    // Returns true if a cycle is found in an undirected graph starting at 'current'
    private boolean cycleUndirected(int current, int parent, boolean[] visited) {
        visited[current] = true;

        for (int[] edge : adjList.get(current)) {
            int neighbor = edge[0];
            if (!visited[neighbor]) {
                if (cycleUndirected(neighbor, current, visited)) return true;
            } else if (neighbor != parent) {
                return true; // reached a visited vertex that isn't where we came from
            }
        }
        return false;
    }

    // -----------------------------------------------------------------------
    // Connectivity
    //
    // A graph is connected if every vertex can be reached from any other.
    // We DFS from vertex 0 and check that all vertices were visited.
    // -----------------------------------------------------------------------

    public boolean isConnected() {
        if (vertices.isEmpty()) return true;

        boolean[] visited = new boolean[vertices.size()];
        dfsVisit(0, visited);

        for (boolean v : visited) {
            if (!v) return false; // at least one vertex was not reachable
        }
        return true;
    }

    private void dfsVisit(int current, boolean[] visited) {
        visited[current] = true;
        for (int[] edge : adjList.get(current)) {
            int neighbor = edge[0];
            if (!visited[neighbor]) {
                dfsVisit(neighbor, visited);
            }
        }
    }

    // -----------------------------------------------------------------------
    // Print the graph
    // -----------------------------------------------------------------------

    public void print() {
        System.out.println((directed ? "Directed" : "Undirected") + " Graph:");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print("  " + vertices.get(i) + " -> ");
            List<String> neighbors = new ArrayList<>();
            for (int[] edge : adjList.get(i)) {
                neighbors.add(vertices.get(edge[0]) + "(w:" + edge[1] + ")");
            }
            System.out.println(neighbors);
        }
    }
}
