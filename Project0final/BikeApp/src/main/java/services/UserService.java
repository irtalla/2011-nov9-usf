package services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import beans.Bikes;
import beans.Offers;
import beans.Usr;
import exceptions.NonUniqueUsernameException;
import utilities.Print;

public interface UserService {
	public double getMoney(Usr u);
	public void setMoney(double money, Usr u);
	public Integer addUser(Usr u) throws NonUniqueUsernameException;
	public Usr getUserById(Integer id);
	public Usr getUserByUsername(String username);
	public String getRoleByUsername(String username);
	public void updateUser(Usr u);
	public void deleteUser(Usr u);
	public Set<Bikes> getBikesByUsername(String username);
	public void placeOffer(Offers offer) throws SQLException; 
	public Set<Bikes> getBikesByUserId(Integer id) throws SQLException;
	public Set<Offers> getOffers() throws SQLException;
	public Offers getOfferById(Integer inputId) throws SQLException;
	public void acceptOffer(Integer id) throws SQLException;

}
