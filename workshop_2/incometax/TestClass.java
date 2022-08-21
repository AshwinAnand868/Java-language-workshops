/**********************************************
Workshop 2
Course:CPP, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 8 June 2022
**********************************************/

package incometax;

import java.util.Scanner;

public class TestClass {
	
	
	public static void printMenuForComputeTax() {
		System.out.println("\n********************************************");
		System.out.println("Select your status below.");
		System.out.println("0 - single filer");
		System.out.println("1 - married jointly or qualifying widow(er)");
		System.out.println("2 - married separately");
		System.out.println("3 - head of household");
		System.out.println("********************************************");
	}
	
	public static void printMainMenu() {
		System.out.println("\nPlease choose one from below options");
		System.out.println("1 - Compute personal income Tax");
		System.out.println("2 - Print the tax tables for taxable incomes (with range)");
		System.out.println("3 - Exit");
		System.out.print("Choice: ");
	}
	
	public static void printLine() {
		System.out.println("-------------------------------------------------------------");
	}
	
	
	public static void printTaxWithStatusTable(IncomeTax incomeTax, int year, double incomeFrom, double incomeTo, int interval) {
	
		System.out.printf("\n%d tax tables for taxable income from $%.0f to $%.0f\n",year, incomeFrom, incomeTo);
		printLine();
		System.out.println("Taxable    Single    Married Joint    Married     Head of");
		System.out.println("Income               or Qualifying    Separate    a House");
		System.out.println("                       Widow(er)");
		printLine();

		for(double taxInc = incomeFrom; taxInc <= incomeTo; taxInc+=interval) {
			incomeTax.setTaxableIncome(taxInc);
			
			
			incomeTax.setFilingStatus(IncomeTax.SINGLE_FILER);
			
			System.out.printf("%-11.0f", taxInc); // default alignment for spaces is left
			System.out.printf("%-12.2f",incomeTax.calculateTax());
			
			incomeTax.setFilingStatus(IncomeTax.MARRIED_JOINTLY_OR_QUALIFYING_WIDOW);
			
			System.out.printf("%-15.2f",incomeTax.calculateTax());
			
			incomeTax.setFilingStatus(IncomeTax.MARRIED_SEPARATELY);
			
			System.out.printf("%-12.2f",incomeTax.calculateTax());
			
			incomeTax.setFilingStatus(IncomeTax.HEAD_OF_HOUSEHOLD);
			
			System.out.printf("%.2f\n",incomeTax.calculateTax());
		}
	}
	
	public static void main(String[] args) {
		
		Scanner prompt = new Scanner(System.in);
		boolean exit, innerExit;
		int status, choice;
		double income, tax;
		String continueUsing = "";
		double [] taxRates_2009 = {0.10,0.15,0.25,0.28,0.33,0.35};
		int [][] intervals_2009 = {
				{8350, 33950, 82250, 171550, 372950},
				{16700, 67900, 137050, 20885, 37295},
				{8350, 33950, 68525, 104425, 186475},
				{11950, 45500, 117450, 190200, 372950}
		};
		double[] taxRates_2001 = {0.15, 0.275, 0.305, 0.355, 0.391};
		int[][] intervals_2001 = {
	            { 27050, 65550, 136750, 297350 },
	            { 45200, 109250, 166500, 297350 },
	            { 22600, 54625, 83250, 148675 },
	            { 36250, 93650, 151650, 297350 }
	    };
		do {
			exit = false;
			innerExit = false;
			printMainMenu();
			choice = prompt.nextInt();
			if(choice == 1) {
				printMenuForComputeTax();
				
				do {
					innerExit = false;
					System.out.print("Enter the filing status: ");
					status = prompt.nextInt();
					if(status < 0 || status > 3) {
						System.out.print("Invalid status.");
						innerExit = true;
					}
				}
				while(innerExit);
				
				do {
					innerExit = false;
					System.out.print("Enter the Taxable Income: $");
					income = prompt.nextDouble();
					if(income <= 0) {
						System.out.print("Invalid income entry.");
						innerExit = true;
					}
				}while(innerExit);
				
				IncomeTax incomeTax = new IncomeTax(status, intervals_2009, taxRates_2009, income);
				tax = incomeTax.calculateTax();
				
				System.out.println("\nTax is: $" + String.format("%.2f", tax));
				
				System.out.println("\nWant to continue using? Enter (Y)es or (N)o below");
				while(!innerExit) {
					continueUsing = prompt.next();
					System.out.println(continueUsing);
					if(continueUsing.equals("Y") || continueUsing.equals("Yes")){
						innerExit = true;
					}
					else if(continueUsing.equals("N") || continueUsing.equals("No")){
						exit = true;
						innerExit = true;
						System.out.println("Exiting...");
						System.out.println("Thankyou for trying out our calculator.");
					}
					else {
						System.out.println("Invalid entry! Try again below");
					}
				}
				
			}
			else if(choice == 2) {
				double incomeFrom, incomeTo;
				boolean tobeReEntered;
				do {
					tobeReEntered = false;
					System.out.print("Enter the amount From: $");
					incomeFrom = prompt.nextDouble();
					System.out.print("Enter the amount To: $");
					incomeTo = prompt.nextDouble();
					
					if(incomeFrom < 0 || incomeTo < 0) {
						System.out.println("Income cannot be negative. Please input values again.\n");
						tobeReEntered = true;
					}
					else if(incomeFrom > incomeTo){
						System.out.println("Upper limit must be greator; Please input values again.\n");
						tobeReEntered = true;
					}
					else if(incomeFrom == incomeTo) {
						System.out.println("Both incomes are same. Please input values again.\n");
						tobeReEntered = true;
					}
					
				}while(tobeReEntered);
				IncomeTax incomeTax_2001 = new IncomeTax(0, intervals_2001, taxRates_2001, incomeFrom);
				IncomeTax incomeTax_2009 = new IncomeTax(0, intervals_2009, taxRates_2009, incomeFrom);
				printTaxWithStatusTable(incomeTax_2001, 2001, incomeFrom, incomeTo, 1000);
				printTaxWithStatusTable(incomeTax_2009, 2009, incomeFrom, incomeTo, 1000);
				
				
			}
			else if(choice == 3){
				exit = true;
				System.out.println("Exiting...");
				System.out.println("Thankyou for trying out our calculator.");
			}
			else {
				System.out.println("Please provide valid entry.");
			}
				
		}while(!exit);
		
		prompt.close();
	}
}
