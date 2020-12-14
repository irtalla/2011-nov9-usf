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
			
			String sql = "UPDATE evt_req SET name= ?, posting_date= ?, direct_supervisor_approval_status_id= ? ,"
					+ " department_head_approval_status_id= ? ,benefits_coordinator_approval_status_id=?,"
					+ " person_id= ?, type_id= ?, req_fr_cmnt_id= ?, priority_id= ?, start_date= ?, amount= ? "
					+ "event_time = ?, location_id = ?, grading_format_id = ?, work_related_justification = ?, passing_cutoff_grade_id = ?,"
					+ " WHERE id= ?";
			
							
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, t.getName());
			pstmt.setDate(2, convertUtilToSql(t.getPosting_date()));
			pstmt.setInt(6, t.getPerson_id());
			pstmt.setInt(7, t.getType_id());
			pstmt.setDate(10, convertUtilToSql(t.getStart_date()));
			pstmt.setDouble(11, t.getAmount()); 
			pstmt.setTime(12, t.getEvent_time());
			pstmt.setInt(13, t.getLocation_id());
			pstmt.setInt(14, t.getGrading_format_id());
			pstmt.setString(15, t.getWork_related_justification());
			pstmt.setInt(16, t.getPassing_cutoff_grade_id());
			pstmt.setInt(17, t.getId());
					
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
			
			String sql = "insert into evt_req (name, posting_date, person_id, type_id, start_date, amount, event_time, location_id, grading_format_id, work_related_justification, passing_cutoff_grade_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			
			pstmt.setString(1, t.getName());
			pstmt.setDate(2, convertUtilToSql(t.getPosting_date()));
			pstmt.setInt(3, t.getPerson_id());
			pstmt.setInt(4, t.getType_id());
			pstmt.setDate(5, convertUtilToSql(t.getStart_date()));
			pstmt.setDouble(6, t.getAmount()); 
			pstmt.setTime(7, t.getEvent_time());
			pstmt.setInt(8, t.getLocation_id());
			pstmt.setInt(9, t.getGrading_format_id());
			pstmt.setString(10,  t.getWork_related_justification());
			pstmt.setInt(11,  t.getPassing_cutoff_grade_id());
			
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
				evtReq.setPosting_date(rs.getDate("posting_date"));
				
				if (rs.getObject("direct_supervisor_approval_status_id") != null) {
					evtReq.setDirect_supervisor_approval_id(rs.getInt("direct_supervisor_approval_status_id"));
				  }
				
				if (rs.getObject("department_head_approval_status_id") != null) {
					evtReq.setDepartment_head_approval_id(rs.getInt("department_head_approval_status_id"));
				  }
				
				if (rs.getObject("benefits_coordinator_approval_status_id") != null) {
					evtReq.setBenefits_coordinator_approval_id(rs.getInt("benefits_coordinator_approval_status_id"));
				  }
				
				if (rs.getObject("req_fr_cmnt_id") != null) {
					evtReq.setReq_fr_cmnt_id(rs.getInt("req_fr_cmnt_id"));
				  }
				
				if (rs.getObject("priority_id") != null) {
					evtReq.setPriority_id(rs.getInt("priority_id"));
				  }
				
				if (rs.getObject("event_time") != null) {
				    
				  }
				
				if (rs.getObject("location_id") != null) {
				    
				  }
				
				if (rs.getObject("grading_format_id") != null) {
				    
				  }
				
				if (rs.getObject("work_related_justification") != null) {
				    
				  }
				
				if (rs.getObject("passing_cutoff_grade_id") != null) {
				    
				  }
				
				evtReq.setPerson_id(rs.getInt("person_id"));
				evtReq.setType_id(rs.getInt("type_id"));
				evtReq.setStart_date(rs.getDate("start_date"));
				evtReq.setAmount(rs.getDouble("amount"));
				

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
				evtReq.setPosting_date(rs.getDate("posting_date"));
				
				if (rs.getObject("direct_supervisor_approval_status_id") != null) {
					evtReq.setDirect_supervisor_approval_id(rs.getInt("direct_supervisor_approval_status_id"));
				  }
				
				if (rs.getObject("department_head_approval_status_id") != null) {
					evtReq.setDepartment_head_approval_id(rs.getInt("department_head_approval_status_id"));
				  }
				
				if (rs.getObject("benefits_coordinator_approval_status_id") != null) {
					evtReq.setBenefits_coordinator_approval_id(rs.getInt("benefits_coordinator_approval_status_id"));
				  }
				
				if (rs.getObject("req_fr_cmnt_id") != null) {
					evtReq.setReq_fr_cmnt_id(rs.getInt("req_fr_cmnt_id"));
				  }
				
				if (rs.getObject("priority_id") != null) {
					evtReq.setPriority_id(rs.getInt("priority_id"));
				  }
				
				if (rs.getObject("event_time") != null) {
				    
				  }
				
				if (rs.getObject("location_id") != null) {
				    
				  }
				
				if (rs.getObject("grading_format_id") != null) {
				    
				  }
				
				if (rs.getObject("work_related_justification") != null) {
				    
				  }
				
				if (rs.getObject("passing_cutoff_grade_id") != null) {
				    
				  }
				
				evtReq.setPerson_id(rs.getInt("person_id"));
				evtReq.setType_id(rs.getInt("type_id"));
				evtReq.setStart_date(rs.getDate("start_date"));
				evtReq.setAmount(rs.getDouble("amount"));
				
				evtReqs.add(evtReq);
			}

		} catch (Exception e) {
			System.out.println("error fetching data");
			e.printStackTrace();
		}

		return evtReqs;
		
		
	}
}
