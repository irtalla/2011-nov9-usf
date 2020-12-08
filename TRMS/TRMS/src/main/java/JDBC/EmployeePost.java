package JDBC;

import java.sql.*;
import java.util.Set;

import Entity.Employee;
import Entity.Role;
import Util.ConnectionUtil;

public class EmployeePost implements EmployeeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Employee getById(Integer id) {
		Employee employee = null;
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select employee.id as emp_id, employee.userName as uname, employee.pass as pass, employee.full_name as fname, \r\n" + 
					"employee.available_funds as af, employee.role_id as rid, roles.r_name as rname from employee join roles \r\n" + 
					"on employee.role_id = roles.id where employee.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("emp_id"));
				emp.setUser(rs.getString("uname"));
				emp.setPass(rs.getString("pass"));
				emp.setAvailFunds(rs.getDouble("af"));
				Role role = new Role();
				role.setId(rs.getInt("rid"));
				role.setName(rs.getString("rname"));
				emp.setRole(role);
				
				employee = emp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return employee;
	}

	
	@Override
	public Employee getByUserName(String username) {
		Employee employee = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select employee.id as emp_id, employee.userName as uname, employee.pass as pass, employee.full_name as fname, \r\n" + 
					"employee.available_funds as af, employee.role_id as rid, roles.r_name as rname from employee join roles \r\n" + 
					"on employee.role_id = roles.id where employee.userName = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Employee emp = new Employee();
				emp.setId(rs.getInt("emp_id"));
				emp.setUser(rs.getString("uname"));
				emp.setPass(rs.getString("pass"));
				emp.setAvailFunds(rs.getDouble("af"));
				Role role = new Role();
				role.setId(rs.getInt("rid"));
				role.setName(rs.getString("rname"));
				emp.setRole(role);
				
				employee = emp;
			}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return employee;
	}


	


	@Override
	public Employee add(Employee t) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void update(Employee t) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update employee set available_funds = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, t.getAvailFunds());
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
	public void delete(Employee t) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public Set<Employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

