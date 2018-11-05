import java.util.Scanner;
import java.util.Arrays;
class PageRank {
	private Double[] prlist;
	PageRank(Digraph g) {
		prlist = new Double[g.V()];
		for (int i = 0; i < g.V(); i++) {
			if (g.indegree(i) == 0) {
				prlist[i] = 0.0;
			} else {
				prlist[i] = 1.0 / (g.V());
			}
		}
		/*for (int k = 0; k < 1000; k++) {
			Double[] x = prcalculation(prlist, g);
			if(Arrays.toString(prlist).equals(Arrays.toString(x))) {
				break;
			} else {
				prlist = x;
			}

		}*/
		prcalculation(prlist, g.reverse());
	}
	public void prcalculation(Double[] list, Digraph g) {
		Double[] l = new Double[g.V()];
		for (int k = 0; k < 1000; k++) {
			for (int i = 0; i < g.V(); i++) {
				Double pr = 0.0;
				/*for(int j = 0; j < g.V(); j++) {
					for(int each: g.adj(j)) {
						if(each == i) {
							pr += (double)(list[j] / g.outdegree(j));
						}
					}
				}*/
				for (int each : g.adj(i)) {
					pr += (double)(list[each] / (double) g.outdegree(each));
				}
				l[i] = pr;
			}
			for (int i = 0; i < g.V(); i++) {
				prlist[i] = l[i];
			}
		}
	}
		public Double getPR(int v) {
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
			for (int i = 0; i < graph.V(); i++) {
				if (graph.outdegree(i) == 0) {
					for (int k = 0; k < graph.V() && (i != k); k++) {
						graph.addEdge(i, k);
					}
				}
			}
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
