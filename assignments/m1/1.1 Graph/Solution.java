import java.util.Scanner;
import java.util.Arrays;
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    //public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
class Graphlist implements Graph {
	private String[] cities;
	private int vertex;
	private int edge;
	private Bag<Integer>[] adj;
	public Graphlist(int v, int e, String[] c) {
		cities = c;
		this.vertex = v;
		this.edge = e;
		adj = (Bag<Integer>[]) new Bag[v];
		for(int i = 0; i < v; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	public int V() {
		return this.vertex;
	}
	public int E() {
		return this.edge;
	}
	public boolean hasEdge(int v, int w) {
		while(adj[v].iterator().hasNext()) {
			if(adj[v].iterator().next() == w) {
				return true;
			}
		}
		return false;
	}
	public void print() {
		if(edge == 0) {
			System.out.println("No edges");
		} else {
			for(int i = 0; i < vertex-1; i++) {
				String str = "";
				str += cities[i] + ": ";
				for(int each: adj[i]) {
					str += cities[each] + " ";
				}
				System.out.println(str);
			}
			String str = "";
				str += cities[vertex-1] + ": ";
				for(int each: adj[vertex-1]) {
					str += cities[each] + " ";
				}
				System.out.println(str.substring(0,str.length()-1));
		}
	}
}
class Graphmatrix implements Graph {
	private String[] cities;
	private int[][] matrix;
	private int vertices;
	private int edges;
	public Graphmatrix(int v, int e, String[] c) {
		cities = c;
		matrix = new int[v][v];
		this.vertices = v;
		this.edges = e;
		for(int i = 0; i < v; i++) {
			for(int j = 0; j < v; j++) {
				matrix[i][j] = 0;
			}
			
		}
	}
	public void addEdge(int v, int w) {
		matrix[v][w] = 1;
		matrix[w][v] = 1;
	}
	public int[] adj(int v) {
		return matrix[v];
	}
	public int V() {
		return this.vertices;
	}
	public int E() {
		return this.edges;
	}
	public boolean hasEdge(int v, int w) {
		return matrix[v][w] == 1;
	}
	public void print() {
		if (edges == 0) {
			System.out.println("No edges");
		} else{
			for(int i = 0; i < vertices-1; i++) {
				String s = "";
				for(int each: matrix[i]) {
					s += each + " ";
				}
				System.out.println(s);
			}
			String s = "";
				for(int each: matrix[vertices - 1]) {
					s += each + " ";
				}
				System.out.println(s.substring(0, s.length()-1));
		}
	}
}
class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String type = sc.nextLine();
		int v = Integer.parseInt(sc.nextLine());
		int e = Integer.parseInt(sc.nextLine());
		String[] cities = sc.nextLine().split(",");
		System.out.println(v + " vertices, " + e + " edges"); 
		if(type.equals("List")) {
			Graphlist l = new Graphlist(v, e, cities);
			for(int k = 0; k < e; k++) {
				String[] tokens = sc.nextLine().split(" ");
				l.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			}
			l.print();
		} else {
			Graphmatrix m = new Graphmatrix(v, e, cities);
			for(int k = 0; k < e; k++) {
				String[] tokens = sc.nextLine().split(" ");
				m.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
			}
			m.print();
		}
	}
}
