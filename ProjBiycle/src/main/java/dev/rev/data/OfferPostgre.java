package dev.rev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.model.Offer;
import dev.rev.model.Person;
import dev.rev.model.Role;
import dev.rev.utils.ConnectionUtil;

public class OfferPostgre implements OfferDAO {


	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public Offer add(Offer t) throws Exception {
		Offer ps = null;
		try(Connection conn=cu.getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into offer values (default, ?, ?, ?,?)";
			String[] keys = {"offer_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setInt(1, t.getOffer_price());
			pstmt.setInt(2, t.getOffer_Person_id());
			pstmt.setInt(3, t.getOffer_Bicycle_id());
			pstmt.setString(4, t.getStatus());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rs.next()) {
				ps = t;
				ps.setOffer_id(rs.getInt(1));
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
		
		return ps;
	}

	
	

	@Override
	public Set<Offer> getAll() {
Set<Offer> people = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql="Select * from offer";
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery(sql);
			
			while (rs.next()) {
				Offer human = new Offer();
				human.setOffer_id(rs.getInt("Offer_id"));
				human.setOffer_Bicycle_id(rs.getInt("bicycle_id"));
				human.setOffer_Person_id(rs.getInt("Person_id"));
				human.setOffer_price(rs.getInt("Offer_price"));
				human.setStatus(rs.getString("Status"));
				
				
				people.add(human);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return people;

	}

	@Override
	public void update(Offer t) {
		
		
	}

	@Override
	public void delete(Offer t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String accept_reject_offer(int id) {
		// TODO Auto-generated method stub
		String acc="Accepted";
		try(Connection conn=cu.getConnection()){
			conn.setAutoCommit(false);
			String sql="Update offer set status = ? where offer_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, acc);
			ps.setInt(2, id);
			int rows=ps.executeUpdate();
			
			ResultSet rs=ps.getGeneratedKeys();
			if(rows>0) {
				conn.commit();
				return acc;
			}else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}




	@Override
	public Offer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Set<Offer> getpersonOffer(int id) {
Set<Offer> people = new HashSet<>();
		
		try (Connection conn = cu.getConnection()) {
			String sql="Select * from offer where offer_id =?";
			PreparedStatement state = conn.prepareStatement(sql);
			state.setInt(1, id);
			ResultSet rs =state.executeQuery();			
			
			while (rs.next()) {
				Offer human = new Offer();
				human.setOffer_id(rs.getInt("Offer_id"));
				human.setOffer_Bicycle_id(rs.getInt("bicycle_id"));
				human.setOffer_Person_id(rs.getInt("Person_id"));
				human.setOffer_price(rs.getInt("Offer_price"));
				human.setStatus(rs.getString("Status"));
				
				
				people.add(human);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return people;
	}




	@Override
	public void rejectothers(int id) {
		// TODO Auto-generated method stub
		String acc="Rejected";
		System.out.println(id+"bike id");
		try(Connection conn=cu.getConnection()){
			conn.setAutoCommit(false);
			String sql="Update offer set status = ? where bicycle_id=? ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, acc);
			ps.setInt(2, id);
		
			int rows=ps.executeUpdate();
			
			ResultSet rs=ps.getGeneratedKeys();
			if(rows>0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}




	@Override
	public int bike_id_byofferid(int id) {
		int bike_id = 0;
		System.out.println(id+" method id");
		try(Connection conn=cu.getConnection()){
		String sql="select bicycle_id from offer where offer_id=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			bike_id=rs.getInt("bicycle_id");
			System.out.println(bike_id+"Bicycle id issss");
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bike_id;
	}




	@Override
	public void rejectOffer(int id) {
		// TODO Auto-generated method stub
		String acc="Rejected";
		try(Connection conn=cu.getConnection()){
			conn.setAutoCommit(false);
			String sql="Update offer set status = ? where offer_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, acc);
			ps.setInt(2, id);
			int rows=ps.executeUpdate();
			
			ResultSet rs=ps.getGeneratedKeys();
			if(rows>0) {
				conn.commit();
				System.out.println("Offer Rejected");
				
			}else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}




	@Override
	public int getpersonId(int id,int ofer_id) {
		// TODO Auto-generated method stub
		int pid=0;
		try(Connection conn=cu.getConnection()){
			conn.setAutoCommit(false);
			String sql="Select person_id from offer where offer_id=?";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setInt(1, id);
			//ps.setInt(2, ofer_id);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				pid=rs.getInt(1);
				System.out.println(pid+"person id is ");
				conn.commit();
				return pid;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
