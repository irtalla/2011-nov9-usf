package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import models.Offer;
import models.Payment;
import services.BikeDaoFactory;
import services.UserDaoFactory;
import utils.ConnectionUtil;

public class PaymentPostgress implements PaymentDAO{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	@Override
	public Payment add(Payment t) throws Exception {
		Payment o = new Payment();
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into payment values (default, ?, ?, ?, ?, ?, ?)";
			String[] keys = {"payment_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql,keys);
			pstmt.setFloat(1, t.getAmount());
			pstmt.setInt(2, t.getWeektotal());
			pstmt.setInt(3, t.getWeeksdone());
			pstmt.setInt(4, t.getCustomer().getUserId());
			pstmt.setBoolean(5, t.isComplete());
			pstmt.setInt(6, t.getBike().getId());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				t = o;
				t.setPaymentId(rs.getInt(1));
			conn.commit();
			} else {
				
				//conn.rollback();
				System.out.println("this is rollback");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public Payment getById(Integer id) {
		Payment o = null;
		UserDaoFactory UserDaoFactory = new UserDaoFactory();
		UserDao userDao;
		userDao = UserDaoFactory.getUserDAO();
		
		BikeDaoFactory bikeDaoFactory = new BikeDaoFactory();
		BikeDao bikeDao;
		bikeDao = bikeDaoFactory.getBikeDAO();

		try (Connection conn = cu.getConnection())
		{
			String sql = "select payment_id, amount, weektotal, weeksdone, customer, complete, bike"
					+ " from payment "
					+ " where payment_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				o = new Payment();
				o.setPaymentId(rs.getInt("payment_id"));
				o.setAmount(rs.getFloat("amount"));

				o.setWeektotal(rs.getInt("weektotal"));
				o.setWeeksdone(rs.getInt("weeksdone"));
				int i = rs.getInt("customer");
				o.setComplete(rs.getBoolean("complete"));
				int z = rs.getInt("bike");
				o.setCustomer(userDao.getById(i));
				o.setBike(bikeDao.getById(z));
			
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return o;	
	}

	@Override
	public Set<Payment> getAll() {
		Set<Payment> pays = new HashSet();
		Payment p = null;
		UserDaoFactory UserDaoFactory = new UserDaoFactory();
		UserDao userDao;
		userDao = UserDaoFactory.getUserDAO();
		
		BikeDaoFactory bikeDaoFactory = new BikeDaoFactory();
		BikeDao bikeDao;
		bikeDao = bikeDaoFactory.getBikeDAO();

		try (Connection conn = cu.getConnection())
		{
			String sql = "select payment_id, amount, weektotal, weeksdone, customer, complete, bike"
					+ " from payment";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				p = new Payment();
				p.setPaymentId(rs.getInt("payment_id"));
				p.setWeektotal(rs.getInt("weektotal"));
				p.setWeeksdone(rs.getInt("weeksdone"));
				int i = rs.getInt("customer");
				p.setComplete(rs.getBoolean("complete"));
				int z = rs.getInt("bike");
				p.setCustomer(userDao.getById(i));
				p.setBike(bikeDao.getById(z));
			pays.add(p);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return pays;
	}

	@Override
	public void update(Payment t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update payment set amount = ?, weektotal = ?, weeksdone = ?, customer = ?, complete = ?, bike = ?";
			String[] keys = {"payment_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql,keys);
			pstmt.setFloat(1, t.getAmount());
			pstmt.setInt(2, t.getWeektotal());
			pstmt.setInt(3, t.getWeeksdone());
			pstmt.setInt(4, t.getCustomer().getUserId());
			pstmt.setBoolean(5, t.isComplete());
			pstmt.setInt(6, t.getBike().getId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Payment t) {
		try (Connection conn = cu.getConnection()) {
			
			// then, we can delete the cat
			String sql = "delete from payment where payment_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getPaymentId());
			pstmt.executeUpdate();
				}catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public Set<Payment> getAllByUserId(Integer i) {
	
			Set<Payment> pays = new HashSet();

			Payment p = null;
			UserDaoFactory UserDaoFactory = new UserDaoFactory();
			UserDao userDao;
			userDao = UserDaoFactory.getUserDAO();
			
			BikeDaoFactory bikeDaoFactory = new BikeDaoFactory();
			BikeDao bikeDao;
			bikeDao = bikeDaoFactory.getBikeDAO();

			try (Connection conn = cu.getConnection())
			{
				String sql = "select payment_id, amount, weektotal, weeksdone, customer, complete, bike"
						+ " from payment "
						+ " where customer = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,i);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
				{
					p = new Payment();
					p.setPaymentId(rs.getInt("payment_id"));
					p.setAmount(rs.getFloat("amount"));
					p.setWeektotal(rs.getInt("weektotal"));
					p.setWeeksdone(rs.getInt("weeksdone"));
					int ig = rs.getInt("customer");
					p.setComplete(rs.getBoolean("complete"));
					int z = rs.getInt("bike");
					p.setCustomer(userDao.getById(ig));
					p.setBike(bikeDao.getById(z));
				pays.add(p);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return pays;
	}

}
