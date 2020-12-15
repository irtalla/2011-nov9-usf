import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Course;
import com.revature.beans.Event;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.services.CourseService;
import com.revature.services.CourseServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

class TRMSTest {

	private static PersonService personServ = new PersonServiceImpl();
    private static CourseService courseServ = new CourseServiceImpl();
	
	@Test
	public void testCourseByEmployeeId() {
		Set<Course> courses = courseServ.getCourseByEmployeeId(1);
		
		assertEquals(3,courses.size());
	}
					
	@Test
	public void testGetPersonById() {
		Person user = personServ.getPersonById(1);
		String password = user.getPassword();
		assertEquals("pass", password);
	}

	@Test
	public void testGetAllCourses() {
		Set<Course> courses = courseServ.getCourses();
		assertEquals(7, courses.size()); // there are 6 courses in the database
	}
	
    @Test
    public void testAddCourse()  {
		Course course = new Course();
		course.setEmployeeId(2);
		course.setStartDate("2020-12-28");
		course.setStartTime("20:00");
		course.setDescription("Test course");
		course.setCourseCost(100f);
		course.setGradingFormat("Presentation");
		Event e = new Event();
		e.setId(1);
		e.setName("Certificaton");
		e.setReimbPcnt(1.00f);
		course.setEventType(e);
		course.setLatestSubmitDate("12/12/2020");
		courseServ.addCourse(course);
		Course addedCourse = courseServ.getCourseById(9);
    	assertEquals("Test course", addedCourse.getDescription());
    	courseServ.removeCourse(course);
    	
    }
    

	
	
	
}
