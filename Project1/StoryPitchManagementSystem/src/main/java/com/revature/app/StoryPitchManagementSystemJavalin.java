package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;

import com.revature.controllers.ApprovalController;
import com.revature.controllers.GenreSpecializationController;
import com.revature.controllers.InfoRequestController;
import com.revature.controllers.InfoResponseController;
//import com.revature.controllers.CatController;
import com.revature.controllers.PersonController;
import com.revature.controllers.RejectionController;
import com.revature.controllers.StoryPitchController;

public class StoryPitchManagementSystemJavalin {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			// all requests to /cats go to this handler
//			path("cats", () -> {
//				get(CatController::getAvailableCats); // get available cats is the default
//				post(CatController::addCat); // add a cat
//				// note: you want your specific paths to be before path variables
//				// so that javalin tries those before mapping it to a path variable
//				// basically, if the :id path was first, the "all" path would also
//				// get mapped to it and it would treat the string "all" as the id
//				// instead of as its own path
//				path ("all", () -> {
//					get(CatController::getAllCats); // get all cats
//				});
//				path ("adopt/:id", () -> {
//					put(CatController::adoptCat); // adopt a cat by its id
//				});
//				path(":id", () -> {
//					get(CatController::getCatById); // get a cat by id
//					put(CatController::updateCat); // update a cat
//					delete(CatController::deleteCat); // delete a cat
//				});
//			});
			
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
//						path("stories", () ->{
//							get(PersonController::getStories); // get logged in user
//							path("storyId", () ->{
//								get(PersonController::getStories); // get logged in user
//								
//							});
//						});
				});
			});

			path("acceptChanges", () ->{
				put(StoryPitchController::acceptChanges);
			});

			path("rejectChanges", () ->{
				put(StoryPitchController::rejectChanges);
			});
			
			path("submitStory", () ->{
				put(StoryPitchController::submitStory);
			});
			
			path("resubmitStory", () ->{
				put(StoryPitchController::resubmitStory);
			});

			path("submitInfoRequest", () ->{
				put(InfoRequestController::submitInfoRequest);
			});
			
			path("submitInfoResponse", () ->{
				put(InfoResponseController::submitInfoResponse);
			});

			path("submitApproval", () ->{
				put(ApprovalController::submitApproval);
			});

			path("submitRejection", () ->{
				put(RejectionController::submitRejection);
			});
			
			path("changeStory", () ->{
				put(StoryPitchController::updateStoryPitch);
			});
			
			path("editStory", () ->{
				put(StoryPitchController::editStoryPitch);
			});
			
			path("getGenreSpecializations", () ->{
				get(GenreSpecializationController::getByGenreID); // get user by id
			});
			
			path("getStory", () ->{
				get(StoryPitchController::getStoryPitchByID); // get user by id
			});
			
			path("stories", () -> {
				get(StoryPitchController::getAll); 
				//put(PersonController::logIn); 
				//post(PersonController::registerUser);
				//delete(PersonController::logOut); 
				path (":id", () -> {
					get(StoryPitchController::getStoryPitchByID); // get user by id
					put(PersonController::updateUser); // update user
					delete(PersonController::deleteUser); // delete user
				});
			});
		});
	}
}
