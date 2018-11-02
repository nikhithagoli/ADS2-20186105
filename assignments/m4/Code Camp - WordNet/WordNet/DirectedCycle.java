/**
 * Class for directed cycle.
 */
public class DirectedCycle {
    /**
     * { var_description }.
     */
    private boolean[] marked;
    /**
     * { var_description }.
     */
    private int[] edgeTo;
    /**
     * { var_description }.
     */
    private boolean[] onStack;
    /**
     * { var_description }.
     */
    private Stack<Integer> cycle;
    /**
     * Determines whether the digraph {@code G} has a directed cycle and.
     * if so, finds such a cycle.
     * @param gph the digraph
     */
    public DirectedCycle(final Digraph gph) {
        marked  = new boolean[gph.vertices()];
        onStack = new boolean[gph.vertices()];
        edgeTo  = new int[gph.vertices()];
        for (int v = 0; v < gph.vertices(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(gph, v);
            }
        }
    }
    /**
     * { function_description }.
     *
     * @param      gph     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(final Digraph gph, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : gph.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(gph, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }

    /**
     * Does the digraph have a directed cycle?.
     * @return {@code true} if the digraph has a directed cycle,
     * {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle.
     * and {@code null} otherwise.
     * @return a directed cycle (as an iterable) if the digraph has a
     * directed cycle,
     *    and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check() {
        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            if (first != last) {
                System.out.println("cycle begins with %d and ends with %d\n"
                                   + first + last);
                return false;
            }
        }
        return true;
    }
}
