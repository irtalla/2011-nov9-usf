package dev.warrington.app;

import io.javalin.Javalin;

public class StoryPitchJavalin {

	public static void main (String[] args) {
		
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			
		});
		
	}
	
}