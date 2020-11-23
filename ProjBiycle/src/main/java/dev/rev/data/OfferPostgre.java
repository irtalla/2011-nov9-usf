package dev.rev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

import dev.rev.model.Offer;
import dev.rev.utils.ConnectionUtil;

public class OfferPostgre implements OfferDAO {


	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public Offer add(Offer t) throws Exception {
		Offer offer=new Offer();
		System.out.println(t);
		
		try(Connection conn= cu.getConnection()){
			conn.setAutoCommit(false);
			String sql="Insert into offer values(default,?,?,?,?)";
			String[] key= {"id"};
			PreparedStatement ps=conn.prepareStatement(sql,key);
			ps.setInt(1, t.getOffer_price());
			ps.setInt(2, t.getOffer_Person_id());
			ps.setInt(3, t.getOffer_Bicycle_id());
			ps.setString(4, t.getStatus());
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if (rs.next()) {
				offer = t;
				offer.setOffer_id(rs.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
			
		}catch(Exception e) {
			
		}
		return offer;
	}

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Offer t) {
		// TODO Auto-generated method stub
		
	}

}
