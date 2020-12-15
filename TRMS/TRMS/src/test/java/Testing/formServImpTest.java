package Testing;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import Entity.Status;
import Entity.SubmitForm;
import Service.SubmitFormServImp;

public class formServImpTest {
	static int x = 8;
	static SubmitFormServImp sfsi = new SubmitFormServImp();
	static SubmitForm sf = sfsi.getById(x);
	static Status stat = new Status();
	static Set<SubmitForm> form = new HashSet<SubmitForm>();
	
	@Test
	public static void getByIdTest() {	
		 assertEquals("under taker", sf.getEmp().getFullName());
	}
	
	@Test
	public static void updateTest() {
		stat.setId(5);
		sf.setStat(stat);
		sf.setGrade("A");
		sf.setDescription("yessir");
		
		sfsi.update(sf);
		
		assertEquals("yessir",sf.getDescription());
	}
	
	@Test
	public static void getAllTest() {
		form = sfsi.getAll();
		
		assertEquals(true, form.contains(sf));
	}

}
