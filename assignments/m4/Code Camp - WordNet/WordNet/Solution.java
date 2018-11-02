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
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}