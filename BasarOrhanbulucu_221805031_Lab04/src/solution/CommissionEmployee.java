package solution;

public class CommissionEmployee {
	
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
	private double grossSales;
	private double commissionRate;
	
	public CommissionEmployee(String fN, String lN, String SSN, double gS, double cR) {
		this.firstName = fN;
		this.lastName = lN;
		this.socialSecurityNumber = SSN;
		if (gS >= 0) {
			this.grossSales = gS;
		}
		else {
			throw new IllegalArgumentException("The grossSales value cannot be less than 0!");
		}
		if (0 < cR && cR < 1) {
			this.commissionRate = cR;
		}
		else {
			throw new IllegalArgumentException("The commisionRate value must be greater than 0 and less than 1!");
		}
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String fN) {
		this.firstName = fN;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lN) {
		this.lastName = lN;
	}
	
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(String SSN) {
		this.socialSecurityNumber = SSN;
	}
	
	public double getGrossSales() {
		return grossSales;
	}
	public void setGrossSales(double gS) {
		if (gS >= 0) {
			this.grossSales = gS;
		}
		else {
			throw new IllegalArgumentException("The grossSales value cannot be less than 0!");
		}
	}
	
	public double getCommisionRate() {
		return commissionRate;
	}
	public void setCommisionRate(double cR) {
		if (0 < cR && cR < 1) {
			this.commissionRate = cR;
		}
		else {
			throw new IllegalArgumentException("The commisionRate value must be between 0 and 1!");
		}
	}
	
	public double earnings() {
		return (commissionRate * grossSales);
	}
	
	public String toString() {
		return ("First Name:	" + firstName
				+ "\nLast Name:	" + lastName
				+ "\nSocial Security Number:	" + socialSecurityNumber
				+ "\nGross Sales:	" + grossSales
				+ "\nCommission Rate:	" + commissionRate
				+ "\nEarnings:	" + earnings());
	}
}
