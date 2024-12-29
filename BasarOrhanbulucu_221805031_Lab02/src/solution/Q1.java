package solution;

import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean[] seats = {false, false, false, false, false, false, false, false, false, false};
		
		while(true) {
			System.out.println("Please type 1 for Buying First Class Ticket");
			System.out.println("Please type 2 for Buying Economy Class Ticket");
			System.out.println("Please type 3 for Ticket Validation");
			
			System.out.print("Choice: ");
			int choice = scan.nextInt();
			
			switch (choice) {
			case 1:
				if (isPlaneFull(seats)) {
					System.out.println("Plane is full. Next flight leaves in 3 hours.\n");
					break;
				}
				
				firstClassTicketBuy(seats);
				break;
				
			case 2:
				if (isPlaneFull(seats)) {
					System.out.println("Plane is full. Next flight leaves in 3 hours.\n");
					break;
				}
				
				economyClassTicketBuy(seats);
				break;
				
			case 3:
				System.out.print("\nEnter seat ID which be validated: ");
				int id = scan.nextInt();
				
				if (0 < id && id < 11) {
					validateTicketBySeatId(id, seats);
				}
				else {
					System.out.println("There is no seat with this ID.\n");
				}
				break;
				
			default:
				System.out.println("This choice is not valid!\n");
			}
		}
		
	}
	
	public static void firstClassTicketBuy(boolean[] seats_a) {
		Scanner scan = new Scanner(System.in);
		
		if (!seats_a[0]) {
			System.out.println("First Class Ticket is bought. Seat #1\n");
			seats_a[0] = true;
		}
		else if (!seats_a[1]) {
			System.out.println("First Class Ticket is bought. Seat #2\n");
			seats_a[1] = true;
		}
		else if (!seats_a[2]) {
			System.out.println("First Class Ticket is bought. Seat #3\n");
			seats_a[2] = true;
		}
		else if (!seats_a[3]) {
			System.out.println("First Class Ticket is bought. Seat #4\n");
			seats_a[3] = true;
		}
		else if (!seats_a[4]) {
			System.out.println("First Class Ticket is bought. Seat #5\n");
			seats_a[4] = true;
		}
		else {
			System.out.print("\nFirst Class is full, Do you prefer Economy Class?\n1. Yes, 2. No. Your Choice: ");
			int choice2 = scan.nextInt();
			
			switch (choice2) {
			case 1:
				economyClassTicketBuy(seats_a);
				break;
				
			case 2:
				System.out.println("Next flight leaves in 3 hours.\n");
				break;
				
			default:
				System.out.println("This choice is not valid!\n");
			}
		}
		scan.close();
	}
	
	public static void economyClassTicketBuy(boolean[] seats_a) {
		Scanner scan = new Scanner(System.in);
		
		if (!seats_a[5]) {
			System.out.println("Economy Class Ticket is bought. Seat #6\n");
			seats_a[5] = true;
		}
		else if (!seats_a[6]) {
			System.out.println("Economy Class Ticket is bought. Seat #7\n");
			seats_a[6] = true;
		}
		else if (!seats_a[7]) {
			System.out.println("Economy Class Ticket is bought. Seat #8\n");
			seats_a[7] = true;
		}
		else if (!seats_a[8]) {
			System.out.println("Economy Class Ticket is bought. Seat #9\n");
			seats_a[8] = true;
		}
		else if (!seats_a[9]) {
			System.out.println("Economy Class Ticket is bought. Seat #10\n");
			seats_a[9] = true;
		}
		else {
			System.out.print("\nEconomy Class is full, Do you prefer First Class?\n1. Yes, 2. No. Your Choice: ");
			int choice2 = scan.nextInt();
			
			switch (choice2) {
			case 1:
				firstClassTicketBuy(seats_a);
				break;
				
			case 2:
				System.out.println("Next flight leaves in 3 hours.\n");
				break;
				
			default:
				System.out.println("This choice is not valid!\n");
			}
		}
		scan.close();
	}
	
	public static void validateTicketBySeatId(int seatID, boolean[] seats_a) {
		if (seats_a[seatID - 1]) {
			System.out.println("Ticket is sold.\n");
		}
		else {
			System.out.println("Ticket is not sold.\n");
		}
	}
	
	public static boolean isPlaneFull(boolean[] seats_a) {
		
		if (seats_a[0] && seats_a[1] && seats_a[2] && seats_a[3] && seats_a[4] && seats_a[5] && seats_a[6] && seats_a[7] && seats_a[8] && seats_a[9]) {
			return true;
		}
		else {
			return false;
		}
	}
	

}
