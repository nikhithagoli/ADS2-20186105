// import java.util.ArrayList;
// import java.util.Scanner;
// import java.io.File;
// public class WordNet {

//     private BinarySearchST<String, ArrayList<Integer>> bst;
//     private BinarySearchST<Integer, String> binst;
//     private Digraph dgh;
//     private SAP sap;
//     private boolean hasCycle = false;
//     private boolean hasMultipleroots = false;

//     public Digraph getDigraph() {
//         return this.dgh;
//     }

//     public boolean gethasCycle() {
//         return this.hasCycle;
//     }

//     public boolean gethasMultipleroots() {
//         return this.hasMultipleroots;
//     }

//     // constructor takes the name of the two input files
//     public WordNet(String syn, String hyper) throws Exception {
//         try {
//             File synsetsfile = new File("Files/" + syn);
//             Scanner sf = new Scanner(synsetsfile);
//             File hyperfile = new File("Files/" + hyper);
//             Scanner hf = new Scanner(hyperfile);
//             bst = new BinarySearchST<String, ArrayList<Integer>>();
//             binst = new BinarySearchST<Integer, String>();
//             while (sf.hasNextLine()) {
//                 String[] inputline = sf.nextLine().split(",");
//                 binst.put(Integer.parseInt(inputline[0]), inputline[1]);
//                 String[] words = inputline[1].split(" ");
//                 for (int i = 0; i < words.length; i++) {
//                     if (bst.contains(words[i])) {
//                         ArrayList<Integer> wordlist = bst.get(words[i]);
//                         wordlist.add(Integer.parseInt(inputline[0]));
//                         //bst.put(wordlist, words[i]);
//                     } else {
//                         ArrayList<Integer> list = new ArrayList<Integer>();
//                         list.add(Integer.parseInt(inputline[0]));
//                         bst.put(words[i], list);
//                     }
//                 }
//             }

//             dgh = new Digraph(bst.size());
//             while (hf.hasNextLine()) {
//                 String[] inputline = hf.nextLine().split(",");
//                 for (int i = 1; i < inputline.length; i++) {
//                     dgh.addEdge(Integer.parseInt(inputline[0]),
//                      Integer.parseInt(inputline[i]));
//                 }
//             }
//         } catch (Exception e) {
//             System.out.println(e);
//         }

//         DirectedCycle dc = new DirectedCycle(dgh);
//         if (dc.hasCycle()) {
//             hasCycle = true;
//         }
//     }

//     public void checkMultipleRoots() {
//         int roots = 0;
//         for (int i = 0; i < dgh.V(); i++) {
//             if (dgh.outdegree(i) == 0) {
//                 roots++;
//             }
//         }

//         if (roots != 1) {
//             hasMultipleroots = true;
//             System.out.println("Multiple roots");
//         }
//     }

//     // returns all WordNet nouns
//     public Iterable<String> nouns() {
//         return bst.keys();
//     }

//     // is the word a WordNet noun?
//     public boolean isNoun(String word) {
//         // for (String str : bst.keys()) {
//         //     if (str.equals(word)) {
//         //         return true;
//         //     }
//         // }
//         return true;
//     }

//     // distance between nounA and nounB (defined below)
//     public int distance(String nounA, String nounB) {
//         ArrayList id1 = bst.get(nounA);
//         ArrayList id2 = bst.get(nounB);
//         sap = new SAP(dgh);
//         return sap.length(id1, id2);
//     }
//     // a synset (second field of synsets.txt) that is
//     //     the common ancestor of nounA and nounB
//     // in a shortest ancestral path (defined below)
//     public String sap(String nounA, String nounB) {
//         ArrayList<Integer> id1 = bst.get(nounA);
//         ArrayList<Integer> id2 = bst.get(nounB);
//         sap = new SAP(dgh);
//         int ans = sap.ancestor(id1, id2);
//         return binst.get(ans);
//     }

