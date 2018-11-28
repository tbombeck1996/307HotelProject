package ignore;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * 
 * @author Kate Last updated: 11/16 This class is meant as the main menu.
 *         Consists of the main method.
 *
 */

public class HotelProgram {
	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		HotelDB db = new HotelDB();
		// assume you already ran db.generateDatabase();
		db.generateDatabase();
		// interface for users
		boolean start = true;
		while (start) {
			System.out.println("What do you want to do? a)Rooms, b)Calendar, c)Bills, d)Reports");
			String command = in.nextLine();
			// Rooms
			if (command.equalsIgnoreCase("a")) {
				boolean cont = true;
				while (cont) {
					System.out.println("Enter a room number: ");
					int roomNum = in.nextInt();
					Rooms room = db.getRoomSpecs(roomNum);
					System.out.println("Price: " + room.getRoomPrice());
					System.out.println("Number of beds: " + room.getRoomPrice());
					System.out.println("Kitchenette: " + room.getKitchenette());
					System.out.println("Handicapped: " + room.getHandicapped());
					System.out.println("Look up another room? (Y/N)");
					String next = in.nextLine();
					if (next.equalsIgnoreCase("n"))
						cont = false;
				}
			}
			// I DONT KNOW WHAT TO DO THIS WON'T WORK HOW I WANT
			// Calendar
			else if (command.equalsIgnoreCase("b")) {
				System.out.println(
						"Choose from the following options: a)Add a room b)Delete a reservation c)Check in d)Check out");
				String option = in.nextLine();
				if (option.equalsIgnoreCase("a")) {
					System.out.println("Which room would you like to reserve? ");
					int roomNum = in.nextInt();
					// if (roomNum.getAvailability() == false)
					// System.out.println("This room is unavailable.");
					// else {
					// roomNum.reserveRoom();

				} else if (option.equalsIgnoreCase("b")) {
					// todo
				} else if (option.equalsIgnoreCase("c")) {
					// todo
				} else if (option.equalsIgnoreCase("d")) {
					// todo
				}
			}
			// Bills
			else if (command.equalsIgnoreCase("c")) {
				System.out.println("Enter a customer's name to look up their bill: ");
				String customerName = in.nextLine();
				Bills bill = db.getBillFromName(customerName);
				System.out.println("Bill amount: $" + bill.getBillAmount());
			} // Reports
			else if (command.equalsIgnoreCase("d")) {
				System.out.println("Would you like a report on a)occupancy rate b)housekeeping? ");
				String option = in.nextLine();
				if (option.equalsIgnoreCase("a")) {
					System.out.println("Occupancy Rate: ");
					// need occupancy rate data
				} else if (option.equalsIgnoreCase("b")) {
					System.out.println("Housekeeping");
					// need housekeeping data
				}

			} else {
				System.out.println("Please enter a valid command. Would you like to quit? (Y/N)");
				String quit = in.nextLine();
				if (command.equalsIgnoreCase("y"))
					start = false;
				else
					break;
			}
		}

	}
}