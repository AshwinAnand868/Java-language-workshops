/**********************************************
Workshop 3
Course:CPP, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 15 June 2022
**********************************************/
package geometricobject;

import java.util.Scanner;

public class TestClass {
	
	static int counter = 1;
	
	public static double inputSide(Scanner input) {
		double side;
		
		System.out.print("Enter the length for side " + counter + ": ");
		side = input.nextDouble();
		while(side <= 0) {
			System.out.print("Please enter value greator than 0: ");
			side = input.nextDouble();
		}
		counter++;
		return side;
	}
	
	public static boolean inputYesNo(Scanner input, String yesNo) {
		boolean response = false, exit = false;
		
		while(!exit) {
			if(yesNo.equalsIgnoreCase("Yes") || yesNo.equalsIgnoreCase("Y")) {
				response = true;
				exit = true;
			}
			else if(yesNo.equalsIgnoreCase("No") || yesNo.equalsIgnoreCase("N")) {
				response = false;
				exit = true;
			}
			else {
				System.out.println("Enter valid response please: ");
				yesNo = input.next();
			}
		}
		
		return response;
		
	}
	
	public static void exit() {
		System.out.println("We thank you for trying out our new tool.");
		
	}
	public static void main(String[] args) {
		
		String yesNo, color = "white";
		Triangle triangle = null;
		boolean exit = false, exception;
		
		double s1, s2, s3;
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome User! This tool creates triangles.");
		System.out.println("Would you like to create one? Enter(Y)es/(N)o below");
		
		
		while(!exit){
			counter = 1;
			yesNo = input.next();
			exception = false;
			if(yesNo.equalsIgnoreCase("Yes") || yesNo.equalsIgnoreCase("Y")) {
				System.out.println("\n************************************\n");
				
				s1 = inputSide(input);
			
				s2 = inputSide(input);
				
				s3 = inputSide(input);
				

				
				try {
					triangle = new Triangle(s1, s2, s3);	
				}catch(TriangleException te) {
					System.out.println(te.getMessage());
					exception = true;
					System.out.println("Would you like to try again with correct length for sides? (Y)es/(N)o");
				}
				
				
				
				if(!exception) {
					
					System.out.println("\nDefault color for your triangle will be white.");
					System.out.println("Do you wish to give some another color to your triangle? Enter(Y)es/(N)o below");
					yesNo = input.next();
					if(inputYesNo(input, yesNo)) {
						System.out.print("Please enter your desired color: ");
						color = input.next();
					}
					
					triangle.setColor(color);
					triangle.setFill(true);
					System.out.println("\nYou are all done! Your triangle is ready.");
					
				
					System.out.print("\nBelow are the properties: \n");
					System.out.println(triangle.toString());

				    exit = true;
				    exit();
				}
			}
			else if(yesNo.equalsIgnoreCase("No") || yesNo.equalsIgnoreCase("N")) {
				exit = true;
				exit();
			}
			else {
				System.out.print("Please enter again with valid value: ");
			}
		}
		return;
	}
}
