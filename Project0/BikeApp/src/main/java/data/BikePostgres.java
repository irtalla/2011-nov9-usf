package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


import beans.Bikes;
import beans.Offers;
import utilities.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Bikes add(Bikes b) {
		Bikes t = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bike values (default, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, b.getName());
			pstmt.setString(2, b.getStatus());
			
			pstmt.executeLargeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				t = b;
				t.setId(rs.getInt(1));
				conn.commit();
			} else {
			conn.rollback();
		}
		}catch(Exception e) {
		e.printStackTrace();
	}
	return b;
}

@Override
public Bikes getById(Integer id) {
	Bikes bike = null;
	try (Connection conn = cu.getConnection()) {
		String sql = "select bike.status_name, bike.name, bike.id from bike where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			bike = new Bikes();
			bike.setId(rs.getInt("id"));
			bike.setName(rs.getString("name"));
			bike.setStatus(rs.getString("status_name"));
		}
	}	catch(Exception e) {
		e.printStackTrace();
	}
	return bike;
}

	@Override
	public Set<Bikes> getAll() {
		Set<Bikes> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select bike.status_name, bike.name, bike.id, from bikes";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bikes bike = new Bikes();
				bike.setId(rs.getInt("id"));
				bike.setName(rs.getString("name"));
				bike.setStatus(rs.getString("status_name"));
				bikes.add(bike);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bikes;
	}

	@Override
public Set<Bikes> getAvailableBikes() {
		Set<Bikes> bikes = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from bike where bike.status_name = 'Available'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Bikes bike = new Bikes();
				bike.setId(rs.getInt("id"));
				bike.setName(rs.getString("name"));
				bike.setStatus(rs.getString("status_name"));
				bikes.add(bike);
			}
			} catch (Exception e) {
				e.printStackTrace();
		}
		return bikes;
	}
	@Override
	public void update(Bikes t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bike set name = ?, status_name = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getStatus());
			pstmt.setInt(3, t.getId());
			
			int rowsAffected= pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			}	else {
 				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}

@Override
public void delete(Bikes b) {
	try (Connection conn = cu.getConnection()) {
		conn.setAutoCommit(false);
		String sql = "delete from bike where bike.id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,  b.getId());
		int rowsAffected = pstmt.executeUpdate();
		try { 
			sql = "delete from usr_bike where bike_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getId());
			rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				conn.commit();
			}	else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, b.getId());
		
		rowsAffected = pstmt.executeUpdate();
		if(rowsAffected > 0)
			conn.commit();
		else
			conn.rollback();
 }catch (Exception e) {
	e.printStackTrace();
}
	}

}
