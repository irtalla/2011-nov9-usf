package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.util.*;
//import com.revature.beans.Person;
import com.revature.beans.PersonBike;

import com.revature.utils.ConnectionUtil;

public class PersonBikePostgres implements PersonBikeDAO {
	
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public PersonBike add(PersonBike pb) {
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);

			
			String sql = "insert into person_bike (person_id, bike_id) values (?, ?);";
		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pb.getPerson_id());
			pstmt.setInt(2, pb.getBike_id());
			

			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pb;
	}
	
	
	@Override
	public PersonBike getById(Integer id) {
		
		return new PersonBike(); 
	}
	
	@Override
	public Set<PersonBike> getAll () {
		return new HashSet<PersonBike> (); 
	}

	@Override
	public void update (PersonBike pb) {
		
	}
	
	@Override
	public void delete (PersonBike pb) {
		
	}

}