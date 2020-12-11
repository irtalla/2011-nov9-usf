package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Title;
import com.revature.data.EvtReqDAO;
import com.revature.data.PersonDAOFactory;
import com.revature.data.TitleDAO;
import com.revature.data.TitleDAOFactory;

public class TitleServiceImpl implements TitleService {

	private TitleDAO titleDao;

	public TitleServiceImpl() {
		TitleDAOFactory title = new TitleDAOFactory();
		titleDao = title.getTitleDAO();
	}

	@Override
	public Title addTitle(Title t) {
		return titleDao.add(t);
	}

	@Override
	public Title getTitleById(Integer id) {
		return titleDao.getById(id);
	}

	@Override
	public Set<Title> getTitlesByPersonId(Integer person_id){
		return titleDao.getTitlesByPersonId(person_id);
	}
	
	@Override
	public Set<Title> getAllTitles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTitle(Title t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTitle(Title t) {
		// TODO Auto-generated method stub

	}
}
