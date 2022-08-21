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

public class TestClass {
	 public static void main(String[] args) {
			
			Scanner input = new Scanner(System.in);
			System.out.print("Enter a filename: ");
			String file = input.next();
			
			int count;
			char ch;
			char chUpper = 'A', chLower= 'a';
			Counter counter = new Counter();
			ch = chUpper;
			while(chUpper <= 'Z') {
				counter.getOccurences(ch, file);
				count = counter.getCounter();
				counter.setCounter(0);
				System.out.println("Number of " + ch + "'s: " + count);
				if(Character.isUpperCase(ch)) {
					ch = chLower;
					chLower++;
				}
				else {
					chUpper++;
					ch = chUpper;					
				}
			}

				input.close();
		 }
}
