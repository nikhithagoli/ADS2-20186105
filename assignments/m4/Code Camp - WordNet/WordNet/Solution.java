/**
 * Files import.
 */
import java.io.File;
/**
 * Scanner import.
 */
import java.util.Scanner;
/**
 * Class for solution.
 */
class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //Empty Constructor.
    }
    /**
     * Main function.
     *
     * @param      args       The arguments
     *
     * @throws     Exception  { exception_description }
     */
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String synsets = sc.nextLine();
        String hypernyms = sc.nextLine();
        try {
            WordNet wrdnet = new WordNet(synsets, hypernyms);
            String input = sc.nextLine();
            if (wrdnet.gethasCycle()) {
                System.out.println("Cycle detected");
                return;
            }

            if (input.equals("Graph")) {
                wrdnet.checkMultipleRoots();
                if (wrdnet.gethasMultipleRoots()) {
                    return;
                } else {
                    System.out.println(wrdnet.getDigraph());
                }
            } 
            if (input.equals("Queries")) {
                while (sc.hasNextLine()) {
                    String[] tokens = sc.nextLine().split(" ");
                    try {
                        wrdnet.sap(tokens[0], tokens[1]);
                        System.out.println("distance = "
                            + wrdnet.distance(tokens[0], tokens[1])
                            + ", ancestor = " + wrdnet.sap(tokens[0], tokens[1]));
                    } catch (Exception e) {
                        System.out.println("IllegalArgumentException");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
