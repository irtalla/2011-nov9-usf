package dev.elliman.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import dev.elliman.controller.PersonController;
import io.javalin.Javalin;

public class TRMSJavalin {

	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			path("users", () -> {
				put(PersonController::login);//login
			});
		});
	}

}
