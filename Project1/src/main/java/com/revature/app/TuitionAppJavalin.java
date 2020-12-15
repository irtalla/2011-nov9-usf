package com.revature.app;



import static io.javalin.apibuilder.ApiBuilder.*;

import io.javalin.Javalin;


import com.revature.controllers.CourseController;
import com.revature.controllers.PersonController;

public class TuitionAppJavalin {
	
	public static void main(String[] args) {
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("/Static"); // pulling from src/main/resources
			config.enableCorsForAllOrigins();
		});
		
		app.start(8080);
		
		app.routes(() -> {
			path("courses", () -> {
				get(CourseController::getAllCourses); // get courses
				post(CourseController::addCourse); // add a course request
//				path ("all", () -> {
//					get(CourseController::getAllCourses); // get all cats
//				});
				path(":id", () -> {
			        get(CourseController::getCourseById);
					put(CourseController::updateCourse); // update a course request
			       
				});
			});
			path("users", () -> {
				get(PersonController::checkLogin); // get logged in user
				put(PersonController::logIn); // log in user
				delete(PersonController::logOut); // log out user
				path (":id", () -> {
					get(PersonController::getUserById); // get user by id

				});
			});
		});
	}
}

