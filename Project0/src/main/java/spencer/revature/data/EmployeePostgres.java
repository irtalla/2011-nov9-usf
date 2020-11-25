package spencer.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import spencer.revature.beans.Customer;
import spencer.revature.beans.Employee;
import spencer.revature.utils.ConnectionUtil;

public class EmployeePostgres implements EmployeeDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Employee add(Employee t) {
        Employee emp=null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into employee values (default, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				emp = t;
				emp.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emp;
	}

	@Override
	public Employee getById(Integer id) {
		Employee emp=null;
		
		try (Connection conn = cu.getConnection()) {	
			conn.setAutoCommit(false);
			String sql = "select id, username, passwd from employee where id=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("passwd"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emp;
	}

	@Override
	public Set<Employee> getAll() {
		Set<Employee> emps = new HashSet<>();
		Employee emp=null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, username, passwd from customer;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("passwd"));
				
				emps.add(emp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emps;
	}

	@Override
	public void update(Employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee getByUsername(String username) {
       Employee emp=null;
		
		try (Connection conn = cu.getConnection()) {	
			conn.setAutoCommit(false);
			String sql = "select id, username, passwd from employee where username=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("passwd"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return emp;
	}

}
