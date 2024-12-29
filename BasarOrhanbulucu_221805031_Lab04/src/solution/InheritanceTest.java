package solution;

public class InheritanceTest {
	
	public static void main(String[] args) {
		try {
			BasePlusCommisionEmployee employee1 = new BasePlusCommisionEmployee(
					"Başar", "Orhanbulucu", "22-180-50-31", 5000, 0.04, 300);
			
			System.out.println("Employee information obtained by get methods and earnings:\n");
			System.out.println("First Name:	" + employee1.getFirstName()
								+ "\nLast Name:	" + employee1.getLastName()
								+ "\nSocial Security Number:	" + employee1.getSocialSecurityNumber()
								+ "\nGross Sales:	" + employee1.getGrossSales()
								+ "\nCommission Rate:	" + employee1.getCommisionRate()
								+ "\nEarnings:	" + employee1.earnings());
			
			System.out.println("\nUpdated employee information obtained by toString and earnings:\n");
			
			try {
				employee1.setBaseSalary(1000);
			} catch (IllegalArgumentException e) {
				System.out.println("Error:" + e.getMessage());
			}
			System.out.println(employee1);	//toString()
		} catch (IllegalArgumentException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
}
