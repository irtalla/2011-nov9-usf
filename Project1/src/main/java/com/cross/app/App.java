package com.cross.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.cross.controllers.AuthController;
import com.cross.controllers.PitchController;
import com.cross.controllers.RequestController;

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
		
		AuthController.initGsonBuilder();
		PitchController.initGsonBuilder();
		RequestController.initGsonBuilder();
		
		app.start(4000);
		
		app.routes(() -> {
			// authentication route for login 
			path ("api/auth/login", () -> {
				post(AuthController::login);
			}); 
			
			// all requests to /cats go to this handler
			path ("api/pitches", () -> {
				get(PitchController::getPendingPitches); // get available cats is the default
				post(PitchController::addPitch); // add a cat
				path(":id", () -> {
					get(PitchController::getPitchById); // get a cat by id
					put(PitchController::updatePitch); // update a cat
					delete(PitchController::deletePitch); // delete a cat
				});
				path ("authorid/:id", () -> {
					get(PitchController::getPitchByAuthorId); // get a cat by id

				});
				path ("generaleditor/:id", () -> {
					get(PitchController::getPitchByGeneralEditorId); // get a cat by id

				});
				path ("genre/:genre", () -> {
					get(PitchController::getPitchByGenre); // get a cat by id

				});
				path ("all", () -> {
					get(PitchController::getAllPitches); // get all cats
				});
				path ("reject/:id", () -> {
					put(PitchController::rejectPitch); // adopt a cat by its id
				});
				path ("accept/:id", () -> {
					put(PitchController::acceptPitch); // adopt a cat by its id
				});
				
				
			});
			
			// all requests to /cats go to this handler
			path ("api/requests", () -> {
				get(RequestController::getRequests); // get open requests is the default
				post(RequestController::addRequest); // add a request
				put(RequestController::updateRequest); // update a request
				path(":id", () -> {
					get(RequestController::getRequestById); // get a request by id
					delete(RequestController::deleteRequest); // delete a request by id
				});
				path ("personid/:id", () -> {
					get(RequestController::getRequestsByPersonId); // all open requests associated with a person

				});
				path ("all", () -> {
					get(RequestController::getAllRequests); // get all requests
				});
				path ("close/:id", () -> {
					put(RequestController::closeRequest); // close a request 
				});
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
