package dev.elliman.beans;

import java.util.ArrayList;

import dev.elliman.data.PersonDAO;
import dev.elliman.exceptions.UnautherizedException;

public class Role {
	
	public static ArrayList<Option> options;
	
	private Integer id;
	private String name;
	
	static {
		options = new ArrayList<Option>();
		options.add(new Option("Logout", 4));
		options.add(new Option("View avalible bikes", 4));
		options.add(new Option("Purchase a bike", 4));
		options.add(new Option("View bikes you own", 4));
		options.add(new Option("View your remaining payments", 4));
		options.add(new Option("View your offer status", 4));
		options.add(new Option("Add bike", 3));
		options.add(new Option("Remove bike", 3));
		options.add(new Option("View active offers", 3));
		options.add(new Option("Accept offer", 3));
		options.add(new Option("Reject offer", 3));
		options.add(new Option("View all remaining payments", 3));
		options.add(new Option("Promote user", 2));
		options.add(new Option("Demote user", 2));
	}
	
	public Role() {
		setCustomer();
	}
	
	public Integer getID() {
		return id;
	}
	
	public Integer getLevel() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void promote(Role autherizedUserRole) throws UnautherizedException {
		if(autherizedUserRole == null || autherizedUserRole.getID() > 2) {
			throw new UnautherizedException();
		} else {
			if(autherizedUserRole.getID().equals(1) && id > 1) {
				id--;
			} else if(autherizedUserRole.getID().equals(2) && id > 3) {
				id--;
			} else if(autherizedUserRole.getID().equals(2) && id == 2) {
				throw new UnautherizedException();
			}
		}
	}
	
	public void demote(Role autherizedUserRole) throws UnautherizedException {
		if(autherizedUserRole == null || autherizedUserRole.getID() > 2) {
			throw new UnautherizedException();
		} else {
			if(autherizedUserRole.getID().equals(1) && id < 4 ) {
				id++;
			} else if (autherizedUserRole.getID().equals(2) && id.equals(3)) {
				id++;
			} else if (autherizedUserRole.getID().equals(2) && (id.equals(2) || id.equals(1))) {
				throw new UnautherizedException();
			}
		}
	}
	
	public void setAdmin(Role autherizedUserRole) throws UnautherizedException {
		if(autherizedUserRole == null || autherizedUserRole.getID() > 1) {
			throw new UnautherizedException();
		} else {
			id = 1;
			name = "Admin";
		}
	}
	
	public void setManager(Role autherizedUserRole) throws UnautherizedException {
		if(autherizedUserRole == null || autherizedUserRole.getID() > 1) {
			throw new UnautherizedException();
		} else {
			id = 2;
			name = "Manager";
		}
	}
	
	public void setEmployee(Role autherizedUserRole) throws UnautherizedException {
		if(autherizedUserRole == null || autherizedUserRole.getID() > 3) {
			throw new UnautherizedException();
		} else {
			id = 3;
			name = "Employee";
		}
	}
	
	public void setCustomer(){
		id = 4;
		name = "Customer";
	}
	
	/*
	 * This exists to create the original admin.
	 * If the class that calls this has access to the database then they are allowed to create the admin user.
	 */
	public void setAdmin(PersonDAO auth) {
		id = 1;
		name = "Admin";
	}
	
	public void setLevel(PersonDAO auth, Integer level) {
		id = level;
		switch (level) {
		case 1:
			name = "Admin";
			break;
		case 2:
			name = "Manager";
			break;
		case 3:
			name = "Employee";
			break;
		case 4:
			name = "Customer";
			break;
		}
	}
	
	public String[] getOptions() {
		ArrayList<String> userOptions = new ArrayList<>();
		for(Option o : options) {
			if(o.LEVEL >= id) {
				userOptions.add(o.TYPE);
			} else {
				break;
			}
		}
		String[] uO = new String[userOptions.size()];
		return userOptions.toArray(uO);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
