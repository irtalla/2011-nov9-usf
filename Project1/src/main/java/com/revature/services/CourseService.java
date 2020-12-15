package com.revature.services;

import java.util.Set;

import com.revature.beans.Course;
import com.revature.beans.Person;

public interface CourseService {

	Integer addCourse(Course course);

	Course getCourseById(Integer id);

	void updateCourse(Course course);

	Set<Course> getCourses();

	Set<Course> getCourseByEmployeeId(Integer id);

	void removeCourse(Course c);



	

}
