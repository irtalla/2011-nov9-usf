package com.bikeshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.bikeshop.beans.Person;
import com.bikeshop.utils.ConnectionUtil;

public class PersonPostgres implements PersonDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Person add(Person p) {
		int id = 0;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO bikeshop.person("
					+ "id,username,password,role,first,last,email)"
					+ "values (default, ? , ?, ?, ?, ?, ?);";
			
			String[] keys = {"id"};
			PreparedStatement pst = conn.prepareStatement(sql, keys);
			pst.setString(1, p.getUsername());
			pst.setString(2, p.getPassword());
			pst.setString(3, p.getRole());
			pst.setString(4, p.getFirst());
			pst.setString(5, p.getLast());
			pst.setString(6, p.getEmail());
			
			

			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
				conn.commit();
				p.setId(id);
				return p;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Person getByUsername(String username) {
		Person p = new Person();
		try(Connection conn = cu.getConnection()) {
			
			String sql = "select * from bikeshop.person where username = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("password"));
				p.setRole(rs.getString("role"));
				p.setFirst(rs.getString("first"));
				p.setLast(rs.getString("last"));
				p.setEmail(rs.getString("email"));
				return p;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public Person getByID(Integer id) {
		Person p = new Person();
		try(Connection conn = cu.getConnection()) {
			
			String sql = "select * from bikeshop.person where id = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				p.setId(rs.getInt("id"));
				p.setUsername(rs.getString("username"));
				p.setPassword(rs.getString("password"));
				p.setRole(rs.getString("role"));
				p.setFirst(rs.getString("first"));
				p.setLast(rs.getString("last"));
				p.setEmail(rs.getString("email"));
				return p;
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Person updatePerson(Person p) {
		
		int id = 0;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bikeshop.person "
					+ "set username=?,password=?,role=?,first=?,last=?,email=?;";
			
			String[] keys = {"id"};
			PreparedStatement pst = conn.prepareStatement(sql, keys);
			pst.setString(1, p.getUsername());
			pst.setString(2, p.getPassword());
			pst.setString(3, p.getRole());
			pst.setString(4, p.getFirst());
			pst.setString(5, p.getLast());
			pst.setString(6, p.getEmail());
			
			

			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			
			if (rs.next()) {
				id = rs.getInt(1);
				conn.commit();
				p.setId(id);
				return p;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	public Set<Person> getAll(){
		Set<Person> people = new HashSet<>();
		return null;
		
	}
	public boolean delete(Integer id) {
		boolean deleted = false;
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "delete * from bikeshop.person where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				deleted = true;
				conn.commit();
			} else conn.rollback();
			return deleted;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleted;
	}



}
