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

import java.util.HashMap;
import java.util.Map;

public class CapitalTeller {

	Map<String, String> countriesWithCapital = new HashMap<>();
	
	CapitalTeller(){
		countriesWithCapital.put("Canada", "Ottawa");
		countriesWithCapital.put("India", "New Delhi");
		countriesWithCapital.put("Australia", "Canberra");
		countriesWithCapital.put("Bangladesh", "Dhaka");
		countriesWithCapital.put("Afghanistan", "Kabul");
		countriesWithCapital.put("Chile", "Santiago");
		countriesWithCapital.put("Egypt", "Cairo");
		countriesWithCapital.put("Germany", "Berlin");
		countriesWithCapital.put("Greece", "Athens");
		countriesWithCapital.put("Indonesia", "Jakarta");
		countriesWithCapital.put("Italy", "Rome");
		countriesWithCapital.put("Japan", "Tokyo");
		countriesWithCapital.put("Mauritius", "Port Louis");
		countriesWithCapital.put("Mexico", "Mexico City");
		countriesWithCapital.put("Monaco", "Monaco");
		countriesWithCapital.put("Nepal", "Kathmandu");
		countriesWithCapital.put("Netherlands", "Amsterdam");
		countriesWithCapital.put("New Zealand", "Wellington");
		countriesWithCapital.put("Pakistan", "Islamabad");
		countriesWithCapital.put("Saudi Arabia", "Riyadh");
		countriesWithCapital.put("Scotland", "Edinburgh");
		countriesWithCapital.put("Singapore", "Singapore");
		countriesWithCapital.put("South Korea", "Seoul");
		countriesWithCapital.put("Sri Lanka", "Sri Jayawardenapura Kotte");
		countriesWithCapital.put("Thailand", "Bangkok");
	}
	
	public String getCapital(String country) {
		return countriesWithCapital.get(country);
	}
}
