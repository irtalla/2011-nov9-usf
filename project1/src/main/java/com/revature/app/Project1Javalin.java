package com.revature.app;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.commons.io.FileUtils;

import com.revature.controllers.IndividualController;
import com.revature.controllers.ReimbursementController;

import io.javalin.Javalin;
//import com.revature.controllers.CatController;
//import com.revature.controllers.IndividualController;

public class Project1Javalin {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			path("users", () -> {
				get(IndividualController::checkLogin); // get logged in user
				put(IndividualController::logIn); // log in user
				post(IndividualController::registerUser); // register new user
				delete(IndividualController::logOut); // log out user
				path (":id", () -> {
					get(IndividualController::getUserById); // get user by id
					put(IndividualController::updateUser); // update user
					delete(IndividualController::deleteUser); // delete user
				});
			});
			path("reimbursements", () -> {
				get(ReimbursementController::getAll); // get all reimbursements
				post(ReimbursementController::addReimbursement); // add a new reimbursement
				path ("attachments/:id", () -> {
					get(ReimbursementController::getAttachmentsById); // get attachments by reimbursement id
				});
				path ("supapprove/:id", () -> {
					put(ReimbursementController::supapprove); // get attachments by reimbursement id
				});	
				path ("benapprove/:id", () -> {
					put(ReimbursementController::benapprove); // get attachments by reimbursement id
				});	
				path ("headapprove/:id", () -> {
					put(ReimbursementController::headapprove); // get attachments by reimbursement id
				});	
				path ("addmessage/:id", () -> {
					put(ReimbursementController::addMessage); // add message
				});	

						
				path (":id", () -> {
					get(ReimbursementController::getById); // get reimbursements by employee id
					put(ReimbursementController::getReimbursementById); // get single reimbursement by reimbursement id
				});
			});
			 app.post("/upload", ctx -> {
			ctx.uploadedFiles("files").forEach(file -> {
				          try {
				      		  String reimbursementId = ctx.queryParam("rid");
				              FileUtils.copyInputStreamToFile(file.getContent(), new File("src/main/resources/static/attachments/" + reimbursementId + "/" + file.getFilename()));
				              ctx.html("Upload successful");
				          } catch (IOException e) {
				              ctx.html("Upload failed");
				              e.printStackTrace();
				          }
				     });
				 });
		});
	}
}
