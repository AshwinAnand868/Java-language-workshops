package application;
	


/*********************************************************************************
A program that stores a person's information into a file, facilitates to navigate
 through the records stored in the file and also modifies the file.            
*********************************************************************************/

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressBook extends Application {
	
	ArrayList<String> fNameList = new ArrayList<>();
	ArrayList<String> lNameList = new ArrayList<>();
	ArrayList<String> cityList = new ArrayList<>();
	ArrayList<String> provinceList = new ArrayList<>();
	ArrayList<String> postalCodeList = new ArrayList<>();
	
	int counter = -1;
	
	// entry point for the GUI
	public void start(Stage primaryStage) {
		
		// labels
		Label firstName = new Label("First Name: ");
		Label lastName = new Label("Last Name: ");
		Label city = new Label("City: ");
		Label province = new Label("Province: ");
		Label postalCode = new Label("Postal Code: ");
		
		// text fields
		TextField firstNameField = new TextField();
		TextField lastNameField = new TextField();
		TextField cityField = new TextField();
		TextField pCodeField = new TextField();
		String[] provinceCodes = {"ON", "QC", "MB", "BC", "SK", "AB", "NS", "PE","NL"};
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ComboBox provinceBox = new ComboBox(FXCollections.observableArrayList(provinceCodes));
		
		// setting text field width
		firstNameField.setPrefColumnCount(30);
		lastNameField.setPrefColumnCount(30);
		cityField.setPrefColumnCount(10);
		pCodeField.setPrefColumnCount(10);
		
		// buttons
	    Button add = new Button("Add");
        Button first = new Button("First");
        Button next = new Button("Next");
        Button previous = new Button("Previous");
        Button last = new Button("Last");
        Button update = new Button("Update");
        
        // setting equal button width
        add.setPrefWidth(80);
        first.setPrefWidth(80);
        next.setPrefWidth(80);
        previous.setPrefWidth(80);
        last.setPrefWidth(80);
        update.setPrefWidth(80);
        
        // Panes or layout instantiation
		GridPane nameGrid = new GridPane();
		GridPane address = new GridPane();
		FlowPane buttonPane = new FlowPane();
		GridPane root = new GridPane();
		
		
		// adding nodes to nameGrid pane and setting their gaps and padding
		nameGrid.addRow(0,firstName, firstNameField);
		nameGrid.addRow(1,lastName, lastNameField);
		nameGrid.setVgap(20);
		nameGrid.setHgap(20);
		nameGrid.setPadding(new Insets(10, 30, 10,30));
		
		// adding nodes to address grid pane and setting their gaps and padding
		address.addRow(0, city, cityField);
		address.addRow(0, province, provinceBox);
		address.addRow(0, postalCode, pCodeField);
		address.setPadding(new Insets(10, 30, 10, 15));
		address.setHgap(6);
	
		//  adding buttons to buttonPane layout and setting their gaps and padding
		buttonPane.getChildren().add(add);
		buttonPane.getChildren().add(first);
		buttonPane.getChildren().add(next);
		buttonPane.getChildren().add(previous);
		buttonPane.getChildren().add(last);
		buttonPane.getChildren().add(update);
		buttonPane.setHgap(3);
		buttonPane.setAlignment(Pos.TOP_CENTER);
		buttonPane.setPadding(new Insets(10, 0, 10, 0));
		
		// adding child panes to root panes
		root.add(nameGrid, 0, 0);
		root.add(address, 0, 1);
		root.add(buttonPane, 0, 2);
		root.setAlignment(Pos.TOP_CENTER);
			
		// scene definition
		Scene scene = new Scene(root);
		scene.setFill(Color.BLUE);
		
		// controlling view or model's width and height
		primaryStage.setMaxWidth(570);
		primaryStage.setMaxHeight(220);
		primaryStage.setMinWidth(570);
		primaryStage.setMinHeight(220);
		
		// setting the stage title and passing scene to stage
		primaryStage.setTitle("Address Book");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		// Add button action
		add.setOnAction(e -> {
			
			String fName = firstNameField.getText();
			String lName = lastNameField.getText();
			String cityValue = cityField.getText();
			String provinceVal = (String) provinceBox.getValue();
			String pCode = pCodeField.getText();
			boolean validPCode = validatePostalCode(pCode);
			
			if(validPCode && fName.length() > 0 && lName.length() > 0 && cityValue.length() > 0 && provinceVal.length() > 0 && pCode.length() > 0) {
				writeToFile(fName, lName, cityValue, provinceVal, pCode);
				clearAllField(firstNameField, lastNameField, cityField, provinceBox, pCodeField);
			}
			else if(!validPCode) {
				System.out.println("Enter valid postal code please");
			}
			else {
				System.out.println("field cannot be empty");
			}
			
		});
		
		// first button action
		first.setOnAction(e->{
			
			if(fNameList.size() == 0) {
				storeDataToList();
			}
			counter = 0;
			setAllField(firstNameField, lastNameField, cityField, provinceBox, pCodeField, 0);
			
		});
		
		// next button action
		next.setOnAction(e->{
			
			if(fNameList.size() == 0) {
				storeDataToList();
			}
			counter++;
			if(counter < fNameList.size()){
				setAllField(firstNameField, lastNameField, cityField, provinceBox, pCodeField, counter);	
			}
			else {
				System.out.println("Next record does not exit");
			}
		});
		
		// previous button action
		previous.setOnAction(e->{
			
			if(fNameList.size() == 0) {
				storeDataToList();
			}
		
			if(counter > 0) {
				counter--;
				setAllField(firstNameField, lastNameField, cityField, provinceBox, pCodeField, counter);
			}
			else {
				System.out.println("Previous record does not exit");
				clearAllField(firstNameField, lastNameField, cityField, provinceBox, pCodeField);
				counter = -1;
			}
		});
		
		// last button action
		last.setOnAction(e->{
			
			if(fNameList.size() == 0) {
				storeDataToList();
			}
			counter = fNameList.size() - 1;
			setAllField(firstNameField, lastNameField, cityField, provinceBox, pCodeField, counter);
			
		});
		
		// update button
		update.setOnAction(e->{
			
			String fName = fNameList.get(counter);
			String lName = lNameList.get(counter);
			String cityValue = cityList.get(counter);
			String provinceVal = provinceList.get(counter);
			String pCode = postalCodeList.get(counter);
			boolean validPCode = validatePostalCode(pCode);
			
			if(validPCode && fName.length() > 0 && lName.length() > 0 && cityValue.length() > 0 && provinceVal.length() > 0 && pCode.length() > 0) {
			
				fNameList.set(counter, firstNameField.getText());
				lNameList.set(counter, lastNameField.getText());
				cityList.set(counter, cityField.getText());
				provinceList.set(counter, (String) provinceBox.getValue());
				postalCodeList.set(counter, pCodeField.getText());
				writeToFile();
				clearAllField(firstNameField, lastNameField, cityField, provinceBox, pCodeField);
			}
			else if(!validPCode) {
				System.out.println("Enter valid postal code please");
			}
			else {
				System.out.println("field cannot be set to empty");
			}
		});
	}
	
	// validating postal code 
	public boolean validatePostalCode(String postCode) {
		Pattern regex = Pattern.compile("^[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d$");
		Matcher matcher = regex.matcher(postCode);
		boolean valid = matcher.find();
		return valid;
	}
	
	// setting all the fields value to the parameters value
	public void setAllField(TextField f_name, TextField l_name, TextField city, ComboBox<String> province, TextField postCode, int index) {
		f_name.setText(fNameList.get(index));
		l_name.setText(lNameList.get(index));
		city.setText(cityList.get(index));
		province.setValue(provinceList.get(index));
		postCode.setText(postalCodeList.get(index));
	}
	
	// clearing all fields in the view
	public void clearAllField(TextField f_name, TextField l_name, TextField city, ComboBox<String> province, TextField postCode) {
		f_name.setText("");
		l_name.setText("");
		city.setText("");
		province.setValue("");
		postCode.setText("");
	}
	
	// writing all updated records to a file 
	public void writeToFile() {
		RandomAccessFile writer;
		String toWrite;
		try {
			writer = new RandomAccessFile("addressbook.txt", "rw");
			writer.seek(0);
			for(int i = 0; i < fNameList.size(); i++) {
				toWrite = fNameList.get(i) + "    " + lNameList.get(i) + "    " + cityList.get(i) + "    " + provinceList.get(i) + "    " + postalCodeList.get(i) + "\n";
				writer.write(toWrite.getBytes());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		storeDataToList();
	}
	
	// writing a single record to the file
	public void writeToFile(String f_name, String l_name, String city, String province, String postCode) {
		File file;
		RandomAccessFile writer;
		long length = 0;
		try {
			file = new File("addressbook.txt");
			writer = new RandomAccessFile(file, "rw");
			length = writer.length();
			writer.seek(length);
			
			String line = f_name + "    " + l_name + "    " + city + "    " + province + "    " + postCode + "\n";
			writer.write(line.getBytes());
			
			writer.close();
			storeDataToList();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// reading all records from the file
	public byte[] readFromFile(File file) {
		byte[] data = {};
		try {
			RandomAccessFile reader = new RandomAccessFile(file, "r");
			reader.seek(0);
			data = new byte[(int) reader.length()];
			reader.read(data);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	// storing data of the file to the respective instance variables of this class
	public void storeDataToList() {
		
		byte[] data = readFromFile(new File("addressbook.txt"));
		
		String fileData = new String(data);
		
		StringTokenizer st = new StringTokenizer(fileData, "\n");
		
		int i = 0;
		ArrayList<String> lines = new ArrayList<>();
		while(st.hasMoreTokens()) {
			lines.add(st.nextToken());
			i++;
		}
		
		i = 0;
		while(i < lines.size()) {
			StringTokenizer lineTokenizer = new StringTokenizer(lines.get(i), "    ");
			while(lineTokenizer.hasMoreTokens()){
				fNameList.add(lineTokenizer.nextToken());
				lNameList.add(lineTokenizer.nextToken());
				cityList.add(lineTokenizer.nextToken());
				provinceList.add(lineTokenizer.nextToken());
				postalCodeList.add(lineTokenizer.nextToken());
			}
			i++;
		}		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}