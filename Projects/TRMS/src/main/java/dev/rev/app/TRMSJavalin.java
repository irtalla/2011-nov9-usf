	package dev.rev.app;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import dev.rev.controllers.employeecontroller;
import dev.rev.controllers.eventcontroller;
import dev.rev.controllers.formcontroller;
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
				get(formcontroller::getallforms); // get available is the default
				post(formcontroller::addform); // add a cat
				// note: you want your specific paths to be before path variables
				// so that javalin tries those before mapping it to a path variable
				// basically, if the :id path was first, the "all" path would also
				// get mapped to it and it would treat the string "all" as the id
				// instead of as its own path
			
			//	path ("event", () -> {
				//	get(eventcontroller::getallevents);
			//	});
				path(":id", () -> {
					//System.out.println("helo");
					//get(formcontroller::getformbyempid); // get a cat by id
					get(formcontroller::getformbyid);
					put(formcontroller::updateform); // update a cat
//					delete(CatController::deleteCat); // delete a cat
				});
				path("emp/:id",()->{
					
					get(formcontroller::getempforms);
					
				});
			});
			path("events",() ->{
				
				get(eventcontroller::getallevents);
				
				path(":id",()->{
					get(eventcontroller::geteventbyid);
				});
				
			});
		
			
			
			// all requests to /users go to this handler
			path("users", () -> {
				get(employeecontroller::checklogin); // get logged in user
				put(employeecontroller::logIn); // log in user
				post(employeecontroller::register); // register new user
				delete(employeecontroller::logout); // log out user
				path (":id", () -> {
					get(employeecontroller::getuserbyid); // get user by id
					put(employeecontroller::updateUser); // update user
//					delete(PersonController::deleteUser); // delete user
			
			});
		});
	});




}
}
