package com.revature.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.utils.ConnectionUtil;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;

public class EvtReqPostgres implements EvtReqDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public EvtReq getById(Integer id) {
		// TODO Auto-generated method stub
		EvtReq evtReq = null;

		try (Connection conn = cu.getConnection()) {
			// Get the cat object
			/*
			 * String sql =
			 * "select cat_status.id, cat_status.name, age, status_id, status_name, breed_id, "
			 * + "breed.name as breed_name from " +
			 * "(select cat.id, cat.name, age, status_id, breed_id, status.name as status_name from "
			 * + "cat join status on status_id = status.id) as cat_status " +
			 * "join breed on breed_id = breed.id where cat_status.id = ?";
			 */

			String sql = "select * from evt_req where id = ?;"; // do you think it's person_id or event_id? Let me show
																// you the ERD
			// just to be sure

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				evtReq = new EvtReq();
				evtReq.setId(rs.getInt("id"));
				evtReq.setName(rs.getString("name"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return evtReq;

	}

	@Override
	public Set<EvtReq> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(EvtReq t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(EvtReq t) {
		// TODO Auto-generated method stub

	}

	@Override
	public EvtReq add(EvtReq e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<EvtReq> getAvailableEvents() {
		Set<EvtReq> evtReqs = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
//			String sql = "select * from (select cat_status.id, cat_status.name, age, "
//					+ "status_id, status_name, breed_id, " 
//					+ "breed.name as breed_name from "
//					+ "(select cat.id, cat.name, age, status_id, breed_id, status.name as "
//					+ "status_name from " 
//					+ "cat join status on status_id = status.id) as cat_status "
//					+ "join breed on breed_id = breed.id) as available where"
//					+ " available.status_name = 'Available'";

			String sql = "select * from evt_req;";

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				EvtReq evtReq = new EvtReq();
				evtReq.setId(rs.getInt("id"));
				evtReq.setName(rs.getString("name"));

				evtReqs.add(evtReq);
			}

		} catch (Exception e) {
			System.out.println("error fetching data");
			e.printStackTrace();
		}

		return evtReqs;

	}

	@Override
	public void addEvent(Person p, EvtReq e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public Set<EvtReq> getEventsByPersonId(Integer person_id) {
		
		Set<EvtReq> evtReqs = new HashSet<>();
		try (Connection conn = cu.getConnection()) {

			String sql = "select * from evt_req where person_id = ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, person_id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				EvtReq evtReq = new EvtReq();
				evtReq.setId(rs.getInt("id"));
				evtReq.setName(rs.getString("name"));

				evtReqs.add(evtReq);
			}

		} catch (Exception e) {
			System.out.println("error fetching data");
			e.printStackTrace();
		}

		return evtReqs;
		
		
	}
}
