package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.AuthController;

import io.javalin.Javalin;

public class App {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			//config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		    config.requestLogger((ctx, ms) -> {
		        System.out.printf("Request took: %fms\n", ms); 
		    });
		});
		
		app.start(4000);
		
		app.routes(() -> {
			// authentication route for login 
			path("api/auth/login", () -> {
				post(AuthController::login);
			}); 
		});
		

		
//		app.routes(() -> {
//			// all requests to /cats go to this handler
//			path("cats", () -> {
//				get(CatController::getAvailableCats); // get available cats is the default
//				post(CatController::addCat); // add a cat
//				path(":id", () -> {
//					get(CatController::getCatById); // get a cat by id
//					put(CatController::updateCat); // update a cat
//					delete(CatController::deleteCat); // delete a cat
//				});
//				path ("all", () -> {
//					get(CatController::getAllCats); // get all cats
//				});
//				path ("adopt/:id", () -> {
//					put(CatController::adoptCat); // adopt a cat by its id
//				});
//			});
//			// all requests to /users go to this handler
//			path("users", () -> {
//				put(PersonController::logIn); // log in user
//				post(PersonController::registerUser); // register new user
//				delete(PersonController::logOut); // log out user
//				path (":id", () -> {
//					get(PersonController::getUserById); // get user by id
//					put(PersonController::updateUser); // update user
//					delete(PersonController::deleteUser); // delete user
//				});
//			});
//		});
	}
}
