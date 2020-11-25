package spencer.revature.beans;

public interface User {
	//Customer and employee share too much, should've been a variable
	Object getPassword();

	void setUsername(String nextLine);

	void setPassword(String nextLine);

	String getUsername();

	void setId(Integer addCustomer);

	Integer getId();

	
}
