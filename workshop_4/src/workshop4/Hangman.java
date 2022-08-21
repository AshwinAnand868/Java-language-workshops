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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Hangman {
	
	public static boolean inputYesNo(Scanner input, String yesNo) {
		boolean response = false, exit = false;
		
		while(!exit) {
			if(yesNo.equalsIgnoreCase("Yes") || yesNo.equalsIgnoreCase("Y")) {
				response = true;
				exit = true;
			}
			else if(yesNo.equalsIgnoreCase("No") || yesNo.equalsIgnoreCase("N")) {
				response = false;
				exit = true;
			}
			else {
				System.out.println("Enter valid response please: ");
				yesNo = input.next();
			}
		}
		
		return response;
		
	}
	
	public static void main(String[] args) {
		
		int tries;
		ArrayList<String> wordsList = new ArrayList<String>();
		ArrayList<String> guessedWords = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
		BufferedReader br;
		BufferedWriter bw;
		Random rand;
		String yesNo;
		String helperString = "";
		String randomWord;
		String hiddenWord;
		try {
			
			br = new BufferedReader(new FileReader("Hangman.txt"));
			String word;
			while((word = br.readLine()) != null) {
				wordsList.add(word);
			}
			
			br.close();
		
		}catch(IOException ie) {
			ie.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		do {
			
			rand = new Random();
			randomWord = wordsList.get(rand.nextInt(wordsList.size()));
			hiddenWord = "";
			tries = 0;
			
			
			for(int i = 0; i < randomWord.length(); i++) {
				hiddenWord += "*";
			}
			
			do {

				boolean guessInWord = false;

				boolean guessAlreadyTried = false;
				
				
				System.out.print("(Guess) Enter a letter in word " + hiddenWord + " > ");
				String guess = scanner.next();
				
				while(guess.length() > 1)
				{
					System.out.print("Please enter only one character. Try again> ");
					guess = scanner.next();
					tries++;
				}
				
				
				
				
				
				if(guessedWords.size() > 0) {
					for(int i = 0; i < guessedWords.size(); i++) {	
						if(guessedWords.get(i) == guess) {
							System.out.println("You have already tried " + guess + ", try a new letter");
							guessAlreadyTried = true;
						}
					}
				}
				
				
				if(!guessAlreadyTried) {
					helperString = hiddenWord;
					hiddenWord = "";
					guessedWords.add(guess);
					for(int i = 0; i < randomWord.length(); i++) {	
						if(randomWord.charAt(i) == guess.charAt(0)) {
							hiddenWord += randomWord.charAt(i);
							guessInWord = true;
						}
						else {
							hiddenWord += helperString.charAt(i);
							
						}
					}
					
					
					if(!guessInWord) {
						System.out.println("        " + guess + " is not in the word");
						tries++;
					}
				}
				
				
				
			}while(!randomWord.equals(hiddenWord));
			
			scanner.nextLine();// extracting the new line to make user input possible for first below call
			
			System.out.println("The word is " + hiddenWord + ". You missed " + tries + " time(s).");
			System.out.print("Enter a new word to be added in the memory> ");
			String newWord = scanner.nextLine();
			
			try {
				bw = new BufferedWriter(new FileWriter("hangman.txt",true));
				bw.write(newWord + "\n");
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.print("Would you like to guess another word? Enter(Y)es/(N)o > ");
			yesNo = scanner.next();
		}while(inputYesNo(scanner, yesNo));
		System.out.println("Closing... Thankyou");
	}
}

