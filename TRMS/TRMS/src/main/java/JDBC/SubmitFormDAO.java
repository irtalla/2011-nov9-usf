package JDBC;
import Entity.SubmitForm;

public interface SubmitFormDAO extends Generics<SubmitForm> {
	public SubmitForm getByIds(Integer emp_id, Integer event_id);

}
