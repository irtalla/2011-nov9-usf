package dev.rev.app;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import dev.rev.controllers.employeecontroller;
import io.javalin.Javalin;

public abstract class TRMSJavalin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			// all requests to /cats go to this handler
			path("form", () -> {
			//	get(CatController::getAvailableCats); // get available cats is the default
			//	post(CatController::addCat); // add a cat
				// note: you want your specific paths to be before path variables
				// so that javalin tries those before mapping it to a path variable
				// basically, if the :id path was first, the "all" path would also
				// get mapped to it and it would treat the string "all" as the id
				// instead of as its own path
				path ("all", () -> {
		//			get(CatController::getAllCats); // get all cats
				});
				path ("adopt/:id", () -> {
		//			put(CatController::adoptCat); // adopt a cat by its id
				});
				path(":id", () -> {
//					get(CatController::getCatById); // get a cat by id
//					put(CatController::updateCat); // update a cat
//					delete(CatController::deleteCat); // delete a cat
				});
			});
			// all requests to /users go to this handler
			path("users", () -> {
//				get(PersonController::checkLogin); // get logged in user
				put(employeecontroller::logIn); // log in user
				post(employeecontroller::register); // register new user
//				delete(PersonController::logOut); // log out user
//				path (":id", () -> {
//					get(PersonController::getUserById); // get user by id
//					put(PersonController::updateUser); // update user
//					delete(PersonController::deleteUser); // delete user
			
			});
		});
	}




}
