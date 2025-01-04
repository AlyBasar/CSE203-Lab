package application;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
		
		final boolean[] updateable = {false};	//	güncel update'lenebilirlik durumu. Final sayesinde referansı sabit
		
		//ComboBox seçim durumu
		cmbchooseEmployeeType.setOnAction(e -> {
			comboBoxSelection(cmbchooseEmployeeType.getValue(), 
					txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, txtgrossSales, 
					txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours);
			
			updateable[0] = false;
		});
		
		//Buton işlevleri
		btnadd.setOnAction(e -> {	//ADD
			if (cmbchooseEmployeeType.getValue() == null || cmbchooseEmployeeType.equals("None") ||
					txtfirstName.getText().isEmpty() || txtlastName.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Missing Fields!");
				alert.showAndWait();
				return;
			}
			
			String selectedType = cmbchooseEmployeeType.getValue();	//	seçilen employee türü
			
			try {
				addEmployee(employeeArray, selectedType, txtfirstName, txtlastName, lblsalaryValue, txtgrossSales, 
						txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours);
				
				updateable[0] = false;
				
			} catch (IOException e1) {
				System.out.println("Add Error");
				e1.printStackTrace();
			}
			
			
			
		});
		
		btnsearch.setOnAction(e -> {	//SEARCH
			if (txtsearchUpdateSSN.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Missing Fields!");
				alert.showAndWait();
				return;
			}
			
			clearTextFields("search", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, 
					txtwage, txthours);
			
			String searching_ssn = txtsearchUpdateSSN.getText();	//	Aranan ssn
			
			searchEmployee(employeeArray, searching_ssn, cmbchooseEmployeeType, txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, 
					lblsalaryValue, txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, 
					txthours);
			
			updateable[0] = true;	//	bu işlem sonrası update edilebilir
			
		});
		
		btnupdate.setOnAction(e -> {	//UPDATE
			if (updateable[0]) {
				
				try {
					updateEmployee(employeeArray, updateable, cmbchooseEmployeeType, txtfirstName, txtlastName, txtSSN, 
							txtsearchUpdateSSN, lblsalaryValue, txtgrossSales, txtcommissionRate, txtbaseSalary, 
							txtweeklySalary, txtwage, txthours);
				} catch (IOException e1) {
					System.out.println("Update Error");
					e1.printStackTrace();
				}
				
			}
			else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("To update, you must first search SSN!");
				alert.showAndWait();
				return;
			}
			
		});
		
		btnclear.setOnAction(e -> {		//CLEAR
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, 
					txtwage, txthours);
			
			updateable[0] = false;
			
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
			
		case "search":
			txtfirstName.clear();
			txtlastName.clear();
			txtSSN.clear();
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
	
	public static void addEmployee(ArrayList<Employee> employeeArray, String selectedType, TextField txtfirstName, 
			TextField txtlastName, Label lblsalaryValue, TextField txtgrossSales, TextField txtcommissionRate, 
			TextField txtbaseSalary, TextField txtweeklySalary, TextField txtwage, TextField txthours) throws IOException {
		
		File file = new File("employeeDatabase.txt");			//	database dosyası
		FileWriter fWriter = new FileWriter(file, true);		//	File Writer
		BufferedWriter bWriter = new BufferedWriter(fWriter);	//	Buffered Writer
		
		Alert alert1 = new Alert(AlertType.INFORMATION);		//bilgilendirme uyarısı
		alert1.setHeaderText("Employee Added");
		
		String firstName = txtfirstName.getText();				//	First Name
		String lastName = txtlastName.getText();				//	Last Name
		String ssn = Integer.toString(employeeArray.size());	//	yeni eklenecek elemanın ssn değeri
		
		switch (selectedType) {
		case "Salaried Employee":
			if (txtweeklySalary.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Missing fields!");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			
			Double weeklySalary = Double.parseDouble(txtweeklySalary.getText());
			if (weeklySalary < 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Weekly salary must be >= 0");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			
			SalariedEmployee salariedEmployee = new SalariedEmployee(firstName, lastName, ssn, weeklySalary);
			employeeArray.add(salariedEmployee);				//arraylist'e ekle
			
			bWriter.write("SalariedEmployee, " + firstName + ", " + lastName + ", " //dosyaya ekle
			+ ssn + ", " + weeklySalary + "\n");
			
			alert1.showAndWait();	//	Bilgilendirme uyarısı ver
			
			clearTextFields("all", txtfirstName, txtlastName, txtfirstName, txtlastName, lblsalaryValue, txtgrossSales, 
					txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours);
			
			break;
			
		case "Hourly Employee":
			if (txtwage.getText().isEmpty() || txthours.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Missing Fields!");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			
			Double wage = Double.parseDouble(txtwage.getText());
			if (wage < 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Wage must be >= 0!");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			Double hours = Double.parseDouble(txthours.getText());
			if (hours < 0 || hours >= 168) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Hours must be >= 0 and < 168!");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			
			HourlyEmployee hourlyEmployee = new HourlyEmployee(firstName, lastName, ssn, wage, hours);
			employeeArray.add(hourlyEmployee);					//arraylist'e ekle
			
			bWriter.write("HourlyEmployee, " + firstName + ", " + lastName + ", " //dosyaya ekle
					+ ssn + ", " + wage + ", " + hours + "\n");

			alert1.showAndWait();	//	Bilgilendirme uyarısı ver
			
			clearTextFields("all", txtfirstName, txtlastName, txtfirstName, txtlastName, lblsalaryValue, txtgrossSales, 
					txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours);
			break;
			
		case "Commission Employee":
			if (txtgrossSales.getText().isEmpty() || txtcommissionRate.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Missing Fields!");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			
			Double grossSales = Double.parseDouble(txtgrossSales.getText());
			if (grossSales < 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Gross sales must be >= 0!");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			Double commissionRate = Double.parseDouble(txtcommissionRate.getText());
			if (commissionRate <= 0 || commissionRate >= 1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Commission rate must be > 0 and < 1");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			
			CommissionEmployee commissionEmployee = new CommissionEmployee(firstName, lastName, ssn, 
					grossSales, commissionRate);
			employeeArray.add(commissionEmployee);				//arraylist'e ekle
			
			bWriter.write("CommissionEmployee, " + firstName + ", " + lastName + ", " //dosyaya ekle
					+ ssn + ", " + grossSales + ", " + commissionRate + "\n");

			alert1.showAndWait();	//	Bilgilendirme uyarısı ver
			
			clearTextFields("all", txtfirstName, txtlastName, txtfirstName, txtlastName, lblsalaryValue, txtgrossSales, 
					txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours);
			
			break;
			
		case "Base Plus Commission Employee":
			if (txtgrossSales.getText().isEmpty() || txtcommissionRate.getText().isEmpty() || txtbaseSalary.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Missing Fields!");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			Double bpgrossSales = Double.parseDouble(txtgrossSales.getText());
			if (bpgrossSales < 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Gross sales must be >= 0!");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			Double bpcommissionRate = Double.parseDouble(txtcommissionRate.getText());
			if (bpcommissionRate <= 0 || bpcommissionRate >= 1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Commission rate must be > 0 and < 1");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			Double baseSalary = Double.parseDouble(txtbaseSalary.getText());
			if (baseSalary < 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Base salary must be >= 0!");
				alert.showAndWait();
				bWriter.close();
				return;
			}
			
			BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee(firstName, lastName, 
					ssn, bpgrossSales, bpcommissionRate, baseSalary);
			employeeArray.add(basePlusCommissionEmployee);		//arraylist'e ekle

			bWriter.write("BasePlusCommissionEmployee, " + firstName + ", " + lastName + ", " //dosyaya ekle
					+ ssn + ", " + bpgrossSales + ", " + bpcommissionRate + ", " + baseSalary + "\n");

			alert1.showAndWait();	//	Bilgilendirme uyarısı ver
			
			clearTextFields("all", txtfirstName, txtlastName, txtfirstName, txtlastName, lblsalaryValue, txtgrossSales, 
					txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours);
			
			break;
			
		}
		
		bWriter.close();
	}
	
	public static void searchEmployee(ArrayList<Employee> employeeArray, String searching_ssn, 
			ComboBox<String> cmbchooseEmployeeType, TextField txtfirstName, TextField txtlastName, TextField txtSSN, 
			TextField txtsearchUpdateSSN, Label lblsalaryValue, TextField txtgrossSales, TextField txtcommissionRate, 
			TextField txtbaseSalary, TextField txtweeklySalary, TextField txtwage, TextField txthours) {
		
		boolean employee_founded = false;
		
		for (int i = 0; i < employeeArray.size(); i++) {
			if (employeeArray.get(i).getSSN().equals(searching_ssn)) {
				Employee employee = employeeArray.get(i);
				employee_founded = true;	//	çalışan bulundu
				
				if (employee instanceof SalariedEmployee) {
					
					SalariedEmployee salariedEmployee = (SalariedEmployee) employee;	//	tür dönüşümü
					cmbchooseEmployeeType.getSelectionModel().select("Salaried Employee");	//	cmbox seçimi
					
					txtfirstName.setText(salariedEmployee.getFirstName());
					txtlastName.setText(salariedEmployee.getLastName());
					txtSSN.setText(salariedEmployee.getSSN());
					lblsalaryValue.setText(Double.toString(salariedEmployee.getPaymentAmount()));
					txtweeklySalary.setText(Double.toString(salariedEmployee.getWeeklySalary()));
				}
				
				else if (employee instanceof HourlyEmployee) {
					
					HourlyEmployee hourlyEmployee = (HourlyEmployee) employee;	//	tür dönüşümü
					cmbchooseEmployeeType.getSelectionModel().select("Hourly Employee");	//	cmbox seçimi
					
					txtfirstName.setText(hourlyEmployee.getFirstName());
					txtlastName.setText(hourlyEmployee.getLastName());
					txtSSN.setText(hourlyEmployee.getSSN());
					lblsalaryValue.setText(Double.toString(hourlyEmployee.getPaymentAmount()));
					txtwage.setText(Double.toString(hourlyEmployee.getWage()));
					txthours.setText(Double.toString(hourlyEmployee.getHours()));
				}
				
				else if (employee instanceof CommissionEmployee) {
					
					if (employee instanceof BasePlusCommissionEmployee) {
						
						BasePlusCommissionEmployee basePlusCommissionEmployee = (BasePlusCommissionEmployee) employee;	//	tür dönüşümü
						cmbchooseEmployeeType.getSelectionModel().select("Base Plus Commission Employee");	//	cmbox seçimi
						
						txtfirstName.setText(basePlusCommissionEmployee.getFirstName());
						txtlastName.setText(basePlusCommissionEmployee.getLastName());
						txtSSN.setText(basePlusCommissionEmployee.getSSN());
						lblsalaryValue.setText(Double.toString(basePlusCommissionEmployee.getPaymentAmount()));
						txtgrossSales.setText(Double.toString(basePlusCommissionEmployee.getGrossSales()));
						txtcommissionRate.setText(Double.toString(basePlusCommissionEmployee.getCommissionRate()));
						txtbaseSalary.setText(Double.toString(basePlusCommissionEmployee.getBaseSalary()));
					}
					else {
						
						CommissionEmployee commissionEmployee = (CommissionEmployee) employee;	//	tür dönüşümü
						cmbchooseEmployeeType.getSelectionModel().select("Commission Employee");	//	cmbox seçimi
						
						txtfirstName.setText(commissionEmployee.getFirstName());
						txtlastName.setText(commissionEmployee.getLastName());
						txtSSN.setText(commissionEmployee.getSSN());
						lblsalaryValue.setText(Double.toString(commissionEmployee.getPaymentAmount()));
						txtgrossSales.setText(Double.toString(commissionEmployee.getGrossSales()));
						txtcommissionRate.setText(Double.toString(commissionEmployee.getCommissionRate()));
					}
				}
				
				break;
				
			}
		}
		
		if (!employee_founded) {	//	Aranan ssn bulunamadıysa
			Alert alert = new Alert(AlertType.WARNING);
		       alert.setTitle("Employee Not Found");
		       alert.setHeaderText("No employee found with the given SSN.");
		       alert.showAndWait();
		}
		
	}
	
	public static void updateEmployee(ArrayList<Employee> employeeArray, boolean[] updateable, 
			ComboBox<String> cmbchooseEmployeeType, TextField txtfirstName, TextField txtlastName, 
			TextField txtSSN, TextField txtsearchUpdateSSN, Label lblsalaryValue, TextField txtgrossSales, 
			TextField txtcommissionRate, TextField txtbaseSalary, TextField txtweeklySalary, TextField txtwage, 
			TextField txthours) throws IOException {
		
		String employee_type = cmbchooseEmployeeType.getValue();	//Employee türü
		int employee_ssn = Integer.parseInt(txtSSN.getText());		//Employee ssn
		Employee employee = employeeArray.get(employee_ssn);		//Seçilen employee
		String updatedLine;											//Dosyaya yazdırılacak yeni satır
		
		Alert alert1 = new Alert(AlertType.INFORMATION);			//Bilgilendirme uyarısı
		alert1.setHeaderText("Employee Updated");
		
		String new_firstName = txtfirstName.getText();
		String new_lastName = txtlastName.getText();
		
		switch (employee_type) {
		case "Salaried Employee":
			SalariedEmployee salariedEmployee = (SalariedEmployee) employee;	//	tür dönüşümü
			
			double new_weeklySalary = Double.parseDouble(txtweeklySalary.getText());
			if (new_weeklySalary < 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Weekly salary must be >= 0");
				alert.showAndWait();
				return;
			}
			
			salariedEmployee.setFirstName(new_firstName);
			salariedEmployee.setLastName(new_lastName);
			salariedEmployee.setWeeklySalary(new_weeklySalary);
			
			updatedLine = "SalariedEmployee, " + new_firstName + ", " + new_lastName + ", " + employee_ssn 
					+ ", " + new_weeklySalary + ", " + salariedEmployee.getPaymentAmount();
			
			updateFile(employee_ssn, updatedLine);	//	Dosyayı güncelle
			
			alert1.showAndWait();	//Bilgilendirme uyarısı ver
			
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours);
			
			updateable[0] = false;
			break;
			
		case "Hourly Employee":
			HourlyEmployee hourlyEmployee = (HourlyEmployee) employee;	//	tür dönüşümü
			
			double new_wage = Double.parseDouble(txtwage.getText());
			if (new_wage < 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Wage must be >= 0!");
				alert.showAndWait();
				return;
			}
			double new_hours = Double.parseDouble(txthours.getText());
			if (new_hours < 0 || new_hours >= 168) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Hours must be >= 0 and < 168!");
				alert.showAndWait();
				return;
			}
			
			hourlyEmployee.setFirstName(new_firstName);
			hourlyEmployee.setLastName(new_lastName);
			hourlyEmployee.setWage(new_wage);
			hourlyEmployee.setHours(new_hours);
			
			updatedLine = "HourlyEmployee, " + new_firstName + ", " + new_lastName + ", " + employee_ssn 
					+ ", " + new_wage + ", " + new_hours + ", " + hourlyEmployee.getPaymentAmount();
			
			updateFile(employee_ssn, updatedLine);	//	Dosyayı güncelle
			
			alert1.showAndWait();	//Bilgilendirme uyarısı ver
			
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours);
			
			updateable[0] = false;
			break;
			
		case "Commission Employee":
			CommissionEmployee commissionEmployee = (CommissionEmployee) employee;	//	tür dönüşümü
			double new_grossSales = Double.parseDouble(txtgrossSales.getText());
			if (new_grossSales < 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Gross sales must be >= 0!");
				alert.showAndWait();
				return;
			}
			double new_commissionRate = Double.parseDouble(txtcommissionRate.getText());
			if (new_commissionRate <= 0 || new_commissionRate >= 1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Commission rate must be > 0 and < 1");
				alert.showAndWait();
				return;
			}
			
			commissionEmployee.setFirstName(new_firstName);
			commissionEmployee.setLastName(new_lastName);
			commissionEmployee.setGrossSales(new_grossSales);
			commissionEmployee.setCommissionRate(new_commissionRate);
			
			updatedLine = "CommissionEmployee, " + new_firstName + ", " + new_lastName + ", " + employee_ssn 
					+ ", " + new_grossSales + ", " + new_commissionRate + ", " + commissionEmployee.getPaymentAmount();
			
			updateFile(employee_ssn, updatedLine);	//	Dosyayı güncelle
			
			alert1.showAndWait();	//Bilgilendirme uyarısı ver
			
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours);
			
			updateable[0] = false;
			break;
			
		case "Base Plus Commission Employee":
			BasePlusCommissionEmployee basePlusCommissionEmployee = (BasePlusCommissionEmployee) employee;	//	tür dönüşümü
			double new_bpgrossSales = Double.parseDouble(txtgrossSales.getText());
			if (new_bpgrossSales < 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Gross sales must be >= 0!");
				alert.showAndWait();
				return;
			}
		    double new_bpcommissionRate = Double.parseDouble(txtcommissionRate.getText());
			if (new_bpcommissionRate <= 0 || new_bpcommissionRate >= 1) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Commission rate must be > 0 and < 1");
				alert.showAndWait();
				return;
			}
			double new_baseSalary = Double.parseDouble(txtbaseSalary.getText());
			if (new_baseSalary < 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Error");
				alert.setHeaderText("Base salary must be >= 0!");
				alert.showAndWait();
				return;
			}

			basePlusCommissionEmployee.setFirstName(new_firstName);
			basePlusCommissionEmployee.setLastName(new_lastName);
			basePlusCommissionEmployee.setGrossSales(new_bpgrossSales);
			basePlusCommissionEmployee.setCommissionRate(new_bpcommissionRate);
			basePlusCommissionEmployee.setBaseSalary(new_baseSalary);

			updatedLine = "BasePlusCommissionEmployee, " + new_firstName + ", " + new_lastName + ", " + employee_ssn 
					+ ", " + new_bpgrossSales + ", " + new_bpcommissionRate + ", " + new_baseSalary + ", " 
					+ basePlusCommissionEmployee.getPaymentAmount();
			
			updateFile(employee_ssn, updatedLine);	//	Dosyayı güncelle
			
			alert1.showAndWait();	//Bilgilendirme uyarısı ver
			
			clearTextFields("all", txtfirstName, txtlastName, txtSSN, txtsearchUpdateSSN, lblsalaryValue, 
					txtgrossSales, txtcommissionRate, txtbaseSalary, txtweeklySalary, txtwage, txthours);
			
			updateable[0] = false;
			break;
			
		}
	}
	
	public static void updateFile(int employee_ssn, String updatedLine) throws IOException {
		
		File originalFile = new File("employeeDatabase.txt");	//Orjinal dosya
		File tempFile = new File ("tempFile.txt");				//Geçici dosya
		
		FileReader fReader = new FileReader(originalFile);		//File Reader
		FileWriter fWriter = new FileWriter(tempFile);			//File Writer
		BufferedReader bReader = new BufferedReader(fReader);	//Buffered Reader
		BufferedWriter bWriter = new BufferedWriter(fWriter);	//Buffered Writer
		
		String currentLine = bReader.readLine();		//	İlk satırı oku
		boolean lineUpdated = false;					//	Update gerçekleşti mi
		
		while (currentLine != null) {	//	Son satıra kadar devam et
			String[] data = currentLine.split(", ");	//	Satırı böl
			if (Integer.parseInt(data[3]) == employee_ssn) {			//	SSN eşleşirse
				bWriter.write(updatedLine);					//	Güncellenmiş satırı yazdır
				lineUpdated = true;
			}
			else {
				bWriter.write(currentLine);					//	Mevcut satırı aynen yaz
			}
			bWriter.newLine();
			currentLine = bReader.readLine();	//Sonraki satırı oku
		}
		bWriter.close();
		bReader.close();
		
		if (lineUpdated) {
			if (!originalFile.delete()) {
			    System.err.println("Error! Original file could not be deleted.");
			    return;
			}
			if (!tempFile.renameTo(originalFile)) {
		        System.err.println("Error! Temp file could not be renamed.");
		        return;
		    }
		}
		else {
			tempFile.delete();	//	Güncelleme yapılmadıysa geçici dosyayı sil
		}
	}
	
}