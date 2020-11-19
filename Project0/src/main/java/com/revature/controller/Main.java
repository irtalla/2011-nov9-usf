package com.revature.controller;

import com.revature.controller.Application;


public class Main {
	
	public static void main(String... args) {
		
		Application app = Application.getApplication(); 
		app.init();

		
//		while ( app.isRunning() ) { 
//			app.getUserResponse(); 
//		}
		
	}

}
