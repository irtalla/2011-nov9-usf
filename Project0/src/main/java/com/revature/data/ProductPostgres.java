package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;



import com.revature.beans.Product;
import com.revature.utils.ConnectionUtil;

public class ProductPostgres implements ProductDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	
	// Constructor 
	public ProductPostgres() {}
	
	@Override
	public Product add(Product t) {
		Product c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into cat values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName());
			pstmt.setDouble(2, t.getPrice());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
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
	public Product getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Product> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Set<Product> getAvailableProducts() {
		return null;
	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub

	}

}
