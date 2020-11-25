package models;

public class Payment {
private int paymentId;
private float amount;
private int weektotal;
private int weeksdone;
private User customer;
private boolean complete;
private Bike bike;

public Payment() {
	super();
}



public Payment(int paymentId, float amount, int weektotal, User customer, Bike bike) {
	super();
	this.paymentId = paymentId;
	this.amount = amount;
	this.weeksdone = 0;
	this.complete = false;
	this.weektotal = weektotal;
	this.customer = customer;
	this.bike = bike;
}



public Payment(int paymentId, float amount, int weektotal, int weeksdone, User customer, boolean complete, Bike bike) {
	super();
	this.paymentId = paymentId;
	this.amount = amount;
	this.weektotal = weektotal;
	this.weeksdone = weeksdone;
	this.customer = customer;
	this.complete = complete;
	this.bike = bike;
}



public int getPaymentId() {
	return paymentId;
}



public void setPaymentId(int paymentId) {
	this.paymentId = paymentId;
}



public float getAmount() {
	return amount;
}



public void setAmount(float amount) {
	this.amount = amount;
}



public int getWeektotal() {
	return weektotal;
}



public void setWeektotal(int weektotal) {
	this.weektotal = weektotal;
}



public int getWeeksdone() {
	return weeksdone;
}



public void setWeeksdone(int weeksdone) {
	this.weeksdone = weeksdone;
}



public User getCustomer() {
	return customer;
}



public void setCustomer(User customer) {
	this.customer = customer;
}



public boolean isComplete() {
	return complete;
}



public void setComplete(boolean complete) {
	this.complete = complete;
}



public Bike getBike() {
	return bike;
}



public void setBike(Bike bike) {
	this.bike = bike;
}



public int MakePayment() {
	weeksdone++;
	return(weeksdone);
}
public double TotalOwed() {
	double i = (this.getWeektotal()-this.getWeeksdone())*this.getAmount();
	return i;
}



@Override
public String toString() {
	return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", weektotal=" + weektotal + ", weeksdone="
			+ weeksdone + ", customer=" + customer + ", complete=" + complete + ", bike=" + bike + "]";
}

}
