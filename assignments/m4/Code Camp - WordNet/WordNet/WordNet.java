import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class WordNet {
    HashMap<Integer, ArrayList<String>> synsets;
    HashMap<String, ArrayList<Integer>> ids;
    int size;
    Digraph graph;
    DirectedCycle dc;
    int outdegreecount;
    SAP sap;
    public WordNet(String s, String h) {
        In syn = new In("E:\\basic\\ADS2-20186105\\ADS2-20186105\\assignments\\m4\\Code Camp - WordNet\\WordNet\\Files\\" + s);
        size = 0;
        while (syn.hasNextLine()) {
            String line = syn.readLine();
            size++;
        }
        synsets = new HashMap<Integer, ArrayList<String>>();
        ids = new HashMap<String, ArrayList<Integer>>(); 
        syn = new In("E:\\basic\\ADS2-20186105\\ADS2-20186105\\assignments\\m4\\Code Camp - WordNet\\WordNet\\Files\\" + s);
        while (syn.hasNextLine()) {
            String line = syn.readLine();
            String[] tokens = line.split(",");
            String[] words = tokens[1].split(" ");
            for(String each: words) {
                if(ids.containsKey(each)) {
                    ArrayList<Integer> a = ids.get(each);
                    a.add(Integer.parseInt(tokens[0]));
                    ids.put(each, a);
                } else {
                    ArrayList<Integer> a = new ArrayList<Integer>();
                    a.add(Integer.parseInt(tokens[0]));
                    ids.put(each, a);
                }
            }
            ArrayList<String> l = new ArrayList<String>(Arrays.asList(words));
            synsets.put(Integer.parseInt(tokens[0]), l);
        }
        In hype = new In("E:\\basic\\ADS2-20186105\\ADS2-20186105\\assignments\\m4\\Code Camp - WordNet\\WordNet\\Files\\" + h);
        graph = new Digraph(size);
        while (hype.hasNextLine()) {
            String line = hype.readLine();
            String[] tokens = line.split(",");
            for (int i = 1; i < tokens.length; i++) {
                graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
        dc = new DirectedCycle(graph);
        for (int i = 0; i < size; i++) {
            if (graph.outdegree(i) == 0) {
                outdegreecount++;
            }
        }
        sap = new SAP(graph);
    }
    public void print() {
        if (dc.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else if (outdegreecount > 1) {
            throw new IllegalArgumentException("Multiple roots");
        } else {
            System.out.println(graph.toString());
        }
    }
    public Iterable<String> nouns() {
        return (Iterable<String>) ids.keySet();
    }
    public boolean isNoun(String word) {
        return ids.containsKey(word);
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
        return String.join(" ", synsets.get(ancestor));
    }
}
