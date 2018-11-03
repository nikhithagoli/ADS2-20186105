import java.util.Scanner;
class PageRank {
	private double[] prlist;
	PageRank(Digraph g) {
		prlist = new double[g.V()];
		for (int i = 0; i < g.V(); i++) {
				prlist[i] = 1.0 / (g.V());
		}
		for (int k = 0; k < 1000; k++) {
			prlist = prcalculation(prlist, g);
		}
	}
	public double[] prcalculation(double[] list, Digraph g) {
		double[] l = new double[g.V()];
		for(int i = 0; i < g.V(); i++) {
			double pr = 0.0;
			for(int j = 0; j < g.V(); j++) {
				for(int each: g.adj(j)) {
					if(each == i) {
						pr += list[j] / (double)g.outdegree(j);
					}
				}
			}
			l[i] = pr;
		}
		return l;
	}
	public double getPR(int v) {
		return prlist[v];
	}
	public String toString() {
		String str = "";
		for (int i = 0; i < prlist.length; i++) {
			str += i + " - " + prlist[i] + "\n";
		}
		return str;
	}
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// read the first line of the input to get the number of vertices
		int n = Integer.parseInt(sc.nextLine());
		// iterate count of vertices times
		// to read the adjacency list from std input
		// and build the graph
		Digraph graph = new Digraph(n);
		for (int i = 0; i < n; i++) {
			String[] tokens = sc.nextLine().split(" ");
			for (int k = 1 ; k < tokens.length ; k++) {
				graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[k]));
			}
		}
		System.out.println(graph);
		// Create page rank object and pass the graph object to the constructor
		PageRank pr = new PageRank(graph);
		// print the page rank object
		System.out.println(pr);
		// This part is only for the final test case
		//
		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
