package dev.elliman.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import dev.elliman.controller.ClaimController;
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
				get(PersonController::checkLogin);
			});
			
			path("events", () -> {
				get(ClaimController::getEventTypes);
			});
			
			path("claims", () -> {
				path("person/:id", () -> {
					get(ClaimController::getClaimsByPerson);//show a person all of their claims
				});
				path("ds", () -> {
					get(ClaimController::getDSUnapprovedClaims);
				});
				path("dh", () -> {
					get(ClaimController::getDHUnapprovedClaims);
				});
				path("bc", () -> {
					get(ClaimController::getBCUnapprovedClaims);
				});
				path("accept/:id", () -> {
					post(ClaimController::accept);
				});
			});
		});
	}

}
