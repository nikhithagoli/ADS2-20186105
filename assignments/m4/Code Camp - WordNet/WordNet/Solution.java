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
		if (mode.equals("Queries")) {
				while(sc.hasNextLine()) {
					String[] input = sc.nextLine().split(" ");
					if(input[0].equals("null") || input[1].equals("null")) {
						System.out.println("IllegalArgumentException");
						return;
					}
				}
				WordNet wordnet = new WordNet(synsetsfile, hypernymsfile);
		}
	}
}