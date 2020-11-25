package dev.rev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

import dev.rev.model.Payment;
import dev.rev.utils.ConnectionUtil;

public class PaymentPostgre implements PaymentDAO {

	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Payment add(Payment t) throws Exception {
		// TODO Auto-generated method stub
	Payment b=null;
		try (Connection conn= cu.getConnection()){
			conn.setAutoCommit(false);
				String sql="insert into payment values(default,?,?,?,?)";
		String[] keys= {"payment_id"};
		PreparedStatement ps=conn.prepareStatement(sql,keys);
		ps.setInt(1, t.getBicycle_id());
		ps.setInt(2, t.getPerson_id());
		ps.setInt(3, t.getActual_payment());
		ps.setInt(4, t.getRemaining_payment());
		
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			System.out.println("Payment done");
			b=t;
			b.setPayment_id(rs.getInt(1));
			conn.commit();
		}else {
			conn.rollback();
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return b;
		
	}	
		
	

	@Override
	public Payment getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Payment> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Payment t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Payment t) {
		// TODO Auto-generated method stub
		
	}




}
