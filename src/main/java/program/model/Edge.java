package program.model;

public class Edge {
    public final int source, dest;

    private Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }
    public static Edge of(int a, int b) {
        return new Edge(a, b);
    }
}
