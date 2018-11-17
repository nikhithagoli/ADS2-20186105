import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.HashMap;


public class Solution {

	// Don't modify this method.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String cases = scan.nextLine();

		switch (cases) {
		case "loadDictionary":
			// input000.txt and output000.txt
			BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
			while (scan.hasNextLine()) {
				String key = scan.nextLine();
				System.out.println(hash.get(key));
			}
			break;

		case "getAllPrefixes":
			// input001.txt and output001.txt
			T9 t9 = new T9(loadDictionary("/Files/t9.csv"));
			while (scan.hasNextLine()) {
				String prefix = scan.nextLine();
				for (String each : t9.getAllWords(prefix)) {
					System.out.println(each);
				}
			}
			break;

		case "potentialWords":
			// input002.txt and output002.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			int count = 0;
			while (scan.hasNextLine()) {
				String t9Signature = scan.nextLine();
				for (String each : t9.potentialWords(t9Signature)) {
					count++;
					System.out.println(each);
				}
			}
			if (count == 0) {
				System.out.println("No valid words found.");
			}
			break;

		case "topK":
			// input003.txt and output003.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			Bag<String> bag = new Bag<String>();
			int k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				bag.add(line);
			}
			for (String each : t9.getSuggestions(bag, k)) {
				System.out.println(each);
			}

			break;

		case "t9Signature":
			// input004.txt and output004.txt
			t9 = new T9(loadDictionary("/Files/t9.csv"));
			bag = new Bag<String>();
			k = Integer.parseInt(scan.nextLine());
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				for (String each : t9.t9(line, k)) {
					System.out.println(each);
				}
			}
			break;

		default:
			break;

		}
	}

	// Don't modify this method.
	public static String[] toReadFile(String file) {
		In in = new In(file);
		return in.readAllStrings();
	}

	public static BinarySearchST<String, Integer> loadDictionary(String file) {
		BinarySearchST<String, Integer>  st = new BinarySearchST<String, Integer>();
		// your code goes here
		String[] messages = toReadFile(file);
		for(String eachmessage: messages) {
			for(String eachword : eachmessage.split(" ")) {
				if(!st.contains(eachword.toLowerCase())) {
					st.put(eachword.toLowerCase(), 1);
				} else {
					st.put(eachword.toLowerCase(), st.get(eachword.toLowerCase())+1);
				}
			}
		}
		return st;
	}

}

class T9 {
	private TST tst;
	public T9(BinarySearchST<String, Integer> st) {
		// your code goes here
		tst = new TST();
		for(String each: st.keys()) {
			tst.put(each, st.get(each));
		}

	}

	// get all the prefixes that match with given prefix.
	public Iterable<String> getAllWords(String prefix) {
		// your code goes here
		return tst.keysWithPrefix(prefix);
	}
	 public static ArrayList<String> letterCombinations(String digits) {
	 	HashMap<Character, String> matches = new HashMap<Character, String>();
		matches.put('2', "abc");
		matches.put('3', "def");
		matches.put('4', "ghi");
		matches.put('5', "jkl");
		matches.put('6', "mno");
		matches.put('7', "pqrs");
		matches.put('8', "tuv");
		matches.put('9', "wxyz");
        ArrayList<String> res = new ArrayList<String>();
        ArrayList<String> preres = new ArrayList<String>();
        res.add("");

        for (int i = 0; i < digits.length(); i++) {
            for (String str : res) {
                String letters = matches.get(digits.charAt(i));
                for (int j = 0; j < letters.length(); j++)
                    preres.add(str + letters.charAt(j));
            }
            res = preres;
            preres = new ArrayList<String>();
        }
        return res;
    }
	public Iterable<String> potentialWords(String t9Signature) {
		// your code goes here
		Queue<String> possibilities = new Queue<>();
		for(String each: letterCombinations(t9Signature)) {
			if(tst.contains(each)) {
				possibilities.enqueue(each);
			}
		}
		return possibilities;
	}

	// return all possibilities(words), find top k with highest frequency.

	public Iterable<String> getSuggestions(Iterable<String> words, int k) {
		// your code goes here
		MaxPQ<Integer> sortedfrequencies = new MaxPQ<Integer>();
		for (String each : words) {
			sortedfrequencies.insert((Integer)tst.get(each));
		}
		TreeSet<String> suggestions = new TreeSet<String>();
		for (int i = 0; i < k; i++) {
			int value = sortedfrequencies.delMax();
			for (String word : words) {
				if (value == (Integer)tst.get(word)) {
					suggestions.add(word);
				}
			}
		}
		return suggestions;
	}

	// final output
	// Don't modify this method.
	public Iterable<String> t9(String t9Signature, int k) {
		return getSuggestions(potentialWords(t9Signature), k);
	}
}
