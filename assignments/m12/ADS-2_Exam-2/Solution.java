import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unsused.
    }
    /**
     * main.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int k = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph g = new EdgeWeightedGraph(n);
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(n);
        for (int i = 0; i < k; i++) {
            String[] tokens = sc.nextLine().split(" ");
            graph.addEdge(new DirectedEdge(
                Integer.parseInt(tokens[0]),
                 Integer.parseInt(tokens[1]),
                  Double.parseDouble(tokens[2])));
            graph.addEdge(new DirectedEdge(
                Integer.parseInt(tokens[1]),
                 Integer.parseInt(tokens[0]),
                  Double.parseDouble(tokens[2])));

            g.addEdge(new Edge(Integer.parseInt(tokens[0]),
             Integer.parseInt(tokens[1]),
              Double.parseDouble(tokens[2])));
        }
        String caseToGo = sc.nextLine();
        switch (caseToGo) {
        case "Graph":
            //Print the Graph Object.
            System.out.println(g);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths,
            //  where two integers are given.
            // First is the source and second is the destination.
            String[] tokens = sc.nextLine().split(" ");
            DijkstraSP sp  = new DijkstraSP(graph,
             Integer.parseInt(tokens[0]));
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."

            if (sp.hasPathTo(Integer.parseInt(tokens[1]))) {
                System.out.println(sp.distTo(
                    Integer.parseInt(tokens[1])));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths,
            //  where three integers are given.
            // First is the source and second is the via
            //  is the one where path should pass throuh.
            // third is the destination.
            tokens = sc.nextLine().split(" ");
            sp  = new DijkstraSP(graph,
             Integer.parseInt(tokens[0]));
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            DijkstraSP sp1 = new DijkstraSP(graph,
             Integer.parseInt(tokens[1]));
            if (sp.hasPathTo(
                Integer.parseInt(tokens[1])) && sp1.hasPathTo(
                Integer.parseInt(tokens[2]))) {
                double d = sp.distTo(

                 Integer.parseInt(tokens[1])) + sp1.distTo(
                    Integer.parseInt(tokens[2]));
                System.out.println(d);
                Iterable<DirectedEdge> path = sp.pathTo(
                    Integer.parseInt(tokens[1]));
                for (DirectedEdge each : path) {
                    System.out.print(each.from() + " ");
                }
                path = sp1.pathTo(Integer.parseInt(tokens[2]));
                int last = 0;
                for (DirectedEdge each : path) {
                    System.out.print(each.from() + " ");
                    last = each.to();
                }
                System.out.print(last);
            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }

    }
}
