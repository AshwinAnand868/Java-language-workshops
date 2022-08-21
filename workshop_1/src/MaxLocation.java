/**********************************************
Workshop 1
Course:JAC444, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 31 May 2022
**********************************************/

import java.util.Scanner;
// finding maximum value in an 2-D array and its location
class MaxLocation {
	int rows, columns;
	double maxValue;
	MaxLocation(){
		rows = 0;
		columns = 0;
		maxValue = 0;
	}
	MaxLocation(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
	}
	MaxLocation(int rows, int columns, double maxValue){
		this.rows = rows;
		this.columns = columns;
		this.maxValue = maxValue;
	}
	
	MaxLocation findMaxValueWithLocation(double[][] doubles, int rows, int cols){
		int i,j , row = 0, col = 0;

		double max = doubles[0][0];
		for(i = 0; i<rows ; i++) {
			for(j = 0; j< cols; j++){
			     if(doubles[i][j] > max)
			     {
			    	 max = doubles[i][j];
			    	 row = i;
			    	 col = j;
			     } 
			}
		}
	
		MaxLocation mL = new MaxLocation(row + 1, col + 1, max);
		return mL;
	}
}

class MaxLocationTest{
	
	public static void print() {
		for(int i = 0; i < 10; i++) {
			System.out.print("*");
		}
		System.out.print(" ");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of rows and columns in the array: ");
		int rows = sc.nextInt();
		int cols = sc.nextInt();
		
		
		MaxLocation maxLoc = new MaxLocation(rows, cols);
		double [][] nums = new double[rows][cols];		
		int i,j;
		
		print();
		System.out.print("Array Input");
		print();
		System.out.println();
		
		for(i = 0; i<rows; i++) {
			for(j = 0; j< cols; j++) {
				System.out.print("Enter Elem" + "[" + i  + "]" + "[" + j + "]" + ": ");
				nums[i][j] = sc.nextDouble();
			}
		}
		
		maxLoc = maxLoc.findMaxValueWithLocation(nums, rows, cols);
		
		System.out.println("Max Element: " + maxLoc.maxValue + " is at " + "(" + (maxLoc.rows)  + "," + (maxLoc.columns) + ")");
		print();
		System.out.print("Program Ends");
		print();
	}
}
