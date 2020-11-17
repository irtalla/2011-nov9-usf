package dev.elliman.beans;

import dev.elliman.data.PersonDAO;
import dev.elliman.exceptions.UnautherizedException;

public class Role {
	private Integer id;
	private String name;
	
	public Integer getID() {
		return id;
	}
	
	public Integer getLevel() {
		return id;
	}
	
	public void setAdmin(Role autherizedUserRole) throws UnautherizedException {
		if(autherizedUserRole == null || autherizedUserRole.getID() > 0) {
			throw new UnautherizedException();
		} else {
			id = 0;
			name = "Admin";
		}
	}
	
	public void setManager(Role autherizedUserRole) throws UnautherizedException {
		if(autherizedUserRole == null || autherizedUserRole.getID() > 0) {
			throw new UnautherizedException();
		} else {
			id = 1;
			name = "Manager";
		}
	}
	
	public void setEmployee(Role autherizedUserRole) throws UnautherizedException {
		if(autherizedUserRole == null || autherizedUserRole.getID() > 2) {
			throw new UnautherizedException();
		} else {
			id = 2;
			name = "Employee";
		}
	}
	
	public void setCustomer(){
		id = 3;
		name = "Customer";
	}
	
	/*
	 * This exists to create the original admin.
	 * If the class that calls this has access to the database then they are allowed to create the admin user.
	 */
	public void setAdmin(PersonDAO auth) {
		id = 0;
		name = "Admin";
	}
}
