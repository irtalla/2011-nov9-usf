package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controller.UserController;

import io.javalin.Javalin;

public class SpmsAppJavalin {

	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		});
				
		app.start(8080);
		
		app.routes(() -> {
			path("html/users", () -> {
				get(UserController::checkLogin);
				put(UserController::logIn);
				post(UserController::registerUser);
				delete(UserController::logOut);
				
				path(":id", () -> {
					get(UserController::getUserById);
					put(UserController::updateUser);
					delete(UserController::deleteUser);
				});
				
				path(":username", () -> {
					get(UserController::getUserByUsername);
				});
				
				path(":email", () -> {
					get(UserController::getUserByEmail);
				});
				
			});
		});
	}
	
}
