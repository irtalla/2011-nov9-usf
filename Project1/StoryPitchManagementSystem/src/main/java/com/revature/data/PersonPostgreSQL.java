package com.revature.data;

import com.revature.beans.Person;
import com.revature.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
//import org.postgresql.util.PSQLException;

public class PersonPostgreSQL implements PersonDAO{
private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Person add(Person p) {
		if(p == null) {
			System.out.println("Cannot add a null person to the database");
			return p;
		}
		else {
		Person n = new Person();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into persons values(default, ?, ?, default, ?, ?)";
			String[] keys = {"person_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setString(1,  p.getName());
			pstmt.setString(2,  p.getPassword());
			pstmt.setInt(3,  p.getRoleID());
			pstmt.setString(4,  p.getUsername());
			
			try {
				pstmt.executeUpdate();
			} catch (SQLException e) {
				return n;
				//e.printStackTrace();
			}
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				n=p;
				n.setPersonID(rs.getInt(1));
				conn.commit();
			}
			else {
				conn.rollback();
			}
			
			}
			catch(Exception e) {
			e.printStackTrace();
			}
			return n;
		}
	}

	@Override
	public Person getByPersonID(Integer id) {
		Person n = new Person();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from persons where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,  id);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				n.setPersonID(rs.getInt(1));
				n.setName(rs.getString(2));
				n.setPassword(rs.getString(3));
				n.setPoints(rs.getInt(4));
				n.setRoleID(rs.getInt(5));
				n.setUsername(rs.getString(6));
				conn.commit();
			}
			else {
				n = null;
				conn.rollback();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public Set<Person> getAll() {
		Set<Person> persons = new HashSet<Person>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from persons order by person_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Person n = new Person();
				n.setPersonID(rs.getInt(1));
				n.setName(rs.getString(2));
				n.setPassword(rs.getString(3));
				n.setPoints(rs.getInt(4));
				n.setRoleID(rs.getInt(5));
				n.setUsername(rs.getString(6));
				persons.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return persons;
	}

	@Override
	public void delete(Person t) {
		if(t == null) {
			System.out.println("Cannot delete a null person from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				//first delete rejections for stories which the person has authorized
				String sql = "delete from rejections where rejector_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getPersonID());
				pstmt.executeUpdate();
				// then delete approvals of stories the person has authorized
				String sql2 = "delete from approvals where approver_id = ?";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, t.getPersonID());
				pstmt2.executeUpdate();
				// then delete change requests of stories for the person
				String sql4 = "delete from change_requests where person_requested = ? or person_requesting = ?";
				PreparedStatement pstmt4 = conn.prepareStatement(sql4);
				pstmt4.setInt(1, t.getPersonID());
				pstmt4.setInt(2, t.getPersonID());
				pstmt4.executeUpdate();
				// then delete info responses for the person
				String sql5 = "delete from info_responses where person_responded = ? or person_responding = ?";
				PreparedStatement pstmt5 = conn.prepareStatement(sql5);
				pstmt5.setInt(1, t.getPersonID());
				pstmt5.setInt(2, t.getPersonID());
				pstmt5.executeUpdate();
				// then delete info requests for the person
				String sql6 = "delete from info_requests where person_requested = ? or person_requesting = ?";
				PreparedStatement pstmt6 = conn.prepareStatement(sql6);
				pstmt6.setInt(1, t.getPersonID());
				pstmt6.setInt(2, t.getPersonID());
				pstmt6.executeUpdate();
				// finally delete person from persons table
				String sql3 = "delete from persons "
						+" where person_id = ?";
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setInt(1, t.getPersonID());
				int r = pstmt3.executeUpdate();
				
				if(r > 0) {
					conn.commit();
				}
				else {
					conn.rollback();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Person t) {
		if(t == null) {
			System.out.println("Cannot update a null person from the database");
		}
		else {
			try (Connection conn = cu.getConnection()){
				conn.setAutoCommit(false);
				String sql = "update persons "
						+" set password = ?, "
						+" role_id = ?, "
						+" points = ? "
						+" where person_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getPassword());
				pstmt.setInt(2, t.getRoleID());
				pstmt.setInt(3, t.getPoints());
				pstmt.setInt(4, t.getPersonID());
				System.out.println(t);
				int r = pstmt.executeUpdate();
				
				if(r > 0) {
					System.out.println("Committing changes");
					conn.commit();
				}
				else {
					conn.rollback();
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Person getByUsername(String username) {
		Person p = null;
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from persons where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  username);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				Person n = new Person();
				n.setPersonID(rs.getInt(1));
				n.setName(rs.getString(2));
				n.setPassword(rs.getString(3));
				n.setPoints(rs.getInt(4));
				n.setRoleID(rs.getInt(5));
				n.setUsername(rs.getString(6));
				conn.commit();
				p = n;
			}
			else {
				conn.rollback();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
