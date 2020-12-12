package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controller.PitchController;
import com.revature.controller.UserController;

import io.javalin.Javalin;

public class SpmsAppJavalin {

	private ObjectMapper objectMapper;
	
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
				

			});
			
			path("html/pitch", () -> {
				post(PitchController::submitPitch);

				path("all", () -> {
					get(PitchController::getAllPitches);
				});
				
				path("genre", () -> {
					get(PitchController::getGenres);
					path(":genre_pitch", () -> {
						get(PitchController::getPitchByGenre);
					});
				});
				
				path("story_type", () -> {
					get(PitchController::getStoryTypes);
					path(":story_type_pitch", () -> {
						get(PitchController::getPitchesByStoryType);
					});
				});
				
				path("pitch_stage", () -> {
					get(PitchController::getPitchStages);
					path(":pitch_stage_pitch", () -> {
						get(PitchController::getPitchesByPitchStage);
					});
				});
				
				path("review_status", () -> {
					get(PitchController::getReviewStatus);
					path(":review_status_pitch", () -> {
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
				});
				
				path(":id", () -> {
					get(PitchController::getPitchById);
					put(PitchController::updatePitch);
					delete(PitchController::deletePitch);
				});
			});
		});
		
	}
	
	@PostConstruct
	public void setUp() {
		objectMapper.registerModule(new JavaTimeModule());
	}
	
}
