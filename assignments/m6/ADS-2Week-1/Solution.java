import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;


class PageRank {
	HashMap<Integer, Double>map;
	Digraph graph;
	Digraph revGraph;
	private int vertices;
	PageRank(Digraph dg) {
		graph=dg;
		vertices = graph.V();
		map = new HashMap<Integer, Double>();
		revGraph = graph.reverse();
	}
	public void calculatePr() {
		Double sum = 0.0;
		int count = 0;
		double temp = (double) vertices;
		double initialPr = (1/temp);
		for(int i = 0; i < vertices; i++) {
			if(graph.indegree(i) == 0) {
				//System.out.println("i am here superman");
				map.put(i, 0.0);
			} else {
				map.put(i, initialPr);
			}
		}
		double[] tempArray = new double[graph.V()];
		for( int j = 0;j < 1000; j++) {
			for( int i = 0; i < vertices; i++) {
				//System.out.println("---------------------------------");
				sum = 0.0000;
				for(int each : revGraph.adj(i)) {
					double value = map.get(each);
					sum += ((double)value/(double)graph.outdegree(each));
				}
				tempArray[i] = sum;
			}
			for(int i = 0; i < vertices; i++) {
				map.put(i, tempArray[i]);
			}
		}
	}
	public void print() {
		for( int i = 0; i<map.size(); i++) {
			System.out.println(i + " - " + map.get(i));
		}
	}
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int totalVertices = Integer.parseInt(sc.nextLine());
		//sc.nextLine();
		Digraph d = new Digraph(totalVertices);
		int i = 0;
		while (i < totalVertices) {
			String[] arra1 = sc.nextLine().split(" ");
			for (int j = 1; j < arra1.length; j++) {
				d.addEdge(Integer.parseInt(arra1[0]), Integer.parseInt(arra1[j]));
			}
			i++;
		}
		System.out.println(d.toString());
		for (int k = 0; k < d.V(); k++) {
			//System.out.println(d.adj(k));
			if (d.outdegree(k) == 0) {
				//System.out.println("i am here");
				for (int a = 0; a < d.V(); a++ ) {
					if (k != a) {
						d.addEdge(k, a);
					}
				}
			}
		}
		PageRank pr = new PageRank(d);
		pr.calculatePr();
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
