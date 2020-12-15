package com.revature.data;

public class CourseDAOFactory {
public CourseDAO getCourseDAO() {
        
        return new CoursePostgres();
    }
}