//     // do unit testing of this class
//     // public static void main(String[] args) {
//     //     Scanner sc = new Scanner(System.in);
//     //     String synsets = sc.nextLine();
//     //     String hypernyms = sc.nextLine();
//     //     //     WordNet wrdnet = new WordNet(synsets, hypernyms);/
//     //     // }
// }


import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * hash map.
     */
    private HashMap<String, ArrayList<Integer>> h = new
    HashMap<String, ArrayList<Integer>>();
    /**
     * hashmap.
     */
    private HashMap<Integer, String> h2 = new
    HashMap<Integer, String>();
    /**
     * digraph.
     */
    private Digraph dg;
    /**
     * SAP variable.
     */
    private SAP sap;
    /**
     * has cycle variable.
     */
    private boolean hasCycle = false;
    /**
     * has multiple roots variable.
     */
    private boolean hasMultipleRoots = false;
    /**
     * Gets the digraph.
     *
     * @return     The digraph.
     */
    public Digraph getDigraph() {
        return this.dg;
    }
    /**
     * get hasCycle.
     *
     * @return   boolean.
     */
    public boolean gethasCycle() {
        return this.hasCycle;
    }
    /**
     * hasmultipleroots.
     *
     * @return  boolean.
     */
    public boolean gethasMultipleRoots() {
        return this.hasMultipleRoots;
    }
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(final String synsets, final String hypernyms) {
        try {
            File fileOne = new File(
                "Files/" + synsets);
            Scanner fOne = new Scanner(fileOne);
            File fileTwo = new File("Files/" + hypernyms);
            Scanner fTwo = new Scanner(fileTwo);
            while (fOne.hasNextLine()) {
                String[] tokens = fOne.nextLine().split(",");
                h2.put(Integer.parseInt(tokens[0]), tokens[1]);
                String[] words = tokens[1].split(" ");
                for (int i = 0; i < words.length; i++) {
                    if (h.containsKey(words[i])) {
                        ArrayList<Integer> arraylist = h.get(words[i]);
                        arraylist.add(Integer.parseInt(tokens[0]));
                    } else {
                        ArrayList<Integer> arraylist = new ArrayList<Integer>();
                        arraylist.add(Integer.parseInt(tokens[0]));
                        h.put(words[i], arraylist);
                    }
                }
            }
            dg = new Digraph(h.size());
            while (fTwo.hasNextLine()) {
                String[] tokens = fTwo.nextLine().split(",");
                for (int i = 1; i < tokens.length; i++) {
                    dg.addEdge(Integer.parseInt(tokens[0]),
                               Integer.parseInt(tokens[i]));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        DirectedCycle dc = new DirectedCycle(dg);
        if (dc.hasCycle()) {
            hasCycle = true;
        }
    }
    /**
     * check multiple roots method.
     */
    public void checkMultipleRoots() {
        int roots = 0;
        for (int i = 0; i < dg.V(); i++) {
            if (dg.outdegree(i) == 0) {
                roots++;
            }
        }
        if (roots != 1) {
            hasMultipleRoots = true;
            System.out.println("Multiple roots");
        }
    }
    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        // for (String s : h.getKeys()) {
        //     if (s.equals(word)) {
        //         return true;
        //     }
        // }
        return true;
    }
    /**
     * distance.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return distance between nouns.
     */
    public int distance(final String nounA, final String nounB) {
        ArrayList id1 = h.get(nounA);
        ArrayList id2 = h.get(nounB);
        sap = new SAP(dg);
        return sap.length(id1, id2);
    }
    /**
     * sap method.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return string.
     */
    public String sap(final String nounA, final String nounB) {
        ArrayList<Integer> id1 = h.get(nounA);
        ArrayList<Integer> id2 = h.get(nounB);
        sap = new SAP(dg);
        // System.out.println(id1);
        // System.out.println(id2);
        int ans = sap.ancestor(id1, id2);
        return h2.get(ans);
    }
}
