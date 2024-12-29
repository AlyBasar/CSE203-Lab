package application;

public class SalariedEmployee extends Employee {
	
	private double weeklySalary;
	
	public SalariedEmployee(String firstName, String lastName, String SSN, double weeklySalary) {
		super(firstName, lastName, SSN);
		
		if (weeklySalary < 0) {
            throw new IllegalArgumentException("Weekly salary must be >= 0");
        }
        this.weeklySalary = weeklySalary;
	}

	public double getWeeklySalary() {
        return weeklySalary;
    }
    public void setWeeklySalary(double weeklySalary) {
        if (weeklySalary < 0) {
            throw new IllegalArgumentException("Weekly salary must be >= 0!");
        }
        this.weeklySalary = weeklySalary;
    }
	
	@Override
	public double getPaymentAmount() {
		return getWeeklySalary();
	}
	
	@Override
    public String toString() {
        return String.format("Salaried employee: %s %s"
        		+ "\nSocial security number: %s"
        		+ "\nWeekly salary: %.2f",
                getFirstName(), getLastName(), getSSN(), getWeeklySalary());
    }
}
