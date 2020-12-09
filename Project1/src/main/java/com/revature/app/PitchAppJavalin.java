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
		
		app.routes(() -> {
			
			// all requests to /users go to this handler
			path("users", () -> {
				get(UserController::checkLogin); // get logged in user
				put(UserController::logIn); // log in user
				//post(UserController::registerUser); // register new user
				delete(UserController::logOut); // log out user
				path (":id", () -> {
					get(UserController::getUserById); // get user by id
					put(UserController::updateUser); // update user
					delete(UserController::deleteUser); // delete user
				});
			});
			path("roles", () -> {
				//get(RoleController::getRoles); // get all breeds
			});
		});
	}
	
}
