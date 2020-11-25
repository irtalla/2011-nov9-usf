package dev.elliman.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.elliman.beans.Bike;
import dev.elliman.beans.Person;
import dev.elliman.utils.DBConnectionUtil;

public class BikePostgres implements BikeDAO {
	private DBConnectionUtil cu = DBConnectionUtil.getDBConnectionUtil();

	@Override
	public Integer add(Bike b) {
		Integer generatedID = null;

		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into bike_shop.bike values (default, ?, ?, ?)";
			String[] keys = {"bike_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, b.getColor());
			pstmt.setString(2, b.getModel());
			pstmt.setInt(3, 1);//admin is always the original owner

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if(rs.next()) {
				generatedID = rs.getInt(1);
				b.setId(generatedID);
				conn.commit();
			} else {
				conn.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return generatedID;
	}

	@Override
	public Bike getByID(Integer id) {
		Bike b = null;

		try(Connection conn = cu.getConnection()){
			String sql = "select * from bike_shop.bike where bike_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				b = new Bike(rs.getString("model"), rs.getString("color"));
				b.setId(rs.getInt("bike_id"));
				b.setOwner(rs.getInt("bike_owner"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return b;
	}

	@Override
	public Set<Bike> getAll() {
		Set<Bike> bikes = new HashSet<>();

		try(Connection conn = cu.getConnection()){
			String sql = "select * from bike_shop.bike";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			Bike b;
			while(rs.next()) {
				b = new Bike(rs.getString("model"), rs.getString("color"));
				b.setId(rs.getInt("bike_id"));
				bikes.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bikes;
	}

	@Override
	public void update(Bike b) {

		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update bike_shop.bike set model = ?, color = ?, bike_owner = ? where bike_id  = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getModel());
			pstmt.setString(2, b.getColor());
			pstmt.setInt(3, b.getOwner());
			pstmt.setInt(4, b.getId());

			Integer rowsChanged = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Bike b) {

		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from bike_shop.bike where bike_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getId());

			Integer rowsChanged = pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Bike getByModel(String model) {
		Bike b = null;

		try(Connection conn = cu.getConnection()){
			String sql = "select * from bike_shop.bike where model = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, model);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				b = new Bike(rs.getString("model"), rs.getString("color"));
				b.setId(rs.getInt("bike_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return b;
	}

	@Override
	public Set<Bike> getAvalibleBikes() {
		Set<Bike> bikes = new HashSet<>();

		try(Connection conn = cu.getConnection()){
			String sql = "select * from bike_shop.bike where bike_owner = 1";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			Bike b;
			while(rs.next()) {
				b = new Bike(rs.getString("model"), rs.getString("color"));
				b.setId(rs.getInt("bike_id"));
				bikes.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bikes;
	}

	@Override
	public Set<Bike> getOwnedBikes(Person person) {
		Set<Bike> bikes = new HashSet<>();

		try(Connection conn = cu.getConnection()){
			String sql = "select * from bike_shop.bike where bike_owner = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, person.getID());
			
			ResultSet rs = pstmt.executeQuery();

			Bike b;
			while(rs.next()) {
				b = new Bike(rs.getString("model"), rs.getString("color"));
				b.setId(rs.getInt("bike_id"));
				bikes.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bikes;
	}

}
