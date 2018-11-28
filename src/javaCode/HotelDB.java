package javaCode;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

/**
 * Updated: 11/27 template used from HW5
 * 
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
				stat.execute("DROP TABLE Calendar");
			} catch (SQLException e) {
				// get exception if table doesn't exist yet
			}

			stat.execute("CREATE TABLE Hotel (roomNum INT, " + "roomPrice DECIMAL(5, 2), " + "numOfBeds INT, "
					+ "kitchenette BOOLEAN, " + "handicapped BOOLEAN) ");
			//needs to be completed
			stat.execute("CREATE TABLE Calendar (roomNum INT, " + "customerName VARCHAR(30) ");

			String inputFileName = "hotel.txt";
			File inputFile = new File(inputFileName);
			Scanner in = new Scanner(inputFile);

			// Inserts all data from hotel.txt
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

			// Employee interface
			Scanner in2 = new Scanner(System.in);
			boolean continueProgram = true;
			System.out.println("Welcome to the Hotel Management System.");
			while (continueProgram) {
				System.out.println("Select from the following options");
				System.out.println("(A) Rooms");
				System.out.println("(B) Calendar");
				System.out.println("(C) Bills");
				System.out.println("(D) Reports");
				System.out.println("(Q) Quit");
				String select = in2.next().toUpperCase();

				if (select.equals("Q")) {
					continueProgram = false;
					System.exit(0);
					// Rooms
				} else if (select.equals("A")) {
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
					// Calendar
				} else if (select.equals("B")) {
					System.out.println("Select from the following options");
					System.out.println("(A) Add a reservation");
					System.out.println("(B) Delete a reservation");
					System.out.println("(C) Check in/Check out");
					String command = in2.next();
					command = command.toUpperCase();
					if (command.equals("A")) {
//						System.out.println("Customer name: ");
//						String customer = in2.next();
//						System.out.println("Model Name: ");
//						String model = in2.next();
//						System.out.println("MPG: ");
//						double efficiency = in2.nextDouble();
//						System.out.println("Price: ");
//						double price = in2.nextDouble();
//						String query = "INSERT INTO Calendar (Manufacturer, Model, Efficiency, Price) VALUES ('" + manufacturer
//								+ "','" + model + "'," + efficiency + "," + price + ")";
//						stat.execute(query);
					} else if (command.equals("B")) {
						String customer = in2.next();

					} else if (command.equals("C")) {
						String customer = in2.next();

					}
					// Bill
					// todo: print customer name too
				} else if (select.equals("C")) {
					System.out.println("Enter room number: ");
					int num = in2.nextInt();
					ResultSet printBill = stat.executeQuery("SELECT RoomPrice FROM Hotel WHERE RoomNum = " + num);
					double bill = printBill.getDouble("RoomPrice");
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