package spencer.revature.beans;
import java.util.Set;

public class Customer implements User {
	private Integer id;
	private String username;
	private String password;
	private Set<Offer> offers;
	private Set<Payment> payments;
	private Set<Bicycle> Bicycles;
	
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Offer> getOffers() {
		return offers;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	public Set<Payment> getPayments() {
		return payments;
	}
	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	public Set<Bicycle> getBicycles() {
		return Bicycles;
	}
	public void setBicycles(Set<Bicycle> bicycles) {
		Bicycles = bicycles;
	}
	
}
