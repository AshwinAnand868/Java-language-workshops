/**********************************************
Workshop 7
Course:CPP, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 19 July 2022
**********************************************/

package task2;

import java.util.Scanner;

public class TestClass {
	public static void main(String[] args) {
		CapitalTeller cpt = new CapitalTeller();
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the country to get its capital: ");
		String country = input.nextLine();
		
		String capital = cpt.getCapital(country);
		if(capital != null) {
			System.out.println("Capital of " + country + " is " + capital + "." );
		}
		else {
			System.out.println("Country specified is not stored");
		}
		
		input.close();
	}
}
