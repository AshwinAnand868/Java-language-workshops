/**********************************************
Workshop 10
Course:CPP, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 12 August 2022
**********************************************/

package application;
	
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class StudentInfoOperation extends Application {

	ArrayList<Integer> idList = new ArrayList<>();
	ArrayList<String> nameList = new ArrayList<>();
	ArrayList<String> emailList = new ArrayList<>();
	ArrayList<String> addressList = new ArrayList<>();
	ArrayList<Integer> yearEnrolledList = new ArrayList<>();
	ArrayList<String> programList = new ArrayList<>();
	int index = 0;
	
	Label idLabel = new Label("Student Id:   ");
	Label nameLabel = new Label("Name: ");
	Label emailLabel = new Label("Email:  ");
	Label addressLabel = new Label("Address:   ");
	Label yearEnrolledLabel = new Label("Enrollment year: ");
	Label programLabel = new Label("Program:  ");
	
	TextField idTxtField = new TextField();
	TextField nameTxtField = new TextField();
	TextField emailTxtField = new TextField();
	TextField addressTxtField = new TextField();
	TextField yearEnrolledTxtField = new TextField();
	TextField programTxtField = new TextField();

	
	public void makeTextFieldUnEditable() {
		idTxtField.setEditable(false);
		nameTxtField.setEditable(false);
		emailTxtField.setEditable(false);
		addressTxtField.setEditable(false);
		yearEnrolledTxtField.setEditable(false);
		programTxtField.setEditable(false);
	}
	
	public void makeTextFieldEditable() {
		idTxtField.setEditable(true);
		nameTxtField.setEditable(true);
		emailTxtField.setEditable(true);
		addressTxtField.setEditable(true);
		yearEnrolledTxtField.setEditable(true);
		programTxtField.setEditable(true);
	}
	
	public void clearTextFields() {
		idTxtField.setText("");
		nameTxtField.setText("");
		emailTxtField.setText("");
		addressTxtField.setText("");
		yearEnrolledTxtField.setText("");
		programTxtField.setText("");
	}
	
	public void storeDataInList() {
		
		ResultSet rs = null;
		
		try {
			rs = Student.getStatement().executeQuery("select * from student");
			
			while(rs.next()) {
				idList.add(rs.getInt(1));
				nameList.add(rs.getString(2));
				emailList.add(rs.getString(3));
				addressList.add(rs.getString(4));
				yearEnrolledList.add(rs.getInt(5));
				programList.add(rs.getString(6));	
			}
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		
	}
	
	public void setAllField(String id, String name, String email, String address, String yearEnrolled, String program) {
		idTxtField.setText(id);
		nameTxtField.setText(name);
		emailTxtField.setText(email);
		addressTxtField.setText(address);
		yearEnrolledTxtField.setText(yearEnrolled);
		programTxtField.setText(program);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			Button insert = new Button("Add Student");
			Button update = new Button("Update Student");
			Button delete = new Button("Remove Student");
			Button viewOne = new Button("View One Student");
			Button viewAll = new Button("View All Students");
			
			
			GridPane buttonsRowOne = new GridPane();
			buttonsRowOne.addRow(0, insert, update);
			GridPane buttonsRowTwo = new GridPane();
			buttonsRowTwo.addRow(0, viewOne, viewAll);
			GridPane buttonsRowThree = new GridPane();
			buttonsRowThree.addRow(0, delete);
			buttonsRowThree.setAlignment(Pos.CENTER);
			
			GridPane buttonsPane = new GridPane();
			buttonsPane.add(buttonsRowOne, 0, 0);
			buttonsPane.add(buttonsRowTwo, 0, 1);
			buttonsPane.add(buttonsRowThree, 0, 2);
			buttonsPane.setAlignment(Pos.CENTER);
			Scene root = new Scene(buttonsPane, 500, 200);
			
			
			buttonsRowOne.setHgap(20);
			buttonsRowOne.setVgap(20);
			buttonsRowOne.setPadding(new Insets(0, 0, 20,0));
			
			buttonsRowTwo.setHgap(20);
			buttonsRowTwo.setVgap(20);
			buttonsRowTwo.setPadding(new Insets(0, 0, 20,0));			
			insert.setPrefWidth(120);
		    update.setPrefWidth(120);
		    delete.setPrefWidth(120);
		    viewOne.setPrefWidth(120);
		    viewAll.setPrefWidth(120);
			primaryStage.setScene(root);
			primaryStage.setTitle("Student Information");
			primaryStage.show();
			
			viewAll.setOnAction(e->{
				clearTextFields();
				storeDataInList();
				makeTextFieldUnEditable();
				GridPane infoPane = new GridPane();
				infoPane.addRow(0, idLabel, idTxtField);
				infoPane.addRow(0, nameLabel, nameTxtField);
				infoPane.addRow(1, emailLabel, emailTxtField);
				infoPane.addRow(1, addressLabel, addressTxtField);
				infoPane.addRow(2, yearEnrolledLabel, yearEnrolledTxtField);
				infoPane.addRow(2, programLabel, programTxtField);
				
				Button next = new Button("Next");
				Button previous = new Button("Previous");
				FlowPane buttonPane = new FlowPane();
				buttonPane.setAlignment(Pos.CENTER);
				
				
				buttonPane.getChildren().add(next);
				buttonPane.getChildren().add(previous);
				buttonPane.setVgap(20);
				buttonPane.setPadding(new Insets(20, 0, 0,0));
				
				
				GridPane studentPane = new GridPane();
				studentPane.add(infoPane, 0, 0);
				studentPane.add(buttonPane, 0, 1);
				studentPane.setAlignment(Pos.CENTER);
				Scene localScene = new Scene(studentPane, 500, 200);
				Stage viewStage = new Stage();
				
				
				viewStage.setScene(localScene);
				viewStage.setTitle("Viewing Student Information");
				viewStage.show();
				
				setAllField(String.valueOf(idList.get(index)),nameList.get(index),emailList.get(index),
						addressList.get(index), String.valueOf(yearEnrolledList.get(index)), programList.get(index));
				
				next.setOnAction(nextRecordEve->{
					index++;
					if(index < idList.size()){
						
						setAllField(String.valueOf(idList.get(index)),nameList.get(index),emailList.get(index),
								addressList.get(index), String.valueOf(yearEnrolledList.get(index)), programList.get(index));						
					}
					else {
						Alert nextRecordAlert = new Alert(AlertType.INFORMATION);
						nextRecordAlert.setTitle("Information Dialog");
						nextRecordAlert.setHeaderText(null);
						nextRecordAlert.setContentText("That was the last record");
						nextRecordAlert.showAndWait();
						index = idList.size() - 1;
					}
					
				});				
				
				previous.setOnAction(prevRecordEve->{
					
					index--;
					
					if(index > 0) {
						
						setAllField(String.valueOf(idList.get(index)),nameList.get(index),emailList.get(index),
								addressList.get(index), String.valueOf(yearEnrolledList.get(index)), programList.get(index));		
					}
					else {
						Alert previousRecordAlert = new Alert(AlertType.INFORMATION);
						previousRecordAlert.setTitle("Information Dialog");
						previousRecordAlert.setHeaderText(null);
						previousRecordAlert.setContentText("You are already seeing the first record");
						previousRecordAlert.showAndWait();
						index = 0;
					}
				});
			});
			
			insert.setOnAction(e->{	
				clearTextFields();
				makeTextFieldEditable();
				Button insertStudent = new Button("Save Student Information");
				
				GridPane insertInfoPane = new GridPane();
				GridPane insertButtonPane = new GridPane();
				
				insertInfoPane.addRow(0, idLabel, idTxtField);
				insertInfoPane.addRow(0, nameLabel, nameTxtField);
				insertInfoPane.addRow(1, emailLabel, emailTxtField);
				insertInfoPane.addRow(1, addressLabel, addressTxtField);
				insertInfoPane.addRow(2, yearEnrolledLabel, yearEnrolledTxtField);
				insertInfoPane.addRow(2, programLabel, programTxtField);
				
				insertButtonPane.add(insertStudent, 0, 1);
				insertButtonPane.setAlignment(Pos.CENTER);
				
				insertButtonPane.setVgap(20);
				
				GridPane insertStudentPane = new GridPane();
				insertStudentPane.add(insertInfoPane, 0, 0);
				insertStudentPane.add(insertButtonPane, 0, 1);
				insertStudentPane.setAlignment(Pos.CENTER);
				
				Scene insertScene = new Scene(insertStudentPane, 500, 200);
				
				Stage insertStage = new Stage();
				insertStage.setTitle("Adding New Student");
				insertStage.setScene(insertScene);
				insertStage.show();
				
				insertStudent.setOnAction(insertEvent->{
					
					int id = Integer.parseInt(idTxtField.getText());
					String name = nameTxtField.getText();
					String email = emailTxtField.getText();
					String address = addressTxtField.getText();
					int yearEnrolled = Integer.parseInt(yearEnrolledTxtField.getText());
					String program = programTxtField.getText();
					
					
					
					Student student = new Student(id, name, email, address, program, yearEnrolled);
					
					Text mainText;
					try {
						student.insertStudentData(student, Student.getStatement());
						mainText = new Text("A record of a student is added into a database");
						insertStage.setTitle("New Student Added");
					}catch(Exception exception) {						
						mainText = new Text("Record not inserted");
						insertStage.setTitle("Error");
					}
					
					GridPane mainTextPane = new GridPane();
					
					mainTextPane.setAlignment(Pos.TOP_CENTER);
					mainTextPane.add(mainText, 0, 0);
					
					
										
					Scene inserted = new Scene(mainTextPane, 300, 200);
					
					
					insertStage.setScene(inserted);
					insertStage.show();
					
				});						
			});
			
			update.setOnAction(e->{
				clearTextFields();
				makeTextFieldEditable();
				GridPane idPane = new GridPane();
				
				Button viewInfo = new Button("View Information");
				viewInfo.setAlignment(Pos.CENTER);
				idPane.addRow(0, idLabel, idTxtField);
				idPane.add(viewInfo, 0, 1);
				idPane.setAlignment(Pos.CENTER);
				Scene toEnterId = new Scene(idPane, 300, 100);
				
				Stage viewStage = new Stage();
				
				viewStage.setTitle("Updating Student");
				viewStage.setScene(toEnterId);
				viewStage.show();
				
				
				viewInfo.setOnAction(viewEvent->{
					Student student = new Student();
					ResultSet rs = null;
					try {
						rs = Student.getStatement().executeQuery("select * from student where studentId = " + Integer.parseInt(idTxtField.getText()));
						
						while(rs.next()) {
							try {
								
								setAllField(String.valueOf(String.valueOf(rs.getInt(1))),rs.getString(2),rs.getString(3),
										rs.getString(4),String.valueOf(rs.getInt(5)), rs.getString(6));
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							
							Button updateStudent = new Button("Save Student Information");
							
							GridPane infoPane = new GridPane();
							GridPane buttonPane = new GridPane();
							buttonPane.setAlignment(Pos.CENTER);
							buttonPane.setVgap(20);
							buttonPane.setPadding(new Insets(20, 0, 0,0));
							
							infoPane.addRow(0, idLabel, idTxtField);
							infoPane.addRow(0, nameLabel, nameTxtField);
							infoPane.addRow(1, emailLabel, emailTxtField);
							infoPane.addRow(1, addressLabel, addressTxtField);
							infoPane.addRow(2, yearEnrolledLabel, yearEnrolledTxtField);
							infoPane.addRow(2, programLabel, programTxtField);
							
							buttonPane.add(updateStudent, 0, 1);
							
							GridPane studentPane = new GridPane();
							studentPane.add(infoPane, 0, 0);
							studentPane.add(buttonPane, 0, 1);
							studentPane.setAlignment(Pos.CENTER);
							
							Scene insertScene = new Scene(studentPane, 500, 200);
							viewStage.setScene(insertScene);
							viewStage.show();
							
							updateStudent.setOnAction(insertEvent->{
								
								student.setId(Integer.parseInt(idTxtField.getText()));
								student.setName(nameTxtField.getText());
								student.setEmail(emailTxtField.getText());
								student.setAddress(addressTxtField.getText());
								student.setEnrollmentYear(Integer.parseInt(yearEnrolledTxtField.getText()));
								student.setProgram(programTxtField.getText());
								
								Text mainText;
								
								try {
									student.updateStudentData(student, Student.getStatement());
									mainText = new Text("A record of a student is updated in the database");
									viewStage.setTitle("Information Updated");
								}
								catch(Exception exception) {
									mainText = new Text("Record not updated");
									viewStage.setTitle("Error");
								}
								
								GridPane mainTextPane = new GridPane();
								
								mainTextPane.add(mainText, 0, 0);
								
								mainTextPane.setAlignment(Pos.TOP_CENTER);
								
								Scene inserted = new Scene(mainTextPane, 300, 200);
								viewStage.setScene(inserted);
								viewStage.show();
								
							});
						}
						
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
				});
			});
			
			viewOne.setOnAction(e->{
				makeTextFieldEditable();
				clearTextFields();
				
				GridPane idPane = new GridPane();
				
				Button viewInfo = new Button("View Information");
				
				idPane.addRow(0, idLabel, idTxtField);
				idPane.add(viewInfo, 0, 1);
				
				Scene toEnterId = new Scene(idPane, 300, 100);
				
				Stage viewStage = new Stage();
				
				viewStage.setTitle("Selecting Student");
				viewStage.setScene(toEnterId);
				viewStage.show();
				
				
				viewInfo.setOnAction(viewEvent->{
				
					ResultSet rs = null;
					try {
						rs = Student.getStatement().executeQuery("select * from student where studentId = " + Integer.parseInt(idTxtField.getText()));
						
						while(rs.next()) {
							try {
								makeTextFieldUnEditable();
								
								setAllField(String.valueOf(String.valueOf(rs.getInt(1))),rs.getString(2),rs.getString(3),
										rs.getString(4),String.valueOf(rs.getInt(5)), rs.getString(6));								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
				
							GridPane infoPane = new GridPane();
							infoPane.addRow(0, idLabel, idTxtField);
							infoPane.addRow(0, nameLabel, nameTxtField);
							infoPane.addRow(1, emailLabel, emailTxtField);
							infoPane.addRow(1, addressLabel, addressTxtField);
							infoPane.addRow(2, yearEnrolledLabel, yearEnrolledTxtField);
							infoPane.addRow(2, programLabel, programTxtField);
								
										
							Scene insertScene = new Scene(infoPane, 500, 200);
							viewStage.setScene(insertScene);
							viewStage.show();
						}
						
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
				});
			});
			
			delete.setOnAction(e->{
				makeTextFieldEditable();
				clearTextFields();
				
				GridPane idPane = new GridPane();
				
				
				Button deleteStudent = new Button("Delete");
				deleteStudent.setAlignment(Pos.CENTER);
				
				idPane.addRow(0, idLabel, idTxtField);
				idPane.add(deleteStudent, 0, 1);
				idPane.setAlignment(Pos.CENTER);
				
				Scene toEnterId = new Scene(idPane, 300, 100);
				
				Stage viewStage = new Stage();
				
				viewStage.setTitle("Selecting Student");
				viewStage.setScene(toEnterId);
				viewStage.show();
				
				
				
				deleteStudent.setOnAction(deleteEvent->{
					
					ResultSet rs = null;

					try {
						rs = Student.getStatement().executeQuery("select * from student where studentId = " + Integer.parseInt(idTxtField.getText()));
						
						if(rs.next()) {
							makeTextFieldUnEditable();
							setAllField(String.valueOf(String.valueOf(rs.getInt(1))),rs.getString(2),rs.getString(3),
									rs.getString(4),String.valueOf(rs.getInt(5)), rs.getString(6));
						}
						else {
							System.out.println("Result set no values");
						}
												
					} catch (Exception excep) {
						excep.printStackTrace();
					}
					
					Alert confirmDelete = new Alert(AlertType.CONFIRMATION);
					confirmDelete.setTitle("Confirmation Dialog");
					confirmDelete.setHeaderText(null);
					confirmDelete.setContentText("Are you sure that you want to delete?");
					Optional<ButtonType> okCancel = confirmDelete.showAndWait();
					
				
					
					if(okCancel.get() == ButtonType.OK) {
						try {
							Student.deleteStudent(Integer.parseInt(idTxtField.getText()), Student.getStatement());
							
								
							GridPane infoPane = new GridPane();
							
								
							infoPane.addRow(0, idLabel, idTxtField);
							infoPane.addRow(0, nameLabel, nameTxtField);
							infoPane.addRow(1, emailLabel, emailTxtField);
							infoPane.addRow(1, addressLabel, addressTxtField);
							infoPane.addRow(2, yearEnrolledLabel, yearEnrolledTxtField);
							infoPane.addRow(2, programLabel, programTxtField);
								
								
							Scene insertScene = new Scene(infoPane, 500, 200);
							viewStage.setTitle("Deleted Record Information");
							viewStage.setScene(insertScene);
							viewStage.show();	
							
						} catch (Exception e1) {
							e1.printStackTrace();
							
						}
					}
					
					
					
				});
				
			});
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
