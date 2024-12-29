package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	static ArrayList<Employee> employeeArray = new ArrayList<>();
	
	public static void main(String[] args) throws FileNotFoundException {
		readDatabase(employeeArray);	//	dosyadan verileri çek
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Bileşenler
		Label lblchooseEmployeeType = new Label("Choose Employe Type");
		ComboBox<String> cmbchooseEmployeeType = new ComboBox<>();
		cmbchooseEmployeeType.getItems().addAll("Salaried Employee", "Hourly Employee", 
				"Commission Employee", "Base Plus Commission Employee", "None");

		Label lblfirstName = new Label("First Name");
		TextField txtfirstName = new TextField();
		
		Label lbllastName = new Label("Last Name");
		TextField txtlastName = new TextField();
		
		Label lblSSN = new Label("SSN");
		TextField txtSSN = new TextField();
		txtSSN.setDisable(true);	//	yazmaya kapalı
		txtSSN.setOpacity(0.2);		//	opaklığı düşük
		
		Label lblsearchUpdateSSN = new Label("Search/Update SSN");
		TextField txtsearchUpdateSSN = new TextField();
		
		Label lblsalary = new Label("SALARY");
		Label lblsalaryValue = new Label("VALUE");
		
		Label lblgrossSales = new Label("Gross Sales");
		TextField txtgrossSales = new TextField();
		
		Label lblcommissionRate = new Label("Commission Rate");
		TextField txtcommissionRate = new TextField();
		
		Label lblbaseSalary = new Label("Base Salary");
		TextField txtbaseSalary = new TextField();
		
		Label lblweeklySalary = new Label("Weekly Salary");
		TextField txtweeklySalary = new TextField();
		
		Label lblwage = new Label("Wage");
		TextField txtwage = new TextField();
		
		Label lblhours = new Label("Hours");
		TextField txthours = new TextField();
		
		Button btnadd = new Button("Add");
		Button btnsearch = new Button("Search by SSN");
		Button btnupdate = new Button("Update by SSN");
		Button btnclear = new Button("Clean TextFields");
		
		//Layout
		//Üst Kısım
		HBox topLayout = new HBox(10);	//	yatay boşluk
		topLayout.getChildren().addAll(lblchooseEmployeeType, cmbchooseEmployeeType);
		topLayout.setAlignment(Pos.CENTER);
		
		//Orta Kısım
		GridPane midLayout = new GridPane();
		midLayout.setVgap(5);	//	dikey boşluk
		midLayout.setHgap(10);	//	yatay boşluk
		
		midLayout.add(lblfirstName, 0, 0);
		midLayout.add(txtfirstName, 1, 0);
		midLayout.add(lbllastName, 0, 1);
		midLayout.add(txtlastName, 1, 1);
		midLayout.add(lblSSN, 0, 2);
		midLayout.add(txtSSN, 1, 2);
		midLayout.add(lblsearchUpdateSSN, 0, 3);
		midLayout.add(txtsearchUpdateSSN, 1, 3);
		midLayout.add(lblsalary, 0, 4);
		midLayout.add(lblsalaryValue, 1, 4);
		midLayout.add(lblgrossSales, 10, 0);
		midLayout.add(txtgrossSales, 11, 0);
		midLayout.add(lblcommissionRate, 10, 1);
		midLayout.add(txtcommissionRate, 11, 1);
		midLayout.add(lblbaseSalary, 10, 2);
		midLayout.add(txtbaseSalary, 11, 2);
		midLayout.add(lblweeklySalary, 10, 3);
		midLayout.add(txtweeklySalary, 11, 3);
		midLayout.add(lblwage, 10, 4);
		midLayout.add(txtwage, 11, 4);
		midLayout.add(lblhours, 10, 5);
		midLayout.add(txthours, 11, 5);
		
		midLayout.setAlignment(Pos.CENTER);
		
		//Alt Kısım
		HBox botLayout = new HBox(10);	//	yatay boşluk
		botLayout.getChildren().addAll(btnadd, btnsearch, btnupdate, btnclear);
		botLayout.setAlignment(Pos.CENTER);
		
		//Ana Layout
		VBox mainLayout = new VBox(20);	//	dikey boşluk
		mainLayout.getChildren().addAll(topLayout, midLayout, botLayout);
		
		//ComboBox seçim durumu
		cmbchooseEmployeeType.setOnAction(e -> comboBoxSelection(cmbchooseEmployeeType.getValue(), 
					txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, txtgrossSales, 
					txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours));
		
		//Buton işlevleri
		btnadd.setOnAction(e -> {
			if (cmbchooseEmployeeType.getValue() == null || cmbchooseEmployeeType.equals("None") ||
					txtfirstName.getText().isEmpty() || txtlastName.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Missing Fields!");
				alert.showAndWait();
				return;
			}
			
			//addDatabase();
		});
		
		btnsearch.setOnAction(e -> {
			System.out.println("search");
		});
		
		btnupdate.setOnAction(e -> {
			System.out.println("update");
		});
		
		btnclear.setOnAction(e -> {
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, 
					txtwage, txthours);
			
		});
		
		Scene scene = new Scene(mainLayout, 700, 300);
		primaryStage.setResizable(false);
		primaryStage.setTitle("BasarOrhanbulucu_221805031");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	public static void readDatabase(ArrayList<Employee> employeeArray) throws FileNotFoundException {
		
		File file = new File("employeeDatabase.txt");	//	database dosyası
		Scanner fileScanner = new Scanner(file);		//	file scanner
		
		while (fileScanner.hasNextLine()) {			//	son satıra kadar git
			String line = fileScanner.nextLine();	//	satırı al
			String[] data = line.split(", ");		//	satırı böl
			
			String employeeType = data[0];	//	çalışan türü
			
			switch (employeeType) {
			case "SalariedEmployee":
				SalariedEmployee salariedEmployee = new SalariedEmployee(data[1], data[2], data[3], 
						Double.parseDouble(data[4]));
				employeeArray.add(salariedEmployee);
				break;
				
			case "HourlyEmployee":
				HourlyEmployee hourlyEmployee = new HourlyEmployee(data[1], data[2], data[3], 
						Double.parseDouble(data[4]), Double.parseDouble(data[5]));
				employeeArray.add(hourlyEmployee);
				break;
				
			case "CommissionEmployee":
				CommissionEmployee commissionEmployee = new CommissionEmployee(data[1], data[2], data[3], 
						Double.parseDouble(data[4]), Double.parseDouble(data[5]));
				employeeArray.add(commissionEmployee);
				break;
				
			case "BasePlusCommissionEmployee":
				BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee(data[1], data[2], data[3], 
						Double.parseDouble(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]));
				employeeArray.add(basePlusCommissionEmployee);
				break;
				
			default:	//	hatalı çalışan türü
				System.out.println("Unknown employee type: " + employeeType);
				break;
			}
			
		}
		fileScanner.close();
	}
	
	public static void comboBoxSelection(String selectedCombo, TextField txtfirstName, TextField txtlastName, 
			TextField txtSSN, TextField txtsearchUpdateSSN, Label lblsalaryValue, TextField txtgrossSales, 
			TextField txtcommissionRate, TextField txtbaseSalary, TextField txtweeklySalary, 
			TextField txtwage, TextField txthours) {
		
		if (selectedCombo == null) {
	        return; // Seçili değer yoksa hiçbir işlem yapma
	    }
		
		switch (selectedCombo) {
		case "Salaried Employee":
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, 
					txtwage, txthours);
			
			txtfirstName.setDisable(false);
			txtfirstName.setOpacity(1);
			txtlastName.setDisable(false);
			txtlastName.setOpacity(1);
			txtSSN.setDisable(true);
			txtSSN.setOpacity(0.2);
			txtsearchUpdateSSN.setDisable(false);
			txtsearchUpdateSSN.setOpacity(1);
			lblsalaryValue.setText("VALUE");
			txtgrossSales.setDisable(true);
			txtgrossSales.setOpacity(0.2);
			txtcommissionRate.setDisable(true);
			txtcommissionRate.setOpacity(0.2);
			txtbaseSalary.setDisable(true);
			txtbaseSalary.setOpacity(0.2);
			txtweeklySalary.setDisable(false);
			txtweeklySalary.setOpacity(1);
			txtwage.setDisable(true);
			txtwage.setOpacity(0.2);
			txthours.setDisable(true);
			txthours.setOpacity(0.2);
			break;
			
		case "Hourly Employee":
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, 
					txtwage, txthours);
			
			txtfirstName.setDisable(false);
			txtfirstName.setOpacity(1);
			txtlastName.setDisable(false);
			txtlastName.setOpacity(1);
			txtSSN.setDisable(true);
			txtSSN.setOpacity(0.2);
			txtsearchUpdateSSN.setDisable(false);
			txtsearchUpdateSSN.setOpacity(1);
			lblsalaryValue.setText("VALUE");
			txtgrossSales.setDisable(true);
			txtgrossSales.setOpacity(0.2);
			txtcommissionRate.setDisable(true);
			txtcommissionRate.setOpacity(0.2);
			txtbaseSalary.setDisable(true);
			txtbaseSalary.setOpacity(0.2);
			txtweeklySalary.setDisable(true);
			txtweeklySalary.setOpacity(0.2);
			txtwage.setDisable(false);
			txtwage.setOpacity(1);
			txthours.setDisable(false);
			txthours.setOpacity(1);
			break;
			
		case "Commission Employee":
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, 
					txtwage, txthours);
			
			txtfirstName.setDisable(false);
			txtfirstName.setOpacity(1);
			txtlastName.setDisable(false);
			txtlastName.setOpacity(1);
			txtSSN.setDisable(true);
			txtSSN.setOpacity(0.2);
			txtsearchUpdateSSN.setDisable(false);
			txtsearchUpdateSSN.setOpacity(1);
			lblsalaryValue.setText("VALUE");
			txtgrossSales.setDisable(false);
			txtgrossSales.setOpacity(1);
			txtcommissionRate.setDisable(false);
			txtcommissionRate.setOpacity(1);
			txtbaseSalary.setDisable(true);
			txtbaseSalary.setOpacity(0.2);
			txtweeklySalary.setDisable(true);
			txtweeklySalary.setOpacity(0.2);
			txtwage.setDisable(true);
			txtwage.setOpacity(0.2);
			txthours.setDisable(true);
			txthours.setOpacity(0.2);
			break;
			
		case "Base Plus Commission Employee":
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, 
					txtwage, txthours);
			
			txtfirstName.setDisable(false);
			txtfirstName.setOpacity(1);
			txtlastName.setDisable(false);
			txtlastName.setOpacity(1);
			txtSSN.setDisable(true);
			txtSSN.setOpacity(0.2);
			txtsearchUpdateSSN.setDisable(false);
			txtsearchUpdateSSN.setOpacity(1);
			lblsalaryValue.setText("VALUE");
			txtgrossSales.setDisable(false);
			txtgrossSales.setOpacity(1);
			txtcommissionRate.setDisable(false);
			txtcommissionRate.setOpacity(1);
			txtbaseSalary.setDisable(false);
			txtbaseSalary.setOpacity(1);
			txtweeklySalary.setDisable(true);
			txtweeklySalary.setOpacity(0.2);
			txtwage.setDisable(true);
			txtwage.setOpacity(0.2);
			txthours.setDisable(true);
			txthours.setOpacity(0.2);
			break;
			
		case "None":
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, 
					txtwage, txthours);
			
			txtfirstName.setDisable(true);
			txtfirstName.setOpacity(1);
			txtlastName.setDisable(true);
			txtlastName.setOpacity(1);
			txtSSN.setDisable(true);
			txtSSN.setOpacity(1);
			txtsearchUpdateSSN.setDisable(true);
			txtsearchUpdateSSN.setOpacity(1);
			lblsalaryValue.setText("VALUE");
			txtgrossSales.setDisable(true);
			txtgrossSales.setOpacity(1);
			txtcommissionRate.setDisable(true);
			txtcommissionRate.setOpacity(1);
			txtbaseSalary.setDisable(true);
			txtbaseSalary.setOpacity(1);
			txtweeklySalary.setDisable(true);
			txtweeklySalary.setOpacity(1);
			txtwage.setDisable(true);
			txtwage.setOpacity(1);
			txthours.setDisable(true);
			txthours.setOpacity(1);
			
			break;
			
		}
	}
	
	public static void clearTextFields(String situation, TextField txtfirstName, TextField txtlastName, 
			TextField txtSSN, TextField txtsearchUpdateSSN, Label lblsalaryValue, TextField txtgrossSales, 
			TextField txtcommissionRate, TextField txtbaseSalary, TextField txtweeklySalary, 
			TextField txtwage, TextField txthours) {
		
		switch (situation) {
		case "all":
			txtfirstName.clear();
			txtlastName.clear();
			txtSSN.clear();
			txtsearchUpdateSSN.clear();
			lblsalaryValue.setText("VALUE");
			txtgrossSales.clear();
			txtcommissionRate.clear();
			txtbaseSalary.clear();
			txtweeklySalary.clear();
			txtwage.clear();
			txthours.clear();

			break;
		}
	}
	
}
