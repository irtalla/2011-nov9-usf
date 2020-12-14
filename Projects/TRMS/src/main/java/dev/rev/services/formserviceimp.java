package dev.rev.services;

import java.util.List;

import dev.rev.beans.reimbForm;
import dev.rev.data.formDAO;
import dev.rev.data.formDAOFactory;

public class formserviceimp implements formservice {

	public formDAO fd;
	
	public formserviceimp() {
		formDAOFactory ff=new formDAOFactory();
		fd=ff.getformDAO();
	}
	
	
	@Override
	public Integer addform(reimbForm form) {
		// TODO Auto-generated method stub
		try {
			return fd.add(form).getFrom_id();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		return null;
		}
	}

	public void update_form(reimbForm form) {
		// TODO Auto-generated method stub
		fd.update(form);
	}


	public void delete_form(reimbForm form) {
		// TODO Auto-generated method stub
		
	}


	public List<reimbForm> getforms() {
		// TODO Auto-generated method stub
		return fd.getAll();
	}


	@Override
	public reimbForm getbyid(int id) {
		// TODO Auto-generated method stub
		return fd.getById(id);
	}


	@Override
	public List<reimbForm> getempforms(int id) {
		// TODO Auto-generated method stub
		return fd.getempforms(id);
	}
}
