package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Breed;
import com.revature.utils.ConnectionUtil;

public class BreedPostgres implements BreedDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Breed add(Breed t) {
		Breed b = null;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into breed values (default,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				b=t;
				b.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public Breed getById(Integer id) {
		Breed b = null;
		try(Connection conn = cu.getConnection()) {
			if(id != null) {
				conn.setAutoCommit(false);
				String sql = "select * from Breed where Breed_id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) {
					b = new Breed();
					b.setId(rs.getInt("id"));
					b.setName(rs.getString("name"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public Set<Breed> getAll() {
		Set<Breed> breed= new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from breed";
			Statement stmt =  conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Breed br=new Breed();	
				br.setId(rs.getInt("id"));
				br.setName(rs.getString("name"));
				breed.add(br);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return breed;
	}

	@Override
	public void update(Breed t) {
		try (Connection conn = cu.getConnection()) {
			if(!(t.equals(null))) {
				conn.setAutoCommit(false);
				String sql = "Update Breed set name = ? where id = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,t.getName());
				pstmt.setInt(2, t.getId());
				int rowsAffected = pstmt.executeUpdate();
				
				if (rowsAffected > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Breed t) {
		try(Connection conn = cu.getConnection()) {
			if(t != null) {
				conn.setAutoCommit(false);
				String sql = "delete from Breed where id=";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,t.getId());
				int rowsAffected = pstmt.executeUpdate();
				
				if (rowsAffected > 0)
					conn.commit();
				else
					conn.rollback();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
