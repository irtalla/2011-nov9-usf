package com.revature.app;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.EmployeeController;
import com.revature.controllers.EventController;

public class TRMSJavalin {

	public static void main(String[] args)
	{
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(()->{
			path("users", () -> {
				get(EmployeeController::checkLogin);
				put(EmployeeController::login);
				delete(EmployeeController::logout);
				path(":id", () -> {
					get(EmployeeController::getEmployeeById);
				});
				
			});
			path("events", () ->{
				get(EventController::getEventById);
				put(EventController::addEvent);
				delete(EventController::deleteEvent);
				path("/types", () -> {
					get(EventController::getEventTypeById);
					path("/all", () -> {
						get(EventController::getAllEventTypes);
					});
				});
			});
		});
	}
}
