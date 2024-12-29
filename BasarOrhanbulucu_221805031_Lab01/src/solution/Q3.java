package solution;

import java.util.Scanner;

public class Q3 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int totalPoint = 200;
		
		System.out.print("Enter playing number of subgame challenge level #1: ");
		totalPoint += (scan.nextInt() * 90);
		
		System.out.print("Enter playing number of subgame challenge level #2: ");
		totalPoint += (scan.nextInt() * 120);
		
		System.out.print("Enter playing number of subgame challenge level #3: ");
		totalPoint += (scan.nextInt() * 250);
		
		System.out.print("Enter playing number of subgame challenge level #4: ");
		totalPoint += (scan.nextInt() * 300);
		
		System.out.println("Player's point: " + totalPoint);
		
		scan.close();
	}
}