package com.revature.app;
import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controller.PersonController;

import io.javalin.Javalin;

public class SPMSJavalinApp {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			
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
			
//		
//			path("pitch", () -> { 
//				post(PitchController::addCat); 
//				path ("all", () -> {
//					get(PitchController::getAllPitches); 
//				});
//				path(":id", () -> {
//					get(PitchController::getPitchById); 
//					put(PitchController::updatePitch); 
//					delete(PitchController::deletePitch);
//				});
//			});
//
//
//			path("Committee", () -> {
//				get(CommitteeController::getCommittee); 
//			});
		});
	}
}
