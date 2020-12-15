package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;
import io.javalin.Javalin;
import com.revature.controllers.PitchController;
import com.revature.controllers.PersonController;
//import com.revature.controllers.DraftController;

public class Project1Javalin {
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		});

		app.start(8080);

		app.routes(() -> {
			path("pitches", () -> {
				get(PitchController::getAllPitches);
				post(PitchController::addPitch);
				path(":status", () -> {
					get(PitchController::getPitchesByStatus);
				});
				path("approve/:id", () -> {
					put(PitchController::approvePitch);
				});
				path("reject/:id/:editorNotes", () -> {
					put(PitchController::rejectPitch);
				});
				path(":id", () -> {
					get(PitchController::getPitchbyId);
					put(PitchController::updatePitch);
					delete(PitchController::deletePitch);
				});
			});
			path("users", () -> {
				get(PersonController::checkLogin);
				put(PersonController::logIn);
				post(PersonController::registerUSer);
				delete(PersonController::logOut);
				path("id:", () -> {
					get(PersonController::getUserById);
					put(PersonController::updateUser);
					delete(PersonController::deleteUser);
				});
				path(":id/pitches", () -> {
					get(PitchController::getPitchesByAuthor);
				});
			});
			/*
			path("drafts", () -> {
				get(DraftController::getPendingDrafts);
				post(DraftController::addDraft);
				path("all", () ->{
					get(DraftController::getAllDrafts);
				});
				path("approve/:id", () -> {
					put(DraftController::approveDraft);
				});
				path("reject/:id", () -> {
					put(DraftController::rejectDraft);
				});
				path(":id", () -> {
					get(DraftController::getDraftById);
					put(DraftController::updateDraft);
					delete(DraftController::deleteDraft);
				});
				path(":userId", () -> {
					get(DraftController::getDraftByAuthor);
				});
			});
			*/
		});
	}
}
