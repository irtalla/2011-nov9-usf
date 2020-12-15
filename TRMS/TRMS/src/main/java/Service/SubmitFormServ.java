package Service;

import java.util.Set;

import Entity.SubmitForm;

public interface SubmitFormServ {
	
	public SubmitForm add(SubmitForm sf);
	
	public Set<SubmitForm> getAll();
	
	public SubmitForm getById(Integer emp_id);
	
	public void update(SubmitForm sf);
	
	public Set<SubmitForm> getDS();
	
	public Set<SubmitForm> getDH();
	
	public Set<SubmitForm> getHY();
	
	public Set<SubmitForm> getPile();

}
