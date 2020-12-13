package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.GenericController;
import com.revature.controllers.PersonController;
import com.revature.controllers.PitchController;
import com.revature.controllers.personController;

import io.javalin.Javalin;

public class StoryPitchApp {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
//		GenericController[] controllers = [new PitchController(), new PersonController()];
		PitchController pitchController = new PitchController();
		PersonController personController = new PersonController();
		
		
		
		app.routes(() -> {
			// all requests to /pitches go to this handler
			path("pitches", () -> {
				get(pitchController::getPitchesViewableBy); // get available pitches is the default
				post(pitchController::add); // add a pitch
				// note: you want your specific paths to be before path variables
				// so that javalin tries those before mapping it to a path variable
				// basically, if the :id path was first, the "all" path would also
				// get mapped to it and it would treat the string "all" as the id
				// instead of as its own path
				path ("all", () -> {
					get(pitchController::getAll); // get all pitches
				});
				path(":id", () -> {
					get(pitchController::getById); // get a pitch by id
					put(pitchController::update); // update a pitch
					delete(pitchController::delete); // delete a pitch
//					path("feedback", () -> {
//						
//					});
				});
			});
			// all requests to /users go to this handler
			path("users", () -> {
				get(personController::checkLogin); // get logged in user
				put(personController::logIn); // log in user
				post(personController::registerUser); // register new user
				delete(personController::logOut); // log out user
				path (":id", () -> {
					get(personController::getById); // get user by id
					put(personController::updateUser); // update user
					delete(personController::delete); // delete user
				});
			});
		});
	}
}
