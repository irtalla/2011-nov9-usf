package data;

import java.sql.SQLException;
import java.util.Set;

import beans.Bikes;
import beans.Offers;
import beans.Usr;
import exceptions.NonUniqueUsernameException;

public interface UserDAO extends GenericDAO<Usr> {
	public Usr add(Usr u) throws NonUniqueUsernameException;
	public Usr getByUsername(String username);
	public Set<Bikes> getBikesByUserId(Integer Id) throws SQLException;
	public Set<Bikes> getBikesByUsername(String username);
	public void placeOffer(Offers offer) throws SQLException;
	public Set<Offers> getOffers() throws SQLException;
	public void accept(Integer id) throws SQLException;
	public Offers getOfferById(Integer id) throws SQLException;
}
