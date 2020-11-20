package com.revature.controller;

import com.revature.beans.Bike;
import com.revature.beans.Product;
import com.revature.controller.Application;
import com.revature.data.ProductPostgres;


public class Main {
	
	public static void main(String... args) {
		
		ProductPostgres postgres_dao = new ProductPostgres(); 
		
		Bike newBike = new Bike(); 
		newBike.setName("X-games");
		newBike.setPrice(449.99);
		
		Product returned_bike = postgres_dao.add(newBike); 
		
		System.out.println(returned_bike.toString()); 
		
//		Application app = Application.getApplication(); 
//		app.init();

		
//		while ( app.isRunning() ) { 
//			app.getUserResponse(); 
//		}
		
	}

}
