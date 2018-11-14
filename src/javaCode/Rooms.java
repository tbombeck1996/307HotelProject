package javaCode;

//kate last worked on
public class Rooms {

	private double roomPrice;
	private int numOfBeds;
	private boolean kitchenette;
	private boolean handicapped;

	public Rooms(int roomNum) {
		// TODO Auto-generated constructor stub
	}

	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public double getRoomPrice() {
		return roomPrice;
	}

	public void setNumOfBeds(int numOfBeds) {
		this.numOfBeds = numOfBeds;
	}

	public int getNumOfBeds() {
		return numOfBeds;
	}

	public void setKitchenette(boolean kitchenette) {
		this.kitchenette = kitchenette;
	}

	public boolean getKitchenette() {
		return kitchenette;
	}

	public void setHandicapped(boolean handicapped) {
		this.handicapped = handicapped;
	}

	public boolean getHandicapped() {
		return handicapped;
	}

}
