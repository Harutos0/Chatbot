/**
 * A program to carry on conversations with a human user.
 * This version:
 *<ul><li>
 * 		Uses advanced search for keywords 
 *</li><li>
 * 		Will transform statements as well as react to keywords
 *</li></ul>
 * @author Laurie White
 * @version April 2012
 *
 */
import java.util.Scanner;
import java.util.Scanner;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.Random;

public class Magpie4
{
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	

	private Boolean state0, state1, state2, state3, state4;
	int currState=0;

	public Magpie4(){
		state1 = false;
		state2 = false;
		state3 = false;
		state4 = false;
		
		state0=true; // initial state
		currState=0;
	}

	public void stateMachine(){
		while(currState != 5){
			currState=getState();
			setState(currState);
		}
	}

	private int getState(){
		int nextState=0;
		while (nextState<1 || nextState>5){
		  Scanner scan = new Scanner(System.in);  // Create a Scanner object
		  System.out.println("Enter next state (1-4), 5 to end: ");
	
		  nextState = scan.nextInt();  // Read user input
		}
		return nextState;
	}
	
	private void setState(int currState){
		state1=false;
		state2=false;
		state3=false;
		state4=false;

		switch(currState) {
			case 1:
			  state1=true;
			  System.out.println("That's great to hear!");
			  break;
			case 2:
			  state2=true;
			  System.out.println("Not bad, I see.");
			  break;
			case 3:
			  state3=true;
			  System.out.println("Sorry to hear that, I hope you have a better day tomorrow.");
			  Scanner in = new Scanner (System.in);
			  System.out.println("Let's talk about your day. How was your day?");
			  String answer = in.nextLine();
			  if (totalSentiment(answer) > 0){
				System.out.println("Oh that's good for you.");
			  }
			  else if (totalSentiment(answer) > 0){
				System.out.println("To make tomorrow a better day, we need to reflect what we should've done and what we shouldn't have done on that day before ending the day. So, let start that tonight.");
			  }
			  break;
			case 4:
			  state4=true;
			  System.exit(0);
			  break;
			default:
				System.out.println("");
		}
	}
	
	public String getGreeting()
	{
		return "Hello, I'm a chatbot, and I'm here to have a chat with you. Wanna chat? Type quit to end the conversation";
	}

	int num = 0;  //when user answers the correct name
	int num1 = 0; //when user didn't answers the correct name

