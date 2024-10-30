class Edge<T> {
    T vertex1;
    T vertex2;
    double cost;

    Edge(T vertex1, T vertex2, double cost) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.cost = cost;
    }
}