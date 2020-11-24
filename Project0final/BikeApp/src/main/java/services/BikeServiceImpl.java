package services;

import java.util.Set;

import beans.Usr;
import beans.Bikes;
import beans.Offers;
import beans.Role;
import data.BikeDAO;
import data.BikeDAOFactory;
import data.UserDAOFactory;
import data.UserDAO;


public class BikeServiceImpl implements BikeService {
	private BikeDAO bikeDao;
	private UserDAO userDao;
	
	public BikeServiceImpl() {
		BikeDAOFactory bikeDaoFactory = new BikeDAOFactory();
		bikeDao = bikeDaoFactory.getBikeDAO();
		
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		userDao = userDaoFactory.getUserDAO();
	}
	@Override
	public Integer addBike(Bikes b) {
		return bikeDao.add(b).getId();
	}
	@Override
	public Bikes getBikeById(Integer id) {
		return bikeDao.getById(id);
	}
	@Override
	public Set<Bikes> getBikes() {
		return bikeDao.getAll();
	}
	@Override
	public Set<Bikes> getAvailableBikes() {
		return bikeDao.getAvailableBikes();
	}
	@Override
	public void updateBike(Bikes b) {
		bikeDao.update(b);
	}
	@Override
	public void removeBike(Bikes b) {
		bikeDao.delete(b);
	}
}
