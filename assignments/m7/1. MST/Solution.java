import java.util.Scanner;
class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = Integer.parseInt(sc.nextLine());
		int e = Integer.parseInt(sc.nextLine());
		EdgeWeightedGraph graph = new EdgeWeightedGraph(v, e, sc);
		/*for(int i = 0; i < edges; i++) {
			String[] tokens = sc.nextLine().split(" ");

		}*/
		KruskalMST mst = new KruskalMST(graph);
		System.out.println(mst.weight());
	}
}