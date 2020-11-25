package models;

public class Offer {
	private Integer OfferId;
	private float Amount;
	private User CustomerId;
	private String Status;
	private Bike BikeId;
	
	public Offer() {
		super();
	}

	public Offer( float amount, User customerId,  Bike bikeId) {
		super();
		Amount = amount;
		CustomerId = customerId;
		Status = "Pending";
		BikeId = bikeId;
	}
	public Offer(Integer offerId, float amount, User customerId, String status, Bike bikeId) {
		super();
		OfferId = offerId;
		Amount = amount;
		CustomerId = customerId;
		Status = status;
		BikeId = bikeId;
	}
	public Integer getOfferId() {
		return OfferId;
	}

	public void setOfferId(Integer offerId) {
		OfferId = offerId;
	}

	public float getAmount() {
		return Amount;
	}

	public void setAmount(float amount) {
		Amount = amount;
	}

	public User getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(User customerId) {
		CustomerId = customerId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Bike getBikeId() {
		return BikeId;
	}

	public void setBikeId(Bike bikeId) {
		BikeId = bikeId;
	}

	@Override
	public String toString() {
		return "Offer [OfferId=" + OfferId + ", Amount=" + Amount + ", CustomerId=" + CustomerId + ", Status=" + Status
				+ ", BikeId=" + BikeId + "]";
	}
	
	
}
