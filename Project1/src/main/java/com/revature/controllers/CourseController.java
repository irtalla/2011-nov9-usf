package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Course;
import com.revature.beans.Person;
import com.revature.services.CourseService;
import com.revature.services.CourseServiceImpl;

import io.javalin.http.Context;

public class CourseController {
	private static CourseService courseServ = new CourseServiceImpl();
	
	
	public static void addCourse(Context ctx) {
	
		
		Course course = ctx.bodyAsClass(Course.class);
	
		courseServ.addCourse(course);
		ctx.status(201);
	}
	
	public static void updateCourse(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Course course = ctx.bodyAsClass(Course.class);
		if (course != null) {
			ctx.status(201);
			courseServ.updateCourse(course);
		} else {
			ctx.status(404);
		}
	}
	public static void getAllCourses(Context ctx) {
		Set<Course> courses = courseServ.getCourses();
		if (courses != null) {
			ctx.status(200);
			ctx.json(courses);
//			System.out.println(courses);
		} else {
			ctx.status(404);
		}
	}
	public static void getCourseById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Course c = courseServ.getCourseById(id);
		if (c != null) {
			ctx.status(200);
			ctx.json(c);
		} else {
			ctx.status(404);
		}
	}
}
