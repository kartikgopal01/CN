import java.util.*;

public class ShortestPathRouting {
    static class Graph {
        private int vertices;
        private List<int[]> edges = new ArrayList<>();

        Graph(int vertices) {
            this.vertices = vertices;
        }

        void addEdge(int u, int v, int weight) {
            edges.add(new int[]{u, v, weight});
        }

        void bellmanFord(int source) {
            int[] distance = new int[vertices];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[source] = 0;

            for (int i = 1; i < vertices; i++) {
                for (int[] edge : edges) {
                    int u = edge[0], v = edge[1], weight = edge[2];
                    if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v])
                        distance[v] = distance[u] + weight;
                }
            }
            System.out.println("Distances from source " + source + ": " + Arrays.toString(distance));
        }
    }

    static class PathVectorRouting {
        private Map<Integer, Integer> routingTable = new HashMap<>();

        void updateRoutingTable(int destination, int nextHop) {
            routingTable.put(destination, nextHop);
        }

        void displayRoutingTable() {
            System.out.println("Routing Table: " + routingTable);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        int[][] edges = {{0, 1, -1}, {0, 2, 4}, {1, 2, 3}, {1, 3, 2}, {1, 4, 2}, {3, 2, 5}, {3, 1, 1}, {4, 3, -3}};
        for (int[] edge : edges) graph.addEdge(edge[0], edge[1], edge[2]);

        graph.bellmanFord(0);

        PathVectorRouting pvr = new PathVectorRouting();
        pvr.updateRoutingTable(1, 2);
        pvr.updateRoutingTable(2, 3);
        pvr.updateRoutingTable(3, 4);
        pvr.displayRoutingTable();
    }
}
