package com.revature.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Course;
import com.revature.beans.Department;
import com.revature.beans.Event;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.utils.ConnectionUtil;

public class CoursePostgres implements CourseDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Course add(Course t) {
		Course c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into course values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getEmployeeId());
			pstmt.setString(2, t.getStartDate());
			pstmt.setString(3, t.getStartTime());
			pstmt.setString(4, t.getDescription());
			pstmt.setFloat(5, t.getCourseCost());
			pstmt.setString(6, t.getGradingFormat());
			pstmt.setString(7, t.getGradingMin());
			pstmt.setInt(8, t.getEventType().getId());
			pstmt.setString(9, t.getDirSup());
			pstmt.setString(10, t.getDeptHead());
			pstmt.setString(11, t.getBenCor());
			pstmt.setString(12, t.getAwardGranted());
			pstmt.setInt(13, t.getApproverId());
			pstmt.setString(14, t.getLatestSubmitDate());
			pstmt.setFloat(15, t.getReimburseAmt());
	
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				 {
					c = t;
					c.setId(rs.getInt(1));
					conn.commit();
				}
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}

//	@Override
//	public Course getByEmployeeId(Integer id) {
//		Course course = null;
//
//	
//		try (Connection conn = cu.getConnection()) {
//		
//			String sql = "select * from course where employee_id =?";
//
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, id);
//		   
//
//			pstmt.executeQuery();
//			ResultSet rs = pstmt.getGeneratedKeys();
//			ResultSet set = pstmt.executeQuery();
//            
//			while (set.next()) {
//
//			    course.setId(set.getInt(1));
//				course.setEmployeeId(set.getInt(2));
//				course.setStartDate(set.getString(3));
//				course.setStartTime(set.getString(4));
//				course.setDescription(set.getString(5));
//				course.setCourseCost(set.getFloat(6));
//				course.setGradingFormat(set.getString(7));
//				Event e = new Event();
//				e.setId(set.getInt(8));
//				course.setEventType(e);
//				course.setDirSup(set.getString(9));
//				course.setDeptHead(set.getString(10));
//				course.setBenCor(set.getString(11));
//				course.setApproverId(set.getInt(12));
//				course.setLatestSubmitDate(set.getString(13));
//				course.setReimburseAmt(set.getFloat(14));
//	
//				
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		
//	}
//		return course;
//	}

	@Override
	public Set<Course> getAll() {
		Set<Course> courses = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {

			String sql = "select * from course ORDER BY start_date DESC";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setEmployeeId(rs.getInt("employee_id"));
				course.setStartDate(rs.getString("start_date"));
				course.setStartTime(rs.getString("start_time"));
				course.setDescription(rs.getString("description"));
				course.setCourseCost(rs.getFloat("course_cost"));
				course.setGradingFormat(rs.getString("grading_format"));
				course.setGradingMin(rs.getString("grade_min"));
				Event e = new Event();
				e.setId(rs.getInt("event_type_id"));
				course.setEventType(getEventById(e.getId(), conn));
				course.setDirSup(rs.getString("dir_sup"));
				course.setDeptHead(rs.getString("dept_head"));
				course.setBenCor(rs.getString("ben_cor"));
				course.setAwardGranted(rs.getString("award_granted"));
				course.setApproverId(rs.getInt("approver_id"));
				course.setLatestSubmitDate(rs.getString("latest_submit_date"));
				course.setReimburseAmt(rs.getFloat("reimburse_amt"));
				courses.add(course);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return courses;
	}
	
	private Event getEventById(Integer id, Connection conn) throws SQLException {
		Event e = new Event();
//		CourseDAO courseEventDao = new CoursePostgres();
		
		String sql = "select * from event_type where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		e.setId(rs.getInt(1));
		e.setName(rs.getString(2));
		e.setReimbPcnt(rs.getFloat(3));
		}
		return e;
	}





	@Override
	public void update(Course t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update course set dir_sup = ?, dept_head = ?, ben_cor = ?, award_granted = ?, approver_id = ?, latest_submit_date = ?, reimburse_amt = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getDirSup());
			pstmt.setString(2, t.getDeptHead());
			pstmt.setString(3, t.getBenCor());
			
			pstmt.setString(4, t.getAwardGranted());
			pstmt.setInt(5,  t.getApproverId());
			pstmt.setString(6, t.getLatestSubmitDate());
			pstmt.setFloat(7, t.getReimburseAmt());
			pstmt.setInt(8, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				
					conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

	

	@Override
	public Set<Course> getCourseByEmployeeId(Integer id) {
	Set<Course> courses = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {

			String sql = "select * from course where employee_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
//			ResultSet set = pstmt.getGeneratedKeys();
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setEmployeeId(rs.getInt("employee_id"));
				course.setStartDate(rs.getString("start_date"));
				course.setStartTime(rs.getString("start_time"));
				course.setDescription(rs.getString("description"));
				course.setCourseCost(rs.getFloat("course_cost"));
				course.setGradingFormat(rs.getString("grading_format"));
				course.setGradingMin(rs.getString("grade_min"));
				Event e = new Event();
				e.setId(rs.getInt("event_type_id"));
				course.setEventType(getEventById(e.getId(), conn));
				course.setDirSup(rs.getString("dir_sup"));
				course.setDeptHead(rs.getString("dept_head"));
				course.setBenCor(rs.getString("ben_cor"));
				course.setAwardGranted(rs.getString("award_granted"));
				course.setApproverId(rs.getInt("approver_id"));
				course.setLatestSubmitDate(rs.getString("latest_submit_date"));
				course.setReimburseAmt(rs.getFloat("reimburse_amt"));
				courses.add(course);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return courses;
	}

	@Override
	public void selectCourse(Person p, Course c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Course getById(Integer id) {
         Course course = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select employee.id as employee_id, role_type.id as role_type_id, first_name, last_name, emp_password,"
					+ "role_type.role_name as role_name from employee join role_type on role_type.id = emp_role"
					+ "where employee.id = ?";
			sql= "select * from course where id =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				course = new Course();
				course.setId(rs.getInt("id"));
				course.setEmployeeId(rs.getInt("employee_id"));
				course.setStartDate(rs.getString("start_date"));
				course.setStartTime(rs.getString("start_time"));
				course.setDescription(rs.getString("description"));
				course.setCourseCost(rs.getFloat("course_cost"));
				course.setGradingFormat(rs.getString("grading_format"));
				course.setGradingMin(rs.getString("grade_min"));
				Event event = new Event();
				event.setId(rs.getInt("event_type_id"));
				course.setEventType(getEventById(event.getId(), conn));
				course.setDirSup(rs.getString("dir_sup"));
				course.setDeptHead(rs.getString("dept_head"));
				course.setBenCor(rs.getString("ben_cor"));
				course.setAwardGranted(rs.getString("award_granted"));
				course.setApproverId(rs.getInt("approver_id"));
				course.setLatestSubmitDate(rs.getString("latest_submit_date"));
				course.setReimburseAmt(rs.getFloat("reimburse_amt"));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return course;
	}

	@Override
	public void delete(Course t) {
		try (Connection conn = cu.getConnection()) {
			String sql = "delete from course where id =?";
		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
		   

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
	}
		
	
	}
}



