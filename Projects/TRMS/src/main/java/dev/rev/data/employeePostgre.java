package dev.rev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;


import dev.rev.beans.employee;
import dev.rev.exception.NonUniqueUsernameException;
import dev.rev.utils.ConnectionUtil;

public class employeePostgre implements employeeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	//private ConnectionUtil cu=dev.rev.utils.ConnectionUtil.getConnectionUtil();
	@Override
	public employee add(employee t) throws Exception {
	employee p = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into person values (default, ?, ?, ?,?)";
			String[] keys = {"emp_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getEmp_name());
			pstmt.setString(2, t.getEmp_email());
			pstmt.setString(3, t.getPassword());
			pstmt.setInt(4, t.getMax_claim());
			
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				p = t;
				p.setEmp_id(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();
		}
		
		return p;

	}

	@Override
	public employee getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(employee t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public employee getbyusername(String name) {
		employee human= null;
		try(Connection con= cu.getConnection()) {
			String sql="select * from employee2";
		//	Statement ps= con.prepareStatement(sql);
			System.out.println(name+"plo0");
			//ps.setString(1, name);
		//	ResultSet rs = ps.executeQuery(sql);
//esultSet rs = pstmt.executeQuery();
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			if (rs.next())
			{
				human = new employee();
				human.setEmp_email(rs.getString("username"));
				human.setEmp_id(rs.getInt("person_id"));
				human.setPassword(rs.getString("passwd"));
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return human;
	}

}
