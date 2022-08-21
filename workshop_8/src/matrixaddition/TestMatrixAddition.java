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

 * For implementation, I needed to use currentTimeMillis()
 * method and copyOfRange() method and I found their use 
 * with the help of the below resources:
 * For currentTimeMillis() - https://www.tutorialspoint.com/how-to-measure-execution-time-for-a-java-method
 * For copyOfRange() - https://www.tutorialspoint.com/How-to-divide-an-array-into-half-in-java#:~:text=Using%20the%20copyOfRange()%20method,2%20to%20length%20to%20other.
**********************************************/


package matrixaddition;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMatrixAddition {
	public static void main(String[] args) {
		
		long startTime, endTime;
		
		int commonSize = 2000;
        double[][] array1 = new double[commonSize][commonSize];
        double[][] array2 = new double[commonSize][commonSize];
        
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) 
            	array1[i][j] = Math.random() * 10;
        }
        
        for (int i = 0; i < array2.length; i++) {
            for (int j = 0; j < array2[i].length; j++) 
            	array2[i][j] = Math.random() * 10; 
        }

        System.out.println("Parallel Programming test begins: ");
        ExecutorService exService = Executors.newCachedThreadPool();
        startTime = System.currentTimeMillis();
        exService.execute(new MatrixAddition(Arrays.copyOfRange(array1, 0, 500), Arrays.copyOfRange(array2, 0, 500), true));
        exService.execute(new MatrixAddition(Arrays.copyOfRange(array1, 501, 1000), Arrays.copyOfRange(array2, 501, 1000), true));
        exService.execute(new MatrixAddition(Arrays.copyOfRange(array1, 1001, 1500), Arrays.copyOfRange(array2, 1001, 1500), true));
        exService.execute(new MatrixAddition(Arrays.copyOfRange(array1, 1501, 2000), Arrays.copyOfRange(array2, 1501, 2000), true));
        exService.shutdown();
        
        endTime = System.currentTimeMillis();
       
        System.out.println("Parallel processing of Matrix Addition took " + (endTime - startTime) + " milliseconds.");
        
        System.out.println("Sequential Programming test begins: ");

        Thread aThread = new Thread(new MatrixAddition(array1, array2, false));
        
        startTime = System.currentTimeMillis();
        aThread.start();
        
        try {
			aThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        endTime = System.currentTimeMillis();
        aThread = null;
       
        System.out.println("Sequential processing of Matrix Addition took " + (endTime - startTime) + " millliseconds.");
	}
}
