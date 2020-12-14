package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Event;
import com.revature.beans.EventType;
import com.revature.utils.ConnectionUtil;

public class EventPostgres implements EventDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Event add(Event t) {
		Connection conn = cu.getConnection();
		

		try 
		{
			
			conn.setAutoCommit(false);
			String sql = "insert into reimbursement_event values (default, ?, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setInt(2, t.getType().getId());
			pstmt.setDate(3, t.getDate());
			pstmt.setTime(4, t.getTime());
			pstmt.setString(5, t.getLocation());
			pstmt.setString(6, t.getDescription());
			pstmt.setDouble(7, t.getCost());
			
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) 
			{
				
				t.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} 
		catch (Exception e) 
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return t;
	}

	@Override
	public Event getById(Integer id) {
		Connection conn = cu.getConnection();
		
		try
		{
			String sql = "select * from reimbursement_event where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Event evt = new Event();
				evt.setId(rs.getInt("id"));
				evt.setName(rs.getString("name"));
				evt.setCost(rs.getDouble("event_cost"));
				evt.setDate(rs.getDate("event_date"));
				evt.setTime(rs.getTime("event_time"));
				evt.setDescription(rs.getString("event_description"));
				evt.setLocation(rs.getString("event_location"));
				EventType type = new EventType();
				type.setId(rs.getInt("event_type_id"));
				
				
				sql = "select * from event_type where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, type.getId());
				
				rs = pstmt.executeQuery();
				
				if (rs.next())
				{
					type.setName(rs.getString("name"));
					evt.setType(type);
				}
				
				
				
				return evt;
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
	}

	@Override
	public Set<Event> getAll() {
		Connection conn = cu.getConnection();
		Set<Event> results = new HashSet<Event>();
		try
		{
			String sql = "select * from reimbursement_event";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				Event evt = new Event();
				evt.setId(rs.getInt("id"));
				evt.setName(rs.getString("name"));
				evt.setCost(rs.getDouble("event_cost"));
				evt.setDate(rs.getDate("event_date"));
				evt.setTime(rs.getTime("event_time"));
				evt.setDescription(rs.getString("event_description"));
				evt.setLocation(rs.getString("event_location"));
				EventType type = new EventType();
				type.setId(rs.getInt("event_type_id"));
				
				
				sql = "select * from event_type where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, type.getId());
				
				ResultSet rs2 = pstmt.executeQuery();
				
				if (rs2.next())
				{
					type.setName(rs2.getString("name"));
					evt.setType(type);
				}
				
				
				
				results.add(evt);
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return results;
	}

	@Override
	public void update(Event t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "update reimbursement_event set event_type_id = ?, name = ?, event_date = ?, event_time = ?, event_location = ?, event_description = ?, event_cost = ?  where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getType().getId());
			pstmt.setString(2, t.getName());
			pstmt.setDate(3, t.getDate());
			pstmt.setTime(4, t.getTime());
			pstmt.setString(5, t.getLocation());
			pstmt.setString(6, t.getDescription());
			pstmt.setDouble(7, t.getCost());
			pstmt.setInt(8, t.getId());
			
			
			
			pstmt.execute();
			
			
			conn.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Event t) {
		Connection conn = cu.getConnection();
		
		try
		{
			conn.setAutoCommit(false);
			String sql = "delete from reimbursement_event where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			
			pstmt.executeUpdate();
			
			conn.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
