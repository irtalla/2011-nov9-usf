package com.revature.services;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Course;
import com.revature.beans.Person;
import com.revature.controllers.PersonController;
import com.revature.data.CourseDAO;
import com.revature.data.CourseDAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.PersonDAOFactory;

public class CourseServiceImpl implements CourseService {
	private CourseDAO courseDao;
	
	public CourseServiceImpl() {
		CourseDAOFactory courseDaoFactory = new CourseDAOFactory();
		courseDao = courseDaoFactory.getCourseDAO();
	}

	

	

	@Override
	public Course getCourseById(Integer id) {
		return courseDao.getById(id);
	}

	 
	@Override
	    public Set<Course> getCourseByEmployeeId(Integer id) {
	        return courseDao.getCourseByEmployeeId(id);
	    }

	@Override
	public Integer addCourse(Course c) {
		
		return courseDao.add(c).getId();
	}

	
	@Override
	    public void removeCourse(Course c) {
	        courseDao.delete(c);
	    }

	@Override
	public void updateCourse(Course c) {
		Set<Course> courses = new HashSet<Course>();
		PersonService personServ = new PersonServiceImpl();
		CourseService courseServ = new CourseServiceImpl();
    	Person approver = new Person();
    	Person student = new Person();
    	approver = personServ.getPersonById(c.getApproverId());
    	Integer empId = c.getEmployeeId();
    	student = personServ.getPersonById(empId);
    	float reimburseAmt = 0f;
    	float funds=student.getFunds();

    
    	if (c.getDirSup().equalsIgnoreCase("approved") && (approver.getRole().getId() ==3 || approver.getRole2().getId()==3)) 
    	{
    		reimburseAmt = c.getCourseCost() * c.getEventType().getReimbPcnt();
    		reimburseAmt = Math.round(reimburseAmt*100)/100;
		    c.setReimburseAmt(reimburseAmt);
    		courseDao.update(c);
    	}
    	else if (c.getDeptHead().equalsIgnoreCase("approved") && approver.getRole().getName().equalsIgnoreCase("benefits coordinator")) courseDao.update(c);
    	else if (c.getDirSup().equalsIgnoreCase("rejected") && approver.getRole().getName().equalsIgnoreCase("department head")){
    	  c.setDeptHead("Rejected");
    	  courseDao.update(c);
    	}
    	else if (c.getDeptHead().equalsIgnoreCase("rejected") && approver.getRole().getId().equals(4)) // benefits coordinator
    		
    	{
    		c.setBenCor("Rejected");
    		courseDao.update(c);
    	}
        if (c.getBenCor().equalsIgnoreCase("rejected")) {
    		c.setAwardGranted("Denied");
    	
    		courseDao.update(c);
    	}
    	else if (c.getBenCor().equalsIgnoreCase("approved") && !(c.getAwardGranted().equalsIgnoreCase("Denied"))) { 
    		// check to see if c.getAwardGranted is already denied before switching to 
    		
    		Integer studentId = student.getId();
    		courses = courseServ.getCourseByEmployeeId(studentId);

//    		for (Course course : courses)
//    		{
//    			System.out.println(course+" course");
//    		}

    		if (c.getAwardGranted().equalsIgnoreCase("Pending")) {}
    		else if (c.getReimburseAmt()> Math.round(c.getCourseCost() * c.getEventType().getReimbPcnt()))// bencor increases amt
    		{
    			funds = funds - c.getReimburseAmt();
    			student.setFunds(funds);
    			//calculate amount to be reimbursed and subtract from total funds
    			personServ.updatePerson(student);
    			c.setAwardGranted("Inc. Funds");
        		courseDao.update(c);
    		}
    		else if ((student.getFunds()>0))// there's money available
    		{
    			if (student.getFunds()-c.getReimburseAmt()<0)
    			{
    				c.setReimburseAmt(funds); // set reimbursement amount to what's left
    			    funds = 0;
    			}else funds = funds - c.getReimburseAmt();

    			student.setFunds(funds);
    			personServ.updatePerson(student);
    			c.setAwardGranted("Pending");    	    	
        		courseDao.update(c);
    		}
    		else 
    		{
    			c.setAwardGranted("No Funds");
        		courseDao.update(c);
    		}
    	
    	} else if (c.getAwardGranted().equalsIgnoreCase("denied")) {
    		student.setFunds(funds+ c.getReimburseAmt());
//    		System.out.println(student.getFunds());
    		personServ.updatePerson(student);
    	}
		courseDao.update(c);
	}

	  
	@Override
	    public Set<Course> getCourses() {
	        return courseDao.getAll();
	    }
	  


}
