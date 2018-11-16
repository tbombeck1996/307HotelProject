package javaCode;

/**
 * @author Kate 
 * last updated: 11/16 
 * This class is meant for displaying the customer's bill amount for their hotel stay
 **/

public class Bills {

	private double billAmount;
	private String customerName;

	/**
	 * 
	 * @param billAmount
	 *            This method is a setter for the bill amount
	 */
	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	/**
	 * This method is a getter method for the bill amount
	 * 
	 * @return bill amount
	 */
	public double getBillAmount() {
		return billAmount;
	}

	/**
	 * This method is a setter method for the customer name
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * This method is a getter method for customer name
	 * 
	 * @return customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

}
