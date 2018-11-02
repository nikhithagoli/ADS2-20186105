import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class WordNet {
    HashMap<Integer, ArrayList<String>> synsets;
    int size;
    Digraph graph;
    DirectedCycle dc;
    public WordNet(String s, String h) {
        try {
            In syn = new In("E:\\basic\\ADS2-20186105\\ADS2-20186105\\assignments\\m4\\Code Camp - WordNet\\WordNet\\Files\\" + s);
            size = 0;
            while (syn.hasNextLine()) {
                String line = syn.readLine();
                size++;
            }
            synsets = new HashMap<Integer, ArrayList<String>>();
            syn = new In("E:\\basic\\ADS2-20186105\\ADS2-20186105\\assignments\\m4\\Code Camp - WordNet\\WordNet\\Files\\" + s);
            while (syn.hasNextLine()) {
                String line = syn.readLine();
                String[] tokens = line.split(",");
                String[] words = tokens[1].split(" ");
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
            int count = 0;
            for (int i = 0; i < size; i++) {
                if(graph.outdegree(i) == 0) {
                    count++;
                } 
            }
            if(count >  1) {
                throw new IllegalArgumentException("Multiple roots");
            }
            if (dc.hasCycle()) {
                throw new IllegalArgumentException("Cycle detected");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void print() {
        System.out.println(graph);
    } 
    public Iterable<String> nouns() {
        return (Iterable<String>) synsets;
    }
    public boolean isNoun(String word) {
        return synsets.containsValue(word);
    }
    public int distance(String nounA, String nounB) {
        return 0;
    }
    public String sap(String nounA, String nounB) {
        return null;
    }
}
