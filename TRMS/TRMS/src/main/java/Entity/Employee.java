package Entity;

public class Employee {
	private int id;
	private String user;
	private String pass;
	private String fullName;
	private double availFunds;
	private Role rol;
	
	public Employee() {
		id=0;
		user="";
		pass="";
		fullName="";
		availFunds=1000;
		rol = new Role();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public double getAvailFunds() {
		return availFunds;
	}
	public void setAvailFunds(double availFunds) {
		this.availFunds = availFunds;
	}
	public Role getRole() {
		return rol;
	}
	public void setRole(Role rol) {
		this.rol = rol;
	}


}
