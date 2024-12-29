package solution;

public class BasePlusCommisionEmployee extends CommissionEmployee {
	
	private double baseSalary;

	public BasePlusCommisionEmployee(String fN, String lN, String SSN, double gS, double cR, double bS) {
		super(fN, lN, SSN, gS, cR);
		
		if (bS >= 0) {
			this.baseSalary = bS;
		}
		else {
			throw new IllegalArgumentException("The baseSalary value cannot be less than 0!");
		}
	}
	
	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double bS) {
		if (bS >= 0) {
			this.baseSalary = bS;
		}
		else {
			throw new IllegalArgumentException("The baseSalary value cannot be less than 0!");
		}
	}
	
	@Override
	public double earnings() {
		return super.earnings() + baseSalary;
	}
	
	@Override
	public String toString() {
		return ("First Name:	" + getFirstName()
				+ "\nLast Name:	" + getLastName()
				+ "\nSocial Security Number:	" + getSocialSecurityNumber()
				+ "\nGross Sales:	" + getGrossSales()
				+ "\nCommission Rate:	" + getCommisionRate()
				+ "\nBase Salary:	" + baseSalary
				+ "\nEarnings:	" + earnings());
	}
}