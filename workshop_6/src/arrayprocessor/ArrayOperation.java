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

package arrayprocessor;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrayOperation{
	
	public static final ArrayProcessor findMax = (double[] array)->{
		double max = 0;
		
		for(int i = 0; i < array.length; i++) {
			if(array[i] > max)
				max = array[i];
		}
		return max;
	};
	
	public static final ArrayProcessor findMin = (double[] array)->{
		double min = array[0];
		
		for(int i = 1; i < array.length; i++) {
			if(array[i] < min)
				min = array[i];
		}
		return min;
	};
	
	public static final ArrayProcessor sum = (double[] array)->{
		double sum = 0;
		
		for(double num : array) {
			sum += num;
		}
		return sum;
	};
	
	public static final ArrayProcessor avg = (double[] array)->{
		return (sum.apply(array)/ array.length);
	};
	
	public static ArrayProcessor counter(double value) {
		
		
		ArrayProcessor getCount = (array)->{
			int count = 0;
			
			for(double num : array) {
				if(value == num)
					count++;
			}
			return count;
		};
		
		return getCount;
	}
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter the length for your array: ");
		int length = reader.nextInt();
		
		double[] enteredArray = new double[length];
		
		int num;
		for(int i = 0; i < length; i++) {
			num = i + 1;
			System.out.print("Enter the " + num + " number: ");
			enteredArray[i] = getDouble(reader);
		}
		
		System.out.println("Your entered array is as below: ");
		System.out.println(Arrays.toString(enteredArray));
		
		System.out.println("\n**********Array Processing Start**********");
		System.out.println("Maximum value: " + findMax.apply(enteredArray));
		System.out.println("Minimum value: " + findMin.apply(enteredArray));
		System.out.print("Total of the values: ");
		System.out.printf("%.2f%n",  sum.apply(enteredArray));
		System.out.print("Average of the values: ");
		System.out.printf("%.2f%n",  avg.apply(enteredArray));
		System.out.println("**********Processing Completes**********\n");
		
		System.out.print("Enter a double value to find its occurences in your array: ");
		double valToBeSearched = getDouble(reader);
		ArrayProcessor getCount = counter(valToBeSearched);
		
		int occurrences = (int) getCount.apply(enteredArray);
		
		System.out.println(valToBeSearched + " found " + occurrences + " times in your array");
		
		System.out.println("Thankyou for using the processor");
		
		reader.close();
	}
	
	public static double getDouble(Scanner reader) {
		
		
		double valueEntered = 0;
		boolean rightInput;
		
		do{
			rightInput = true;
			try {
				valueEntered = reader.nextDouble();
			}catch(InputMismatchException ime) {
				
				reader.next();
				System.out.print("Enter valid value(s): ");
				rightInput = false;
				
			}
		}while(!rightInput);
		
		
		return valueEntered;
	}
	
}
