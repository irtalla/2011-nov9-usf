package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Feature;
import com.revature.beans.Offer;
import com.revature.beans.Product;
import com.revature.utils.ConnectionUtil;

/**
 * 
 * ProductFeacturePostgres operates on the product_feature join table. 
 * Most of the CRUD operations for this DAO are unimplemented. 
 */

public class ProductFeaturePostgres implements ProductFeatureDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public ProductFeaturePostgres() {}
	
	@Override
	public Feature add(Feature t) {
		
		Feature c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into feature values (default, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getId());
			pstmt.setString(2, t.getName() );
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if ( rs.next() ) {
				c = t;
				c.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Feature getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Feature> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Feature t) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Feature t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * This is the method we need 
	 */	
	@Override
	public Set<Feature> getFeaturesByProductId(Integer id) {
		
		Set<Feature> features = new HashSet<Feature>(); 
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " select "
						+ "	product_feature.feature_id as feature_id, "
						+ "	feature.name as feature_name "
						+ "	from product_feature "
						+ "		join feature "
						+ "		on product_feature.feature_id = feature.id "
						+ "	where product_feature.product_id = ? ";
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery(); 
			
			
			if ( rs.next() ) {
				
				try {
					features.add( deserializeFeature(rs) ); 
				} catch (Exception e) {
					e.printStackTrace();
				}
				

			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return features; 
	}

	private Feature deserializeFeature(ResultSet rs) throws SQLException {
		
		Feature returnedFeature = new Feature(); 
		
		returnedFeature.setId( rs.getInt("feature_id") );
		returnedFeature.setName( rs.getString("feature_name") ); 
		return returnedFeature; 
		
		
	}

}
