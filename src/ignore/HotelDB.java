package ignore;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * This class is a template from HW5. This class is meant to connect and get all of our data from the hotel.txt
 * Work in progress to convert this to work for our project.
 * @author Kate
 * Last updated: 11/16
 */

public class HotelDB {

	private Connection conn;
	private Statement stat;

	HotelDB() {
		try {
			SimpleDataSource.init("database.properties");
			conn = SimpleDataSource.getConnection();
			stat = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateDatabase() {
		// Create tables for the database
		try {
			stat.execute("CREATE TABLE Hotel (roomNum INT, " + "roomPrice DECIMAL(3, 2), " + "numOfBeds INT, "
					+ "kitchenette BOOLEAN, " + "handicapped BOOLEAN) ");
			// stat.execute(create another table)
			// stat.execute("CREATE TABLE bills (amount INT, name STRING)");
		} catch (SQLException e) {

		}
	}

	public Bills getBillFromName(String name) {
		ResultSet billsSet;
		try {
			billsSet = stat.executeQuery("SELECT * FROM bills WHERE name = '" + name + "'");
			billsSet.next();
			double amount = billsSet.getDouble("amount");
			String customerName = billsSet.getString("name");
			Bills bill = new Bills(amount, customerName);
			return bill;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Bills(-1, "error");
	}

	// todo wtf
	public boolean insertBill(Bills bill) {
		try {
			stat.execute("INSERT INTO bills (amount, name, etc) VALUES (" + bill.getBillAmount() + ","
					+ bill.getCustomerName() + ");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true; // set this as boolean if the insert fails but probably not
						// necessary

	}

	public Rooms getRoomSpecs(int roomNum) {
		ResultSet roomSet;
		try {
			roomSet = stat.executeQuery("SELECT * FROM Hotel WHERE roomNum = '" + roomNum + "'");
			roomSet.next();
			double roomPrice = roomSet.getDouble("roomPrice");
			int numOfBeds = roomSet.getInt("numOfBeds");
			boolean kitchenette = roomSet.getBoolean("kitchenette");
			boolean handicapped = roomSet.getBoolean("handicapped");
			Rooms room = new Rooms(roomPrice, numOfBeds, kitchenette, handicapped);
			return room;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Rooms(0.0, 0, false, false);
	}

	public void fillDatabaseFromFile() throws IOException, SQLException, ClassNotFoundException {
		// Use database.properties
		Statement stat = conn.createStatement();
		stat.execute("CREATE TABLE Hotel (roomNum INT, " + "roomPrice DECIMAL(3, 2), " + "numOfBeds INT, "
				+ "kitchenette BOOLEAN, " + "handicapped BOOLEAN) ");

		String inputFileName = "hotel.txt";
		File inputFile = new File(inputFileName);
		Scanner in = new Scanner(inputFile);

		// insert all rooms from txt file
		while (in.hasNextLine()) {
			int roomNum = in.nextInt();
			double roomPrice = in.nextDouble();
			int numOfBeds = in.nextInt();
			boolean kitchenette = in.nextBoolean();
			boolean handicapped = in.nextBoolean();
			String query = "INSERT INTO Hotel (RoomNum, RoomPrice, NumOfBeds, Kitchenette, Handicapped) VALUES ('"
					+ roomNum + "','" + roomPrice + "'," + numOfBeds + "," + kitchenette + "," + handicapped + ")";
			stat.execute(query);
		}
		in.close();
		stat.close();
	}
}
