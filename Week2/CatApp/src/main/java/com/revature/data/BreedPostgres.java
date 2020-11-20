package com.revature.data;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.utils.ConnectionUtil;

public class BreedPostgres implements BreedDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
			

	@Override
	public Breed add(Breed t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Breed getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Breed> getAll() {
		Set<Breed> breed= new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from breed";
			Statement stmt =  conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {							//	1    persian
				Breed br=new Breed();						//2    short hair	
				br.setId(rs.getInt("id"));				//	3    antuerh
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
	Breed c = null;
	int id=t.getId();
	String new_breed= t.getName();
		
		try (Connection conn = cu.getConnection()) {
			if(!(t.equals(null))) {
			conn.setAutoCommit(false);
			String sql = "Update Breed set name = ? where id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,new_breed);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				c = t;
				c.setId(rs.getInt(1));
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
		// TODO Auto-generated method stub

	}

}
