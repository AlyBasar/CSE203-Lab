package solution;

import java.util.Scanner;

public class Q2 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String input = "";
		
		while (!input.equalsIgnoreCase("q")) {	//q girilmediği sürece çalıştır
			
			System.out.print("Enter initial star number(enter \"q\" for quit): ");
			input = scan.nextLine();
			
			if (!input.equalsIgnoreCase("q")) {
				
				int number = Integer.parseInt(input);	//girilen string'i int'e dönüştür
				
				if ((number % 2) == 0) {	//çift ise
					
					for (int i = 0; i < (number / 2); i ++) {	//üst üçgen satırı
						for (int b = 0; b < i; b++) {	//boşluk döngüsü
							System.out.print(" ");
						}
						for (int y = 0; y < (number - (2*i)); y++) {	//yıldız döngüsü
							System.out.print("*");
						}
						System.out.println();
					}
					for (int i = 0; i < (number / 2); i ++) {	//alt üçgen satırı
						for (int b = 0; b < ((number / 2) - i - 1); b++) {
							System.out.print(" ");
						}
						for (int y = 0; y < ((i * 2) + 2); y++) {	
							System.out.print("*");
						}
						System.out.println();
					}
				}
				else {	//tek ise
					for (int i = 0; i < (number / 2) + 1; i ++) {	//üst üçgen satırı
						for (int b = 0; b < i; b++) {	//boşluk döngüsü
							System.out.print(" ");
						}
						for (int y = 0; y < (number - (2*i)); y++) {	//yıldız döngüsü
							System.out.print("*");
						}
						System.out.println();
					}
					for (int i = 0; i < (number / 2); i ++) {	//alt üçgen satırı
						for (int b = 0; b < (number / 2) - i - 1; b++) {	//boşluk döngüsü
							System.out.print(" ");
						}
						for (int y = 0; y < ((2 * i) + 3); y++) {	//yıldız döngüsü
							System.out.print("*");
						}
						System.out.println();
					}
					
				}
			}
			else {
				System.out.print("Program Terminates.");
			}
		}
		scan.close();
	}
}