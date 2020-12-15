package services;

import data.UsrDAO;
import data.UsrDAOFactory;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import beans.Committee;
import beans.Genre;
import beans.Role;
import beans.Story_type;
import beans.Usr;
import exceptions.NonUniqueUsernameException;

public class UsrServiceImpl implements UsrService {
	private UsrDAO usrDao;

	
	public UsrServiceImpl() {
		UsrDAOFactory usrDaoFactory = new UsrDAOFactory();
		usrDao = usrDaoFactory.getUsrDAO();
	}
	public Usr getUsrById(Integer id) {
		return usrDao.getById(id);
	}
	@Override
	public Usr getUsrbyUsrname(String username) {
		return usrDao.getByUsrname(username);
	}
	@Override
	public Integer addUsr(Usr u) throws NonUniqueUsernameException{
		return usrDao.add(u).getUsr_id();
	}
	@Override
	public void updateUsr(Usr u) {
		usrDao.update(u);
		
	}
	@Override
	public void deleteUsr(Usr u) {
		usrDao.delete(u);
		
	}
	@Override
	public Set<Usr> getUsrsByCommittee(Committee c) {
		Set<Usr> s = usrDao.getUsrsByCommittee(c);
		return s;
	}
	@Override
	public Set<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Genre> getGenres() {
		return usrDao.getGenres();
	}
	@Override
	public Set<Story_type> getTypes() {
		return usrDao.getTypes();
	}
}
