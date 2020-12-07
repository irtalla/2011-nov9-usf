package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;

public class StoryPitchManagementSystemApp {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		});
		
		app.start(8081);
		
		app.routes(() ->{
				//only meant for /users
				path("users", () ->{
					get(UserServicesImpl::validateUser);
				});
				
				//only meant for
		});
	}

}
