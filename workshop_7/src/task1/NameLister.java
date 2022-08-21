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

package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NameLister {

	static ArrayList<String> boyNameList = new ArrayList<>();
	static ArrayList<String> girlNameList = new ArrayList<>();
	static ArrayList<String> commonNameList = new ArrayList<>();
	public static void main(String[] args) {
		
		
		
		Scanner scanner = new Scanner(System.in);
		String line = "";
		System.out.print("Enter filename: ");
		String filename = scanner.next();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while((line = br.readLine()) != null) {
				String [] lineContent = line.split("\\s+");
				boyNameList.add(lineContent[1]);
				girlNameList.add(lineContent[3]);
				
			}
			
			br.close();
		}catch(IOException ioe) {
			ioe.printStackTrace();
			
		}
		
		for(int i = 0; i < boyNameList.size(); i++) {
			for(int j = 0; j < girlNameList.size(); j++) {
				if(boyNameList.get(i).equals(girlNameList.get(j)))
					commonNameList.add(boyNameList.get(i));
			}	
		}
		
		System.out.println(commonNameList.size() + " names used for both genders");
		
		if(commonNameList.size() > 0) {
			System.out.println("They are listed below: ");
			
	        for (String name : commonNameList) {
	            System.out.println(name);
	        }
	        
	        System.out.println();
	        
		}
		
		Collections.sort(boyNameList);
		Collections.sort(girlNameList);
		
		for(String commonName : commonNameList) {
			boyNameList.remove(commonName);
			girlNameList.remove(commonName);
		}
        
		System.out.println("Boys names without duplicates after sorting: " + boyNameList);
		System.out.println("Girls names without duplicates after sorting: " + girlNameList);
		
		scanner.close();
	}
}
