package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.Role;
import com.revature.utils.ConnectionUtil;

public class EmployeePostgres implements EmployeeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public Employee add(Employee t) {
		Connection conn = cu.getConnection();
		

		try 
		{
			
			conn.setAutoCommit(false);
			String sql = "insert into employee values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getRole().getId());
			pstmt.setString(2, t.getUsername());
			pstmt.setString(3, t.getPassword());
			pstmt.setString(4, t.getName());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) 
			{
				
				t.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
			sql = "insert into employee_department values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.setInt(2, t.getDepartment().getId());
			
			pstmt.executeUpdate();
			
			sql = "insert into supervisor_employee values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getSupervisorId());
			pstmt.setInt(2, t.getId());
			pstmt.executeUpdate();
			
			conn.commit();
			
		} 
		catch (Exception e) 
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return t;
	}

	@Override
	public Employee getById(Integer id) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select * from employee where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("passwd"));
				emp.setName(rs.getString("name"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				
				sql = "select * from employee_department where employee_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				
				rs = pstmt.executeQuery();
				
				if (rs.next())
				{
					int depId = rs.getInt("department_id");
					sql = "select * from department where id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, depId);
					
					rs = pstmt.executeQuery();
					if (rs.next())
					{
						Department dep = new Department();
						dep.setName(rs.getString("name"));
						dep.setDepartmentHeadId(rs.getInt("department_head_id"));
						dep.setBencoId(rs.getInt("benco_id"));
						
						emp.setDepartment(dep);
					}
					
				}
				
				sql = "select * from supervisor_employee where employee_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					emp.setSupervisorId(rs.getInt("supervisor_id"));
				}
				
				sql = "select * from employee_role where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, role.getId());
				
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					role.setName(rs.getString("name"));
					emp.setRole(role);
				}
				
				
				
				return emp;
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
	}

	@Override
	public Set<Employee> getAll() {
		Connection conn = cu.getConnection();
		Set<Employee> results = new HashSet<Employee>();
		try
		{
			String sql = "select * from employee";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("passwd"));
				emp.setName(rs.getString("name"));
				
				sql = "select * from employee_department where employee_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, emp.getId());
				
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next())
				{
					int depId = rs2.getInt("department_id");
					sql = "select * from department where id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, depId);
					
					rs2 = pstmt.executeQuery();
					if (rs2.next())
					{
						Department dep = new Department();
						dep.setName(rs2.getString("name"));
						dep.setDepartmentHeadId(rs2.getInt("department_head_id"));
						dep.setBencoId(rs2.getInt("benco_id"));
						
						emp.setDepartment(dep);
					}
					
					sql = "select * from supervisor_employee where employee_id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, emp.getId());
					
					rs2 = pstmt.executeQuery();
					if (rs2.next())
					{
						emp.setSupervisorId(rs2.getInt("supervisor_id"));
					}
				}
				
				
				
				results.add(emp);
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return results;
	}

	@Override
	public void update(Employee t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "update employee set username = ?, passwd = ?, role_id = ?, name = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			
			pstmt.setInt(3, t.getRole().getId());
			
			pstmt.setString(4, t.getName());
			pstmt.setInt(5, t.getId());
			
			
			pstmt.execute();
			
			
			conn.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void delete(Employee t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			
			String sql = "delete from supervisor_employee where employee_id = ? or supervisor_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.setInt(2, t.getId());
			
			pstmt.executeUpdate();
			
			sql = "delete from employee_department where employee_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			
			pstmt.executeUpdate();
			
			sql = "delete from employee where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			
			pstmt.executeUpdate();
			
			
			conn.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Employee getByUserName(String username) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select * from employee where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("passwd"));
				emp.setName(rs.getString("name"));
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				
				sql = "select * from employee_department where employee_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, emp.getId());
				
				rs = pstmt.executeQuery();
				
				if (rs.next())
				{
					int depId = rs.getInt("department_id");
					sql = "select * from department where id = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, depId);
					
					rs = pstmt.executeQuery();
					if (rs.next())
					{
						Department dep = new Department();
						dep.setName(rs.getString("name"));
						dep.setDepartmentHeadId(rs.getInt("department_head_id"));
						dep.setBencoId(rs.getInt("benco_id"));
						
						emp.setDepartment(dep);
					}
					
				}
				
				sql = "select * from supervisor_employee where employee_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, emp.getId());
				
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					emp.setSupervisorId(rs.getInt("supervisor_id"));
				}
				
				sql = "select * from employee_role where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, role.getId());
				
				rs = pstmt.executeQuery();
				if (rs.next())
				{
					role.setName(rs.getString("name"));
					emp.setRole(role);
				}
				
				
				
				return emp;
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
	}

}
