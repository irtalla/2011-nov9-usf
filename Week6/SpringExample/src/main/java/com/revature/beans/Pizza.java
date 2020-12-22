package com.revature.beans;

import java.util.Arrays;

import org.springframework.stereotype.Component;

// @Component
public class Pizza {
	private String type;
	private String[] toppings;
	
	public Pizza() {
		type = "Veggie";
		toppings = new String[4];
		toppings[0] = "Green Peppers";
		toppings[1] = "Mushrooms";
		toppings[2] = "Onions";
		toppings[3] = "Pineapple";
	}
	
	public Pizza(String t1, String[] t2) {
		type = t1;
		toppings = t2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getToppings() {
		return toppings;
	}

	public void setToppings(String[] toppings) {
		this.toppings = toppings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(toppings);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Pizza other = (Pizza) obj;
		if (!Arrays.equals(toppings, other.toppings))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pizza [type=" + type + ", toppings=" + Arrays.toString(toppings) + "]";
	}

}
