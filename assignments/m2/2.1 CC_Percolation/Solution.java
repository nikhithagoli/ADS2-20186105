import java.util.Scanner;

/**
 * Class for percolation.
 */
class Percolation {
    /**
     * grid.
     */
    private Graph g;
    /**
     * n.
     */
    private int n;
    /**
     * top.
     */
    private int top;
    /**
     * bottom.
     */
    private int bottom;
    /**
     * size.
     */
    private int size;
    /**
     * connected.
     */
    private boolean[][] connected;
    /**
     * open count.
     */
    private int opencount;
    /**
     * Constructs the object.
     *
     * @param      n     { parameter_description }
     */
    Percolation(final int n1) {
        n = n1;
        opencount = 0;
        size = n1 * n1;
        top = size;
        bottom = size + 1;
        connected = new boolean[n][n];
        g = new Graph(size + 2);
        
    }
    /**
     * open.
     *
     * @param      row   The row
     * @param      col   The col
     */
    public void open(final int row, final int col) {
        if (!isOpen(row, col)) {
            connected[row - 1][col - 1] = true;
            opencount++;
        }
        if (row == 1) {
            g.addEdge(getIndex(row, col), top);
        }
        if (row == n) {
            g.addEdge(getIndex(row, col), bottom);
        }
        if (col > 1 && isOpen(row, col - 1)) {
            g.addEdge(getIndex(row, col), getIndex(row, col - 1));
        }
        if (col < n && isOpen(row, col + 1)) {
            g.addEdge(getIndex(row, col), getIndex(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            g.addEdge(getIndex(row, col), getIndex(row - 1, col));
        }
        if (row < n && isOpen(row + 1, col)) {
            g.addEdge(getIndex(row, col), getIndex(row + 1, col));
        }
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
        return connected[row - 1][col - 1];
    }
    /**
     * Determines if full.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     True if full, False otherwise.
     */
    public boolean isFull(final int row, final int col) {
        if (0 < row && row <= n && 0 < col && col <= n) {
            return g.hasEdge(top, getIndex(row, col));
        }
        return false;
    }
    /**
     * opensites.
     *
     * @return     { description_of_the_return_value }
     */
    public int numberOfOpenSites() {
        return opencount;
    }
    /**
     * percolates.
     *
     * @return     { description_of_the_return_value }
     */
    public boolean percolates() {
        CC cc = new CC(g);
        return cc.connected(top, bottom);
    }
    /**
     * getInex.
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     The index.
     */
    private int getIndex(final int row, final int col) {
        return n * (row - 1) + col - 1;
    }
}
/**
 * soluton class.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //constructor.
    }
    /**
     * main.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Percolation p = new Percolation(n);
        while (sc.hasNext()) {
            String[] tokens = sc.nextLine().split(" ");
            p.open(Integer.parseInt(tokens[0]),
                    Integer.parseInt(tokens[1]));
        }
        System.out.println(p.percolates());
    }
}
