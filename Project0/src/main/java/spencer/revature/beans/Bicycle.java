package spencer.revature.beans;
import java.util.HashSet;
import java.util.Set;

public class Bicycle {
	private Integer id;
	private String model;
	private BicycleStatus available;
	private Customer owner;
	private Set<Payment> payments;
	private int paymentsDue;
	public Bicycle() {
		paymentsDue=0;
		model="";
		Customer c=new Customer();
		c.setId(1);
		owner=c;
		BicycleStatus bs = new BicycleStatus();
		bs.setId(1);
		available=bs;
	}

	
	public String toString() {
		return "Id:"+id+"  Model:"+model;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public int getPaymentsDue() {
		return paymentsDue;
	}

	public void setPaymentsDue(int paymentsDue) {
		this.paymentsDue = paymentsDue;
	}
	//This is dumb don't let someone full replace
	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public BicycleStatus getAvailable() {
		return available;
	}

	public void setAvailable(BicycleStatus available) {
		this.available = available;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	
}
