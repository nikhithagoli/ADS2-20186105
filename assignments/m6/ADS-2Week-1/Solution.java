import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;


class PageRank {
	Double[] prlist;
	Digraph graph;
	Digraph reversegraph;
	PageRank(Digraph g) {
		graph=g;
		prlist = new Double[g.V()];
		reversegraph = graph.reverse();
	}
	public void prcalculation() {
		Double pr = 0.0;
		for(int i = 0; i < graph.V(); i++) {
			if(graph.indegree(i) == 0) {
				prlist[i] = 0.0;
			} else {
				prlist[i] = 1/(double)graph.V();
			}
		}
		double[] l = new double[graph.V()];
		for( int j = 0;j < 1000; j++) {
			for( int i = 0; i < graph.V(); i++) {
				pr = 0.0000;
				for(int each : reversegraph.adj(i)) {
					double value = prlist[each];
					pr += ((double)value/(double)graph.outdegree(each));
				}
				l[i] = pr;
			}
			for(int i = 0; i < graph.V(); i++) {
				prlist[i] = l[i];
			}
		}
	}
	public void print() {
		for( int i = 0; i<prlist.length; i++) {
			System.out.println(i + " - " + prlist[i]);
		}
	}
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int v = Integer.parseInt(sc.nextLine());
		Digraph g = new Digraph(v);
		int i = 0;
		while (i < v) {
			String[] tokens = sc.nextLine().split(" ");
			for (int j = 1; j < tokens.length; j++) {
				g.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[j]));
			}
			i++;
		}
		System.out.println(g.toString());
		for (int k = 0; k < g.V(); k++) {
			if (g.outdegree(k) == 0) {
				for (int a = 0; a < g.V(); a++ ) {
					if (k != a) {
						g.addEdge(k, a);
					}
				}
			}
		}
		PageRank pr = new PageRank(g);
		pr.prcalculation();
		pr.print();
		//System.out.println(d.toString());
		//System.out.println(d.reverse());
		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

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
