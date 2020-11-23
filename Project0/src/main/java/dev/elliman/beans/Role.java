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
		options.add(new Option("Logout", 3));
		options.add(new Option("Purchase a Bike", 3));
		options.add(new Option("Add Bike", 2));
		options.add(new Option("Remove Bike", 2));
		options.add(new Option("Promote User", 1));
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
		name = "Admin";
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
}
