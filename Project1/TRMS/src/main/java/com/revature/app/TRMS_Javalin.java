package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;

import com.revature.controllers.EvtReqController;
import com.revature.controllers.PersonController;




public class TRMS_Javalin {
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			// all requests to /events go to this handler
			path("events", () ->{
				//get (ctx -> ctx.result("Hello World"));
				get(EvtReqController::getAvailableEvtReqs); // get available events is the default
				post(EvtReqController::addEvtReq); // add a event //it will call this method
				// note: you want your specific paths to be before path variables
				// so that javalin tries those before mapping it to a path variable
				// basically, if the :id path was first, the "all" path would also
				// get mapped to it and it would treat the string "all" as the id
				// instead of as its own path
				path ("all", () -> {
					get(EvtReqController::getAllEvtReqs); // get all events
				});
				path ("approve", () -> {
					path(":id", () -> {
						get(EvtReqController::approveEvtReqs); // approve a event
					});
				});
				path ("request/:id", () -> {
					put(EvtReqController::initEvtReq); // request a event by its id
				});
				path(":id", () -> {
					get(EvtReqController::getEvtReqById); // get a event by id
					put(EvtReqController::updateEvtReq); // update a event
					delete(EvtReqController::deleteEvtReq); // delete a event
				});
			});
			path ("all", () -> {
				get(EvtReqController::getAllEvtReqs); // get all events
			});
			
			// all requests to /users go to this handler
			path("users", () -> {
				get(PersonController::checkLogin); // get logged in user
				put(PersonController::logIn); // log in user
				post(PersonController::registerUser); // register new user,
				delete(PersonController::logOut); // log out user
				path (":id", () -> {
					get(PersonController::getUserById); // get user by id
					put(PersonController::updateUser); // update user
					delete(PersonController::deleteUser); // delete user
				});
			});
		});
	}


}
