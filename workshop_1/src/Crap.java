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

class Crap {
	int getRandomNumber() {
		int randomNo = (int) ((Math.random() * 6) + 1);
		return randomNo;
	}
	
	int rollDice() {
		return getRandomNumber();
	}
}

class CrapTest{
	public static void main(String[] args) {
		Crap crap = new Crap();
		int numOne = crap.rollDice();
		int numTwo = crap.rollDice();
		Boolean pointEstablished = false, done = false;;
		int sum = numOne + numTwo;
		int point = 0;
		System.out.println("You rolled " + numOne + " + " + numTwo + " = " + sum);
		if(sum == 2 || sum == 3 || sum==12) {
			System.out.println("Craps, Better Luck Next Time, You lose");
		}
		else if(sum == 7 || sum == 11) {
			System.out.println("Congratulations, You win");
		}
		else if(sum >= 4 && sum <= 10) {
			System.out.println("Point is (established) set to " + sum);
			point = sum;
			pointEstablished = true;
		}
		
		if(pointEstablished){
			numOne = crap.rollDice();
			numTwo = crap.rollDice();
			sum = numOne + numTwo;
			
			
			while(!done) {
				numOne = crap.rollDice();
				numTwo = crap.rollDice();
				sum = numOne + numTwo;
				System.out.println("You rolled " + numOne + " + " + numTwo + " = " + sum);
				if(sum == 7) {
					System.out.println("Craps, Better Luck Next Time, You lose");
					done = true;
				}
				else if(sum == point) {
					System.out.println("Congratulations, You win");
					done = true;
				}
			}
		}
	}
}