import java.util.Scanner;

public class ChatBotRunner {
	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args) {
		WLeungFoodBot chatbot1 = new WLeungFoodBot();
		AAnwarEntertainment chatbot2 = new AAnwarEntertainment();
        sfarukbotTourist chatbot3 = new sfarukbotTourist();

		Scanner in = new Scanner (System.in);
		System.out.println("Welcome to the New York chat bot!" + "\n" + "Please type in the name of the bot which you wish to talk to.");
		String statement = in.nextLine();

		if (statement.equalsIgnoreCase("entertainment")) {
			while (!statement.equals("Bye")) {
				chatbot2.chatLoop(statement);
				statement = in.nextLine();
			}
		}
	}
}
