package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Payment;
import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.PersonService;
import com.revature.utils.ConnectionUtil;

public class PaymentPostgres implements PaymentDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Payment add(Payment t) throws NullPointerException, NonUniqueUsernameException {
		Payment pay = null;
		
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into payment values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getP().getId());
			pstmt.setDouble(2, t.getAmount());
			pstmt.setInt(3, t.getB().getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				pay = t;
				pay.setId(rs.getInt("id"));
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return pay;
	}

	@Override
	public Payment getById(Integer id) throws NullPointerException {
		Payment pay = null;
		PersonDAO pDao = new PersonPostgres();
		BicycleDAO bDao = new BicyclePostgres();
		try(Connection conn = cu.getConnection()){
			String sql = "select * from payment where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				pay = new Payment();
				pay.setId(rs.getInt("id"));
				pay.setP(pDao.getById(rs.getInt("person_id")));
				pay.setAmount(rs.getDouble("amount"));
				pay.setB(bDao.getById(rs.getInt("bike_id")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return pay;
	}
	public Set<Payment> getPaymentsByUserId(Integer id){
		Set<Payment> userPayments = new HashSet<>();
		PersonDAO pDao = new PersonPostgres();
		BicycleDAO bDao = new BicyclePostgres();
		
		try(Connection conn = cu.getConnection()){
			String sql = "select * from payment where person_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Payment pay = getById(rs.getInt("id"));
				userPayments.add(pay);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return userPayments;
	}
	
	@Override
	public Set<Payment> getAll() {
		PersonDAO pDao = new PersonPostgres();
		BicycleDAO bDao = new BicyclePostgres();

		Set<Payment> payments = new HashSet<>();
		try(Connection conn = cu.getConnection()){
			
			String sql = "select * from payment";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Payment pay = new Payment();
				pay.setId(rs.getInt("id"));
				pay.setP(pDao.getById(rs.getInt("person_id")));
				pay.setAmount(rs.getDouble("amount"));
				pay.setB(bDao.getById(rs.getInt("bike_id")));
				payments.add(pay);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public void update(Payment t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "update payment set person_id = ?, amount = ?, bike_id = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,  t.getP().getId());
			pstmt.setDouble(2, t.getAmount());
			pstmt.setInt(3, t.getB().getId());
			pstmt.setInt(4, t.getId());
			
			
			int rowsChanged = pstmt.executeUpdate();
			
			if(rowsChanged > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Payment t) {
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "delete from payment where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  t.getId());
			
			int rowsChanged = pstmt.executeUpdate();
			if(rowsChanged>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
