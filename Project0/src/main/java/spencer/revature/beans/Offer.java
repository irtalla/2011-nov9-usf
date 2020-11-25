package spencer.revature.beans;

public class Offer {
	private int id;
	private Bicycle bicycle;
	private Customer customer;
	private Employee employee;
	private OfferStatus offerStatus;
	private Double amount;
	private int payments;

	public Offer(){
		Employee e = new Employee();
		e.setId(1);
		employee = e;
		OfferStatus os = new OfferStatus();
		os.setId(2);
		offerStatus= os;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Bicycle getBicycle() {
		return bicycle;
	}
	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public OfferStatus getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}
	public int getPayments() {
		return payments;
	}
	public void setPayments(int payments) {
		this.payments = payments;
	}
}
