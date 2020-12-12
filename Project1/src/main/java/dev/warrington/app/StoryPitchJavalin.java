package dev.warrington.app;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;
import dev.warrington.controller.PersonController;

public class StoryPitchJavalin {

	public static void main (String[] args) {
		
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			path("users", () -> {
				get(PersonController::CheckLogin);
				put(PersonController::LogIn);
				post(PersonController::RegisterUser);
			});
		});
		
	}
	
}