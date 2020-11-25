package dev.warrington.beans;

public class Payment {

	private Integer id;
	private Integer customerId;
	private Integer bicycleId;
	private Double totalOwed;
	private Double weeklyPayment;
	private Integer paymentsRemaining;
	
	public Payment(Integer cid, Integer bid, Double owed, Double weekly, Integer left) {
		
		this.customerId = cid;
		this.bicycleId = bid;
		this.totalOwed = owed;
		this.weeklyPayment = weekly;
		this.paymentsRemaining = left;
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getBicycleId() {
		return bicycleId;
	}
	
	public void setBicycleId(Integer bicycleId) {
		this.bicycleId = bicycleId;
	}
	
	public Double getTotalOwed() {
		return totalOwed;
	}
	
	public void setTotalOwed(Double totalOwed) {
		this.totalOwed = totalOwed;
	}

	public Double getWeeklyPayment() {
		return weeklyPayment;
	}

	public void setWeeklyPayment(Double weeklyPayment) {
		this.weeklyPayment = weeklyPayment;
	}

	public Integer getPaymentsRemaining() {
		return paymentsRemaining;
	}

	public void setPaymentsRemaining(Integer paymentsRemaining) {
		this.paymentsRemaining = paymentsRemaining;
	}
	
}