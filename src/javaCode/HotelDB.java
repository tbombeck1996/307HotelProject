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
 * need to create a calendar table
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

			String inputFileNameH = "hotel.txt";
			File inputFileH = new File(inputFileNameH);
			Scanner hot = new Scanner(inputFileH);

			// Inserts all data from hotel.txt
			while (hot.hasNextLine()) {
				int roomNum = hot.nextInt();
				double roomPrice = hot.nextDouble();
				int numOfBeds = hot.nextInt();
				boolean kitchenette = hot.nextBoolean();
				boolean handicapped = hot.nextBoolean();
				String query = "INSERT INTO Hotel (RoomNum, RoomPrice, NumOfBeds, Kitchenette, Handicapped) VALUES ("
						+ roomNum + "," + roomPrice + "," + numOfBeds + "," + kitchenette + "," + handicapped + ")";
				stat.execute(query);
			}

			// Calendar table
			stat.execute("CREATE TABLE Calendar (roomNum INT, " + "date DATE, " + "customerName VARCHAR(30), "
					+ "checkIn BOOLEAN, " + "checkOut BOOLEAN) ");
			String inputFileNameC = "calendar.txt";
			File inputFileC = new File(inputFileNameC);
			Scanner cal = new Scanner(inputFileC);
			// Inserts all data from calendar.txt
			while (cal.hasNextLine()) {
				int roomNum = cal.nextInt();
				String date = cal.next();
				String customerName = cal.next();
				boolean checkIn = cal.nextBoolean();
				boolean checkOut = cal.nextBoolean();
				String query = "INSERT INTO Calendar (RoomNum, Date, CustomerName, CheckIn, CheckOut) VALUES ("
						+ roomNum + ",'" + date + "','" + customerName + "'," + checkIn + "," + checkOut + ")";
				stat.execute(query);
			}

			// Employee interface
			Scanner in = new Scanner(System.in);
			boolean continueProgram = true;
			System.out.println("Welcome to the Hotel Management System.");
			while (continueProgram) {
				System.out.println("Select from the following options");
				System.out.println("(A) Rooms");
				System.out.println("(B) Calendar");
				System.out.println("(C) Bills");
				System.out.println("(D) Reports");
				System.out.println("(Q) Quit");
				String select = in.next().toUpperCase();

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
					String command = in.next();
					command = command.toUpperCase();
					if (command.equals("A")) {
						// System.out.println("Customer name: ");
						// String customer = in2.next();
						// System.out.println("Model Name: ");
						// String model = in2.next();
						// System.out.println("MPG: ");
						// double efficiency = in2.nextDouble();
						// System.out.println("Price: ");
						// double price = in2.nextDouble();
						// String query = "INSERT INTO Calendar (Manufacturer,
						// Model, Efficiency, Price) VALUES ('" + manufacturer
						// + "','" + model + "'," + efficiency + "," + price +
						// ")";
						// stat.execute(query);
					} else if (command.equals("B")) {
						String customer = in.next();

					} else if (command.equals("C")) {
						String customer = in.next();

					}
					// write database to file
					PrintWriter outputFile = new PrintWriter("calendar.txt");
					ResultSet printTable = stat.executeQuery("SELECT * FROM Calendar");
					String output = "";
					while (printTable.next()) {
						int roomNum = printTable.getInt("RoomNum");
						String date = printTable.getString("Date");
						String customerName = printTable.getString("CustomerName");
						boolean checkIn = printTable.getBoolean("CheckIn");
						boolean checkOut = printTable.getBoolean("CheckOut");
						output += roomNum + "\t" + date + "\t" + customerName + "\t" + checkIn + "\t" + checkOut
								+ System.lineSeparator();
					}
					outputFile.write(output);
					outputFile.close();
					// Bill
					// todo: print customer name too
				} else if (select.equals("C")) {
					System.out.println("Enter room number: ");
					int num = in.nextInt();
					ResultSet printBill = stat.executeQuery("SELECT RoomPrice FROM Hotel WHERE RoomNum = " + num);
					printBill.next();
					double bill = printBill.getDouble("RoomPrice");
					System.out.println("Customer bill: " + bill + "\n");
				} else if (select.equals("D")) {
					System.out.println("Select from the following options");
					System.out.println("(A) Occupancy Report");
					System.out.println("(B) Housekeeping Report");
					String command = in.next();
					command = command.toUpperCase();
					if (command.equals("A")) {
						// Occupancy Rate table
						ResultSet numCheckedIn = stat
								.executeQuery("SELECT COUNT(*) As count FROM Calendar WHERE checkIn = true");
						numCheckedIn.next();
						int numChecked = numCheckedIn.getInt("count");
						ResultSet totalRooms = stat.executeQuery("SELECT COUNT(*) As count FROM Hotel");
						totalRooms.next();
						int totalRoom = totalRooms.getInt("count");
						double total = (numChecked * 1.0) / totalRoom;
						System.out.println("\nOccupany Rate Report \nTotal Rooms: " + totalRoom + "\nTotal Occupants: "
								+ numChecked + "\nOccupancy Rate: " + total);
						// write report to file
						PrintWriter outputFile = new PrintWriter("OccupancyRateReport.txt");
						String output = "";
						output += "Total rooms: " + totalRoom + System.lineSeparator() + "Total occupants: "
								+ numChecked + System.lineSeparator() + "Occupancy Rate: " + total;
						outputFile.write(output);
						outputFile.close();

					} else if (command.equals("B")) {
						// Housekeeping
						ResultSet needHousekeeping = stat
								.executeQuery("SELECT COUNT(RoomNum) As count FROM Calendar WHERE CheckIn = false");
						needHousekeeping.next();
						int housekeepingResult = needHousekeeping.getInt("count");
						System.out.println("\nTotal rooms needing housekeeping: " + housekeepingResult);
						// write report to file
						PrintWriter outputFile = new PrintWriter("HousekeepingReport.txt");
						ResultSet printTable = stat.executeQuery("SELECT RoomNum FROM Calendar WHERE CheckIn = false");
						String output = "";
						output += "Total rooms needing housekeeping: " + housekeepingResult + System.lineSeparator();
						while (printTable.next()) {
							int roomNum = printTable.getInt("RoomNum");
							output += roomNum + "\tNeeds Housekeeping" + System.lineSeparator();
						}
						outputFile.write(output);
						outputFile.close();
					}
					System.out.println();
				}
			}
			cal.close();
			hot.close();
			in.close();
			stat.close();
			conn.close();
		}
	}

}