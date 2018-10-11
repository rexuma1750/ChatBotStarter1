import java.util.Scanner;

public class ChatBotRunner {
	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args) {
		WLeungFoodBot chatbot1 = new WLeungFoodBot();
		AAnwarEntertainment chatbot2 = new AAnwarEntertainment();
        sfarukbotTourist chatbot3 = new sfarukbotTourist();
		ChatBot4 chatbot4 = new ChatBot4();
		Scanner in = new Scanner (System.in);
		System.out.println("Welcome to the New York chat bot!" + "\n" + "Please type in the name of the bot which you wish to talk to." + "\n" + "We currently have Entertainment, Transportation, Tourism, and Food.");
		String statement = in.nextLine();
		if (statement.equalsIgnoreCase("food")) {
			while (!statement.equals("Bye")) {
				chatbot1.chatLoop(statement);
				statement = in.nextLine();
			}
		}
		if (statement.equalsIgnoreCase("tourism")) {
			while (!statement.equals("Bye")) {
				chatbot3.chatLoop(statement);
				statement = in.nextLine();
			}
		}



		if (statement.equalsIgnoreCase("transportation")) {
			while (!statement.equals("Bye")) {
				chatbot4.chatLoop(statement);
				statement = in.nextLine();
			}
		}
		if (statement.equalsIgnoreCase("entertainment")) {
			while (!statement.equals("Bye")) {
				chatbot2.chatLoop(statement);
				statement = in.nextLine();
			}
		}
	}
}
