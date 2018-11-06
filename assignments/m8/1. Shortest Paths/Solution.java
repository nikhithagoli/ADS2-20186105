import java.util.Scanner;
import java.util.HashMap;
class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		sc.nextLine();
		HashMap<String, Integer> places = new HashMap<>();
		String[] input = sc.nextLine().split(" ");
		for(int i = 0; i < n; i++) {
			places.put(input[i], i);
		}
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(n);
		for(int i = 0; i < m; i++) {
			String[] tokens = sc.nextLine().split(" ");
			graph.addEdge(new DirectedEdge(places.get(tokens[0]), places.get(tokens[1]), Double.parseDouble(tokens[2])));
		}
		DijkstraAllPairsSP sp = new DijkstraAllPairsSP(graph);
		int q = Integer.parseInt(sc.nextLine());
		for(int i = 0; i < q; i++) {
			String[] tokens = sc.nextLine().split(" ");
			System.out.println(Math.round(sp.dist(places.get(tokens[0]), places.get(tokens[1]))));
		}
	}
}