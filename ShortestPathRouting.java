import java.util.*;

class Graph {
    private int vertices;
    private List<int[]> edges = new ArrayList<>();

    public Graph(int vertices) {
        this.vertices = vertices;
    }

    public void addEdge(int u, int v, int weight) {
        edges.add(new int[]{u, v, weight});
    }

    public void bellmanFord(int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int i = 1; i < vertices; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], weight = edge[2];
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                }
            }
        }

        System.out.println("Distances from source " + source + ": " + Arrays.toString(distance));
    }
}

class PathVectorRouting {
    private Map<Integer, Integer> routingTable = new HashMap<>();

    public void updateRoutingTable(int destination, int nextHop) {
        routingTable.put(destination, nextHop);
    }

    public void displayRoutingTable() {
        System.out.println("Routing Table: " + routingTable);
    }
}

public class ShortestPathRouting {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);

        graph.bellmanFord(0);

        PathVectorRouting pvr = new PathVectorRouting();
        pvr.updateRoutingTable(1, 2);
        pvr.updateRoutingTable(2, 3);
        pvr.updateRoutingTable(3, 4);
        pvr.displayRoutingTable();
    }
}