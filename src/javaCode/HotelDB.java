package javaCode;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

/**
 * Updated: 11/27 
 * @author Katherine DuVall
 */
public class HotelDB {
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		// Use database.properties
		if (args.length == 0) {
			System.out.println("Usage: java HotelDB propertiesFile");
			System.exit(0);
		}

		SimpleDataSource.init(args[0]);
		try (Connection conn = SimpleDataSource.getConnection(); Statement stat = conn.createStatement()) {
			try {
				stat.execute("DROP TABLE Hotel");
			} catch (SQLException e) {
				// get exception if table doesn't exist yet
			}

			stat.execute("CREATE TABLE Hotel (roomNum INT, " + "roomPrice DECIMAL(5, 2), " + "numOfBeds INT, "
					+ "kitchenette BOOLEAN, " + "handicapped BOOLEAN) ");

			String inputFileName = "hotel.txt";
			File inputFile = new File(inputFileName);
			Scanner in = new Scanner(inputFile);

			// COMPLETE THIS WHILE LOOP to insert all cars from the input text
			// file
			while (in.hasNextLine()) {
				int roomNum = in.nextInt();
				double roomPrice = in.nextDouble();
				int numOfBeds = in.nextInt();
				boolean kitchenette = in.nextBoolean();
				boolean handicapped = in.nextBoolean();
				String query = "INSERT INTO Hotel (RoomNum, RoomPrice, NumOfBeds, Kitchenette, Handicapped) VALUES ("
						+ roomNum + "," + roomPrice + "," + numOfBeds + "," + kitchenette + "," + handicapped + ")";
				stat.execute(query);
			}

			// Main loop of the program. Complete this while loop.
			Scanner in2 = new Scanner(System.in);
			boolean continueProgram = true;
			while (continueProgram) {
				System.out.println("Select from the following options");
				System.out.println("(Q) Quit");
				System.out.println("(A) Rooms");
				System.out.println("(B) Calendar");
				System.out.println("(C) Bills");
				System.out.println("(D) Reports");
				String select = in2.next();

				select = select.toUpperCase();
				if (select.equals("Q"))
					continueProgram = false;
				else if (select.equals("A")) {
					System.out.println("Room specs:");
					System.out.println("Room Number \t Price \t Bedrooms \t Kitchenette \t Handicapped");
					ResultSet printRooms = stat.executeQuery("SELECT * FROM Hotel");
					while (printRooms.next()) {
						int roomNum = printRooms.getInt("RoomNum");
						double roomPrice = printRooms.getDouble("RoomPrice");
						int numOfBeds = printRooms.getInt("NumOfBeds");
						boolean kitchenette = printRooms.getBoolean("Kitchenette");
						boolean handicapped = printRooms.getBoolean("Handicapped");
						System.out.println(roomNum + "\t\t" + roomPrice + "\t\t" + numOfBeds + "\t\t" + kitchenette
								+ "\t\t" + handicapped);
					}
					System.out.println();
				} else if (select.equals("B")) {
					System.out.println("Select from the following options");
					System.out.println("(A) Add a reservation");
					System.out.println("(B) Delete a reservation");
					System.out.println("(C) Check in/Check out");
					String command = in2.next();
					command = command.toUpperCase();
					if (command.equals("A")) {

					} else if (command.equals("B")) {

					} else if (command.equals("C")) {

					}

					// ResultSet avgResult = stat.executeQuery("SELECT
					// AVG(Efficiency) As ef FROM Car");
					// while (avgResult.next()) {
					// double avg = avgResult.getDouble("ef");
					// System.out.println("Average fuel efficiency (MPG): " +
					// avg + "\n");
					// }
				} else if (select.equals("C")) {
					System.out.println("Enter room number: ");
					int num = in2.nextInt();
					ResultSet printBill = stat.executeQuery("SELECT roomPrice FROM " + num + " FROM Hotel");
					double bill = printBill.getDouble("roomPrice");
					System.out.println("Customer bill: " + bill + "\n");
				} else if (select.equals("D")) {
					System.out.println("Select from the following options");
					System.out.println("(A) Occupancy Report");
					System.out.println("(B) Housekeeping Report");
					String command = in2.next();
					command = command.toUpperCase();
					if (command.equals("A")) {
						Reports oReport = new Reports();
						// todo
					} else if (command.equals("B")) {
						Reports hReport = new Reports();
						// todo
					}
					System.out.println();
				}
			}
			in2.close();
			in.close();
			stat.close();
			conn.close();
		}
	}
}