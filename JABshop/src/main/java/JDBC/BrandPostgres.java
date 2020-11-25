package JDBC;
import java.util.HashSet;
import java.util.Set;
import java.sql.*;

import Entity.Brand;
import Utils.ConnectionUtil;

public class BrandPostgres implements BrandJDBC{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Brand add(Brand b) {
		Brand t = null;
		
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into brand values (default,?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, b.getBname());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				t=b;
				t.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return t;

	}

	@Override
	public Brand getById(Integer id) {
		Brand b = null;
		try(Connection conn = cu.getConnection()) {
			if(id != null) {
				conn.setAutoCommit(false);
				String sql = "select * from brand where brand_id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) {
					b = new Brand();
					b.setId(rs.getInt("id"));
					b.setBname(rs.getString("name"));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;

	}

	@Override
	public Set<Brand> getAll() {
		Set<Brand> brand= new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select * from brand";
			Statement stmt =  conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Brand br=new Brand();	
				br.setId(rs.getInt("id"));
				br.setBname(rs.getString("name"));
				brand.add(br);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brand;

	}

	@Override
	public void update(Brand b) {
		try (Connection conn = cu.getConnection()) {
			if(!(b.equals(null))) {
				conn.setAutoCommit(false);
				String sql = "update brand set name = ? where id = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,b.getBname());
				pstmt.setInt(2, b.getId());
				int rowsAffected = pstmt.executeUpdate();
				
				if (rowsAffected > 0) {
					conn.commit();
				} else {
					conn.rollback();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remove(Brand b) {
		try(Connection conn = cu.getConnection()) {
			if(b != null) {
				conn.setAutoCommit(false);
				String sql = "delete from brand where id = ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,b.getId());
				int rowsAffected = pstmt.executeUpdate();
				
				if (rowsAffected > 0)
					conn.commit();
				else
					conn.rollback();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
