public class CC {
    /**
     * marked array.
     */
    private boolean[] marked;
    /**
     * // id[v] = id of connected componet containing.
     */
    private int[] id;
    /**
     * // size[id] = number of vertices.
     */
    private int[] size;
    /**
     * // number of connected components.
     */
    private int count;

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param g the undirected graph
     */
    public CC(final Graph g) {
        marked = new boolean[g.vertices()];
        id = new int[g.vertices()];
        size = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    /**
     * depth first search.
     *
     * @param      g     { graph g
     * @param      v     { vertex }
     */
    private void dfs(final Graph g, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }


    /**
     * id generator.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int id(final int v) {
        validateVertex(v);
        return id[v];
    }

    /**
     * size function.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int size(final int v) {
        validateVertex(v);
        return size[id[v]];
    }

    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return the number of connected components in the graph {@code G}
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the sam
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     */
    public boolean connected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }

    /**
     * Returns true if vertices {@code v} and {@code w} are in the same
     * connected component.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @return {@code true} if vertices {@code v} and {@code w} are in the sam
     *         connected component; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @throws IllegalArgumentException unless {@code 0 <= w < V}
     * @deprecated Replaced by {@link #connected(int, int)}.
     */
    @Deprecated
    public boolean areConnected(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }

    /**
     * validate vertex.
     *
     * @param      v     { int }
     */
    private void validateVertex(final int v) {
        int mv = marked.length;
        if (v < 0 || v >= mv) {
            throw new IllegalArgumentException("vertex "
            + v + " is not between 0 and " + (mv - 1));
        }
    }
}
