package spencer.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.BicycleStatus;
import spencer.revature.beans.Customer;
import spencer.revature.utils.ConnectionUtil;

public class CustomerPostgres implements CustomerDAO {


	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public Customer add(Customer t) {
		Customer cus=null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into customer values (default, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				cus = t;
				cus.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cus;
	}

	public Customer getById(Integer id) {
		Customer cus=null;
		
		try (Connection conn = cu.getConnection()) {	
			conn.setAutoCommit(false);
			String sql = "select id, username, passwd from customer where id=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				cus = new Customer();
				cus.setId(rs.getInt("id"));
				cus.setUsername(rs.getString("username"));
				cus.setPassword(rs.getString("passwd"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cus;
	}

	public Set<Customer> getAll() {
		Set<Customer> custs = new HashSet<>();
		Customer cus=null;
		try (Connection conn = cu.getConnection()) {
			String sql = "select id, username, passwd from customer;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				cus = new Customer();
				cus.setId(rs.getInt("id"));
				cus.setUsername(rs.getString("username"));
				cus.setPassword(rs.getString("passwd"));
				
				custs.add(cus);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return custs;
	}

	public void update(Customer t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update customer set username = ?, passwd = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getUsername());
			pstmt.setString(2, t.getPassword());
			pstmt.setInt(3, t.getId());
			
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

	public void delete(Customer t) {
		// TODO Auto-generated method stub

	}

	
	public Customer getByUsername(String username) {
		Customer cus=null;
		
		try (Connection conn = cu.getConnection()) {	
			conn.setAutoCommit(false);
			String sql = "select id, username, passwd from customer where username=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				cus = new Customer();
				cus.setId(rs.getInt("id"));
				cus.setUsername(rs.getString("username"));
				cus.setPassword(rs.getString("passwd"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cus;
	}
	//Should be in the bicycleDAO
	public Set<Bicycle> getCustomersBikes(Customer c) {
		Set<Bicycle> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select bicycle.id, model, owner_id, status, username, bicycle.status_id, owner_id from customer join "+
					"(bicycle join bicycle_status on bicycle.status_id=bicycle_status.id) on customer.id = owner_id where owner_id=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getId());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Bicycle bike = new Bicycle();
				bike.setId(rs.getInt("id"));
				bike.setModel(rs.getString("model"));
				BicycleStatus stat = new BicycleStatus();
				stat.setId(rs.getInt("status_id"));
				stat.setStatus(rs.getString("status"));
				bike.setAvailable(stat);
				bike.setOwner(c);
				
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}
}


