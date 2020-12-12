package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;

public class StoryPitchApp {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			// all requests to /cats go to this handler
			path("pitches", () -> {
				get(PitchController::getViewablePitches); // get available cats is the default
				post(PitchController::addPitch); // add a cat
				// note: you want your specific paths to be before path variables
				// so that javalin tries those before mapping it to a path variable
				// basically, if the :id path was first, the "all" path would also
				// get mapped to it and it would treat the string "all" as the id
				// instead of as its own path
				path ("all", () -> {
					get(PitchController::getAllPitches); // get all cats
				});
				path ("approve/:id", () -> {
					put(PitchController::approvePitch); // adopt a cat by its id
				});
				path ("reject/:id", () -> {
					put(PitchController::rejectPitch); // adopt a cat by its id
				});
				path ("give_feedback/:id", () -> {
					put(PitchController::giveFeedbackForPitch); // adopt a cat by its id
				});
				path(":id", () -> {
					get(PitchController::getPitchById); // get a cat by id
					put(PitchController::updatePitch); // update a cat
					delete(PitchController::deletePitch); // delete a cat
				});
			});
			// all requests to /users go to this handler
			path("users", () -> {
				get(PersonController::checkLogin); // get logged in user
				put(PersonController::logIn); // log in user
				post(PersonController::registerUser); // register new user
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
