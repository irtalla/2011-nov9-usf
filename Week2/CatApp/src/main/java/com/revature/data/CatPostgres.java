package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class CatPostgres implements CatDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Cat add(Cat t) {
		Cat c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into cat values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getAge());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.setInt(4, t.getBreed().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				c = t;
				c.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Cat getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Cat> getAll() {
		Set<Cat> cats = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select cat_status.id, cat_status.name, age, status_id, status_name, breed_id, "
					+ "breed.name as breed_name from "
					+ "(select cat.id, cat.name, age, status_id, breed_id, status.name as status_name from "
					+ "cat join status on status_id = status.id) as cat_status "
					+ "join breed on breed_id = breed.id";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Cat cat = new Cat();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				cat.setAge(rs.getInt("age"));
				Breed b = new Breed();
				b.setId(rs.getInt("breed_id"));
				b.setName(rs.getString("breed_name"));
				cat.setBreed(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				cat.setStatus(s);
				cats.add(cat);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cats;
	}
	
	@Override
	public Set<Cat> getAvailableCats() {
		return null;
	}

	@Override
	public void update(Cat t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Cat t) {
		// TODO Auto-generated method stub

	}

}
