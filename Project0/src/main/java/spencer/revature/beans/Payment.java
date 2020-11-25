package spencer.revature.beans;

import java.util.Date;

public class Payment {
	private int id;
	private double payment;
	private Offer offer;
	//private Date date;  Wasn't sure how to make it work and didn't need it
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	/*public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}*/
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
}
