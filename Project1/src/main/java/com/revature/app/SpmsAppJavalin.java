package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controller.PitchController;
import com.revature.controller.RequestController;
import com.revature.controller.UserController;

import io.javalin.Javalin;

public class SpmsAppJavalin {
	public static final String USER_FILE_LOC = "./src/main/resources/static/files";
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		});
				
		app.start(8080);
		
		app.routes(() -> {
			path("html/users", () -> {
				get(UserController::checkLogin);
				put(UserController::logIn);
				post(UserController::registerUser);
				delete(UserController::logOut);
				
				path("all", () -> {
					get(UserController::getAllUsers);
				});
				
				path(":id", () -> {
					get(UserController::getUserById);
					put(UserController::updateUser);
					delete(UserController::deleteUser);
				});
				
				path("username/:username", () -> {
					get(UserController::getUserByUsername);
				});
				
				path("email/:email", () -> {
					get(UserController::getUserByEmail);
				});
				
				path("editor/", () -> {
					path("committees/:id", () -> {
						get(UserController::getUserCommittees);				
					});
				});

			});
			
			path("html/pitch", () -> {
				post(PitchController::submitPitch);

				path("all", () -> {
					get(PitchController::getAllPitches);
				});
				
				path("author/:id", () -> {
					get(PitchController::getPitchesByAuthor);
				});
				
				path("genre", () -> {
					get(PitchController::getGenres);
					path(":ids", () -> {
						path(":within_genre", () -> {
							get(PitchController::getPitchByGenre);
						});
					});
				});
				
				path("story_type", () -> {
					get(PitchController::getStoryTypes);
					path(":id", () -> {
						get(PitchController::getPitchesByStoryType);
					});
				});
				
				path("pitch_stage", () -> {
					get(PitchController::getPitchStages);
					path(":id", () -> {
						get(PitchController::getPitchesByPitchStage);
					});
				});
				
				path("review_status", () -> {
					get(PitchController::getReviewStatus);
					path(":id", () -> {
						get(PitchController::getPitchesByReviewStatus);
					});
				});
				
				path("priority", () -> {
					get(PitchController::getPriorities);
					path(":priority_pitch", () -> {
						get(PitchController::getPitchesByPriority);
					});
				});
				
				path("file", () -> {
					post(PitchController::uploadFile);
					path(":id/:name", () -> {
						get(PitchController::downloadFile);		
					});
				});
				
				path(":id", () -> {
					get(PitchController::getPitchById);
					put(PitchController::updatePitch);
					delete(PitchController::deletePitch);
				});
			});
			
			path("html/request", () -> {
				post(RequestController::makeRequest);
				
				path("all", () -> {
					get(RequestController::getAllRequests);
				});
				
				path("requester/:id", () -> {
					get(RequestController::getRequestByRequester);
				});
				
				path("requestee/:id", () -> {
					get(RequestController::getRequestByRequestee);
				});
				
				path(":id", () -> {
					get(RequestController::getRequestById);
					put(RequestController::updateRequests);
				});
			});
			
		});
		
	}
	
}
