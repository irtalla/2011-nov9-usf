package services;


import java.sql.SQLException;
import java.util.Set;

import beans.Usr;
import beans.Bikes;
import beans.Offers;
import data.UserDAO;
import data.UserDAOFactory;
import exceptions.NonUniqueUsernameException;


public class UserServiceImpl implements UserService{
	private UserDAO userDao;

	
	public UserServiceImpl() {
		UserDAOFactory userDaoFactory = new UserDAOFactory();
		userDao = userDaoFactory.getUserDAO();

		
	}
	@Override
	public Integer addUser(Usr u) throws NonUniqueUsernameException {
		return userDao.add(u).getId();
	}
	@Override
	public Usr getUserById(Integer id) {
		return userDao.getById(id);
	}
	@Override
	public Usr getUserByUsername(String username) {
		return userDao.getByUsername(username);
	}
	@Override
	public void updateUser(Usr u) {
		userDao.update(u);
	}
	@Override
	public void deleteUser(Usr u) {
		userDao.delete(u);
	}
	@Override
	public Set<Bikes> getBikesByUsername(String username){
		return userDao.getBikesByUsername(username);
}
	@Override
	public String getRoleByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void placeOffer(Offers offer) throws SQLException {
		userDao.placeOffer(offer);
	}
	@Override
	public Set<Offers> getOffers() throws SQLException {
		return userDao.getOffers();
	}
	@Override
	public Set<Bikes> getBikesByUserId(Integer id) throws SQLException {
		return userDao.getBikesByUserId(id);
	}
	@Override
	public void acceptOffer(Integer id) throws SQLException{
		userDao.accept(id);
	}
	@Override
	public Offers getOfferById(Integer inputId) throws SQLException {
		return userDao.getOfferById(inputId);
	}
	@Override
	public void setMoney(double money, Usr u) {
		userDao.setMoney(money, u);
		
	}
	@Override
	public double getMoney(Usr u) {
		return userDao.getMoney(u);
	}
}