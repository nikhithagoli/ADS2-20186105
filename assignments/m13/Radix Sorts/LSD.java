import java.util.Arrays;
/**
 * Class for lsd.
 */
class Lsd {
    /**
     * bits.
     */
    private static final int BITS_PER_BYTE = 8;
    /**
     * Constructs the object.
     */
    protected Lsd() {

    }
    /**
      * Rearranges the array of W-character strings in ascending order.
      *
      * @param a the array to be sorted
      * @param w the number of characters per string
      */
    public static void sort(final String[] a, final int w) {
        int n = a.length;
        final int radix = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[radix + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < radix; r++) {
                count[r + 1] += count[r];
            }

            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
