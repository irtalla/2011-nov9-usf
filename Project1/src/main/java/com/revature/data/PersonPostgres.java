package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


import com.revature.beans.Course;
import com.revature.beans.Department;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Person add(Person t){
		Person p = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into employee values (default, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getPassword());
			pstmt.setString(2, t.getFirstName());
			pstmt.setString(3, t.getLastName());
			pstmt.setString(4, t.getLocation());
			pstmt.setInt(5, t.getRole().getId());
			pstmt.setInt(6, t.getRole2().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				p = t;
				p.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Person getById(Integer id) {
		Person person = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select employee.id as employee_id, role_type.id as role_type_id, first_name, last_name, emp_password,"
					+ "role_type.role_name as role_name from employee join role_type on role_type.id = emp_role"
					+ "where employee.id = ?";
			sql= "select * from employee where id =?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				person = new Person();
				person.setId(rs.getInt("id"));
				person.setPassword(rs.getString("emp_password"));
				person.setFirstName(rs.getString("first_name"));
				person.setLastName(rs.getString("last_name"));
				person.setLocation(rs.getString("emp_location"));
				Department department = new Department();
				department.setId(rs.getInt("department"));
				person.setDepartment(getDepartmentById(department.getId(), conn));
				Role role = new Role();
				role.setId(rs.getInt("emp_role"));
				person.setRole(getRoleById(role.getId(), conn));
				

				Role role2 = new Role();
				role2.setId(rs.getInt("emp_role2"));
				person.setRole2(getRoleById(role2.getId(), conn));
				person.setFunds(rs.getFloat("funds"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(person);
		return person;
	}
	private Role getRoleById(Integer id, Connection conn) throws SQLException {
		Role r = new Role();
//		PersonDAO personRoleDao = new PersonPostgres();
		
		String sql = "select * from role_type where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		r.setId(rs.getInt(1));
		r.setName(rs.getString(2));
		}
		return r;
	}
	
	private Department getDepartmentById(Integer id, Connection conn) throws SQLException {
		Department d = new Department();
//		PersonDAO personRoleDao = new PersonPostgres();
		
		String sql = "select * from department where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
		d.setId(rs.getInt(1));
		d.setName(rs.getString(2));
		}
		return d;
	}
	
	@Override
	public Set<Person> getAll() {
		Set<Person> people = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select employee.id as employee_id, role_type.id as role_type_id, first_name, last_name, emp_password,"
					+ "role_type.role_name as role_name from employee join role_type on role_type.id = emp_role;";
					
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Person human = new Person();
				human.setId(rs.getInt("person_id"));
//				human.setUsername(rs.getString("username"));
				human.setPassword(rs.getString("emp_password"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setName(rs.getString("role_name"));
				human.setRole(job);
				
				human.setCourses(getCoursesByPersonId(human.getId(), conn));
				
				people.add(human);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return people;
	}
	


//	@Override
//	public Person getByUsername(String username) {
//		Person human = null;
//		
//		try (Connection conn = cu.getConnection())
//		{
//			String sql = "select person.id as person_id, user_role.id as role_id, username, passwd, "
//					+ "user_role.name as role_name from person "
//					+ "join user_role on user_role_id = user_role.id where username = ?";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, username);
//			ResultSet rs = pstmt.executeQuery();
//			
//			if (rs.next())
//			{
//				human = new Person();
//				human.setUsername(rs.getString("username"));
//				human.setId(rs.getInt("person_id"));
//				human.setPassword(rs.getString("passwd"));
//				Role job = new Role();
//				job.setId(rs.getInt("role_id"));
//				job.setName(rs.getString("role_name"));
//				human.setRole(job);
//				human.setCats(getCatsByPersonId(human.getId(), conn));
//			}
//						
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//		return human;
//	}

	@Override
	public void update(Person t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update employee set funds = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, t.getFunds());
		
			pstmt.setInt(2, t.getId());
			
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
	public void delete(Person t) {
//		try (Connection conn =  cu.getConnection())
//		{
//			conn.setAutoCommit(false);
//			String sql = "delete from person_cat where person_id = ?";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, t.getId());
//			pstmt.executeUpdate();
//			
//			sql = "delete from person where id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, t.getId());
//			int rowsAffected = pstmt.executeUpdate();
//			
//			if (rowsAffected > 0)
//				conn.commit();
//			else
//				conn.rollback();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}
	
	private Set<Course> getCoursesByPersonId(Integer id, Connection conn) throws SQLException {
		Set<Course> courses = new HashSet<>();
		CourseDAO courseDao = new CoursePostgres();
		
		String sql = "select * from course where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			 Course c = courseDao.getById(rs.getInt("id")); 
			courses.add(c);
		}
		
		return courses;
	}

}
