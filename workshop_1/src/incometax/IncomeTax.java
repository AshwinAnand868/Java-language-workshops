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

public class IncomeTax {
	
	// constant class members
	static final int SINGLE_FILER = 0;
	static final int MARRIED_JOINTLY_OR_QUALIFYING_WIDOW = 1;
	static final int MARRIED_SEPARATELY = 2;
	static final int HEAD_OF_HOUSEHOLD = 3;
	
	// data fields
	double taxableIncome;
	int filingStatus;
	int[][] intervals;
	double[] rates;
	
	
	// Constructors
	IncomeTax(){
		this.taxableIncome = 0.0;
		this.filingStatus = 0;
		this.intervals = new int[0][0];
		this.rates = new double[0];
	}
	
	IncomeTax(int filingStatus, int[][] intervals, double[] rates, double taxableIncome){
		this.filingStatus = filingStatus;
		this.intervals = intervals;
		this.rates = rates;
		this.taxableIncome = taxableIncome;
	}
		
	// Getters and Setters
	void setTaxableIncome(double taxInc) {
		taxableIncome = taxInc;
	}
	
	double getTaxableIncome() {
		return taxableIncome;
	}
	
	void setFilingStatus(int filingStatus) {
		this.filingStatus = filingStatus;
	}
	
	int getFilingStatus() {
		return filingStatus;
	}
	
	void setIntervals(int[][] intervals) {
		this.intervals = intervals;
	}
	
	int[][] getIntervals(){
		return intervals;
	}
	
	void setRates(double[] rates) {
		this.rates = rates;
	}
	
	double[] getRates() {
		return rates;
	}
	
	// Tax is calculated using below method
	public double calculateTax() {
		double incTax = 0;
		boolean done = false;
		
		if(taxableIncome <= intervals[filingStatus][0]) {
			incTax = taxableIncome * rates[0];
		}
		else
			incTax = intervals[filingStatus][0] * rates[0];
		
		 if(taxableIncome > intervals[filingStatus][0]) {
			 for(int i = 1; i < intervals[filingStatus].length && !done; i++) {
					
					if(taxableIncome > intervals[filingStatus][i]) {
							incTax += (intervals[filingStatus][i] - intervals[filingStatus][i-1]) * rates[i];			
					}
					else{
						incTax += (taxableIncome - intervals[filingStatus][i-1]) * rates[i];
						done = true;
					}		
				}
		 }
			
		return incTax;
	}
}

