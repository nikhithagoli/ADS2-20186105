import java.util.Scanner;
/**
 * class for sloution.
 */
final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {
		//unsed.
	}
	/**
	 * main.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String[] strings = new String[n];
		for(int i = 0; i < n; i++) {
			strings[i] = sc.nextLine();
		}
		LSD lsd = new LSD();
		lsd.sort(strings, strings[0].length());
	}
}