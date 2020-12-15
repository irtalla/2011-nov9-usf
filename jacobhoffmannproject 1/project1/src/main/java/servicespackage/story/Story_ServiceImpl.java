package servicespackage.story;

import java.util.Set;

import data.StoryDAO;
import data.Story_DAOFactory;
import models.Story;

public class Story_ServiceImpl implements Story_Service{
	private StoryDAO storydao;
	
	public Story_ServiceImpl() {
		Story_DAOFactory storyfactory = new Story_DAOFactory();
		storydao = storyfactory.getDao();
	}
	public Story getById(Integer id) {
		return storydao.getById(id);
	}

	public Story add(Story e) {
		return storydao.add(e);
	}

	public void update(Story e) {
		storydao.update(e);
		
	}
	@Override
	public Set<Story> getByUserId(Integer id) {
		// TODO Auto-generated method stub
		return storydao.getByUserId(id);
	}

}
