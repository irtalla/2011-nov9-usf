package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controller.PersonController;
import com.revature.controller.RequestController;

import io.javalin.Javalin;

public class TrmsAppJavalin {

	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			
			// all requests to /users go to this handler
			//	
			path("users", () -> {
				get(PersonController::checkLogin); // get logged in user
				put(PersonController::logIn); // log in user
				//post(PersonController::registerUser); // register new user
				delete(PersonController::logOut); // log out user
				path (":id", () -> {
					//get(PersonController::getUserById); // get user by id
					//put(PersonController::updateUser); // update user
					//delete(PersonController::deleteUser); // delete user
				});
			});
			//	all request to /requests got this handler
			path("requests", () -> {
				
				get(RequestController::getAllRequests);
				post(RequestController::newRequest);	//	add a new request				
			});
			path("requests/:id", () -> {
				//get(CatController::getCatById); // get a cat by id
				post(RequestController::updateReuest); // update a cat
				//delete(CatController::deleteCat); // delete a cat
			});
			
		});
	}
}
