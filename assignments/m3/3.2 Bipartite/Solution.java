import java.util.Scanner;
/**
 * class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {
        //unused.
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = Integer.parseInt(sc.nextLine());
        int e = Integer.parseInt(sc.nextLine());
        Graph graph = new Graph(v);
        for (int i = 0; i < e; i++) {
            String[] tokens = sc.nextLine().split(" ");
            graph.addEdge(Integer.parseInt(tokens[0]),
             Integer.parseInt(tokens[1]));
        }
        DirectedCycle dc = new DirectedCycle(graph);
        if (dc.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");

        }
    }
}
