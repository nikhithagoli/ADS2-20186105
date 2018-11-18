import java.util.Arrays;
import java.util.ArrayList;
public class WordNet {
    private int vertices;
    private LinearProbingHashST <String, ArrayList<Integer>> strings;
    private LinearProbingHashST <Integer, String> ids;
    private Digraph graph;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
    	strings = new LinearProbingHashST<String, ArrayList<Integer>>();
    	ids = new LinearProbingHashST<Integer, String>();
    	try {
    		In input = new In("./Files/" + synsets);
    		int id = 0;
    		while (!input.isEmpty()) {
    			String[] tokens = input.readLine().split(",");
    			id = Integer.parseInt(tokens[0]);
    			String[] words = tokens[1].split(" ");
    			ArrayList<String> wordList = new ArrayList<String>();
    			for (String noun : words) {
    				wordList.add(noun);
    			}
    			ids.put(id, tokens[1]);
    			for (String noun : words) {
    				if (strings.contains(noun)) {
    					strings.get(noun).add(id);
    				} else {
    					ArrayList<Integer> list = new ArrayList<Integer>();
    					list.add(id);
    					strings.put(noun, list);
    				}
    			}
    		}
    		this.graph = new Digraph(id + 1);
    		input = new In("./Files/" + hypernyms);
    		while (!input.isEmpty()) {
    			String[] tokens = input.readLine().split(",");
    			int synsetid = Integer.parseInt(tokens[0]);
    			for (int i = 1; i < tokens.length; i++) {
    				graph.addEdge(synsetid, Integer.parseInt(tokens[i]));
    			}
    		}
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	sap = new SAP(graph);
    }

//     // returns all WordNet nouns
    public void display() {
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
	return strings.keys();

}

//     // is the word a WordNet noun?
public boolean isNoun(String word) {
	return strings.contains(word);

}

//     // distance between nounA and nounB (defined below)
public int distance(String nounA, String nounB) {
	if (!isNoun(nounA) || !isNoun(nounB)) {
		throw new IllegalArgumentException();
	}
	ArrayList<Integer> idA = strings.get(nounA);
	ArrayList<Integer> idB = strings.get(nounB);
	return sap.length(idA, idB);
}

//     // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
//     // in a shortest ancestral path (defined below)
public String sap(String nounA, String nounB) {
	if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> idA = strings.get(nounA);
        ArrayList<Integer> idB = strings.get(nounB);

        int ancestor = sap.ancestor(idA, idB);
        return ids.get(ancestor);
    }
}
