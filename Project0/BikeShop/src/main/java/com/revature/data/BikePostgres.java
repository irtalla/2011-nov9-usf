package com.revature.data;

//import java.beans.Statement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bike;
import com.revature.beans.Brand;
import com.revature.beans.SpecialFeature;
import com.revature.beans.Status;
import com.revature.utils.ConnectionUtil;

public class BikePostgres implements BikeDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Bike add(Bike t) {
		Bike bk = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into bike (name, condition, status_id, brand_id) values (?, ?, ?, ?)";
			String[] keys = {"bike_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getCondition());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.setInt(4, t.getBrand().getId());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				if (t.getSpecialFeatures().size() > 0) {
					if (addSpecialFeatures(t, conn)) {
						bk = t;
						bk.setId(rs.getInt(1));
						conn.commit();
					} else {
						conn.rollback();
					}
				} else {
					bk = t;
					bk.setId(rs.getInt(1));
					conn.commit();
				}
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bk;
	}


	@Override
	public Bike getById(Integer id) {
		Bike bike = null;

		try (Connection conn = cu.getConnection()) {			
			
			String sql = "select bike_status.bike_id, bike_status.name, bike_status.condition, bike_status.status_id, bike_status.status_name, brand.brand_id, brand.name as brand_name" 
					+ " from (select bike.bike_id, bike.name, bike.condition, bike.status_id, bike.brand_id, status.name as status_name" 
					+		" from bike join status on bike.status_id = status.status_id) as bike_status"
					+	" join brand on bike_status.brand_id = brand.brand_id"  
					+ " where bike_id = ?"; 
					
				/* 	"select bike_status.id, bike_status.name, age, status_id, status_name, brand_id, "
					+ "brand.name as brand_name from "
					+ "(select bike.id, bike.name, age, status_id, brand_id, status.name as status_name from "
					+ "bike join status on status_id = status.id) as bike_status "
					+ "join brand on brand_id = brand.id where id = ?";
					*/
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bike = new Bike();
				bike.setId(rs.getInt("bike_id"));
				bike.setName(rs.getString("name"));
				bike.setCondition(rs.getString("condition"));
				Brand b = new Brand();
				b.setId(rs.getInt("brand_id"));
				b.setName(rs.getString("brand_name"));
				bike.setBrand(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				bike.setStatus(s);
				bike.setSpecialFeatures(getSpecialFeaturesByBikeId(bike.getId(), conn));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bike;
	}

	@Override
	public Set<Bike> getAll() {
		Set<Bike> bikes = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select bike_status.id, bike_status.name, age, status_id, status_name, brand_id, "
					+ "brand.name as brand_name from "
					+ "(select bike.id, bike.name, age, status_id, brand_id, status.name as status_name from "
					+ "cat join status on status_id = status.id) as cat_status "
					+ "join brand on brand_id = brand.id";
		/* add casts to fix errors to the following statements
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		*/
			
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("id"));
				bike.setName(rs.getString("name"));
				bike.setCondition(rs.getString("condition"));
				Brand b = new Brand();
				b.setId(rs.getInt("brand_id"));
				b.setName(rs.getString("brand_name"));
				bike.setBrand(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				bike.setStatus(s);
				
				bike.setSpecialFeatures(getSpecialFeaturesByBikeId(bike.getId(), conn));
				
				/* Is this necessary?
				bike.add(bike);
				*/
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
	}
	
	@Override
	public Set<Bike> getAvailableBikes() {
		Set<Bike> bikes = new HashSet<>();
		try (Connection conn = cu.getConnection()) {
			String sql = 
					
//					"select * from (select bike_status.id, bike_status.name, condition, status_id, status_name, brand_id, " 
//					+ "brand.name as brand_name from "
//					+ "(select bike.id, bike.name, condition, status_id, brand_id, status.name as status_name from " 
//					+ "bike join status on status_id = status.id) as bike_status "
//					+ "join brand on brand_id = brand.id) as available where available.status_name = 'available'";
			
"select * from (select bike_status.bike_id, bike_status.name, bike_status.condition, bike_status.status_id, bike_status.status_name, brand.brand_id, brand.name as brand_name " 
+ "from (select bike.bike_id, bike.name, bike.condition, bike.status_id, bike.brand_id, status.name as status_name " 
+ "from bike join status on bike.status_id = status.status_id) as bike_status " 
+ "join brand on brand.brand_id = bike_status.brand_id) as available " 
+ "where available.status_name = 'available' order by available.bike_id";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
		
//			Statement stmt = (Statement) conn.createStatement();
//			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
			
			while (rs.next()) {
				Bike bike = new Bike();
				bike.setId(rs.getInt("bike_id"));
				bike.setName(rs.getString("name"));
				bike.setCondition(rs.getString("condition"));
				Brand b = new Brand();
				b.setId(rs.getInt("brand_id"));
				b.setName(rs.getString("brand_name"));
				bike.setBrand(b);
				Status s = new Status();
				s.setId(rs.getInt("status_id"));
				s.setName(rs.getString("status_name"));
				bike.setStatus(s);
				
				bike.setSpecialFeatures(getSpecialFeaturesByBikeId(bike.getId(), conn));
				
				bikes.add(bike);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bikes;
		
	}

	@Override
	public void update(Bike t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update bike set name = ?, condition = ?, status_id = ?, brand_id = ? where bike_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getCondition());
			pstmt.setInt(3, t.getStatus().getId());
			pstmt.setInt(4, t.getBrand().getId());
			pstmt.setInt(5, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				if (updateSpecialFeatures(t, conn)) {
					System.out.println("UpdateSpecialFeatures succeded");
					conn.commit();
				}		
				else {
					System.out.println("UpdateSpecialFeatures failed");
					conn.rollback();
				}
			} else {
				System.out.println("Bike.update did not update anything!"); 
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Bike t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false); // added this 
			// first, we need to delete the bike's special features
			//String sql = "delete from special_feature where bike_id = ?";
//			String sql = "delete from special_feature where special_feature_id = ?";
			//PreparedStatement pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, t.getId());

			//int rowsAffected = pstmt.executeUpdate();
			
			int rowsAffected = 0;
			//System.out.println( "Deleted successfully from special_feature");
			
			//if (rowsAffected == t.getSpecialFeatures().size()) {
				// next, we need to delete the associates between the bike
				// and its owner(s)
				String sql = "delete from person_bike where bike_id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());
				pstmt.executeUpdate();
				
				System.out.println( "Deleted successfully from person_bike");
				
				// then, we can delete the bike
				sql = "delete from bike where bike_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getId());   // changed 2, to 1, ?????
				
				rowsAffected = pstmt.executeUpdate();
				
				System.out.println( "Deleted successfully from bike");
				
				if(rowsAffected > 0)
					conn.commit();		
				else
					conn.rollback();	
			//} else {
			//	conn.rollback();
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private boolean addSpecialFeatures(Bike bk, Connection conn) throws SQLException {
		String sql = "insert into bike_special_feature values";
		for (int i = 0; i < bk.getSpecialFeatures().size(); i++) {
			sql += " (?,?)";
			if (i < bk.getSpecialFeatures().size() - 1)
				sql += ",";
		}
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int i = 0;
		for (SpecialFeature sf : bk.getSpecialFeatures()) {
			pstmt.setInt(++i, bk.getId());
			pstmt.setInt(++i, sf.getId());
		}
		int rowsAffected = pstmt.executeUpdate();
		
		return (rowsAffected == bk.getSpecialFeatures().size());
	}
	
	private Set<SpecialFeature> getSpecialFeaturesByBikeId(Integer id, Connection conn) throws SQLException {
		Set<SpecialFeature> features = new HashSet<>();
		
		return null;  //?????
//		String sql = 
////				"select special_feature_id, name from bike_special_feature join special_feature on "
////				+ "special_feature_id = id where bike_id = ?";
//				"select bike_special_feature.special_feature_id, special_feature.name from bike_special_feature "
//			+ "join special_feature on special_feature.special_feature_id = bike_id where bike_id = ?";
//
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setInt(1, id);
//		ResultSet rsSn = pstmt.executeQuery();
//		
//		while (rsSn.next()) {
//			SpecialFeature sf = new SpecialFeature();
//			sf.setId(rsSn.getInt("special_feature_id"));
//			sf.setName(rsSn.getString("name"));
//			features.add(sf);
//		}
//		
//		return features;
	}

	// this is just ONE way of handling this problem, and it isn't
	// necessarily the best way! :) just want to show different options.
	private boolean updateSpecialFeatures(Bike bk, Connection conn) throws SQLException {
		return true;    ////????
//		int needsInDatabase = 0;
//		
//		String sql = "select bike_id, special_feature_id, name "
//				+ "from bike_special_feature join special_feature on "
//				+ "special_feature_id = special_feature.id where bike_id = ?";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setInt(1, bk.getId());
//		
//		ResultSet rs = pstmt.executeQuery();
//		
//		// this set will end up including needs that are in
//		// the database that are no longer associates with this
//		// cat anymore (after the update)
//		Set<SpecialFeature> featuresToDelete = new HashSet<>();
//		
//		// this set will end up including needs that are not
//		// yet in the database, but need to be added as they
//		// are part of the updated cat
//		Set<SpecialFeature> updatedFeatures = bk.getSpecialFeatures();
//		
//		while (rs.next()) {
//			SpecialFeature sf = new SpecialFeature();
//			sf.setId(rs.getInt("special_feature_id"));
//			sf.setName(rs.getString("name"));
//			
//			// if the cat's current special needs do not include this,
//			// it must be deleted from the database
//			if (!updatedFeatures.contains(sf)) {
//				featuresToDelete.add(sf);
//			} else {
//				// if it does contain the special need, we don't
//				// need to add it
//				updatedFeatures.remove(sf);
//			}
//			
//			needsInDatabase++;
//		}
//		
//		for (SpecialFeature sf : featuresToDelete) {
//			sql = "delete from bike_special_feature where bike_id = ? and "
//					+ "special_feature_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bk.getId());
//			pstmt.setInt(2, sf.getId());
//			int rowsAffected = pstmt.executeUpdate();
//			needsInDatabase -= rowsAffected;
//		}
//		
//		for (SpecialFeature sf : updatedFeatures) {
//			sql = "insert into bike_special_feature values (?,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bk.getId());
//			pstmt.setInt(2, sf.getId());
//			pstmt.executeUpdate();
//			needsInDatabase++;
//		}
//		
//		return (needsInDatabase == bk.getSpecialFeatures().size());
	}


}
