package application;

public class HourlyEmployee extends Employee {
	
	private double wage;
	private double hours;
	
	public HourlyEmployee(String firstName, String lastName, String SSN, double wage, double hours) {
		super(firstName, lastName, SSN);
		
		if (wage < 0) {
            throw new IllegalArgumentException("Wage must be >= 0!");
        }
		this.wage = wage;
		
        if (hours < 0 || hours >= 168) {
            throw new IllegalArgumentException("Hours must be >= 0 and < 168!");
        }
        this.hours = hours;
	}

	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		if (wage < 0) {
            throw new IllegalArgumentException("Wage must be >= 0");
        }
		this.wage = wage;
	}

	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		if (hours < 0 || hours >= 168) {
            throw new IllegalArgumentException("Hours must be >= 0 and < 168");
        }
        this.hours = hours;
	}

	@Override
	public double getPaymentAmount() {
		if (hours <= 40) {
			return (wage * hours);
		}
		else if (hours > 40) {
			return ((40 * wage) + ((hours - 40) * wage * 1.5));
		}
		return 0;
	}
	
	@Override
    public String toString() {
        return String.format("Hourly employee: %s %s"
        		+ "\nSocial security number: %s"
        		+ "\nHourly wage: %.2f; Hours worked: %.2f",
                getFirstName(), getLastName(), getSSN(), getWage(), getHours());
    }
}
