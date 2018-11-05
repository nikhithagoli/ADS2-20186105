import java.util.Scanner;
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
		int v = Integer.parseInt(sc.nextLine());
		int e = Integer.parseInt(sc.nextLine());
		EdgeWeightedGraph graph = new EdgeWeightedGraph(v, e, sc);
		KruskalMST mst = new KruskalMST(graph);
		System.out.println(String.format("%.5f",mst.weight()));
	}
}