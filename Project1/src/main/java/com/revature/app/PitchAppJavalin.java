package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;
import com.revature.controllers.PitchController;
import com.revature.controllers.UserController;

public class PitchAppJavalin {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
	}

}
