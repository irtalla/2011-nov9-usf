package dev.elliman.beans;

public class Offer {
	private Integer id;
	private Person person;
	private Bike bike;
	private Integer price;
	private String status;
	
	private Integer paymentRemaining;
	private Integer paymentSize;
	
	public Offer(Person buyer, Bike bike, Integer offerPrice, Integer paymentSize, Integer debt) {
		person = buyer;
		this.bike = bike;
		price = offerPrice;
		status = "Pending";
		this.paymentSize = paymentSize;
		paymentRemaining = debt;
	}
	
	public void accept() {
		status = "Accepted";
	}
	
	public void reject() {
		status = "Rejected";
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Person getPerson() {
		return person;
	}

	public Bike getBike() {
		return bike;
	}

	public Integer getPrice() {
		return price;
	}

	public String getStatus() {
		return status;
	}
	
	public Integer getPaymentRemaining() {
		return paymentRemaining;
	}

	public void setPaymentRemaining(Integer paymentRemaining) {
		this.paymentRemaining = paymentRemaining;
	}

	public Integer getPaymentSize() {
		return paymentSize;
	}

	public void setPaymentSize(Integer paymentSize) {
		this.paymentSize = paymentSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bike == null) ? 0 : bike.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Offer other = (Offer) obj;
		if (bike == null) {
			if (other.bike != null)
				return false;
		} else if (!bike.equals(other.bike))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", person=" + person.getID() + ", bike=" + bike.getId() + ", price=" + price + ", status=" + status + "]";
	}
	
	
}
