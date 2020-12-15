package app;

import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;
import controllers.*;

public class BookApp {

public static void main(String[] args) {
	Javalin app = Javalin.create((config) -> {
		config.addStaticFiles("/static"); // this is necessary to get files from resources
		config.enableCorsForAllOrigins();
	});
		
	app.start(8080);
		
	app.routes(() -> {
		path("pitches", () -> {
			get(PitchController::getPendingPitches);
			post(PitchController::addPitch);
			path("all", () -> {
				get(PitchController::getAllPitches);
			});
			path("approve", () -> {
				get(PitchController::getApprovals);
				post(PitchController::approvePitch);
				put(PitchController::updateApproval);
				path("/:id", () -> {
				get(PitchController::getApprovalById);
				});
				});
			path("drafts", () -> {
				get(PitchController::getDrafts);
				post(PitchController::acceptApproval);
			});
			path("suggestions", () -> {
				get(PitchController::getSuggestions);
				post(PitchController::makeSuggestion);
			});
			path("/:id", () -> {
				get(PitchController::getPitchById);
				put(PitchController::updatePitch);
				delete(PitchController::deletePitch);
			});
			
		});
				path("users", () -> {
					get(UsrController::checkLogin);
					put(UsrController::logIn);
					post(UsrController::registerUsr);
					delete(UsrController::logOut);
					path (":id", () -> {
						get(UsrController::getUsrById);
						put(UsrController::updateUsr);
						delete(UsrController::deleteUsr);
					});
					path (":name", () -> {
						get(UsrController::getUsrByName);
					});
				});
			path ("types", () -> {
				get(UsrController::getTypes);
			});
			path ("genres", () -> {
				get(UsrController::getGenres);
			});
		});
	}
}