	public void introduction()
	{
		Scanner in = new Scanner (System.in);
		String username = "";
		//Q1
		System.out.println("Hello again, I'm your tennis mentor. What is your name?");
		String answer = in.nextLine();
		username = answer;
		//Q2
		System.out.println("How are you doing today " + username + "?");
		answer = in.nextLine().toLowerCase();
		if (totalSentiment(answer) > 1)
		{
			setState(1);
		}
		else if (totalSentiment(answer) < 0.4 && totalSentiment(answer) > -0.4)
		{
			setState(2);
		}
		else if (totalSentiment(answer) < 1)
		{
			setState(3);
		}
		else if (answer.equals("quit")){
			setState(4);
		}
		else 
		{
			System.out.println("I see...");
		}

		//Q3
		System.out.println("Let's talk about sport.");
		 System.out.println("Which sport do you like?");
		 answer = in.nextLine().toLowerCase();
		if (findKeyword(answer, "tennis") >= 0)
		{
			System.out.println("Really? I like tennis too!");
		}
		else if (answer.equals("quit")){
			setState(4);
		}
		else
		{
			System.out.println("I don't know much about " + answer + ". Do you want to talk about tennis instead?");
			answer = in.nextLine().toLowerCase();
			if (totalSentiment(answer) < 0)
			{
				System.out.println("Why so negative?");
				answer = in.nextLine().toLowerCase();
				if (totalSentiment(answer) < 0)
				{
					System.out.println("We are talking about tennis");
				}
				else if (totalSentiment(answer) > 0)
				{
					System.out.println("You sound better now. Let's talk about tennis");
				}
				else{
					System.out.println("I guess we're talking about tennis");
				}
			}
			else if (answer.equals("quit")){
				setState(4);
			}
			else{
				System.out.println("");
			}
		}

		 //Q4
	}
	int start = 0;
	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			response = "Say something, please.";
		}

		else if (findKeyword(statement, "no") >= 0
				|| findKeyword(statement, "nope") >= 0
				|| findKeyword(statement, "nah") >= 0)
		{
			response = "Why so negative?";
		}
		else if (findKeyword(statement, "yes") >= 0
				|| findKeyword(statement, "yeah") >= 0
				|| findKeyword(statement, "yea") >= 0
				|| findKeyword(statement, "ya") >= 0
				|| findKeyword(statement, "sure") >= 0
				|| findKeyword(statement, "ok") >= 0
				|| findKeyword(statement, "okay") >= 0
				|| findKeyword(statement, "k") >= 0 )
		{
			if (start == 0){
				introduction();
				response = "Who's your favorite notable men's singles tennis player?";
				start ++;
				num1 = 1;
			}
			
		}
		else if (findKeyword(statement, "Carlos Alcaraz") >= 0
				|| findKeyword(statement, "Rafael Nadal") >= 0
				|| findKeyword(statement, "Daniil Medvedev") >= 0
				|| findKeyword(statement, "Pete Sampras") >= 0
				|| findKeyword(statement, "Andre Agassi") >= 0
				|| findKeyword(statement, "Alexander Zverev") >= 0
				|| findKeyword(statement, "Novak Djokovic") >= 0
				|| findKeyword(statement, "Andy Murray") >= 0
				|| findKeyword(statement, "Kei Nishikori") >= 0
				|| findKeyword(statement, "Michael Chang") >= 0
				|| findKeyword(statement, "Dominic Thiem") >= 0
				|| findKeyword(statement, "Rod Laver") >= 0
				|| findKeyword(statement, "Cameron Norrie") >= 0
				|| findKeyword(statement, "Jimmy Connors") >= 0
				|| findKeyword(statement, "Ken Rosewall") >= 0
				|| findKeyword(statement, "John McEnroe") >= 0
				|| findKeyword(statement, "Ivan Lendl") >= 0
				|| findKeyword(statement, "Roger Federer") >= 0)
		{
			response = "Tell me about him.";
			num = 1;
			num1 = 0;
		}
		else if (num ==1)
		{
			response = "What's his specialty?";
			num ++;
		}
		else if (num ==2)
		{
			response = "Any memorable matches?";
			num ++;
		}

		else if (state1 == true && num ==3){
			response = "You are feeling positive today. Are you planning on playing tennis?";
			num ++;
		}
		else if (state2 == true && num ==3){
			response = "You are feeling neutral today. Are you planning on playing tennis to make today or tomorrow a better day?";
			num ++;
		}
		else if (state3 == true && num ==3){
			response = "You are feeling negative today. Are you planning on playing tennis to make today or tomorrow a better day?";
			num ++;
		}
		else if (state1 == true && num ==4){
			response = "Great! Hope you make it happen!";
			num ++;
		}
		else if (state2 == true && num ==4){
			response = "Nice plan. I hope your experience is pleasant.";
			num ++;
		}
		else if (state3 == true && num ==4){
			response = "Nice plan! Having exercise is the best way to feel better.";
			num ++;
		}

		else if (num ==5)
		{
			response = "Say something cool";
			num ++;
		}
		else if (num ==6)
		{
			response = "What do you mean by \"" + statement + "\"?";
			num ++;
		}
		else if (num ==7)
		{
			response = "Any other sports you like?";
		}

		else if (state1 == true && num ==7){
			response = "You are feeling positive today. Are you planning on playing" + statement + "?";
			num ++;
		}
		else if (state2 == true && num ==7){
			response = "You are feeling neutral today. Are you planning on playing" + statement + "to make today or tomorrow a better day?";
			num ++;
		}
		else if (state3 == true && num ==7){
			response = "You are feeling negative today. Are you planning on playing" + statement + "to make today or tomorrow a better day?";
			num ++;
		}
		else if (state1 == true && num ==8){
			response = "Great! Hope you make it happen!";
			num = 0;
		}
		else if (state2 == true && num ==8){
			response = "Nice plan. I hope your experience is pleasant.";
			num = 0;
		}
		else if (state3 == true && num ==8){
			response = "Nice plan! Having exercise is the best way to feel better.";
			num = 0;
		}

		else if (num1 ==1)
		{
			response = "Can you explain who \"" + statement + "\" is?";
			num1 ++;
		}
		else if (num1 ==2)
		{
			response = "What's that person's specialty?";
			num1 ++;
		}
		else if (num1 ==3)
		{
			response = "Any memorable matches?";
			num1 ++;
		}
		else if (state1 == true && num ==4){
			response = "You are feeling positive today. Are you planning on playing tennis?";
			num ++;
		}
		else if (state2 == true && num ==4){
			response = "You are feeling neutral today. Are you planning on playing tennis to make today or tomorrow a better day?";
			num ++;
		}
		else if (state3 == true && num ==4){
			response = "You are feeling negative today. Are you planning on playing tennis to make today or tomorrow a better day?";
			num ++;
		}
		else if (state1 == true && num ==5){
			response = "Great! Hope you make it happen!";
			num ++;
		}
		else if (state2 == true && num ==5){
			response = "Nice plan. I hope your experience is pleasant.";
			num ++;
		}
		else if (state3 == true && num ==5){
			response = "Nice plan! Having exercise is the best way to feel better.";
			num ++;
		}
		else if (num1 ==6)
		{
			response = "Say something cool";
			num1 ++;
		}
		else if (num1 ==7)
		{
			response = "What do you mean by \"" + statement + "\"?";
			num1 ++;
		}
		else if (num1 ==8)
		{
			response = "Any other sports you like?";
		}
		else if (state1 == true && num1 ==8){
			response = "You are feeling positive today. Are you planning on playing" + statement + "?";
			num1 ++;
		}
		else if (state2 == true && num1 ==8){
			response = "You are feeling neutral today. Are you planning on playing" + statement + "to make today or tomorrow a better day?";
			num1 ++;
		}
		else if (state3 == true && num1 ==8){
			response = "You are feeling negative today. Are you planning on playing" + statement + "to make today or tomorrow a better day?";
			num1 ++;
		}
		else if (state1 == true && num ==9){
			response = "Great! Hope you make it happen!";
			num1 = 0;
		}
		else if (state2 == true && num ==9){
			response = "Nice plan. I hope your experience is pleasant.";
			num1 = 0;
		}
		else if (state3 == true && num ==9){
			response = "Nice plan! Having exercise is the best way to feel better.";
			num1 = 0;
		}


		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}

		else if (findKeyword(statement, "I like", 0) >= 0)
		{
			response = transformILike(statement);
		}

		else
		{
			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0
					&& findKeyword(statement, "me", psn) >= 0)
			{
				response = transformYouMeStatement(statement);
			}
			else
			{
				response = getRandomResponse(statement);
			}
		}
		if (statement.equals("quit")){
			setState(4);

		}
		return response;
	}
	
	private String transformIWantToStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}

	private String transformILike(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword (statement, "I like", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Why do you like " + restOfStatement + "?";
	}


	public static String[] extractNouns(String sentenceWithTags) {
		// Split String into array of Strings whenever there is a tag that starts with "._NN"
		// followed by zero, one or two more letters (like "_NNP", "_NNPS", or "_NNS")
		String[] nouns = sentenceWithTags.split("_NN\\w?\\w?\\b");
		// remove all but last word (which is the noun) in every String in the array
		for(int index = 0; index < nouns.length; index++) {
			nouns[index] = nouns[index].substring(nouns[index].lastIndexOf(" ") + 1)
			// Remove all non-word characters from extracted Nouns
			.replaceAll("[^\\p{L}\\p{Nd}]", "");
		}
		return nouns;
	}
	
	/**
	 * Take a statement with "you <something> me" and transform it into 
	 * "What makes you think that I <something> you?"
	 * @param statement the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement)
	{
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}
		
		int psnOfYou = findKeyword (statement, "you", 0);
		int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
		
		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I " + restOfStatement + " you?";
	}
	
	private int findKeyword(String statement, String goal, int startPos)
	{
		String phrase = statement.trim();
		//  The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
		
		//  Refinement--make sure the goal isn't part of a word 
		while (psn >= 0) 
		{
			//  Find the string of length 1 before and after the word
			String before = " ", after = " "; 
			if (psn > 0)
			{
				before = phrase.substring (psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}
			
			//  If before and after aren't letters, we've found the word
			if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
					&& ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
			{
				return psn;
			}
			
			//  The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
			
		}
		
		return -1;
	}
	
	
	
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse(String statement)
	{
		final int NUMBER_OF_RESPONSES = 11;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Interesting, tell me more.";
		}
		else if (whichResponse == 1)
		{
			response = "What's your favorite tennis shot?";
		}
		else if (whichResponse == 2)
		{
			response = "Any other sports you like?";
		}
		else if (whichResponse == 3)
		{
			response = "You don't say.";
		}
		else if (whichResponse == 4)
		{
			response = "How long have you been playing?";
		}
		else if (whichResponse == 5)
		{
			response = "Why did you start playing sport?";
		}
		else if (whichResponse == 6)
		{
			response = "Any mentor?";
		}
		else if (whichResponse == 7)
		{
			response = "What do you mean by \"" + statement + "\"?";
		}
		else if (whichResponse == 8)
		{
			response = "What do you mean by \"" + statement + "\"?";
		}
		else if (whichResponse == 9)
		{
			response = "What do you mean by \"" + statement + "\"?";
		}
		else if (whichResponse == 10)
		{
			response = "What do you mean by \"" + statement + "\"?";
		}

		return response;
	}

  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();


  private static final String SPACE = " ";

  static {
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while (input.hasNextLine()) {
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0], Double.parseDouble(temp[1]));
        // System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }

    // read in the positive adjectives in postiveAdjectives.txt
    try {
      Scanner input = new Scanner(new File("SimpleReview.txt"));
      while (input.hasNextLine()) {
        String temp = input.nextLine().trim();
        String[] arrOfStr = temp.split(" ", -2);
        // System.out.println(arrOfStr);
        // posAdjectives.add(temp);
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }
    try {
      Scanner input = new Scanner(new File("SimpleReview.txt"));
      while (input.hasNextLine()) {
        String temp = input.nextLine().trim();
        String[] arrOfStr = temp.split(" ", -2);
        // System.out.println(arrOfStr);
        // posAdjectives.add(temp);
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }

    // read in the negative adjectives in negativeAdjectives.txt
    try {
      Scanner input = new Scanner(new File("negativeAdjectives.txt"));
      while (input.hasNextLine()) {
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    } catch (Exception e) {
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }
  }

  /**
   * returns a string containing all of the text in fileName (including
   * punctuation),
   * with words separated by a single space
   */
  public static String textToString(String fileName) {
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));

      // add 'words' in the file to the string, separated by a single space
      while (input.hasNext()) {
        temp = temp + input.next() + " ";
      }
      input.close();

    } catch (Exception e) {
      System.out.println("Unable to locate " + fileName);
    }
    // make sure to remove any additional space that may have been added at the end
    // of the string.
    return temp.trim();
  }

  /**
   * @returns the sentiment value of word as a number between -1 (very negative)
   *          to 1 (very positive sentiment)
   */
  public static double sentimentVal(String word) {
    try {
      return sentiment.get(word.toLowerCase());
    } catch (Exception e) {
      return 0;
    }
  }

  public static double totalSentiment(String string) {
    List<String> arrayList = new ArrayList<>();
    double score = 0;
    // try {
    //   Scanner input = new Scanner(new File("cleanSentiment.csv"));
    //   while (input.hasNextLine()) {
    //     String temp = input.nextLine().trim();
        String[] arrOfStr = string.split(" ", 100000000);
        for (String a : arrOfStr) {
          if (a.substring(a.length()-1, a.length()).equals("!")){
            a = a.substring(0, a.length()-1);
            double temp1 = sentimentVal(a);
            score += temp1*10;
            // System.out.println(temp1*10);
          }
          else{
            removePunctuations(a);
            arrayList.add(a);
          }
          // System.out.println(a);
        }
    //   }
    //   input.close();
    // }

    // catch (Exception e) {
    //   System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    // }

    for (int i = 0; i < arrayList.size(); i++) {
      score += sentimentVal(arrayList.get(i));
      //System.out.println(score);
    }
    return score;
  }

  public static String removePunctuations(String source) {
	return source.replaceAll("\\p{IsPunctuation}", "");
	}

}
