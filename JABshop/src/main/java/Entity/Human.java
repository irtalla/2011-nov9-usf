package Entity;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

public class Human {
	private Integer id;
	private String username;
	private String password;
	private Role purpose;
	private Set<Bike> bikes;
	
	
	
	public Human(){
		id = 0;
		username = "";
		password = "";
		purpose = new Role();
		bikes = new HashSet<Bike>();
		
	}
	
	
	public Integer getId() {
		return id;
	}
	public String getUser() {
		return username;
	}
	public String getPass() {
		return password;
	}
	public Role getPurp() {
		return purpose;
	}
	public Set<Bike> getBikeList(){
		return bikes;
	}

	
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUser(String username) {
		this.username = username;
	}
	public void setPass(String password) {
		this.password = password;
	}
	public void setPurp(Role purpose) {
		this.purpose = purpose; 
	}
	public void setBikes(Set<Bike> bikes) {
		this.bikes = bikes;
	}
	public String makeoffer(int a) {
		
		int emp = a;

		if (emp%2==1) 
			
			return "sure";
		else
				return "no thanks";
		}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		if (bikes == null) {
			if (other.bikes != null)
				return false;
		} else if (!bikes.equals(other.bikes))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (purpose == null) {
			if (other.purpose != null)
				return false;
		} else if (!purpose.equals(other.purpose))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

		
	}
	


