import java.util.Scanner;
class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String synsetsfile = sc.nextLine();
		String hypernymsfile = sc.nextLine();
		String mode =sc.nextLine();
		try {
			if (mode.equals("Graph")) {
				WordNet wordnet = new WordNet(synsetsfile, hypernymsfile);
				wordnet.print();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			if (mode.equals("Queries")) {
				WordNet wordnet = new WordNet(synsetsfile, hypernymsfile);
				while (sc.hasNextLine()) {
					String[] input = sc.nextLine().split(" ");
					if(input[0].equals("null") || input[1].equals("null")) {
						throw new IllegalArgumentException("IllegalArgumentException");
					}
					System.out.println("distance = " + wordnet.distance(input[0], input[1]) + ", ancestor = " + wordnet.sap(input[0], input[1]));
				}
			}
		} catch (Exception e) {
		                System.out.println(e.getMessage());
	    }
	}
}