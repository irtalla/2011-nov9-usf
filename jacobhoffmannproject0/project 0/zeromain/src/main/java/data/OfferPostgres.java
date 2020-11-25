package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import models.Bike;
import models.Offer;
import models.User;
import services.BikeDaoFactory;
import services.UserDaoFactory;
import utils.ConnectionUtil;

public class OfferPostgres implements OfferDao{
	private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
	@Override
	public Offer add(Offer t){
		Offer o = new Offer();
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "insert into offer values (default, ?, ?, ?, ?)";
			String[] keys = {"offer_id"};
			PreparedStatement pstmt = conn.prepareStatement(sql,keys);
			pstmt.setFloat(1, t.getAmount());
			pstmt.setString(2, t.getStatus());
			pstmt.setInt(3, t.getCustomerId().getUserId());
			pstmt.setInt(4, t.getBikeId().getId());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				System.out.println("here");
				t = o;
				t.setOfferId(rs.getInt(1));
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
	public Offer getById(Integer id) {
		Offer o = null;
		UserDaoFactory UserDaoFactory = new UserDaoFactory();
		UserDao userDao;
		userDao = UserDaoFactory.getUserDAO();
		
		BikeDaoFactory bikeDaoFactory = new BikeDaoFactory();
		BikeDao bikeDao;
		bikeDao = bikeDaoFactory.getBikeDAO();

		try (Connection conn = cu.getConnection())
		{
			String sql = "select offer_id, amount, offer_status,customer_id,bike_id"
					+ " from offer "
					+ " where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				o = new Offer();
				o.setOfferId(rs.getInt("offer_id"));
				o.setAmount(rs.getFloat("amount"));
				o.setStatus(rs.getString("offer_status"));
				int i = rs.getInt("customer_id");
				int z = rs.getInt("bike_id");
				o.setCustomerId(userDao.getById(i));
				o.setBikeId(bikeDao.getById(z));
			
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return o;	
	}

	@Override
	public Set<Offer> getAll() {
		Set<Offer> offers = new HashSet();
		Offer o = null;
		UserDaoFactory UserDaoFactory = new UserDaoFactory();
		UserDao userDao;
		userDao = UserDaoFactory.getUserDAO();
		
		BikeDaoFactory bikeDaoFactory = new BikeDaoFactory();
		BikeDao bikeDao;
		bikeDao = bikeDaoFactory.getBikeDAO();

		try (Connection conn = cu.getConnection())
		{
			String sql = "select offer_id, amount, offer_status,customer_id,bike_id"
					+ " from offer ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				o = new Offer();
				o.setOfferId(rs.getInt("offer_id"));
				o.setAmount(rs.getFloat("amount"));
				o.setStatus(rs.getString("offer_status"));
				int i = rs.getInt("customer_id");
				int z = rs.getInt("bike_id");
				o.setCustomerId(userDao.getById(i));
				o.setBikeId(bikeDao.getById(z));
			offers.add(o);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public void update(Offer t) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update offer set amount = ?, offer_status = ?,  customer_id = ?, bike_id = ? where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, t.getAmount());
			pstmt.setString(2, t.getStatus());
			pstmt.setInt(3, t.getCustomerId().getUserId());
			pstmt.setInt(4, t.getBikeId().getId());
			pstmt.setInt(5, t.getOfferId());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Offer t) {
		// TODO Auto-generated method stub
		try (Connection conn = cu.getConnection()) {
			
			// then, we can delete the cat
			String sql = "delete from offer where offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, t.getOfferId());
			pstmt.executeUpdate();
				}catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Set<Offer> getByUserId(Integer Id) {
		Set<Offer> offers = new HashSet();

		Offer o = null;
		UserDaoFactory UserDaoFactory = new UserDaoFactory();
		UserDao userDao;
		userDao = UserDaoFactory.getUserDAO();
		
		BikeDaoFactory bikeDaoFactory = new BikeDaoFactory();
		BikeDao bikeDao;
		bikeDao = bikeDaoFactory.getBikeDAO();

		try (Connection conn = cu.getConnection())
		{
			String sql = "select offer_id, amount, offer_status,customer_id,bike_id"
					+ " from offer "
					+ " where customer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				o = new Offer();
				o.setOfferId(rs.getInt("offer_id"));
				o.setAmount(rs.getFloat("amount"));
				o.setStatus(rs.getString("offer_status"));
				int i = rs.getInt("customer_id");
				int z = rs.getInt("bike_id");
				o.setCustomerId(userDao.getById(i));
				o.setBikeId(bikeDao.getById(z));
			offers.add(o);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public void MassUpdate(Integer bikeId, Integer OfferId) {
		try (Connection conn = cu.getConnection()) {
			conn.setAutoCommit(false);
			String sql = "update offer set offer_status = ? where bike_id = ? and not offer_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Rejected");
			pstmt.setInt(2, bikeId);
			pstmt.setInt(3, OfferId);
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



}
