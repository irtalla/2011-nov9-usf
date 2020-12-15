package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.sql.SQLException;
import java.util.Date;
import com.revature.beans.InfoRequest;
import com.revature.beans.Person;
import com.revature.util.ConnectionUtil;
import com.revature.beans.GenreSpecialization;

public class GenreSpecializationPostgreSQL implements GenreSpecializationDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public void update(GenreSpecialization t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<GenreSpecialization> getAll() {
		Set<GenreSpecialization> gspecs = new HashSet<GenreSpecialization>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from genre_specializations";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				GenreSpecialization n = new GenreSpecialization();
				n.setGenreSpecID(rs.getInt(1));
				n.setPersonID(rs.getInt(2));
				n.setGenreID(rs.getInt(3));
				gspecs.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return gspecs;
	}

	@Override
	public Set<GenreSpecialization> getByGenreID(Integer id) {
		Set<GenreSpecialization> gspecs = new HashSet<GenreSpecialization>();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from genre_specializations where genre_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				GenreSpecialization n = new GenreSpecialization();
				n.setGenreSpecID(rs.getInt(1));
				n.setPersonID(rs.getInt(2));
				n.setGenreID(rs.getInt(3));
				gspecs.add(n);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return gspecs;
	}

	@Override
	public GenreSpecialization getByGenreSpecializationID(Integer id) {
		GenreSpecialization n = new GenreSpecialization();
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "select * from genre_specializations where genre_specialization_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				n.setGenreSpecID(rs.getInt(1));
				n.setPersonID(rs.getInt(2));
				n.setGenreID(rs.getInt(3));
			}
			else {
				return null;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public void delete(GenreSpecialization gs) {
		// TODO Auto-generated method stub

	}

}
