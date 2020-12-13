package dev.rev.services;

import java.util.List;

import dev.rev.beans.reimbForm;

public interface formservice {

	public Integer addform(reimbForm form);
	public void update_form(reimbForm form);
	public void delete_form(reimbForm form);
	public List<reimbForm> getforms();
	public reimbForm getbyid(int id);
	public List<reimbForm> getempforms(int id);
	
	
}
