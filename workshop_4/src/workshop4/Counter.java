/**********************************************
Workshop 4
Course:CPP, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 22 June 2022
**********************************************/

package workshop4;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Counter {
	
	int counter;
	
	Counter(){
		counter = 0;
	}
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getCounter() {
		return counter;
	}
	
	public void getOccurences(char ch, String file) {
		BufferedReader br;
		int intRead;
		char charRead;
		try {
			br = new BufferedReader(new FileReader(file));
			
			while((intRead = br.read()) != -1){
				charRead = (char)intRead;
				if(ch == charRead) {
					counter++;
				}
			}
			br.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
		
	}
	
}
