package beans;

public class Offers {
	private Integer id;
	private Double amount;
	private Integer userId;
	private Bikes bike;
	private String status;
	
	public Offers() {
		id = 0;
		amount = 0.00;
		userId= 0;
		bike = new Bikes();
		status = "Pending";
		
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	
	public void setID(Integer id) {
		this.id = id;
	}
	public Bikes getTargetBike() {
		return bike;
	}
	public void setTargetBike(Bikes bike) {
		this.bike = bike;
	}
	public Integer getOfferer() {
		return userId;
	}
	public void setOfferer(Integer userId) {
		this.userId=userId;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmount() {
		return amount;
	}
	@Override
	public int hashCode() {
		int result = 1;
		final int prime = 31;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((bike == null) ? 0 : bike.hashCode()); 
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
		Offers other = (Offers) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (bike == null) {
			if (other.bike != null)
				return false;
		} else if (!bike.equals(other.bike))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bike [id=" + id + ", UserId=" + userId + ", amount=" + amount + ", target bike=" + bike.getName() + ", status=" + status
				+  "]";
	}
}
