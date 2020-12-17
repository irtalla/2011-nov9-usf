package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.AttachmentController;
import com.revature.controllers.DraftController;
import com.revature.controllers.DraftFeedbackController;
import com.revature.controllers.PersonController;
import com.revature.controllers.PitchController;
import com.revature.controllers.PitchFeedbackController;
import com.revature.controllers.PitchInfoRequestController;

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
		DraftController draftController = new DraftController();
		DraftFeedbackController draftFeedbackController = new DraftFeedbackController();
		PersonController personController = new PersonController();
		PitchFeedbackController pitchFeedbackController = new PitchFeedbackController();
		PitchInfoRequestController pitchInfoRequestController = new PitchInfoRequestController();
		AttachmentController attachmentsController = new AttachmentController();
		
		app.routes(() -> {
			// all requests to /pitches go to this handler
			path("pitches", () -> {
				get(pitchController::getAll); // get available pitches is the default
				post(pitchController::add); 
				path(":id", () -> {
					get(pitchController::getById); // get a pitch by id
					put(pitchController::update); // update a pitch
					delete(pitchController::delete); // delete a pitch
					path("feedback", () -> {
						post(pitchFeedbackController::add);
//						put(pitchFeedbackController::update); //say, to update status?
						path(":id", () -> {
							delete(pitchFeedbackController::delete);
						});
					});
					path("info_requests", () -> {
						post(pitchInfoRequestController::add);
						path(":id", () -> {
							delete(pitchInfoRequestController::delete);
						});
					});
					path("attachments", () -> {
						post(attachmentsController::add);
						path(":id", () -> {
							delete(attachmentsController::delete);
						});
					});
				});
			});
			
			path("drafts", () -> {
				get(draftController::getAll); // get available pitches is the default
				post(draftController::add); 
				path(":id", () -> {
					get(draftController::getById); // get a draft by id
					put(draftController::update); // update a draft
					delete(draftController::delete); // delete a draft
					path("feedback", () -> {
						post(draftFeedbackController::add);
//						put(draftFeedbackController::update); //say, to update status?
						path(":id", () -> {
							delete(draftFeedbackController::delete);
						});
					});
				});
			});
			
			// all requests to /users go to this handler
			path("users", () -> {
				get(personController::checkLogin); // get logged in user
				put(personController::logIn); // log in user
				post(personController::registerUser); // register new user
				delete(personController::logOut); // log out user
				path("pitches", () -> {
					get(pitchController::getPitchesViewableBy);
				});
				path("drafts", () -> {
					get(draftController::getDraftsViewableBy);
				});
				path("authors", () -> {
					get(personController::getAllAuthors);
				});
				path("editors/:role", () -> {
					get(personController::getAllEditorsWithRole);
					path(":genre", () -> {
						get(personController::getAllEditorsWithRoleAndGenre);
					});
				});
				path (":id", () -> {
					get(personController::getById); // get user by id
					put(personController::updateUser); // update user
					delete(personController::delete); // delete user
					path("viewablePitches", () -> {
						get(pitchController::getPitchesViewableBy);
					});
					path("viewableDrafts", () -> {
						get(draftController::getDraftsViewableBy);
					});
				});
			});
		});
	}
}
