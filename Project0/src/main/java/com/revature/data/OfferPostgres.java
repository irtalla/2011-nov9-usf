package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.utils.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	private PersonDAO pDao = new PersonPostgres();
	private BicycleDAO bDao = new BicyclePostgres();
	@Override
	public Offer add(Offer t) throws NullPointerException, NonUniqueUsernameException {
		Offer ofr = null;
		try(Connection conn = cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into offer values (default, ?, ?, ?)";
			String[] keys = {"id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1,  t.getPerson().getId());
			pstmt.setInt(2, t.getBicycle().getId());
			pstmt.setDouble(3, t.getAmount());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				//System.out.println("commiting!");
				ofr = t;
				ofr.setId(rs.getInt("id"));
				conn.commit();
			}else {
				System.out.println("rollingback!");
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ofr;
	}

	@Override
	public Offer getById(Integer id) throws NullPointerException {
		Offer ofr = null;

		try(Connection conn = cu.getConnection()){
			if(id!=null) {
				String sql = "select * from offer where id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					ofr = new Offer();
					ofr.setId(rs.getInt("id"));
					Person p = pDao.getById(rs.getInt("person_id"));
					ofr.setPerson(p);
					Bicycle b = bDao.getById(rs.getInt("bike_id"));
					ofr.setBicycle(b);
					ofr.setAmount(b.getPrice());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ofr;
	}

	@Override
	public Set<Offer> getAll() {
		Set<Offer> offers = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			String sql = "select * from offer";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("id"));
				Person p = pDao.getById(rs.getInt("person_id"));
				o.setPerson(p);
				Bicycle b = bDao.getById(rs.getInt("bike_id"));
				o.setBicycle(b);
				o.setAmount(b.getPrice());
				offers.add(o);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public void update(Offer t) {
		try(Connection conn = cu.getConnection()){
			if(!t.equals(null)) {
				conn.setAutoCommit(false);
				String sql = "update offer set person_id = ?, bike_id = ?, amount = ? where id = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, t.getPerson().getId());
				pstmt.setInt(2, t.getBicycle().getId());
				//or is it t.getBicycle().getPrice()
				pstmt.setDouble(3, t.getAmount());
				pstmt.setInt(4, t.getId());
				int rowsChanged = pstmt.executeUpdate();
				if(rowsChanged > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Offer t) {
		try(Connection conn = cu.getConnection()){
			if(t != null){
				conn.setAutoCommit(false);
				String sql = "delete from offer where id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,  t.getId());
				
				int rowsChanged = pstmt.executeUpdate();
				
				if(rowsChanged > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void accept(Integer id) {
		try(Connection conn = cu.getConnection()){
			Offer o = null;
			conn.setAutoCommit(false);
			String sql = "select * from offer where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				o = new Offer();
				o.setId(rs.getInt("id"));
				Person p = 	pDao.getById(rs.getInt("person_id"));
				o.setPerson(p);
				Bicycle b = bDao.getById(rs.getInt("bike_id"));
				o.setBicycle(b);
				o.setAmount(b.getPrice());
				
				String sql2 = "insert into person_bike values(?,?)";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, p.getId());
				pstmt2.setInt(2, b.getId());
				int rowsChanged = pstmt2.executeUpdate();
				if(rowsChanged > 0) {
					conn.commit();
					String sql3 = "delete from offer where bike_id = ?;";
					PreparedStatement pstmt3 = conn.prepareStatement(sql3);
					pstmt3.setInt(1, b.getId());
					pstmt3.executeUpdate();
					conn.commit();
				}else {
					conn.rollback();
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
}
