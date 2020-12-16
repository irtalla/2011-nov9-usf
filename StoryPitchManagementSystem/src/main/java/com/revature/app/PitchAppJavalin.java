package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;

import com.revature.controllers.PitchController;
import com.revature.controllers.StorytypeController;
import com.revature.controllers.GenreController;
import com.revature.controllers.PersonController;

public class PitchAppJavalin {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			// all requests to /pitches go to this handler
			path("pitches", () -> {
				get(PitchController::getAllPitches);
				post(PitchController::addPitch);
				path(":id", () -> {
					get(PitchController::getPitchById); // get a pitch by id
					put(PitchController::updatePitch); // update a pitch
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
			path("genres", () -> {
				get(GenreController::getGenres); // get all breeds
			});
			path("storytypes", () -> {
				get(StorytypeController::getStorytypes); // get all breeds
			});
		});
	}
}
