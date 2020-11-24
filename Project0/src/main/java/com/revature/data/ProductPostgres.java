package com.revature.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import com.revature.beans.Product;
import com.revature.utils.ConnectionUtil;

public class ProductPostgres implements ProductDAO {
	
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private ProductFeaturePostgres prodPostgres= new ProductFeaturePostgres(); 
	private OfferPostgres offerPostgres = new OfferPostgres(); 
	
	// Constructor 
	public ProductPostgres() {}
	
	private Product deserializeProduct(ResultSet rs) throws SQLException {
		
		Product returnedProduct = new Product(); 
		
		returnedProduct.setId( rs.getInt("product_id") );
		returnedProduct.setName( rs.getString("product_name") );
		returnedProduct.setPrice( rs.getDouble("product_price") );
		returnedProduct.getStatus().setId( rs.getInt("status_id") );
		returnedProduct.getStatus().setName( rs.getString("status_name") );
		returnedProduct.getCategory().setId( rs.getInt("category_id") );
		returnedProduct.getCategory().setName( rs.getString("category_name") );		
		return returnedProduct; 	
	}
	
	
	@Override
	public Product add(Product t) {
		Product c = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " insert into product values (default, ?, ?, ?, ?) ";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getName() );
			pstmt.setDouble(2, t.getPrice() );
			pstmt.setInt(3, t.getStatus().getId() );
			pstmt.setInt(4, t.getCategory().getId() );

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
	public Product getById(Integer id) {
		
		Product returnedProduct = null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select"
					+ "	subqry.product_id as product_id, "
					+ "	subqry.product_name as product_name, "
					+ "	subqry.product_price as product_price, "
					+ "	subqry.status_id as status_id, "
					+ "	subqry.status_name as status_name, "
					+ "	category.id as category_id, "
					+ "	category.name as category_name "
					+ "	from category "
					+ "		join ( "
					+ "			select "
					+ "				product.id as product_id, "
					+ "				product.name as product_name, "
					+ "				product.price as product_price, "
					+ "				product.status_id as status_id, "
					+ "				status.name as status_name, "
					+ "				product.category_id as category_id "
					+ "				from product "
					+ "				join status "
					+ "				on product.status_id = status.id "
					+ "			) as subqry "
					+ "	on subqry.category_id = category.id "
					+ " where subqry.product_id = ? "; 
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery(); 
			
			if ( rs.next() ) {
				
				try {
					returnedProduct = deserializeProduct(rs); 
					returnedProduct.setFeatures( this.prodPostgres.getFeaturesByProductId(returnedProduct.getId()) );
					returnedProduct.setOffers( this.offerPostgres.getOffersByProductId(returnedProduct.getId()) );
				} catch (Exception e) {
					e.printStackTrace();
				}
				

			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnedProduct; 
	}

	@Override
	public Set<Product> getAll() {
		
		Set<Product> products = new HashSet<Product>(); 
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select"
					+ "	subqry.product_id as product_id, "
					+ "	subqry.product_name as product_name, "
					+ "	subqry.product_price as product_price, "
					+ "	subqry.status_id as status_id, "
					+ "	subqry.status_name as status_name, "
					+ "	category.id as category_id, "
					+ "	category.name as category_name "
					+ "	from category "
					+ "		join ( "
					+ "			select "
					+ "				product.id as product_id, "
					+ "				product.name as product_name, "
					+ "				product.price as product_price, "
					+ "				product.status_id as status_id, "
					+ "				status.name as status_name, "
					+ "				product.category_id as category_id "
					+ "				from product "
					+ "				join status "
					+ "				on product.status_id = status.id "
					+ "			) as subqry "
					+ "	on subqry.category_id = category.id "; 
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery(); 
			
			
			Product returnedProduct = null;
			while ( rs.next() ) {
				
				try {
					returnedProduct = deserializeProduct(rs); 
					returnedProduct.setFeatures( this.prodPostgres.getFeaturesByProductId(returnedProduct.getId()) );
					returnedProduct.setOffers( this.offerPostgres.getOffersByProductId(returnedProduct.getId()) );
					products.add(returnedProduct);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return products; 
		
	}
		
	
	@Override
	public Set<Product> getAvailableProducts() {
		
		Set<Product> availableProducts = new HashSet<Product>();
		availableProducts = this.getAll(); 
		availableProducts.removeIf( 
				product -> product.getStatus().getName().equalsIgnoreCase("unavailable")
				);
		return availableProducts;
	}
	/**
	 * Current implementation only supports updating status
	 */
	@Override
	public boolean update(Product t) {
		
		Integer rowsUpdated = 0; 
		try (Connection conn = cu.getConnection()) { 
			conn.setAutoCommit(false);
			String sql = " update product "
							+ " set status_id = ?"
							+ " where id = ? ";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getStatus().getId() );
			pstmt.setInt(2, t.getId() );

			rowsUpdated = pstmt.executeUpdate();
			
			
			if (rowsUpdated > 1) {
				// TODO throw error
				conn.rollback();
			}
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
		return rowsUpdated > 0 ? true : false;
	}

	@Override
	public boolean delete(Product t) {
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = " call delete_product(?) ";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, t.getId() );
			cstmt.executeUpdate();
			conn.commit();
			
		} catch (Exception e) {
			System.out.println(e); 
			return false; 
		}
		return true; 
	}	
}
