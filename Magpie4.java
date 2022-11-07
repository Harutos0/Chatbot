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

public class Magpie4
{
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	
	public String getGreeting()
	{
		return "Hello, I'm a chatbot, and I'm here to have a chat with you. Wanna chat?";
	}
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	int num = 0;  //when user answers the correct name
	int num1 = 0; //when user didn't answers the correct name

	public void introduction()
	{
		Scanner in = new Scanner (System.in);
		String username = "";
		//Q1
		System.out.println("Hello, I'm your tennis mentor. What is your name?");
		String answer = in.nextLine();
		username = answer;
		//Q2
		System.out.println("How are you doing today " + username + "?");
		answer = in.nextLine().toLowerCase();
		if (findKeyword(answer, "good") >= 0
			|| findKeyword(answer, "great") >= 0
			|| findKeyword(answer, "ok") >= 0
			|| findKeyword(answer, "fine") >= 0
			|| findKeyword(answer, "well") >= 0
			|| findKeyword(answer, "calm") >= 0
			|| findKeyword(answer, "joyful") >= 0
			|| findKeyword(answer, "happy") >= 0)
		{
			System.out.println("That's great to hear!");
		}
		else if (findKeyword(answer, "angry") >= 0
			|| findKeyword(answer, "bad") >= 0
			|| findKeyword(answer, "annoyed") >= 0
			|| findKeyword(answer, "sad") >= 0
			|| findKeyword(answer, "depress") >= 0
			|| findKeyword(answer, "horrible") >= 0
			|| findKeyword(answer, "gloomy") >= 0
			|| findKeyword(answer, "hopeless") >= 0)
		{
			System.out.println("Sorry to hear that, I hope you have a better day tomorrow.");
		}
		else 
		{
			System.out.println("I see...");
		}

		//Q3
		 System.out.println("Which sport do you like?");
		 answer = in.nextLine().toLowerCase();
		if (findKeyword(answer, "tennis") >= 0)
		{
			System.out.println("Really? I like tennis too!");
		}
		else
		{
			System.out.println("I don't know much about " + answer + ". Do you want to talk about tennis instead?");
			answer = in.nextLine().toLowerCase();
		}

		 //Q4
	}

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
				|| findKeyword(statement, "k") >= 0)
		{			
			introduction();
			response = "Who's your favorite notable men's singles tennis player?";
			num1 = 1;
		
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
				|| findKeyword(statement, "Ivan Lendl") >= 0)
		{
			response = "Tell me about him.";
			num = 1;
			num1 = 0;
		}
		else if (num == 1)
		{
			response = "What's his specialty?";
			num++;
		}
		else if (num == 2)
		{
			response = "Any memorable matches?";
			num++;
		}
		else if (num == 3)
		{
			response = "Say something cool";
			num++;
		}
		else if (num == 4)
		{
			response = "What do you mean by \"" + statement + "\"?";
			num = 0;
		}
		else if (num1 == 1)
		{
			response = "Can you explain who \"" + statement + "\" is?";
			num1++;
		}
		else if (num1 == 2)
		{
			response = "What's that person's specialty?";
			num1++;
		}
		else if (num1 == 3)
		{
			response = "Any memorable matches?";
			num1++;
		}
		else if (num1 == 4)
		{
			response = "Say something cool";
			num1++;
		}
		else if (num1 == 5)
		{
			response = "What do you mean by \"" + statement + "\"?";
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
			response = "Why did you start playing tennis?";
		}
		else if (whichResponse == 6)
		{
			response = "Any tennis mentor?";
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

}
