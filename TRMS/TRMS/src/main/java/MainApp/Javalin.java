package MainApp;




import java.util.Set;
import java.util.HashSet;
import Entity.Employee;
import Entity.EventType;
import Entity.Status;
import Entity.SubmitForm;
import JDBC.SubmitFormDAO;
import JDBC.SubmitFormPost;

public class Javalin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee emp = new Employee();
		EventType et = new EventType();
		SubmitForm sub = new SubmitForm();
		Status stat = new Status();
		SubmitFormDAO sfp = new SubmitFormPost();
		
		Set<SubmitForm> forms = sfp.getAll();
		for(SubmitForm bob:forms) {
			System.out.println(bob);
		}
		
	}

}
