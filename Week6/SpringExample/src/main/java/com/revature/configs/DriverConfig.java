package com.revature.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.beans.Driver;
import com.revature.beans.Pizza;

@Configuration
public class DriverConfig {
	@Bean
	public static Pizza getVeggiePizza() {
		Pizza pizza = new Pizza();
		return pizza;
	}
	
	@Bean
	public static Pizza getCheesePizza() {
		String[] toppings = {"mozzarella", "cheddar"};
		Pizza pizza = new Pizza("Cheese", toppings);
		return pizza;
	}
	
	@Bean
	public static Driver getDefaultDriver() {
		Driver driver = new Driver();
		return driver;
	}
	
	@Bean
	public static Driver getVeggiePizzaDriver() {
		// constructor injection
		return new Driver("Sierra", "Pontiac Grand Am", getVeggiePizza());
	}
	
	@Bean
	public static Driver getCheesePizzaDriver() {
		// setter injection
		Driver driver = new Driver();
		driver.setPizza(getCheesePizza());
		return driver;
	}
}
