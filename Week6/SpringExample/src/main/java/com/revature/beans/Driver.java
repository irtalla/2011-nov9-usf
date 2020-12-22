package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class Driver {
	private String name;
	private String car;
	// @Autowired // field injection
	private Pizza pizza;
	
	public Driver() {
		name = "Rebecca";
		car = "Chevy Astro";
	}
	
	//@Autowired // constructor injection
	public Driver(Pizza p) {
		name = "Ash Ketchum";
		car = "Chevy Volt";
		pizza = p;
	}
	
//	@Autowired // constructor injection
	public Driver(String n, String c, Pizza p) {
		System.out.println("Using constructor");
		name = n;
		car = c;
		pizza = p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public Pizza getPizza() {
		return pizza;
	}
	
	// @Autowired // setter injection
	public void setPizza(Pizza pizza) {
		System.out.println("Set pizza using setter injection");
		this.pizza = pizza;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pizza == null) ? 0 : pizza.hashCode());
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
		Driver other = (Driver) obj;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pizza == null) {
			if (other.pizza != null)
				return false;
		} else if (!pizza.equals(other.pizza))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Driver [name=" + name + ", car=" + car + ", pizza=" + pizza + "]";
	}

}
