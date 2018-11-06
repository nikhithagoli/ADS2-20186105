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
        for(int i = 0; i < n; i++) {
            places.put(input[i], i);
        }
        EdgeWeightedGraph graph = new EdgeWeightedGraph(n);
        for(int i = 0; i < m; i++) {
            String[] tokens = sc.nextLine().split(" ");
            graph.addEdge(new Edge(places.get(tokens[0]),
                 places.get(tokens[1]),
                     Double.parseDouble(tokens[2])));
        }
        int q = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < q; i++) {
            String[] tokens = sc.nextLine().split(" ");
            DijkstraUndirectedSP sp = new DijkstraUndirectedSP(graph, places.get(tokens[0]));
            System.out.println(Math.round(sp.distTo(places.get(tokens[1]))));
        }
    }
}