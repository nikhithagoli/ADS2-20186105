import java.util.HashMap;
import java.util.ArrayList;
public class WordNet {
	private LinearProbingHashST<Integer, String> synsets;
    private LinearProbingHashST<String, ArrayList<Integer>> ids;
    private Digraph graph;
    private SAP sap;
    public WordNet(String s, String h) {
        In syn = new In("Files//" + s);
        synsets = new LinearProbingHashST<Integer, String>();
        ids = new LinearProbingHashST<String, ArrayList<Integer>>(); 
        syn = new In("Files\\" + s);
        int id = 0;
        while (syn.hasNextLine()) {
            String[] tokens = syn.readLine().split(",");
            id = Integer.parseInt(tokens[0]);
            String[] words = tokens[1].split(" ");
            for(String each: words) {
                if(ids.contains(each)) {
                    ids.get(each).add(id);
                } else {
                    ArrayList<Integer> a = new ArrayList<Integer>();
                    a.add(id);
                    ids.put(each, a);
                }
            }
            synsets.put(Integer.parseInt(tokens[0]), tokens[1]);
        }
        In hype = new In("Files\\" + h);
        graph = new Digraph(id + 1);
        while (hype.hasNextLine()) {
            String[] tokens = hype.readLine().split(",");
            for (int i = 1; i < tokens.length; i++) {
                graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
        sap = new SAP(graph);
    }
    public void print() {
    	DirectedCycle dc = new DirectedCycle(graph);
        if (dc.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else if (graph.noOfOutdegree() > 1) {
            throw new IllegalArgumentException("Multiple roots");
        } else {
            System.out.println(graph.toString());
        }
    }
    public Iterable<String> nouns() {
        return ids.keys();
    }
    public boolean isNoun(String word) {
        return ids.contains(word);
    }
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idA = ids.get(nounA);
        ArrayList<Integer> idB = ids.get(nounB);
        return sap.length(idA, idB);
    }
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idA = ids.get(nounA);
        ArrayList<Integer> idB = ids.get(nounB);
        int ancestor = sap.ancestor(idA, idB);
        return synsets.get(ancestor);
    }
}
