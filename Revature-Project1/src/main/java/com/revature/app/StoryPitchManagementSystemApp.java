package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.UserController;
import com.revature.controllers.ProposedWorkController;
import com.revature.controllers.ApprovalController;

import io.javalin.Javalin;

public class StoryPitchManagementSystemApp {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		});
		
		app.start(8081);
		
		app.routes(() -> {
				//only meant for /users
				path("users", () ->{
					get(UserController::checkLogin);
					
					post(UserController::validateUser);	//log in as part of it 
														//involves validating user
					
					
					
					delete(UserController::logOut);	//logging out
					
					//put(UserController::addUser);		//registering user	
					
					//delete(UserController::removeUser); //deleting user
				});
				
				//only meant for /proposedwork
				path("proposedWork", () -> {
					put(ProposedWorkController::addProposedWork);	//adds the same work
					get(ProposedWorkController::getAllProposedWorksByAuthor);
					
					/*
					 * path("additionalInfo", () -> {
					 * 		put(ProposedWorkController::addAdditionalInfo);
					 * })
					*/
					
					/*
					path(":id", () -> {
						get(ProposedWorkController::getProposedWorkById);	//gets proposed work by id

					});
					path(":name", () -> {
						get(ProposedWorkController::getProposedWorkByName);	//gets proposed work 
																			//by name

					});
					*/
				});				
				//only meant for /approval
				path("approval", () -> {
					path(":id", () -> {
						delete(ApprovalController::editorRejectProposedWork); //editor rejects
																			  //proposed work
						put(ApprovalController::acceptProposedWork);	//editor accepts work
						path("requestInfo", () -> {
							post(ApprovalController::addRequestedInfo);	//author adds info to request
							path(":stage", () -> {
								get(ApprovalController::getRequestedInfo); //editor looks at
																		   //extra info for a work
																		   //at a specific stage
							});
						});
					});
					
					path(":userId/:workId", () ->{
						delete(ApprovalController::authorRemoveProposedWork); //author removes work
																			  //if changes are made 
					});
					
					path("editor/:editorID", () -> {
						get(ApprovalController::getAllNeededApprovals);
					});
			   });
		});
	}

}
