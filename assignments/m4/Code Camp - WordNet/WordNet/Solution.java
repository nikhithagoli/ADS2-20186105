/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    protected Solution() {

    }
    /**
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String synsetfile = StdIn.readLine();
        String hypersetfile = StdIn.readLine();
        String token = StdIn.readLine();
        try {
            if (token.equals("Graph")) {
                WordNet wn = new WordNet(synsetfile, hypersetfile);
                wn.display();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            if (token.equals("Queries")) {
                WordNet wn = new WordNet(synsetfile, hypersetfile);
                while (StdIn.hasNextLine()) {
                    String[] tokens = StdIn.readLine().
                                    split(" ");
                    if (tokens[0].equals("null")) {
                    throw new IllegalArgumentException(
                            "IllegalArgumentException");
                    }
                    System.out.println("distance = "
                        + wn.distance(tokens[0], tokens[1])
                        + ", ancestor = " + wn.sap(tokens[0],
                    tokens[1]));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
