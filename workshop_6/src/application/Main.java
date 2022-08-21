/**********************************************
Workshop 6
Course:CPP, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 13 July 2022
**********************************************/

package application;
	
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		
		try {
		
			Label year = new Label("Enter the Year:   ");
			Label gender = new Label("Enter the Gender: ");
			Label name = new Label("Enter the name:  ");
			
			TextField forYear = new TextField();
			TextField forGender = new TextField();
			TextField forName = new TextField();
			
			forYear.setPrefColumnCount(10);
			forGender.setPrefColumnCount(1);
			forName.setPrefColumnCount(10);
			
			
			
			Button submit = new Button("Submit Query");
			Button exit = new Button("Exit");
			submit.setPrefWidth(90);
			exit.setPrefWidth(90);
			
			
			GridPane yearGrid = new GridPane();
			GridPane genderGrid = new GridPane();
			GridPane nameGrid = new GridPane();
			
			FlowPane buttonLayout = new FlowPane();
			GridPane detailGrid = new GridPane();
			GridPane root = new GridPane();
			
			yearGrid.addRow(0, year, forYear);
			genderGrid.addRow(0, gender, forGender);
			nameGrid.addRow(0,  name, forName);
			
			yearGrid.setHgap(10);			
			genderGrid.setHgap(2);
			nameGrid.setHgap(8);

			yearGrid.setPadding(new Insets(15,5,15,5));
			genderGrid.setPadding(new Insets(15,5,15,5));
			nameGrid.setPadding(new Insets(15,5,15,5));
			
			
			buttonLayout.getChildren().add(submit);
			buttonLayout.getChildren().add(exit);
			buttonLayout.setHgap(80);
			buttonLayout.setAlignment(Pos.CENTER);
			buttonLayout.setPadding(new Insets(20,5,15,5));
			
			
			detailGrid.add(yearGrid, 0, 0);
			detailGrid.add(genderGrid, 0, 1);
			detailGrid.add(nameGrid, 0, 2);
			detailGrid.setAlignment(Pos.CENTER);
			root.add(detailGrid, 0, 1);
			root.add(buttonLayout, 0, 2);
			root.setAlignment(Pos.CENTER);
			
			Scene scene = new Scene(root,400,250);
		
			primaryStage.setTitle("Search Name Ranking Application");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
				
			submit.setOnAction(e ->{
				
				String yearInput = forYear.getText();
				String genderInput = forGender.getText();
				String nameInput = forName.getText();		
				String fileName = "babynamesranking" + yearInput + ".txt";
				
				String rank = getRank(yearInput, nameInput, genderInput, fileName);

				String str = "";
				Text mainText;
				GridPane mainTextPane = new GridPane();
				GridPane searchPaneText = new GridPane();
				FlowPane yesNo = new FlowPane();
				GridPane resultRoot = new GridPane();
				Button yes = new Button("Yes");
				Button no  = new Button("No");
				yes.setPrefWidth(90);
				no.setPrefWidth(90);
				
				
				if(!rank.equals("")) {
					
					str = "Boy name " + nameInput + " is ranked #" + rank + " in " + yearInput + " year";
					mainText = new Text(str);
					Text search = new Text("Do you want to Search for another Name:");
					mainTextPane.add(mainText, 0, 0);
					mainTextPane.setAlignment(Pos.TOP_CENTER);
					mainTextPane.setPadding(new Insets(30, 0,30,0));
					
					searchPaneText.add(search, 0, 0);
					searchPaneText.setAlignment(Pos.CENTER);
					searchPaneText.setVgap(10);
					
					yesNo.getChildren().add(yes);
					yesNo.getChildren().add(no);
					yesNo.setAlignment(Pos.CENTER);
					yesNo.setHgap(70);
					
					
					resultRoot.add(mainTextPane, 0, 0);
					resultRoot.add(searchPaneText, 0, 1);
					resultRoot.add(yesNo, 0, 2);			
					
					
					
				}
				else {
					str = "Name not found in the file";
					mainText = new Text(str);
					resultRoot.add(mainText, 0, 0);
					resultRoot.setAlignment(Pos.CENTER);
				}
				
				
				
				
				Scene resultScene = new Scene(resultRoot, 400, 200);
				
				Stage resultStage = new Stage();
				resultStage.setTitle("Search Name Ranking Application");
				resultStage.setScene(resultScene);
				resultStage.show();
				
				yes.setOnAction(eYes->{
					resultStage.hide();
				});
				
				no.setOnAction(eNo ->{
					System.exit(0);
				});
			});
			
			
			exit.setOnAction(e -> {
	            System.exit(0);
	        });
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getRank(String year, String name, String gender, String fileName) {
		
		String rank = "";
		
		String line = "";

		boolean found = false;
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			
			while((line = br.readLine()) != null && !found) {
				String[] lineContent = line.split("\\s+");
				if(gender.equalsIgnoreCase("m")) {
					if(lineContent[1].equalsIgnoreCase(name)) {
						rank = lineContent[0];
						found = true;
					}
				}else if(gender.equalsIgnoreCase("f")){
					if(lineContent[3].equalsIgnoreCase(name)) {
						rank = lineContent[0];
						found = true;
					}
				}
				
			}
			br.close();
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return rank;
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
