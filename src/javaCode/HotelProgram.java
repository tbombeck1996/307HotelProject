package javaCode;

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
			// Rooms
			if (command.equalsIgnoreCase("a")) {
				boolean cont = true;
				while (cont) {
					System.out.println("Enter a room number: ");
					int input = in.nextInt();
					// need to set up database reading
					System.out.println("Price: " + roomNum.getRoomPrice());
					System.out.println("Number of beds: " + roomNum.getRoomPrice());
					System.out.println("Kitchenette: " + roomNum.getKitchenette());
					System.out.println("Handicapped: " + roomNum.getHandicapped());
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
						"Choose from the following options: a)Reserve a room b)Delete a reservation c)Check a room's availablity");
				String option = in.nextLine();
				if (option.equalsIgnoreCase("a")) {
					System.out.println("Which room would you like to reserve? ");
					int roomNum = in.nextInt();
					if (roomNum.getAvailability() == false)
						System.out.println("This room is unavailable.");
					else {
						roomNum.reserveRoom();
					}

				} else if (option.equalsIgnoreCase("b")) {

				} else if (option.equalsIgnoreCase("c")) {

				} else
					break;
			} // Bills
			else if (command.equalsIgnoreCase("c")) {
				System.out.println("Enter a customer's name to look up their bill: ");
				String option = in.nextLine();
				System.out.println("Bill amount: $" + option.getBillAmount());
			} // Reservations
			else if (command.equalsIgnoreCase("d")) {
				System.out.println(
						"Choose from the following options: a)Lookup reservation details b)Find a reservation c)Check in d)Check out");
				String option = in.nextLine();
				if (option.equalsIgnoreCase("a")) {
					//todo
				} else if (option.equalsIgnoreCase("b")) {
					//todo
				} else if (option.equalsIgnoreCase("c")) {
					//todo
				} else if (option.equalsIgnoreCase("d")) {
					//todo
				}
			} // Customers
			else if (command.equalsIgnoreCase("e")) {
				System.out.println("Enter a customer's name: ");
				String option = in.nextLine();
				System.out.println("ID: " + option.getID());
				System.out.println("Address: " + option.getAddress());
			} // Reports
			else if (command.equalsIgnoreCase("f")) {
				System.out.println("Would you like a report on a)occupancy rate or b)housekeeping? ");
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