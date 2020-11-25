package spencer.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import spencer.revature.beans.Bicycle;
import spencer.revature.beans.BicycleStatus;
import spencer.revature.beans.Customer;
import spencer.revature.beans.Employee;
import spencer.revature.beans.Offer;
import spencer.revature.beans.OfferStatus;
import spencer.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {


	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Offer add(Offer t) {
		Offer off=null;
		
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into offer values (default, ?, ?, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setDouble(1, t.getAmount());
			pstmt.setInt(2, t.getOfferStatus().getId());
			pstmt.setInt(3, t.getEmployee().getId());
			pstmt.setInt(4, t.getCustomer().getId());
			pstmt.setInt(5, t.getBicycle().getId());
			
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
			e.printStackTrace();
		}
		
		return off;
	}

	@Override
	public Offer getById(Integer id) {
		Offer off= null;

		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "select offer.id, amount, offerstat_id, status, employee_id, "+
		"employee.username as euser, customer_id, customer.username as cuser, bicycle_id, model from offer_status"+
		" join (bicycle join (customer join (offer join employee on employee_id=employee.id)"+
		" on customer_id=customer.id) on bicycle_id=bicycle.id) on offerstat_id=offer_status.id where offer.id = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				off = new Offer();
				off.setId(rs.getInt("id"));
				off.setAmount(rs.getDouble("amount"));
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("bicycle_id"));
				b.setModel(rs.getString("model"));
				off.setBicycle(b);
				Customer c = new Customer();
				c.setId(rs.getInt("customer_id"));
				c.setUsername(rs.getString("cuser"));
				off.setCustomer(c);
				Employee e = new Employee();
				e.setId(rs.getInt("employee_id"));
				e.setUsername(rs.getString("euser"));
				off.setEmployee(e);
				OfferStatus os = new OfferStatus();
				os.setId(rs.getInt("offerstat_id"));
				os.setStatus(rs.getString("status"));
				off.setOfferStatus(os);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return off;
	}

	@Override
	public Set<Offer> getAll() {
		Set<Offer> offs = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql = "select offer.id, amount, offerstat_id, status, employee_id, "+
		"employee.username as euser, customer_id, customer.username as cuser, bicycle_id, model from offer_status"+
		" join (bicycle join (customer join (offer join employee on employee_id=employee.id)"+
		" on customer_id=customer.id) on bicycle_id=bicycle.id) on offerstat_id=offer_status.id;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Offer off = new Offer();
				off.setId(rs.getInt("id"));
				off.setAmount(rs.getDouble("amount"));
				Bicycle b = new Bicycle();
				b.setId(rs.getInt("bicycle_id"));
				b.setModel(rs.getString("model"));
				off.setBicycle(b);
				Customer c = new Customer();
				c.setId(rs.getInt("customer_id"));
				c.setUsername(rs.getString("cuser"));
				off.setCustomer(c);
				Employee e = new Employee();
				e.setId(rs.getInt("employee_id"));
				e.setUsername(rs.getString("euser"));
				off.setEmployee(e);
				OfferStatus os = new OfferStatus();
				os.setId(rs.getInt("offerstat_id"));
				os.setStatus(rs.getString("status"));
				off.setOfferStatus(os);
				offs.add(off);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return offs;
	}

	@Override
	public void update(Offer t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update offer set amount = ?, offerstat_id = ?, employee_id = ?,"+
			" customer_id = ?, bicycle_id = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, t.getAmount());
			pstmt.setInt(2, t.getOfferStatus().getId());
			pstmt.setInt(3, t.getEmployee().getId());
			pstmt.setInt(4, t.getCustomer().getId());
			pstmt.setInt(5, t.getBicycle().getId());
			pstmt.setInt(6, t.getId());
			
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
			{		
					conn.commit();
			}
				else {
					conn.rollback();
			} 
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Offer t) {
		try (Connection conn =  cu.getConnection())
		{
			conn.setAutoCommit(false);
			String sql = "delete from offer where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getId());
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0)
				conn.commit();
			else
				conn.rollback();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void rejectAll(Bicycle b) {
		Set<Offer> offs = getAll();
		OfferStatus os = new OfferStatus();
		os.setId(3);
		for(Offer off: offs) {
			if(off.getBicycle().getId()==b.getId() && off.getOfferStatus().getId()!=1) {
				off.setOfferStatus(os);
				update(off);
			}
		}
	}

	public double remainder(Integer id) {
		double offers = 0;
		double payments=0;
		try (Connection conn =  cu.getConnection())
		{
			conn.setAutoCommit(false);
			String sql = "select sum(payamount) from payment join offer on offer_id=offer.id where customer_id=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				payments = (rs.getDouble("sum"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn =  cu.getConnection())
		{
			conn.setAutoCommit(false);
			String sql = "select sum(amount) from offer where customer_id=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				offers = (rs.getDouble("sum"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (offers-payments)/(double)52;
	}

	public double getPayDue(Integer id) {
		double offer = 0;
		double payments=0;
		try (Connection conn =  cu.getConnection())
		{
			conn.setAutoCommit(false);
			String sql = "select sum(payamount) from payment join offer on offer_id=offer.id where bicycle_id=?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				payments = (rs.getDouble("sum"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (Connection conn =  cu.getConnection())
		{
			conn.setAutoCommit(false);
			String sql = "select amount from offer where bicycle_id=? and offerstat_id=1;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				offer = (rs.getDouble("amount"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (offer-payments);
	}


}
