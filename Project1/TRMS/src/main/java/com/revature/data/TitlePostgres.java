package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;
import com.revature.beans.Title;
import com.revature.utils.ConnectionUtil;

public class TitlePostgres implements TitleDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Title getById(Integer id) {
		Title title = null;

		try (Connection conn = cu.getConnection()) {
	
			String sql = "select * from user_title where id = ?;"; 
															
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				title = new Title();
				title.setId(rs.getInt("id"));
				title.setName(rs.getString("name"));
				title.setPerson_id(rs.getInt("person_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return title;
	}

	@Override
	public Set<Title> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Title t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Title t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Title add(Title t) {	
		Title title = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "insert into user_title (name, person_id) values (?, ?)";
			String[] keys = {"id"};
			
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getPerson_id());
			
			int row = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
					title = t;
					title.setId(rs.getInt(1));
					conn.commit();
			
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return title;
	}
	
	public Set<Title> getTitlesByPersonId(Integer person_id){
		
		Set<Title> titles = new HashSet<>();

		try (Connection conn = cu.getConnection()) {
	
			String sql = "select * from user_title where person_id = ?;"; 
															
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, person_id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Title title = new Title();
				title.setId(rs.getInt("id"));
				title.setName(rs.getString("name"));
				title.setPerson_id(rs.getInt("person_id"));
				
				titles.add(title);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return titles;
	}
}
