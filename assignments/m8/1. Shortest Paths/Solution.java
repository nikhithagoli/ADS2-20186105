import java.util.Scanner;
import java.util.HashMap;
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * main.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        HashMap<String, Integer> places = new HashMap<>();
        String[] input = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            places.put(input[i], i);
        }
        /* it creates an empty undirected graph in O(v) time
         * where v is no of vertices.
         * 
         * and it takes O(E) time to add all the edges in the graph
         * where E is no of edges.
         * 
         * so in total O(V + E)
         */
        EdgeWeightedGraph graph = new EdgeWeightedGraph(n);
        for (int i = 0; i < m; i++) {
            String[] tokens = sc.nextLine().split(" ");
            //it adds a new edge in O(1) time.
            graph.addEdge(new Edge(places.get(tokens[0]),
                                   places.get(tokens[1]),
                                   Double.parseDouble(tokens[2])));
        }
        int q = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < q; i++) {
            String[] tokens = sc.nextLine().split(" ");
            /* it takes O(Elog(V)) time to form an dijkstra sp */
            DijkstraUndirectedSP sp = new DijkstraUndirectedSP(graph,
                    places.get(tokens[0]));
            //its returns a distto value in O(1) time.
            System.out.println(Math.round(sp.distTo(places.get(tokens[1]))));
        }
    }
}
