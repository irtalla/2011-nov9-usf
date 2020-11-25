package spencer.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import spencer.revature.utils.*;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.BicycleStatus;
import spencer.revature.beans.Customer;



public class BicyclePostgres implements BicycleDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


	public Bicycle add(Bicycle b) {
		Bicycle bike=null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bicycle values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, b.getModel());
			pstmt.setInt(2, b.getPaymentsDue());
			pstmt.setInt(3, b.getAvailable().getId());
			pstmt.setInt(4, b.getOwner().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				bike = b;
				bike.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bike;
	}

	public Bicycle getById(Integer id) {
		Bicycle bike = null;

		try (Connection conn = cu.getConnection()) {	
			conn.setAutoCommit(false);
			String sql = "select bicycle.id, model, owner_id, status, username, bicycle.status_id, owner_id from customer join "+
			"(bicycle join bicycle_status on bicycle.status_id=bicycle_status.id) on bicycle.id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bike = new Bicycle();
				bike.setId(rs.getInt("id"));
				bike.setModel(rs.getString("model"));
				BicycleStatus stat = new BicycleStatus();
				stat.setId(rs.getInt("status_id"));
				stat.setStatus(rs.getString("status"));
				bike.setAvailable(stat);
				Customer c = new Customer();
				c.setId(rs.getInt("owner_id"));
				c.setUsername(rs.getString("username"));
				bike.setOwner(c);
				
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bike;
	}

	public Set<Bicycle> getAll() {
		Set<Bicycle> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select bicycle.id, model, owner_id, status, username, bicycle.status_id, owner_id from customer join "+
					"(bicycle join bicycle_status on bicycle.status_id=bicycle_status.id) on customer.id = owner_id;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bicycle bike = new Bicycle();
				bike.setId(rs.getInt("id"));
				bike.setModel(rs.getString("model"));
				BicycleStatus stat = new BicycleStatus();
				stat.setId(rs.getInt("status_id"));
				stat.setStatus(rs.getString("status"));
				bike.setAvailable(stat);
				Customer c = new Customer();
				c.setId(rs.getInt("owner_id"));
				c.setUsername(rs.getString("username"));
				bike.setOwner(c);
				
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}
	

	public void update(Bicycle t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bicycle set model = ?, status_id = ?, owner_id = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getModel());
			pstmt.setInt(2, t.getAvailable().getId());
			pstmt.setInt(3, t.getOwner().getId());
			pstmt.setInt(4, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
			{		
					conn.commit();
			}
				else {
					conn.rollback();
			} 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void delete(Bicycle t) {
		try (Connection conn =  cu.getConnection())
		{
			conn.setAutoCommit(false);
			String sql = "delete from bicycle where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
	public Set<Bicycle> getAvailableBicycles() {
		Set<Bicycle> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select bicycle.id, model, owner_id, status, username, bicycle.status_id, owner_id from customer join "+
					"(bicycle join bicycle_status on bicycle.status_id=bicycle_status.id) on customer.id = owner_id"
					+" where status = 'Available';";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bicycle bike = new Bicycle();
				bike.setId(rs.getInt("id"));
				bike.setModel(rs.getString("model"));
				BicycleStatus stat = new BicycleStatus();
				stat.setId(rs.getInt("status_id"));
				stat.setStatus(rs.getString("status"));
				bike.setAvailable(stat);
				Customer c = new Customer();
				c.setId(rs.getInt("owner_id"));
				c.setUsername(rs.getString("username"));
				bike.setOwner(c);
				
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}

	
	
}
