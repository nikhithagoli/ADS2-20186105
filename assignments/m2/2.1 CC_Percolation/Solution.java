import java.util.Scanner;
/**
 * Class for connectedcomponents.
 */
class Connectedcomponents {
	/**
	 * visited.
	 */
	private boolean marked[];
	/**
	 * id list.
	 */
	private int[] id;
	private int[] size;
	/**
	 * count.
	 */
	private int count;
	/**
	 * Constructs the object.
	 */
	Connectedcomponents(final Graphmatrix graph) {
		marked = new boolean[graph.vertices()];
		id = new int[graph.vertices()];
		size = new int[graph.vertices()];
		for(int v = 0; v < graph.vertices(); v++) {
			if(!marked[v]) {
				dfs(graph, v);
				count++;
			}
		}
	}
	void dfs(final Graphmatrix g, int v) {
		marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
	}
	boolean ispercolated(Graphmatrix g) {
		return id[0] == id[g.vertices()-1];
	}
}
/**
 * Class for graphmatrix.
 */
class Graphmatrix {
    /**
     * matix.
     */
    private int[][] matrix;
    /**
     * no of vertices.
     */
    private int vertex;
    /**
     * edges count.
     */
    private int edge;
    /**
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     */
    Graphmatrix(final int v) {
        matrix = new int[v][v];
        this.vertex = v;
        this.edge = 0;
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
        // matrix[w][v] = 1;
        edge++;
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
    public int vertices() {
        return this.vertex;
    }
    /**
     * no of edges.
     *
     * @return     { description_of_the_return_value }
     */
    public int edges() {
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
        return matrix[v][w] == 1;
    }
}
/**
 * Class for solution.
 */
class Solution {
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
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		Graphmatrix grid = new Graphmatrix(n);
		/*while(sc.hasNextLine()) {
			String[] input = sc.nextLine().split(" ");
			grid.addEdge(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1);
		}*/
		while (sc.hasNext()) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            grid.addEdge(r-1, c-1);
        }
		Connectedcomponents cc = new Connectedcomponents(grid);
		System.out.println(cc.ispercolated(grid));
	}
}