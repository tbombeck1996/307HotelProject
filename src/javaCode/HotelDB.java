package javaCode;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;

/**
 * Updated: 11/29
 * 
 * This class is the program for the hotel database management system. The main
 * method executes everything and the SQL database
 * 
 * @author Katherine DuVall, Thomas Bombeck, Haylee Weaver, Sara Braymen
 */
public class HotelDB {
	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		// Use database.properties
		if (args.length == 0) {
			System.out.println("Usage: java HotelDB propertiesFile");
			System.exit(0);
		}
		// database connection
		SimpleDataSource.init(args[0]);
		try (Connection conn = SimpleDataSource.getConnection(); Statement stat = conn.createStatement()) {
			try {
				stat.execute("DROP TABLE Hotel");
				stat.execute("DROP TABLE Calendar");
			} catch (SQLException e) {
				// get exception if table doesn't exist yet
			}

			// hotel table
			stat.execute("CREATE TABLE Hotel (roomNum INT, " + "roomPrice DECIMAL(5, 2), " + "numOfBeds INT, "
					+ "kitchenette VARCHAR(1), " + "handicapped VARCHAR(1)) ");

			String inputFileNameH = "hotel.txt";
			File inputFileH = new File(inputFileNameH);
			Scanner hot = new Scanner(inputFileH);

			// Inserts all data from hotel.txt
			while (hot.hasNextLine()) {
				int roomNum = hot.nextInt();
				double roomPrice = hot.nextDouble();
				int numOfBeds = hot.nextInt();
				String kitchenette = hot.next().toUpperCase();
				String handicapped = hot.next().toUpperCase();
				String query = "INSERT INTO Hotel (RoomNum, RoomPrice, NumOfBeds, Kitchenette, Handicapped) VALUES ("
						+ roomNum + "," + roomPrice + "," + numOfBeds + ",'" + kitchenette + "','" + handicapped + "')";
				stat.execute(query);
			}

			// Calendar table
			stat.execute("CREATE TABLE Calendar (roomNum INT, " + "date DATE, " + "customerName VARCHAR(30), "
					+ "checkIn VARCHAR(1), " + "checkOut VARCHAR(1)) ");
			String inputFileNameC = "calendar.txt";
			File inputFileC = new File(inputFileNameC);
			Scanner cal = new Scanner(inputFileC);
			// Inserts all data from calendar.txt
			while (cal.hasNextLine()) {
				int roomNum = cal.nextInt();
				String date = cal.next();
				String customerName = cal.next().toUpperCase();
				String checkIn = cal.next().toUpperCase();
				String checkOut = cal.next().toUpperCase();
				String query = "INSERT INTO Calendar (RoomNum, Date, CustomerName, CheckIn, CheckOut) VALUES ("
						+ roomNum + ",'" + date + "','" + customerName + "','" + checkIn + "','" + checkOut + "')";
				stat.execute(query);
			}

			// Employee interface
			Scanner in = new Scanner(System.in);
			boolean continueProgram = true;
			System.out.println("Welcome to the Hotel Management System.");
			while (continueProgram) {
				System.out.println(
						"Select from the following options:\n(A) Rooms\n(B) Bills\n(C) Calendar\n(D) Reports\n(Q) Quit");
				String select = in.next().toUpperCase();

				// QUIT
				if (select.equals("Q")) {
					continueProgram = false;
					System.exit(0);
				}

				// ROOMS
				else if (select.equals("A")) {
					System.out.println("\nRoom specs:\nRoom Number \t Price \t Bedrooms \t Kitchenette \t Handicapped");
					ResultSet printRooms = stat.executeQuery("SELECT * FROM Hotel");
					while (printRooms.next()) {
						int roomNum = printRooms.getInt("RoomNum");
						double roomPrice = printRooms.getDouble("RoomPrice");
						int numOfBeds = printRooms.getInt("NumOfBeds");
						String kitchenette = printRooms.getString("Kitchenette");
						String handicapped = printRooms.getString("Handicapped");
						System.out.println(roomNum + "\t\t" + roomPrice + "\t\t" + numOfBeds + "\t\t" + kitchenette
								+ "\t\t" + handicapped);
					}
					System.out.println();
				}

				// BILLS
				else if (select.equals("B")) {
					System.out.println("\nEnter room number to print the bill: ");
					int num = in.nextInt();
					ResultSet printBill = stat.executeQuery("SELECT RoomPrice FROM Hotel WHERE RoomNum = " + num);
					printBill.next();
					double bill = printBill.getDouble("RoomPrice");
					System.out.println("Customer bill will be: $" + bill + "\n");
				}

				// CALENDAR
				else if (select.equals("C")) {
					System.out.println(
							"\nSelect from the following options:\n(A) Add a reservation\n(B) Delete a reservation\n(C) Check in/Check out\n(D) Print Calendar");
					String command = in.next();
					command = command.toUpperCase();
					// add reservation
					if (command.equals("A")) {
						System.out.println("Enter customer name: ");
						String customerName = in.next().toUpperCase();
						System.out.println("Enter room number: ");
						int roomNum = in.nextInt();
						System.out.println("Enter date YYYY-MM-DD:");
						String date = in.next();
						String checkIn = "N";
						String checkOut = "N";
						String query = "INSERT INTO Calendar (RoomNum, Date, CustomerName, CheckIn, CheckOut) VALUES ("
								+ roomNum + ",'" + date + "','" + customerName + "','" + checkIn + "','" + checkOut
								+ "')";
						stat.execute(query);
					} // delete reservation
					else if (command.equals("B")) {
						System.out.println("Enter a customer name: ");
						String customer = in.next().toUpperCase();
						String query = "DELETE FROM Calendar WHERE CustomerName = '" + customer + "'";
						stat.execute(query);
					} // check in and out
					else if (command.equals("C")) {
						System.out.println("(A) Check In\n(B)Check Out ");
						command = in.next().toUpperCase();
						// check in
						if (command.equals("A")) {
							System.out.println("Enter customer name: ");
							String customer = in.next().toUpperCase();
							String update = "UPDATE Calendar SET CheckIn = 'Y', CheckOut = 'N' WHERE CustomerName = '"
									+ customer + "'";
							stat.executeUpdate(update);
						} // check out
						else if (command.equals("B")) {
							System.out.println("Enter customer name: ");
							String customer = in.next().toUpperCase();
							String update = "UPDATE Calendar SET CheckIn = 'N', CheckOut = 'Y' WHERE CustomerName = '"
									+ customer + "'";
							stat.executeUpdate(update);
						}
					} // print calendar database
					else if (command.equals("D")) {
						System.out.println(
								"\nCurrent Calendar:\nRoom Number \t Date \t\t Customer \t Check In \t Check Out");
						ResultSet printCalendar = stat.executeQuery("SELECT * FROM Calendar");
						while (printCalendar.next()) {
							int roomNum = printCalendar.getInt("roomNum");
							String date = printCalendar.getString("date");
							String customer = printCalendar.getString("customerName");
							String checkIn = printCalendar.getString("checkIn");
							String checkOut = printCalendar.getString("checkOut");
							System.out.println(
									roomNum + "\t\t" + date + "\t" + customer + "\t\t" + checkIn + "\t\t" + checkOut);
						}
						System.out.println();
					}

					// write calendar database to text file
					PrintWriter outputFile = new PrintWriter("calendar.txt");
					ResultSet printTable = stat.executeQuery("SELECT * FROM Calendar");
					String output = "";
					int i = 0;
					while (printTable.next()) {
						if (i > 0)
							output += System.lineSeparator();
						int roomNum = printTable.getInt("RoomNum");
						String date = printTable.getString("Date");
						String customerName = printTable.getString("CustomerName");
						String checkIn = printTable.getString("CheckIn");
						String checkOut = printTable.getString("CheckOut");
						output += roomNum + "\t" + date + "\t" + customerName + "\t" + checkIn + "\t" + checkOut;
						i++;
					}
					outputFile.write(output);
					outputFile.close();
				}

				// reports
				else if (select.equals("D")) {
					System.out.println(
							"\nSelect from the following options:\n(A) Occupancy Report\n(B) Housekeeping Report");
					String command = in.next();
					command = command.toUpperCase();

					// occupancy report
					if (command.equals("A")) {
						ResultSet numCheckedIn = stat
								.executeQuery("SELECT COUNT(*) As count FROM Calendar WHERE checkIn = 'Y'");
						numCheckedIn.next();
						int numChecked = numCheckedIn.getInt("count");
						ResultSet totalRooms = stat.executeQuery("SELECT COUNT(*) As count FROM Hotel");
						totalRooms.next();
						int totalRoom = totalRooms.getInt("count");
						double total = ((numChecked * 1.0) / totalRoom) * 100;
						System.out.println("\nOccupany Rate Report \nTotal Rooms: " + totalRoom + "\nTotal Occupants: "
								+ numChecked + "\nOccupancy Rate: " + total + "%");
						// write report to txt file
						PrintWriter outputFile = new PrintWriter("OccupancyRateReport.txt");
						String output = "";
						output += "Total rooms: " + totalRoom + System.lineSeparator() + "Total occupants: "
								+ numChecked + System.lineSeparator() + "Occupancy Rate: " + total + "%";
						outputFile.write(output);
						outputFile.close();

					} // housekeeping report
					else if (command.equals("B")) {
						ResultSet needHousekeeping = stat
								.executeQuery("SELECT COUNT(RoomNum) As count FROM Calendar WHERE CheckIn = 'N'");
						needHousekeeping.next();
						int housekeepingResult = needHousekeeping.getInt("count");
						System.out.println("\nTotal rooms needing housekeeping: " + housekeepingResult);
						// write report to txt file
						PrintWriter outputFile = new PrintWriter("HousekeepingReport.txt");
						ResultSet printTable = stat.executeQuery("SELECT RoomNum FROM Calendar WHERE CheckIn = 'N'");
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

} // end class