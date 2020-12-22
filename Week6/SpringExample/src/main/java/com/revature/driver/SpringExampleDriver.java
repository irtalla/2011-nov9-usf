package com.revature.driver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.revature.beans.Driver;
import com.revature.beans.Pizza;
import com.revature.configs.DriverConfig;

public class SpringExampleDriver {
	public static ApplicationContext ac;
	
	public static void main(String[] args) {
		ac = new AnnotationConfigApplicationContext(DriverConfig.class);
		
		Driver defaultDriver = ac.getBean("getDefaultDriver", Driver.class);
		Driver cheesePizzaDriver = ac.getBean("getCheesePizzaDriver", Driver.class);
		Driver veggiePizzaDriver = ac.getBean("getVeggiePizzaDriver", Driver.class);
		
		System.out.println(defaultDriver);
		System.out.println(cheesePizzaDriver);
		System.out.println(veggiePizzaDriver);
		
//		Driver driver = ac.getBean(Driver.class);
//		System.out.println(driver);
//		
//		Pizza pizza = ac.getBean(Pizza.class);
//		System.out.println(pizza);
		
		((AbstractApplicationContext) ac).close();
	}
}
