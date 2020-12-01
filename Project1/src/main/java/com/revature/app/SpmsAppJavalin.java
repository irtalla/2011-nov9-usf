package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;

public class SpmsAppJavalin {

	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.enableCorsForAllOrigins();
		});
				
		app.start(8080);
		
		
		app.routes(() -> {
			
		});
	}
	
}
