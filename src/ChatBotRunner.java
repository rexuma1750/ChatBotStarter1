import java.util.Scanner;

/**
 * A simple class to run our chatbot teams.
 * @author Brooklyn Tech CS Department
 * @version September 2018
 */
public class ChatBotRunner
{

	/**
	 * Create instances of each chatbot, give it user input, and print its replies. Switch chatbot responses based on which chatbot the user is speaking too.
	 */
	public static void main(String[] args)
	{
		ChatBot1 chatbot1 = new ChatBot1();
		ChatBot2 chatbot2 = new ChatBot2();
		ChatBot3 chatbot3 = new ChatBot3();
		ChatBot4 chatbot4 = new ChatBot4();

		Scanner in = new Scanner (System.in);
		System.out.println("Welcome to the chatbot NYC Edition. Type 1,2,3 or 4 to speak to the other bots");
		String statement = in.nextLine();

		if(statement.equals("4"))
		{
			System.out.println(chatbot4.getGreeting());
		}

		while (!statement.equals("Bye"))
		{
			//Use Logic to control which chatbot is handling the conversation\
			//This example has only chatbot1



			chatbot1.chatLoop(statement);


			statement = in.nextLine();


		}
	}

}
