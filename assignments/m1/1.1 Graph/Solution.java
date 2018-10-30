import java.util.Scanner;
import java.util.Arrays;
/**
 * Interface for graph.
 */
interface Graph {
    /**
     * no of vertices.
     *
     * @return     { description_of_the_return_value }
     */
    public int V();
    /**
     * no of edges.
     *
     * @return     { description_of_the_return_value }
     */
    public int E();
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(int v, int w);
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(int v, int w);
}
/**
 * Class for graphlist.
 */
class Graphlist implements Graph {
    /**
     * vities list.
     */
    private String[] cities;
    /**
     * no of vertices.
     */
    private int vertex;
    /**
     * no of edges.
     */
    private int edge;
    /**
     * ajacents list.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     * @param      e     { parameter_description }
     * @param      c     { parameter_description }
     */
    public Graphlist(final int v, final int e, final String[] c) {
        cities = c;
        this.vertex = v;
        this.edge = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (v == w || hasEdge(v, w)) {
            return;
        }
        adj[v].add(w);
        adj[w].add(v);
        edge ++;
    }
    /**
     * adj.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return adj[v];
    }
    /**
     * no of vertice.
     *
     * @return     { description_of_the_return_value }
     */
    public int V() {
        return this.vertex;
    }
    /**
     * no of edges.
     *
     * @return     { description_of_the_return_value }
     */
    public int E() {
        return this.edge;
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (int each : adj[v]) {
            if (each == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * prints.
     */
    public void print() {
        if (edge == 0) {
            System.out.println("No edges");
        } else {
            for (int i = 0; i < vertex - 1; i++) {
                String str = "";
                str += cities[i] + ": ";
                for (int each : adj[i]) {
                    str += cities[each] + " ";
                }
                System.out.println(str);
            }
            String str = "";
            str += cities[vertex - 1] + ": ";
            for (int each : adj[vertex - 1]) {
                str += cities[each] + " ";
            }
            System.out.println(str.substring(0, str.length() - 1));
        }
    }
}
/**
 * Class for graphmatrix.
 */
class Graphmatrix implements Graph {
    /**
     * cities list.
     */
    private String[] cities;
    /**
     * matix.
     */
    private int[][] matrix;
    /**
     * no of vertices.
     */
    private int vertices;
    /**
     * edges count.
     */
    private int edges;
    /**
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     * @param      e     { parameter_description }
     * @param      c     { parameter_description }
     */
    public Graphmatrix(final int v, final int e, final String[] c) {
        cities = c;
        matrix = new int[v][v];
        this.vertices = v;
        this.edges = 0;
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                matrix[i][j] = 0;
            }

        }
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (v == w || hasEdge(v, w)) {
            return;
        }
        matrix[v][w] = 1;
        matrix[w][v] = 1;
        edges ++;
    }
    /**
     * adj.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int[] adj(final int v) {
        return matrix[v];
    }
    /**
     * no of vertices.
     *
     * @return     { description_of_the_return_value }
     */
    public int V() {
        return this.vertices;
    }
    /**
     * no of edges.
     *
     * @return     { description_of_the_return_value }
     */
    public int E() {
        return this.edges;
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        return matrix[v][w] == 1;
    }
    /**
     * prints.
     */
    public void print() {
        if (edges == 0) {
            System.out.println("No edges");
        } else {
            for (int i = 0; i < vertices - 1; i++) {
                String s = "";
                for (int each : matrix[i]) {
                    s += each + " ";
                }
                System.out.println(s);
            }
            String s = "";
            for (int each : matrix[vertices - 1]) {
                s += each + " ";
            }
            System.out.println(s.substring(0, s.length() - 1));
        }
    }
}
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused constructor.
    }
    /**
     * main.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.nextLine();
        int v = Integer.parseInt(sc.nextLine());
        int e = Integer.parseInt(sc.nextLine());
        String[] cities = sc.nextLine().split(",");
        if (type.equals("List")) {
            Graphlist l = new Graphlist(v, e, cities);
            for (int k = 0; k < e; k++) {
                String[] tokens = sc.nextLine().split(" ");
                l.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            }
            System.out.println(l.V() + " vertices, " + l.E() + " edges");
            l.print();
        } else {
            Graphmatrix m = new Graphmatrix(v, e, cities);
            for (int k = 0; k < e; k++) {
                String[] tokens = sc.nextLine().split(" ");
                m.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            }
            System.out.println(m.V() + " vertices, " + m.E() + " edges");
            m.print();
        }
    }
}
