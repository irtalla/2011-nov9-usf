package com.revature.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.text.DateFormat;
import com.revature.utils.ConnectionUtil;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;

public class EvtReqPostgres implements EvtReqDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public EvtReq getById(Integer id) {
		EvtReq evtReq = null;

		try (Connection conn = cu.getConnection()) {
	
			String sql = "select * from evt_req where id = ?;"; 
															
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
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "UPDATE evt_req SET name= ?, posting_date= ?, direct_supervisor_approval_status_id= ? , department_head_approval_status_id= ? ,benefits_coordinator_approval_status_id=?, person_id= ?, type_id= ?, req_fr_cmnt_id= ?, priority_id= ?, start_date= ?, amount= ? WHERE id= ?";
							
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, t.getName());
			pstmt.setDate(2, convertUtilToSql(t.getPosting_date()));
			pstmt.setInt(6, t.getPerson_id());
			pstmt.setInt(7, t.getType_id());
			pstmt.setDate(10, convertUtilToSql(t.getStart_date()));
			pstmt.setDouble(11, t.getAmount()); 
			pstmt.setInt(12, t.getId());
					
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
	
	@Override
	public void delete(EvtReq t) {
		// TODO Auto-generated method stub

	}

	@Override
	public EvtReq add(EvtReq t) {
		
      EvtReq evtReq = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "insert into evt_req (name, posting_date, person_id, type_id, start_date, amount) values (?, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setString(1, t.getName());
			pstmt.setDate(2, convertUtilToSql(t.getPosting_date()));
			pstmt.setInt(3, t.getPerson_id());
			pstmt.setInt(4, t.getType_id());
			pstmt.setDate(5, convertUtilToSql(t.getStart_date()));
			pstmt.setDouble(6, t.getAmount()); 
			
			int row = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
					evtReq = t;
					evtReq.setId(rs.getInt(1));
					conn.commit();
			
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return evtReq;

	}

	@Override
	public Set<EvtReq> getAvailableEvents() {
		Set<EvtReq> evtReqs = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
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
