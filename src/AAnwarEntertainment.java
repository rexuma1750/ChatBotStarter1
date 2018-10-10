import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class AAnwarEntertainment
{
	/**
	 * Runs the conversation for this particular chatbot, should allow switching to other chatbots.
	 * @param statement the statement typed by the user
	 */
	public void chatLoop(String statement) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome! My name is Jarvis, I'll assist you with finding things to do around NYC.");
        System.out.println(getGreeting());

        while (!statement.equals("Bye")) {
            statement = in.nextLine();
            addToUserResponses(statement);
            System.out.println(getResponse(statement));
        }
    }

    public String getGreeting() {
	    return "I hope you are doing well, which borough are you in?";
    }

    public String getResponse(String statement) {
	    String response = "";
	    if (statement.length() == 0) {
            response = "Sorry, I didn't get that. What did you want to say?";
        } else if (isInStringArray(boroughs, statement) == 1) {
	        hobbyArrayBuilder();
            System.out.println("Thank you! Now I know you are in " + statement + " Now what are you in the mood for?");
        } else if (hobbies.stream().anyMatch(statement::equalsIgnoreCase)) {
	        System.out.println("Working...");
        }
        return response;
    }

    public void addToUserResponses(String statement) {
	    int nextItemIndex = 0;
	    for (int i = 0; i < userResponses.length; i++) {
	        if (userResponses[i] != null) {
	            nextItemIndex = i + 1;
            }
        }
        userResponses[nextItemIndex] = statement;
    }

    public int isInStringArray(String array[], String string) {
	    for (int i = 0; i < array.length; i++) {
	        if (array[i].equalsIgnoreCase(string)){
	            return 1;
            }
        }
        return 0;
    }

    String userResponses[] = new String[100];
	String boroughs[] = new String[] {"Manhattan", "Brooklyn", "Queens", "Bronx", "Staten Island"};

	ArrayList<String> hobbies = new ArrayList<>();
	public void hobbyArrayBuilder() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("hobbies.txt"));
            String line = reader.readLine();
            for (int i = 0; line != null; i++) {
                hobbies.add(i, line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

	// Provided Methods
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
    private int findKeyword(String statement, String goal, int startPos)
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
}
