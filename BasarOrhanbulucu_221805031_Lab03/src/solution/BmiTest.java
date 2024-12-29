package solution;

import java.util.Scanner;

public class BmiTest {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		int size = 3;
		
		Bmi[] bmiArray = new Bmi[size];
		
		for (int i = 0; i < size; i++) {	
			bmiArray[i] = new Bmi();
			
			System.out.println("---ENTER PERSON " + (i + 1) + "'S VALUES---");
			System.out.println("Enter name, age, weight(pound), height(inch): (as space separated)");
			
			String input = scan.nextLine();
			String[] s_input = input.split(" ");
			
			String name = (s_input[0] + " " + s_input[1]);
			int age = Integer.parseInt(s_input[2]);
			double weight = Double.parseDouble(s_input[3]);
			double height = Double.parseDouble(s_input[4]);
			
			bmiArray[i].setName(name);
			bmiArray[i].setAge(age);
			bmiArray[i].setWeight(weight);
			bmiArray[i].setHeight(height);
		}
		
		for (int i = 0; i < size; i++) {
			String name = bmiArray[i].getName();
			int age = bmiArray[i].getAge();
			double weight = bmiArray[i].getWeight();
			double height = bmiArray[i].getHeight();
			double bmi = bmiArray[i].getBMI();
			
			System.out.println("** The BMI result for " + name + " (Age: " + age + " Weight: " + weight + " Height: " + height + " BMI: " + bmi + ")is");
			System.out.println(bmiArray[i].getStatus());
		}
		
		scan.close();
	}

}
