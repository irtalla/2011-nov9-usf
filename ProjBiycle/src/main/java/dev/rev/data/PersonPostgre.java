package dev.rev.data;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.model.Person;
import dev.rev.model.Role;
import dev.rev.utils.ConnectionUtil;

public class PersonPostgre implements PersonDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public Person getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Person> getAll() {
Set<Person> people = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql="Select * from person";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Person human = new Person();
				human.setId(rs.getInt("id"));
				human.setName(rs.getString("Name"));
				human.setUsername(rs.getString("username"));
				human.setPassword(rs.getString("password"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setRole_name(rs.getString("name"));
				
				human.setRole(job);
				
				
				people.add(human);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return people;
		
	}

	public void update(Person t) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Person t) {
		
		try (Connection conn =  cu.getConnection())
		{
			String sql = "delete from person where id = ?; delete from  where id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			pstmt.setInt(2, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public Person getByUsername(String username,String password) {
		// TODO Auto-generated method stub
		Person person = null;
		
		try (Connection conn = cu.getConnection())
		{
			String sql = "select * from person where "
					+ "person.username = ? And person.password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2,password);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				person = new Person();
				person.setName(rs.getString("Name"));
				person.setUsername(rs.getString("username"));
				person.setId(rs.getInt("id"));
				person.setPassword(rs.getString("password"));
				Role job = new Role();
				job.setId(rs.getInt("role_id"));
				job.setRole_name(rs.getString("name"));
				person.setRole(job);
			}else {
				return null;
			}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return person;
	
	}

	public Person add(Person p) throws NonUniqueUsernameException {
	
Person ps = null;
		try(Connection conn=cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into person values (default, ?, ?, ?,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getUsername());
			pstmt.setString(3, p.getPassword());
			pstmt.setInt(4, p.getRole().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				ps = p;
				ps.setId(rs.getInt(1));
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
		
		return ps;

	}

	@Override
	public String checkusername(String usernmae) {
		String ret="";
		  int count = 0;
		try(Connection conn=cu.getConnection()){
			//conn.setAutoCommit(false);
			String sql="select count(*) from person where username = ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, usernmae);
			//ps.executeQuery();
			ResultSet rs=ps.executeQuery();
			//System.out.println(rs);
			   rs.next();
	            count = rs.getInt(1);
	          if(count == 0) {
	        	  ret="0";
	          }else {
	        	  ret="!0";
	          }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return ret;
	}

}
