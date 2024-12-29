package solution;

import java.util.Random;
import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Random random = new Random();
		
		System.out.println("--Number Guess Game--");
		
		int number = random.nextInt(199) + 1;	//0 ve 200 aralığı
		boolean numberfound = false;
		int guess;
		int totalguess = 0;
		
		while (!numberfound) {	//eğer sayı bulunmadıysa çalışmaya devam et
			System.out.print("Enter a guess: ");
			guess = scan.nextInt();
			
			if (guess > number) {
				System.out.println("Please enter a lower number");
				totalguess++;
			}
			else if (guess < number) {
				System.out.println("Please enter a higher number");
				totalguess++;
			}
			else {
				totalguess++;
				System.out.println("Congrats you guessed the number after " + totalguess + " attempts.");
				numberfound = true;	//sayının bulunduğunu belirt
			}
		}
		scan.close();
	}

}
