import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class WordNet {
    // Bag<String>[] synsets;
    HashMap<Integer, ArrayList<String>> synsets;
    int size;
    // constructor takes the name of the two input files
    public WordNet(String s, String h) {
        In syn = new In("E:\\basic\\ADS2-20186105\\ADS2-20186105\\assignments\\m4\\Code Camp - WordNet\\WordNet\\Files\\" + s);
        size = 0;
        while(syn.hasNextLine()) {
            String line = syn.readLine();
            size++;
        }
        // synsets = (Bag<String>[]) new Bag[size];
        synsets = new HashMap<Integer, ArrayList<String>>();
        syn = new In("E:\\basic\\ADS2-20186105\\ADS2-20186105\\assignments\\m4\\Code Camp - WordNet\\WordNet\\Files\\" + s);
        while(syn.hasNextLine()) {
            String line = syn.readLine();
            String[] tokens = line.split(",");
            String[] words = tokens[1].split(" ");
            ArrayList<String> l = new ArrayList<String>(Arrays.asList(words));
            // for(int i = 0; i < words.length; i++) {
                // synsets.put(Integer.parseInt(tokens[0]), );
            // }
            synsets.put(Integer.parseInt(tokens[0]), l);
        }
        In hype = new In("E:\\basic\\ADS2-20186105\\ADS2-20186105\\assignments\\m4\\Code Camp - WordNet\\WordNet\\Files\\" + h);
        Digraph graph = new Digraph(size);
        while(hype.hasNextLine()) {
            String line = hype.readLine();
            String[] tokens = line.split(",");
            for(int i = 1; i < tokens.length; i++) {
                graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
        System.out.println(graph);  
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return (Iterable<String>) synsets;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        /*for(Bag bag: synsets) {
            for(String each: bag.iterator()) {
                if(each.equals(word)) {
                    return true;
                }
            }
        }
        return false;*/
        return synsets.containsValue(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        return 0;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return null;
    }
}
