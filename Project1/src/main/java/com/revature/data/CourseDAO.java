package com.revature.data;

import java.util.Set;

import com.revature.beans.Course;
import com.revature.beans.Person;

public interface CourseDAO extends GenericDAO<Course> {
	public Course add(Course c);
	public Set<Course> getCourseByEmployeeId(Integer id);
	public void selectCourse(Person p, Course c);
//	public Course getByEmployeeId(Integer id);
	public void delete(Course c);
}