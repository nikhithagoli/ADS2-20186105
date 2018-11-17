/**.
 * Class for trie st.
 */
public class TrieST {
    /**.
     * { var_description }
     */
    private static final int R = 26;
    /**.
     * { var_description }
     */
    private Node root;
    /**.
     * { var_description }
     */
    private int size;
    /**.
     * Class for node.
     */
    private static class Node {
        /**.
         * { var_description }
         */
        private Node[] next = new Node[R];
        /**.
         * { var_description }
         */
        private boolean isString;
    }
    /**.
     * Constructs the object.
     */
    public TrieST() {
    }
    /**
     * Does the set contain the given key?
     * @param key the key
     * @return <tt>true</tt> if the set contains <tt>key</tt> and
     *     <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     * Time complexity is O(1)
     */
    public boolean contains(final String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return false;
        }
        return x.isString;
    }
    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(1)
     * @param x [description]
     * @param key [description]
     * @param d [description]
     * @return [description]
     */
    private Node get(final Node x, final String key, final int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = Character.toUpperCase(key.charAt(d));
        return get(x.next[c - 'A'], key, d + 1);
    }

    /**
     * Adds the key to the set if it is not already present.
     * @param key the key to add
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     * Time complexity is O(N)
     */
    public void add(final String key) {
        root = add(root, key, 0);
    }
    /**.
     * { function_description }
     *
     * @param      n     { parameter_description }
     * @param      key   The key
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node add(final Node n, final String key, final int d) {

        Node x = n;
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (!x.isString) {
                size++;
            }
            x.isString = true;
        } else {
            char c = Character.toUpperCase(key.charAt(d));
            x.next[c - 'A'] = add(x.next[c - 'A'], key, d + 1);
        }
        return x;
    }

    /**
     * Returns the number of strings in the set.
     * @return the number of strings in the set
     * Time complexity is O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Is the set empty?
     * @return <tt>true</tt> if the set is empty,
     * and <tt>false</tt> otherwise
     * Time complexity is O(1)
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }
    /**.
     * { function_description }
     *
     * @param      prefix  The prefix
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keysWithPrefix(final String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }
    /**.
     * { function_description }
     *
     * @param      x        { parameter_description }
     * @param      prefix   The prefix
     * @param      results  The results
     */
    private void collect(final Node x, final StringBuilder prefix,
                         final Queue<String> results) {
        if (x == null) {
            return;
        }
        if (x.isString) {
            results.enqueue(prefix.toString());
        }
        for (char c = 'A'; c < 'A' + R; c++) {
            prefix.append(c);
            collect(x.next[c - 'A'], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
    /**.
     * { function_description }
     *
     * @param      pattern  The pattern
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> keysThatMatch(final String pattern) {
        Queue<String> results = new Queue<String>();
        StringBuilder prefix = new StringBuilder();
        collect(root, prefix, pattern, results);
        return results;
    }
    /**.
     * { function_description }
     *
     * @param      x        { parameter_description }
     * @param      prefix   The prefix
     * @param      pattern  The pattern
     * @param      results  The results
     */
    private void collect(final Node x, final StringBuilder prefix,
                         final String pattern, final Queue<String> results) {
        if (x == null) {
            return;
        }
        int d = prefix.length();
        if (d == pattern.length() && x.isString) {
            results.enqueue(prefix.toString());
        }
        if (d == pattern.length()) {
            return;
        }
        char c = Character.toUpperCase(pattern.charAt(d));
        if (c == '.') {
            for (char ch = 'A'; ch < 'A' + R; ch++) {
                prefix.append(ch);
                collect(x.next[ch - 'A'], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c - 'A'], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

   /**.
    * { function_description }
    *
    * @param      query  The query
    *
    * @return     { description_of_the_return_value }
    */
    public String longestPrefixOf(final String query) {
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) {
            return null;
        }
        return query.substring(0, length);
    }
    /**.
     * { function_description }
     *
     * @param      x      { parameter_description }
     * @param      query  The query
     * @param      d      { parameter_description }
     * @param      len    The length
     *
     * @return     { description_of_the_return_value }
     */
    private int longestPrefixOf(final Node x, final String query,
     final int d, final int len) {
        int length = len;
        if (x == null) {
            return length;
        }
        if (x.isString) {
            length = d;
        }
        if (d == query.length()) {
            return length;
        }
        char c = Character.toUpperCase(query.charAt(d));
        return longestPrefixOf(x.next[c - 'A'], query, d + 1, length);
    }

    /**.
     * Removes the key from the set if the key is present.
     * @param key the key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     * Time complexity is O(N)
     */
    public void delete(final String key) {
        root = delete(root, key, 0);
    }
    /**.
     * { function_description }
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private Node delete(final Node x, final String key, final int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            if (x.isString) {
                size--;
            }
            x.isString = false;
        } else {
            char c = key.charAt(d);
            x.next[c - 'A'] = delete(x.next[c - 'A'], key, d + 1);
        }
        if (x.isString) {
            return x;
        }
        for (int c = 'A'; c < 'A' + R; c++) {
            if (x.next[c - 'A'] != null) {
                return x;
            }
        }
        return null;
    }
    /**.
     * Determines if it has prefix.
     *
     * @param      query  The query
     *
     * @return     True if has prefix, False otherwise.
     */
    public boolean hasPrefix(final String query) {
        Node x = get(root, query, 0);
        return x != null;
    }
}
