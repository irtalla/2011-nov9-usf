package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import com.revature.beans.Purchase;
import com.revature.utils.ConnectionUtil;

public class PurchasePostgres implements PurchaseDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	public PurchasePostgres() {}
	
	
	@Override
	public Purchase add(Purchase t) {
		
		Integer rowsAdded = 0; 
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into purchase values (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getCustomerId() );
			pstmt.setInt(2, t.getProductId() );
			rowsAdded = pstmt.executeUpdate();
			
			if (rowsAdded > 1) {
				// throw error
				conn.rollback();
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsAdded == 1 ? t : null;
	}

	@Override
	public Purchase getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Purchase> getAll() {
		
		Set<Purchase> purchases = new HashSet<Purchase>(); 
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " select "
							+ " purchase.person_id as person_id, "
							+ " purchase.product_id as product_id "
							+ " from purchase ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery(); 
			
			
			Purchase returnedPurchase = null;
			while ( rs.next() ) {
				
				try {
					returnedPurchase = deserializeOffer(rs); 
					
					purchases.add(returnedPurchase);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return purchases; 
				
	}

	private Purchase deserializeOffer(ResultSet rs) throws SQLException {
		
		Purchase returnedPurchase = new Purchase(); 
		returnedPurchase.setCustomerId( rs.getInt("person_id") );
		returnedPurchase.setProductId( rs.getInt("product_id") );
		return returnedPurchase; 
	}


	@Override
	public boolean update(Purchase t) {
		return false;
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean delete(Purchase t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Purchase> getPurchasesByCustomerId(Integer customerId) {
		
		Set<Purchase> purchases = this.getAll(); 
		purchases.removeIf( purchase -> purchase.getCustomerId() != customerId ); 
		return purchases; 
	}


}
