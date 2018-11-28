package ignore;

/**
 * 
 * @author Kate Last updated: 11/27 This class is meant to get the customer's
 *         name and ID
 *
 */
public class Customers {

	private String name;
	private String ID;

	Customers(String name, String ID) {
		this.name = name;
		this.ID = ID;
	}

	/**
	 * This is a setter method for name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This is a getter method for name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This is a setter method for ID
	 * 
	 * @param ID
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * This is a getter method for ID
	 * 
	 * @return ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * This is potentially a method to store customer data to the database. Work
	 * in progress.
	 */
	public void store() {
		// this method will store all of the customer information
	}

}
