package servicespackage.story_type;

import data.Employee_Dao;
import data.Story_TypeDAO;
import data.Story_Type_DAOFactory;
import models.Story_Type;

public class Story_Type_ServiceImpl implements Story_Type_Service{
	private Story_TypeDAO storytypedao;
	
	public Story_Type_ServiceImpl() {
		Story_Type_DAOFactory storytypedaofactry = new Story_Type_DAOFactory();
	}
	public Story_Type getById(Integer id) {
		// TODO Auto-generated method stub
		return storytypedao.getById(id);
	}

	public Story_Type add(Story_Type e) {
		// TODO Auto-generated method stub
		return storytypedao.add(e);
	}

	public void update(Story_Type e) {
		// TODO Auto-generated method stub
		storytypedao.update(e);
	}

}
