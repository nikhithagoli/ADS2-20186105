import java.util.Scanner;
/**
 * class for Solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * main.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        //Your code goes here...
         TST tst = new TST();
        for (int i = 0; i < words.length; i++) {
            int n = words[i].length();
            // string array of all the suffixes of the given word.
            String[] suffixes = new String[n];
            for (int j = 0; j < n; j++) {
                suffixes[j] = words[i].substring(j, n);
                tst.put(suffixes[j], 0);

            }

        }
        Scanner sc = new Scanner(System.in);
        // iterablr String of all the keys with prefix as given word.
        Iterable<String> st = tst.keysWithPrefix(sc.nextLine());

        for (String s : st) {
            System.out.println(s.toString());
        }
    }
    /**
     * Loads words.
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
