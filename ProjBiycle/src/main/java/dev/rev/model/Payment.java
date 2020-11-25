package dev.rev.model;

public class Payment {

	private int payment_id;
	private int bicycle_id;
	private int person_id;
	private int actual_payment;
	private int remaining_payment;
	

	public Payment(){
	payment_id=bicycle_id=person_id=actual_payment=remaining_payment=0;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getBicycle_id() {
		return bicycle_id;
	}
	public void setBicycle_id(int bicycle_id) {
		this.bicycle_id = bicycle_id;
	}
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public int getActual_payment() {
		return actual_payment;
	}
	public void setActual_payment(int actual_payment) {
		this.actual_payment = actual_payment;
	}
	public int getRemaining_payment() {
		return remaining_payment;
	}
	public void setRemaining_payment(int remaining_payment) {
		this.remaining_payment = remaining_payment;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + actual_payment;
		result = prime * result + bicycle_id;
		result = prime * result + payment_id;
		result = prime * result + person_id;
		result = prime * result + remaining_payment;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (actual_payment != other.actual_payment)
			return false;
		if (bicycle_id != other.bicycle_id)
			return false;
		if (payment_id != other.payment_id)
			return false;
		if (person_id != other.person_id)
			return false;
		if (remaining_payment != other.remaining_payment)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Payment [payment_id=" + payment_id + ", bicycle_id=" + bicycle_id + ", person_id=" + person_id
				+ ", actual_payment=" + actual_payment + ", remaining_payment=" + remaining_payment + "]";
	}
	
}
