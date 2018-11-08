import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class HotelProgram {
	// main menu
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		// interface for users
		boolean start = true;
		while (start) {
			System.out.println(
					"What do you want to do? a)Rooms, b)Calendar, c)Bills, d)Reservations, e)Customers, f)Reports");
			String command = in.nextLine();
			if (command.equalsIgnoreCase("a")) {

			} else if (command.equalsIgnoreCase("b")) {

			} else if (command.equalsIgnoreCase("c")) {

			} else if (command.equalsIgnoreCase("d")) {

			} else if (command.equalsIgnoreCase("e")) {

			} else if (command.equalsIgnoreCase("f")) {

			} else {
				System.out.println("Please enter a valid command. Would you like to quit?");
				String quit = in.nextLine();
				if (command.equalsIgnoreCase("yes"))
					start = false;
				else
					break;
			}
		}

	}
}