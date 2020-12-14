package com.cross.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.cross.controllers.AuthController;
import com.cross.controllers.CommentController;
import com.cross.controllers.DecisionController;
import com.cross.controllers.PitchController;
import com.cross.controllers.RequestController;
import com.cross.services.PriorityUpdaterService;

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
		CommentController.initGsonBuilder();
		DecisionController.initGsonBuilder();
		
		app.start(4000);
		
		app.routes(() -> {
			// authentication route for login 
			path ("api/auth/login", () -> {
				post(AuthController::login);
			}); 
			
			path ("api/comments", () -> {
				post(CommentController::addComment);
				path("requestid/:id", () -> {
					get(CommentController::getByRequestId);
				});
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
			
			path ("api/decisions", () -> {
				post(DecisionController::addDecision); 
				path("pitchid/:id", () -> {
					get(DecisionController::getDecisionsByPitchId);
				});
				
			});
			
			
		});
		
		PriorityUpdaterService priorityUpdateServ = PriorityUpdaterService.getPriorityUpdaterService(); 
		priorityUpdateServ.run();
	}
}
