package application;

public class BasePlusCommissionEmployee extends CommissionEmployee {
	
	private double baseSalary;
	
	public BasePlusCommissionEmployee(String firstName, String lastName, String SSN, double grossSales,
			double commissionRate, double baseSalary) {
		super(firstName, lastName, SSN, grossSales, commissionRate);
		
		if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary must be >= 0");
        }
        this.baseSalary = baseSalary;
	}

	public double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(double baseSalary) {
		if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary must be >= 0");
        }
        this.baseSalary = baseSalary;
	}
	
	@Override
    public double getPaymentAmount() {
        return (super.getCommissionRate() * super.getGrossSales()) + baseSalary;
    }

    @Override
    public String toString() {
        return String.format("Base salaried commission employee: %s %s"
        		+ "\nSocial security number: %s"
        		+ "\nGross sales: %.2f"
        		+ "\nCommission rate: %.2f"
        		+ "\nBase salary: %.2f",
                getFirstName(), getLastName(), getSSN(), getGrossSales(), getCommissionRate(), getBaseSalary());
    }
}
