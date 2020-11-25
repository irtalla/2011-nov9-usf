package com.james.data;

import java.util.Set;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.james.beans.Offer;
import com.james.exceptions.NonUniqueUsernameException;
import com.james.utils.ConnectionUtil;

public class OfferPostgres implements OfferDOA {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public Offer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Offer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Offer t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update offers set offer_state = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getOfferState());			
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Offer t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Offer add(Offer t) throws NonUniqueUsernameException {
		Offer off = null;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into offers values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, t.getUserName());
			pstmt.setString(2, t.getBikeName());
			pstmt.setFloat(3, t.getOffer());
			pstmt.setString(4, "pending");			
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				off = t;
				off.setId(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (Exception e) {
			if (e.getMessage().contains("violates unique constraint")) {
				throw new NonUniqueUsernameException();
			}
			e.printStackTrace();			
		}
		
		return off;
	}

	@Override
	public void view() throws NonUniqueUsernameException {
		try (Connection conn =  cu.getConnection()) {
			String sql = "select * from offers where offer_state='owned'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("id     name");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String bikeName = rs.getString("bike_name");
				System.out.println(id + "    " + bikeName + "    ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
