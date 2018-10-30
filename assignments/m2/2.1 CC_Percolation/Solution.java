import java.util.Scanner;
/**
 * Class for percolation.
 */
class Percolation {
  /**
   * declaration of wqu.
   */
  private Graph g;
  /**
   * declaration of n.
   */
  private int n;
  /**
   * declaration of size.
   */
  private int size;
  /**
   * declaration of first.
   */
  private int first;
  /**
   * declaration of last.
   */
  private int last;
  /**
   * declaration of connected.
   */
  private boolean[] connected;
  /**
   * declaration of count.
   */
  private int count;
  /**
   * Constructs the object.
   *
   * @param      n1    The n 1
   */
   Percolation(final int n1) {
    this.n = n1;
    this.count = 0;
    this.size = n1 * n1;
    this.first = size;
    this.last = size + 1;
    connected = new boolean[size];
    g = new Graph(size + 2);
    for (int i = 0; i < n; i++) {
      g.addEdge(first, i);
      g.addEdge(last, size - i - 1);
    }
   }
   /**
    * Searches for the first match.
    *
    * @param      i     { parameter_description }
    * @param      j     { parameter_description }
    *
    * @return     { description_of_the_return_value }
    */
   public int indexOf(final int i, final int j) {
      return n * (i - 1) + j - 1;
   }
   /**
    * Links open sites.
    *
    * @param      row   The row
    * @param      col   The col
    */
   private void linkOpenSites(final int row, final int col) {
    if (connected[col] && !g.hasEdge(row, col)) {
      g.addEdge(row, col);
    }
   }
   /**
    * numberOfOpenSites.
    *
    * @return     { description_of_the_return_value }
    */
   public int numberOfOpenSites() {
    return count;
   }
   /**
    * open.
    *
    * @param      row   The row
    * @param      col   The col
    */
   public void open(final int row, final int col) {
    int index = indexOf(row, col);
    connected[index] = true;
    count++;
    int bottom = index + n;
    int top = index - n;
    if (n == 1) {
      g.addEdge(first, index);
      g.addEdge(last, index);

    }
    if (bottom < size) {
      linkOpenSites(index, bottom);
    }
    if (top >= 0) {
      linkOpenSites(index, top);
    }
    if (col == 1) {
      if (col != n) {
        linkOpenSites(index, index + 1);
      }
      return;
    }
    if (col == n) {
      linkOpenSites(index, index - 1);
      return;
    }
    linkOpenSites(index, index + 1);
    linkOpenSites(index, index - 1);


   }
   /**
    * Determines if open.
    *
    * @param      row   The row
    * @param      col   The col
    *
    * @return     True if open, False otherwise.
    */
   public boolean isOpen(final int row, final int col) {
    return connected[indexOf(row, col)];
   }
   /**
    * percolates.
    *
    * @return     { description_of_the_return_value }
    */
   public boolean percolates() {
    CC cc = new CC(g);
    return cc.connected(first, last);
   }
}

/**
 * Class for graphmatrix.
 */
class Graph {
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
    Graph(final int v) {
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
        /*if (v == w || hasEdge(v, w)) {
            return;
        }*/
        matrix[v][w] = 1;
        matrix[w][v] = 1;
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
		// Graph grid = new Graph(n);
		Percolation p = new Percolation(n);
		/*while(sc.hasNextLine()) {
			String[] input = sc.nextLine().split(" ");
			grid.addEdge(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1);
		}*/
		while (sc.hasNext()) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            p.open(r-1, c-1);
        }
		// Connectedcomponents cc = new Connectedcomponents(grid);
		// System.out.println(cc.ispercolated(grid));
		System.out.println(p.percolates()
      			&& p.numberOfOpenSites() != 0);
	}
}