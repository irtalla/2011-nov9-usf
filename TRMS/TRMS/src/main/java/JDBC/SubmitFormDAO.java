package JDBC;
import java.util.Set;

import Entity.SubmitForm;

public interface SubmitFormDAO extends Generics<SubmitForm> {
	public SubmitForm getById(Integer emp_id);
	public Set<SubmitForm> getDS();
	public Set<SubmitForm> getDH();
	public Set<SubmitForm> getHY();
	public Set<SubmitForm> getPile();
	

}
