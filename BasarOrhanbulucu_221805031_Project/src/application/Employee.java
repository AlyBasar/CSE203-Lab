package application;

public abstract class Employee implements IPayable {
	
	private String firstName;
	private String lastName;
	private String SSN;
	
	public Employee(String firstName, String lastName, String SSN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
    }

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	
	@Override
	public String toString() {
	    return String.format("%s %s"
	    		+ "\nSocial security number: %s",
	            getFirstName(), getLastName(), getSSN());
	}
	
	@Override
	public abstract double getPaymentAmount();
}
