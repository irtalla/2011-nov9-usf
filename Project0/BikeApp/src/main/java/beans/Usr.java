package beans;

import java.util.HashSet;
import java.util.Set;

public class Usr {
	private Integer id;
	private String username;
	private String password;
	private Set<Bikes> bikes;
	private Role role;
	
	public Usr() {
		id = 0;
		username = "";
		password = "";
		bikes = new HashSet<Bikes>();
		role = new Role();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setID(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setBikes(Set<Bikes> bikes) {
		this.bikes = bikes;
	}
	public Set<Bikes> getBikes() {
		return bikes;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		final int prime = 31;
		result = prime * result + ((bikes == null) ? 0 : bikes.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode()); 
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
		Usr other = (Usr) obj;
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
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Usr [id=" + id + ", username =" + username + ", password = ******"  + ", role =" + role +  "]";
}
}
