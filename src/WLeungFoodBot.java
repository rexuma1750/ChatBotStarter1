import java.util.Random;
import java.util.Scanner;

/**
 * A program to carry on conversations with a human user.
 * This version:
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class WLeungFoodBot
{
	//emotion can alter the way our bot responds. Emotion can become more negative or positive over time.
	int emotion = 0;

	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement)
	{

		Scanner in = new Scanner (System.in);
		System.out.println (getGreeting());

		while (!statement.toLowerCase().equals("bye"))
		{
			statement = in.nextLine();
			//getResponse handles the user reply
			if(statement.equals("food"))
			{
				System.out.println("At what price point do you want? Enter your minimum price point.");
				int minPricePoint = in.nextInt();
				System.out.println("Enter you maximum price point.");
				int maxPricePoint = in.nextInt();
				System.out.println("What kind of food? Do you want food from a restaurant, a bar or a food stall?");
				statement = in.nextLine();
			}
			else
			{
				System.out.println(getResponse(statement));
			}
		}

	}
	/**
	 * Get a default greeting 	
	 * @return a greeting
	 */	
	public String getGreeting()
	{
		return "Hello, my name is FoodBot. How are you?";
	}
	
	/**
	 * Gives a response to a user statement
	 * 
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		String response = "";
		
		if (statement.length() == 0)
		{
			response = "Sorry, I didn't catch that. Please repeat what you said..";
		}
		else if (findKeyword(statement, "Restaurant") >= 0)
		{
			response = "I see. Which type of restaurants would you like? Seafood, Vegetarian, Steakhouse, Fast food, Italian, French, American, Asian, Mexican, etc.?";
			emotion++;
		}
		else if (findKeyword(statement, "Bar") >= 0)
		{
			response = "Okay. What kind of bar would you like?";
			emotion++;
		}
		else if (findKeyword(statement, "Food stall") >= 0)
		{
			response = "Food stalls, huh? Let's see here. Would you like a hotdog food stall, bagel food stall, etc?";
			emotion--;
		}
		else if (findKeyword(statement, "Never mind") >= 0)
		{
			response = "Bye, have a great day!";
			emotion--;
		}
		else if (findKeyword(statement, "seafood", 0) >= 0)
		{
			response = "Seafood restaurants near you.";
			emotion++;
		}
		else if (findKeyword(statement, "vegetarian", 0) >= 0)
		{
			response = "Vegetarian restaurants near you";
			emotion++;
		}
		else if (findKeyword(statement, "steakhouse", 0) >= 0)
		{
			response = "Steakhouses near you";
			emotion++;
		}
		else if (findKeyword(statement, "fast food", 0) >= 0)
		{
			response = "Fast food restaurants near you";
			emotion++;
		}
		else if (findKeyword(statement, "italian", 0) >= 0)
		{
			response = "Italian restaurants near you";
			emotion++;
		}
		else if (findKeyword(statement, "french", 0) >= 0)
		{
			response = "French restaurants near you";
			emotion++;
		}
		else if (findKeyword(statement, "american", 0) >= 0)
		{
			response = "American restaurants near you";
			emotion++;
		}
		else if (findKeyword(statement, "asian", 0) >= 0)
		{
			response = "Asian restaurants near you";
			emotion++;
		}
		else if (findKeyword(statement, "mexican", 0) >= 0)
		{
			response = "Mexican restaurants near you";
			emotion++;
		}
		else if (findKeyword(statement, "etc", 0) >= 0)
		{
			response = "Sorry, we currently don't have that in our database. Please select one of the ones from above";
			emotion--;
		}
		else if (findKeyword(statement, "bye", 0) >= 0)
		{
			response = "Are you sure you want to go? Type Bye again to leave.";
			emotion--;
		}
		// Response transforming I want to statement
		else if (findKeyword(statement, "I want to", 0) >= 0)
		{
			response = transformIWantToStatement(statement);
		}
		else if (findKeyword(statement, "I want",0) >= 0)
		{
			response = transformIWantStatement(statement);
		}
		else if (findKeyword(statement, "I'm", 0) >= 0)
		{
			response = transformIFeelToStatement(statement);
		}
		else if (findKeyword(statement,"I " + "you", 0) >= 0)
		{
			response = transformIYouStatement(statement);
		}
		else
		{
			response = getRandomResponse();
		}
		return response;
	}
	
	/**
	 * Take a statement with "I want to <something>." and transform it into 
	 * "Why do you want to <something>?"
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
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
		return "Why do you want to " + restOfStatement + "?";
	}

	
	/**
	 * Take a statement with "I want <something>." and transform it into 
	 * "Would you really be happy if you had <something>?"
	 * @param statement the user statement, assumed to contain "I want"
	 * @return the transformed statement
	 */
	private String transformIWantStatement(String statement)
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
		int psn = findKeyword (statement, "I want", 0);
		String restOfStatement = statement.substring(psn + 6).trim();
		return "Would you really be happy if you had " + restOfStatement + "?";
	}
	
	
	/**
	 * Take a statement with "I <something> you" and transform it into 
	 * "Why do you <something> me?"
	 * @param statement the user statement, assumed to contain "I" followed by "you"
	 * @return the transformed statement
	 */
	private String transformIYouStatement(String statement)
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
		
		int psnOfI = findKeyword (statement, "I", 0);
		int psnOfYou = findKeyword (statement, "you", psnOfI);
		
		String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
		return "Why do you " + restOfStatement + " me?";
	}

	private String transformIFeelToStatement(String statement)
	{
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement
					.length() - 1);
		}

		int IFeel = findKeyword (statement, "I feel", 0);

		String restOfStatement = statement.substring(IFeel + 5).trim();
		return "I'm feeling " + restOfStatement + " too! So, how may I help you?";
	}

	
	
	/**
	 * Search for one word in phrase. The search is not case
	 * sensitive. This method will check that the given goal
	 * is not a substring of a longer string (so, for
	 * example, "I know" does not contain "no").
	 *
	 * @param statement
	 *            the string to search
	 * @param goal
	 *            the string to search for
	 * @param startPos
	 *            the character of the string to begin the
	 *            search at
	 * @return the index of the first occurrence of goal in
	 *         statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal,
			int startPos)
	{
		String phrase = statement.trim().toLowerCase();
		goal = goal.toLowerCase();

		// The only change to incorporate the startPos is in
		// the line below
		int psn = phrase.indexOf(goal, startPos);

		// Refinement--make sure the goal isn't part of a
		// word
		while (psn >= 0)
		{
			// Find the string of length 1 before and after
			// the word
			String before = " ", after = " ";
			if (psn > 0)
			{
				before = phrase.substring(psn - 1, psn);
			}
			if (psn + goal.length() < phrase.length())
			{
				after = phrase.substring(
						psn + goal.length(),
						psn + goal.length() + 1);
			}

			// If before and after aren't letters, we've
			// found the word
			if (((before.compareTo("a") < 0) || (before
					.compareTo("z") > 0)) // before is not a
											// letter
					&& ((after.compareTo("a") < 0) || (after
							.compareTo("z") > 0)))
			{
				return psn;
			}

			// The last position didn't work, so let's find
			// the next, if there is one.
			psn = phrase.indexOf(goal, psn + 1);

		}

		return -1;
	}
	
	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.  
	 * @param statement the string to search
	 * @param goal the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal)
	{
		return findKeyword (statement, goal, 0);
	}
	


	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse ()
	{
		Random r = new Random ();
		if (emotion == 0)
		{	
			return randomNeutralResponses [r.nextInt(randomNeutralResponses.length)];
		}
		if (emotion < 0)
		{	
			return randomAngryResponses [r.nextInt(randomAngryResponses.length)];
		}	
		return randomHappyResponses [r.nextInt(randomHappyResponses.length)];
	}
	
	private String [] randomNeutralResponses = {"Interesting, tell me more",
			"Hmmm.",
			"Do you really think so?",
			"You don't say.",
			"It's all boolean to me.",
			"So, would you like to go for a walk?",
			"Could you say that again?"
	};
	private String [] randomAngryResponses = {"Bahumbug.", "Harumph", "The rage consumes me!"};
	private String [] randomHappyResponses = {"H A P P Y, what's that spell?", "Today is a good day", "You make me feel like a brand new pair of shoes."};
	
}
