package application;

public class CommissionEmployee extends Employee {
	
	private double grossSales;
    private double commissionRate;
    
	public CommissionEmployee(String firstName, String lastName, String SSN, double grossSales, double commissionRate) {
		super(firstName, lastName, SSN);
		
		if (grossSales < 0) {
            throw new IllegalArgumentException("Gross sales must be >= 0!");
        }
		this.grossSales = grossSales;
		
        if (commissionRate <= 0 || commissionRate >= 1) {
            throw new IllegalArgumentException("Commission rate must be > 0 and < 1!");
        }
        this.commissionRate = commissionRate;
	}

	public double getGrossSales() {
		return grossSales;
	}
	public void setGrossSales(double grossSales) {
		if (grossSales < 0) {
            throw new IllegalArgumentException("Gross sales must be >= 0");
        }
		this.grossSales = grossSales;
	}

	public double getCommissionRate() {
		return commissionRate;
	}
	public void setCommissionRate(double commissionRate) {
		if (commissionRate <= 0 || commissionRate >= 1) {
            throw new IllegalArgumentException("Commission rate must be > 0 and < 1");
        }
        this.commissionRate = commissionRate;
	}

	@Override
    public double getPaymentAmount() {
        return grossSales * commissionRate;
    }
	
	@Override
    public String toString() {
        return String.format("Commission employee: %s %s"
        		+ "\nSocial security number: %s"
        		+ "\nGross sales: %.2f"
        		+ "\nCommission rate: %.2f",
                getFirstName(), getLastName(), getSSN(), getGrossSales(), getCommissionRate());
    }

}
