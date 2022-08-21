/**********************************************
Workshop 8
Course:JAC444, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 27 July 2022
**********************************************/

package matrixaddition;

import java.util.Arrays;

public class MatrixAddition implements Runnable {
	// matrix addition is possible only if the array1 and array2 are of equal sizes
	double[][] array1;
	double[][] array2;
	
	boolean concurrency;
	
	MatrixAddition(double[][] array1, double[][] array2, boolean concurrency){
		this.array1 = array1;
		this.array2 = array2;
		this.concurrency = concurrency;
	}
	
	public static double[][] parallelAddMatrix(double[][] a, double[][] b){
		double[][] resultantMatrix = new double[a.length][a[0].length];
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) 
				resultantMatrix[i][j] = a[i][j] + b[i][j];
		}
		return resultantMatrix;
	}
	
	public static double[][] sequentialAddMatrix(double[][] a, double[][] b){
		double[][] resultantMatrix = new double[a.length][a[0].length];
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) 
				resultantMatrix[i][j] = a[i][j] + b[i][j];
		}
		return resultantMatrix;
	}
	
	
	public void run() {
		double[][] result = new double[array1.length][array1[0].length];
		if(concurrency)
			 result = parallelAddMatrix(array1, array2);
		else
			 result = sequentialAddMatrix(array1, array2);			
	}
	
	
}
