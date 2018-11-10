import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		int k = Integer.parseInt(sc.nextLine());
		EdgeWeightedGraph graph = new EdgeWeightedGraph(n);
		for (int i = 0; i < k; i++) {
			String[] tokens = sc.nextLine().split(" ");
			graph.addEdge(new Edge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2])));
		}
		String caseToGo = sc.nextLine();
		switch (caseToGo) {
		case "Graph":
			//Print the Graph Object.
			System.out.println(graph);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			String[] tokens = sc.nextLine().split(" ");
			DijkstraUndirectedSP sp  = new DijkstraUndirectedSP(graph, Integer.parseInt(tokens[0]));
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			if (sp.hasPathTo(Integer.parseInt(tokens[1]))) {
				System.out.println(sp.distTo(Integer.parseInt(tokens[1])));
			} else {
				System.out.println("No Path Found.");
			}
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			tokens = sc.nextLine().split(" ");
			sp  = new DijkstraUndirectedSP(graph, Integer.parseInt(tokens[0]));
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			if (sp.hasPathTo(Integer.parseInt(tokens[2]))) {
				if(sp.hasviapath(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]))) {
					System.out.println(sp.distTo(Integer.parseInt(tokens[2])));
				} else {
					System.out.println("No Path Found.");
				}
			} else {
				System.out.println("No Path Found.");
			}
			break;

		default:
			break;
		}

	}
}