package javaCode;

/**
 * This class is meant as a way to get information about the different rooms in the hotel
 * @author Kate
 * Last updated: 11/16
 */
public class Rooms {

	private double roomPrice;
	private int numOfBeds;
	private boolean kitchenette;
	private boolean handicapped;

	/**
	 * Constructor for room number
	 * Work in progress
	 * @param roomNum
	 */
	public Rooms(int roomNum) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Setter method for room price
	 * @param roomPrice
	 */
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	/**
	 * Getter method for room price
	 * @return room price
	 */
	public double getRoomPrice() {
		return roomPrice;
	}

	/**
	 * Setter method for number of beds
	 * @param numOfBeds
	 */
	public void setNumOfBeds(int numOfBeds) {
		this.numOfBeds = numOfBeds;
	}

	/**
	 * Getter method for number of beds
	 * @return number of beds
	 */
	public int getNumOfBeds() {
		return numOfBeds;
	}

	/**
	 * setter method for kitchenette
	 * @param kitchenette
	 */
	public void setKitchenette(boolean kitchenette) {
		this.kitchenette = kitchenette;
	}

	/**
	 * getter method for kitchenette
	 * @return kitchenette 
	 */
	public boolean getKitchenette() {
		return kitchenette;
	}

	/**
	 * setter method for handicapped
	 * @param handicapped
	 */
	public void setHandicapped(boolean handicapped) {
		this.handicapped = handicapped;
	}

	/**
	 * getter method for handicapped
	 * @return handicapped
	 */
	public boolean getHandicapped() {
		return handicapped;
	}

}
