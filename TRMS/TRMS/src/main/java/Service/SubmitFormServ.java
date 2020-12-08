package Service;

import java.util.Set;

import Entity.SubmitForm;

public interface SubmitFormServ {
	
	public SubmitForm add(SubmitForm sf);
	
	public Set<SubmitForm> getAll();
	
	public SubmitForm getByIds(Integer emp_id, Integer event_id);
	
	public void update(SubmitForm sf);
	

}
