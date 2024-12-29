package solution;

public class Bmi {
	
	private String name;
	private int age;
	private double weight;	//	pound cinsinden kilo
	private double height;	//	inch cinsinden uzunluk
	
	public static final double KILOGRAMS_PER_POUND = 0.45359237;	//	1 pound'un kaç kg olduğunun sabiti
	public static final double METERS_PER_INCH = 0.0254;	//	1 inch'in kaç m olduğunun sabiti
	
	public Bmi() {	//	No argument constructor
		name = "John Black";
		age = 25;
		weight = 100;
		height = 50;
	}
	
	public Bmi(String i_name, double i_weight, double i_height) {
		name = i_name;
		age = 20;
		weight = i_weight;
		height = i_height;
	}
	
	public Bmi(String i_name, int i_age, double i_weight, double i_height) {
		name = i_name;
		age = i_age;
		weight = i_weight;
		height = i_height;
	}
	
	public void setName(String i_name) {
		name = i_name;
	}
	public void setAge(int i_age) {
		age = i_age;
	}
	public void setWeight(double i_weight) {
		weight = i_weight;
	}
	public void setHeight(double i_height) {
		height = i_height;
	}
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public double getWeight() {
		return weight;
	}
	public double getHeight() {
		return height;
	}
	
	public double getBMI() {
		double weightinkg = weight * KILOGRAMS_PER_POUND;	//	kg ye çevir
		double heightinm = height * METERS_PER_INCH;	//	m ye çevir
		
		double bmi = Math.round((weightinkg  / (heightinm * heightinm)) * 10) / 10.0;//1 basamak için10 ile çarpıp 10.0 a böldük
		return bmi;
	}
	
	public String getStatus() {
		double bmi = getBMI();
		
		if (bmi > 30.0) {
			return "Obese";
		}
		else if (bmi >= 25.0) {
			return "Overweight";
		}
		else if (bmi >= 18.5) {
			return "Normal";
		}
		else {
			return "Underweight";
		}
	}
}
