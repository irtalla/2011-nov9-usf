package app;
import static io.javalin.apibuilder.ApiBuilder.*;

import controller.Approval_OtherController;
import controller.AuthorController;
import controller.CommiteeController;
import controller.DSAController;
import controller.DSOController;
import controller.DSSController;
import controller.DraftController;
import controller.Draft_ApprovalController;
import controller.Editor_Controller;
import controller.EmployeeController;
import controller.GenreController;
import controller.Pitch_ApprovalController;
import controller.Pitch_StatusController;
import controller.Request_InfoController;
import controller.StoryController;
import controller.Story_TypeController;
import controller.UsersController;
import io.javalin.Javalin;

public class StoryBoardJavalin {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			// all requests to /cats go to this handler
			path("Approval_Other", () -> 				{
				get(Approval_OtherController::getById); // get available cats is the default
				//get(Approval_OtherController::getByEditorId);
				post(Approval_OtherController::add); // add a cat

				path ("update", () -> {
					get(DSSController::update); // get all cats
				});

			});
			path("Author", () -> 				{
				//get(AuthorController::checkLogin);

				put(AuthorController::getById); // get available cats is the default
				//get(AuthorController::getByUserId);
				post(AuthorController::add); // add a cat
				path ("user", () -> {
					get(AuthorController::getByUserId); // get all cats
				});
				path ("update", () -> {
					get(AuthorController::update); // get all cats
				});

			});
			path("commitee", () -> 				{
				get(CommiteeController::getAll); // get available cats is the default
				//get(CommiteeController::getByEditorId);
				//get(CommiteeController::getByGenreId);
				post(CommiteeController::add); // add a cat
				path (":id", () -> {
					get(DSSController::getById); // get all cats
				});
				path ("update", () -> {
					get(DSSController::update); // get all cats
				});

			});
			path("Draft_Approval", () -> 				{
				get(Draft_ApprovalController::getById); // get available cats is the default
				post(Draft_ApprovalController::add); // add a cat
				path (":id", () -> {
				put(Draft_ApprovalController::updateDraft_Approval);

				});
				path ("edit", () -> {
					get(Draft_ApprovalController::getByEditorId); // get all cats
				});

			});
			path("Draft", () -> 				{
				get(DraftController::getById); // get available cats is the default
				//get(DraftController::getByAuthorId);
				post(DraftController::add); // add a cat
				path ("edit", () -> {
					get(DraftController::getByUsersID); // get all cats
				});
				path ("update", () -> {
					get(DraftController::update); // get all cats
				});

			});
			path("DSA", () -> 				{
				get(DSAController::getById); // get available cats is the default
				post(DSAController::add); // add a cat
				path ("update", () -> {
					get(DSAController::update); // get all cats
				});

			});
			path("DSO", () -> 				{
				get(DSOController::getById); // get available cats is the default
				post(DSOController::add); // add a cat
				path ("update", () -> {
					get(DSOController::update); // get all cats
				});

			});
			path("DSS", () -> 				{
				get(DSSController::getById); // get available cats is the default
				post(DSSController::add); // add a cat
				path ("update", () -> {
					get(DSSController::update); // get all cats
				});

			});
			path("Editor", () -> 				{
			//get(Editor_Controller::getByEditorId); // get available cats is the default
			put(Editor_Controller::getByEmployeeId);
			get(Editor_Controller::checkLogin);
			post(Editor_Controller::add); // add a cat
			path ("update", () -> {
				get(Editor_Controller::update); // get all cats
			});

		});
			path("Employee", () -> {
				//get(EmployeeController::getByEmployeeId); // get available cats is the default
				put(EmployeeController::getByUserId);
				post(EmployeeController::add); // add a cat
				path ("update", () -> {
					get(EmployeeController::update); // get all cats
				});

			});
			path("Genre", () -> {
				get(GenreController::getById); // get available cats is the default
				post(GenreController::add); // add a cat
				path ("update", () -> {
					get(GenreController::update); // get all cats
				});

			});
			path("Pitch_Approval", () -> {
				get(Pitch_ApprovalController::getById); // get available cats is the default
				post(Pitch_ApprovalController::add); // add a cat
					put(Pitch_ApprovalController::update); // get all cats
				
				path ("editor", () -> {
					get(Pitch_ApprovalController::getByEditorId); // get all cats
				});

			});
			path("Pitch_Status", () -> {
				get(Pitch_StatusController::getByEditorId); // get available cats is the default
				post(Pitch_StatusController::add); // add a cat
				path (":id", () -> {
					put(Pitch_StatusController::update); // get all cats
				});
				path (":find", () -> {
					put(Pitch_StatusController::getById); // get all cats
				});
				path (":author", () -> {
					get(Pitch_StatusController::getByAuthorId); // get all cats
				});
				path("all",()->{
					get(Pitch_StatusController::getAll);
					
				});

			});
			path("info", () -> {
				get(Request_InfoController::getById); // get available cats is the default
				post(Request_InfoController::add); // add a cat
				path (":id", () -> {
					put(Request_InfoController::update); // get all cats
				});

			});
			path("Story_Type", () -> {
				get(Story_TypeController::getById); // get available cats is the default
				post(Story_TypeController::add); // add a cat
				path ("update", () -> {
					get(Story_TypeController::update); // get all cats
				});

			});
			path("Story", () -> {
				get(StoryController::getById); // get available cats is the default
				post(StoryController::add); // add a cat
				path ("user", () -> {
					get(StoryController::getByUsersID); // get all cats
				});
				path ("update", () -> {
					get(StoryController::update); // get all cats
				});

			});
			// all requests to /users go to this handler
			path("users", () -> {
				get(UsersController::checkLogin); // get logged in user
				put(UsersController::logIn); // log in user
				post(UsersController::addUser); // register new user
				delete(UsersController::logOut); // log out user
				path (":id", () -> {
					get(UsersController::getUserById); // get user by id
					put(UsersController::updateUser); // update user
				});
			});

		});
	}
	}
