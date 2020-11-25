package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Finance;
import com.revature.utils.ConnectionUtil;

public class FinancePostgres implements FinanceDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private BicyclePostgres bicyclePostgres = new BicyclePostgres();
	private PaymentPostgres paymentPostgres = new PaymentPostgres();
	
	@Override
	public Finance add(Finance finance) {
		Finance retFinance = null;
		
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "insert into finance values (default, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sqlString,keys);
			pstmt.setInt(1, finance.getBicycle().getId());
			pstmt.setDouble(2, finance.getPaidAmount());
			pstmt.setDouble(3, finance.getFinancedAmount());
			pstmt.setNull(4, java.sql.Types.INTEGER);
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				retFinance = finance;
				retFinance.setId(rs.getInt(1));
				conn.commit();
			}else {
				conn.rollback();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retFinance;
	}

	@Override
	public Finance getById(Integer id) {
		Finance retFinance = null;
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select * from finance where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
			
			ResultSet rs= pstmt.getResultSet();
			while(rs.next()) {
				Finance finance = new Finance();
				finance.setId(rs.getInt("id"));
				finance.setBicycle(bicyclePostgres.getById(rs.getInt("bicycle_id")));
				finance.setPaidAmount(rs.getDouble("paid_amount"));
				finance.setFinancedAmount(rs.getDouble("financed_amount"));
				finance.setLastPayment(paymentPostgres.getById(rs.getInt("last_payment")));
				retFinance = finance;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retFinance;
	}

	@Override
	public void update(Finance finance) {
		try (Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sqlString = "update finance set bicycle_id = ?,  paid_amount = ?, financed_amount = ?, last_payment = ? "
					+ "where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, finance.getBicycle().getId());
			pstmt.setDouble(2, finance.getPaidAmount());
			pstmt.setDouble(3, finance.getFinancedAmount());
			pstmt.setInt(4, finance.getLastPayment().getId());
			pstmt.setInt(5, finance.getId());
			
			int i = pstmt.executeUpdate();
			if (i > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Finance t) {
		try(Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sqlString = "delete from finance where id = ?";
			String[] keyStrings = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sqlString,keyStrings);
			pstmt.setInt(1, t.getId());
			int i = pstmt.executeUpdate();
			if (i > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		resetDefault();
	}

	@Override
	public Set<Finance> getAll() {
		Set<Finance> finances = new HashSet<Finance>();
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select * from finance";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			
			while(rs.next()) {
				Finance finance = new Finance();
				finance.setId(rs.getInt("id"));
				finance.setBicycle(bicyclePostgres.getById(rs.getInt("bicycle_id")));
				finance.setPaidAmount(rs.getDouble("paid_amount"));
				finance.setFinancedAmount(rs.getDouble("financed_amount"));
				finance.setLastPayment(paymentPostgres.getById(rs.getInt("last_payment")));
				finances.add(finance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return finances;
	}
	@Override
	public Finance getByBicycle(Bicycle b) {
		Finance retFinance = null;
		
		try (Connection conn = cu.getConnection()){
			String sqlString = "select * from finance where bicycle_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sqlString);
			pstmt.setInt(1, b.getId());
			pstmt.executeQuery();
			
			ResultSet rs= pstmt.getResultSet();
			while(rs.next()) {
				Finance finance = new Finance();
				finance.setId(rs.getInt("id"));
				finance.setBicycle(bicyclePostgres.getById(rs.getInt("bicycle_id")));
				finance.setPaidAmount(rs.getDouble("paid_amount"));
				finance.setFinancedAmount(rs.getDouble("financed_amount"));
				finance.setLastPayment(paymentPostgres.getById(rs.getInt("last_payment")));
				retFinance = finance;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retFinance;
	}
	
	public void resetDefault() {
		try (Connection conn = cu.getConnection()){
			String sqlString = "select setval('bicycleshop.finance_id_seq', max(id)) FROM finance";
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sqlString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
