package javaCode;

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

			stat.execute("CREATE TABLE Hotel (roomNum INT, " + "roomPrice DECIMAL(3, 2), "
					+ "numOfBeds INT, " + "kitchenette BOOLEAN, " + "handicapped BOOLEAN) ");

			String inputFileName = "hotel.txt";
			File inputFile = new File(inputFileName);
			Scanner in = new Scanner(inputFile);

			// insert all rooms from tzt file
			while (in.hasNextLine()) {
				String manufacturer = in.next();
				String model = in.next();
				double efficiency = in.nextDouble();
				double price = in.nextDouble();
				String query = "INSERT INTO Car (Manufacturer, Model, Efficiency, Price) VALUES ('" + manufacturer
						+ "','" + model + "'," + efficiency + "," + price + ")";
				stat.execute(query);
				// Main loop of the program. Complete this while loop.
				Scanner in2 = new Scanner(System.in);
				boolean continueProgram = true;
				while (continueProgram) {
					System.out.println("Select from the following options");
					System.out.println("(Q) Quit");
					System.out.println("(A) Add a car");
					System.out.println("(C) Calculate average");
					System.out.println("(W) Write the entire table to a text file");
					System.out.println("(P) Print the entire table");
					System.out.println("(M) Print a subset of the cars based on MPG");
					String select = in2.next();

					select = select.toUpperCase();
					if (select.equals("Q"))
						continueProgram = false;
					else if (select.equals("A")) {
						System.out.println("Manufacturer name: ");
						String manufacturer = in2.next();
						System.out.println("Model Name: ");
						String model = in2.next();
						System.out.println("MPG: ");
						double efficiency = in2.nextDouble();
						System.out.println("Price: ");
						double price = in2.nextDouble();
						String query = "INSERT INTO Car (Manufacturer, Model, Efficiency, Price) VALUES ('"
								+ manufacturer + "','" + model + "'," + efficiency + "," + price + ")";
						stat.execute(query);
						System.out.println();
					} else if (select.equals("C")) {
						ResultSet avgResult = stat.executeQuery("SELECT AVG(Efficiency) As ef FROM Car");
						while (avgResult.next()) {
							double avg = avgResult.getDouble("ef");
							System.out.println("Average fuel efficiency (MPG): " + avg + "\n");
						}
					} else if (select.equals("W")) {
						System.out.println("Output file name: ");
						in2.nextLine();
						String file = in2.nextLine();
						PrintWriter outputFile = new PrintWriter(file);
						ResultSet printTable = stat.executeQuery("SELECT * FROM Car");
						String output = "";
						while (printTable.next()) {
							String Manufacturer = printTable.getString("Manufacturer");
							String Model = printTable.getString("Model");
							double Efficiency = printTable.getDouble("Efficiency");
							double Price = printTable.getDouble("Price");
							output += Manufacturer + "\t" + Model + "\t" + Efficiency + "\t" + Price
									+ System.lineSeparator();
						}
						outputFile.write(output);
						outputFile.close();
						System.out.println();
					} else if (select.equals("P")) {
						ResultSet printTable = stat.executeQuery("SELECT * FROM Car");
						while (printTable.next()) {
							String Manufacturer = printTable.getString("Manufacturer");
							String Model = printTable.getString("Model");
							double Efficiency = printTable.getDouble("Efficiency");
							double Price = printTable.getDouble("Price");
							System.out.println(Manufacturer + "\t" + Model + "\t" + Efficiency + "\t" + Price);
						}
						System.out.println();
					} else if (select.equals("M")) {
						System.out.println("Upper bound on efficiency (MPG): ");
						in2.nextLine();
						String upperBound = in2.nextLine();
						ResultSet printSubset = stat.executeQuery("SELECT * FROM Car WHERE Efficiency <=" + upperBound);
						System.out.println("Manufacturer \t Model \t Efficiency \t Price");
						while (printSubset.next()) {
							String Manufacturer = printSubset.getString("Manufacturer");
							String Model = printSubset.getString("Model");
							double Efficiency = printSubset.getDouble("Efficiency");
							double Price = printSubset.getDouble("Price");
							System.out.println(Manufacturer + "\t" + Model + "\t" + Efficiency + "\t" + Price);
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
}