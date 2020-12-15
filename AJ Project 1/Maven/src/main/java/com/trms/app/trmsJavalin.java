package com.trms.app;
import static io.javalin.apibuilder.ApiBuilder.*;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.trms.beans.Request;
import io.javalin.Javalin;
import io.javalin.core.util.FileUtil;


import com.trms.controller.*;

public class trmsJavalin {

	public static void main(String[] args) {
		ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
		es.scheduleAtFixedRate(RequestController::autoApprove, 1, 1, TimeUnit.DAYS);
		
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/static");
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.post("/upload", ctx -> {
		    ctx.uploadedFiles("files").forEach(file -> { 
		        FileUtil.streamToFile(file.getContent(), "src/main/resources/static/upload/" + file.getFilename());
		        String s = "upload/" + file.getFilename();
//		        ctx.json(s);
		        ctx.status(200);
		    });
		});
		
		app.routes(() -> {
			//path request
			path("request", () -> {
				get(RequestController::getMyRequests);
				put(RequestController::newRequest);
				post(RequestController::requestUpdate);
//				post(RequestController::get);
				
				path(":id", () -> {
					get(RequestController::getById);
					post(RequestController::updateById);
					
				});

				
				
			});
			
			path("activity", () -> {
//				put(RequestController::activityStatusUpdate);
				put(RequestController::activityAttachmentAdd);
				post(RequestController::activityAddComment);
				
			});
			path("console", () -> {

				get(RequestController::getByManagerOrDheadId);
				post(RequestController::HRGetOpen);

			});


			//path users
			path("users", () -> {
				get(PersonController::checkLogin);
				put(PersonController::logIn);
				post(PersonController::registerUser);
				delete(PersonController::logOut);
				
				path(":id", () -> {
					get(PersonController::getUserById);
					put(PersonController::updateUser);
				});
			});
			
		});

	}

}
