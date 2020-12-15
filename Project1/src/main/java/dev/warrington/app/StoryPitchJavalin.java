package dev.warrington.app;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;
import dev.warrington.controller.PersonController;
import dev.warrington.controller.StoryController;

public class StoryPitchJavalin {

	public static void main (String[] args) {
		
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			path("users", () -> {
				get(PersonController::checkLogin);
				put(PersonController::logIn);
			});
			path("stories", () -> {
				put(StoryController::getMyStories);
				post(StoryController::addStory);
			});
			path("approve", () -> {
				put(StoryController::approve);
			});
			path("deny", () -> {
				put(StoryController::deny);
			});
			path("req", () -> {
				put(StoryController::req);
			});
		});
		
	}
	
}