package javaCode;

/**
 * 
 * @author Kate 
 * Last updated: 11/16 
 * This class is meant to get the customer's name, address, and ID
 *
 */
public class Customers {

	private String name;
	private String address;
	private int ID;

	/**
	 * This is a setter method for name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This is a getter method for name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This is a setter method for address
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * This is a getter method for address
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This is a setter method for ID
	 * @param ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * This is a getter method for ID
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * This is potentially a method to store customer data to the database. 
	 * Work in progress.
	 */
	public void store() {
		// this method will store all of the customer information
	}

}
