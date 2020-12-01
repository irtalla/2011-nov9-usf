package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import com.revature.beans.Attachment;
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
	public Attachment add(Attachment t) {
		
		Attachment c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " insert into feature values (default, ?) ";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName() );
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
	public Attachment getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Attachment> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Attachment t) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Attachment t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * This is the method we need 
	 */	
	@Override
	public Set<Attachment> getFeaturesByProductId(Integer productId) {
		
		Set<Attachment> features = new HashSet<Attachment>(); 
		
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
			pstmt.setInt(1, productId);

			ResultSet rs = pstmt.executeQuery(); 
			
			
			if ( rs.next() ) {
				features.add( deserializeFeature(rs) ); 
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return features; 
	}

	private Attachment deserializeFeature(ResultSet rs) throws SQLException {
		
		Attachment returnedFeature = new Attachment(); 
		
		returnedFeature.setId( rs.getInt("feature_id") );
		returnedFeature.setName( rs.getString("feature_name") ); 
		return returnedFeature; 
		
		
	}

}
