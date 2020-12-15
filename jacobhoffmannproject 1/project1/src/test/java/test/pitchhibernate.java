package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;

import data.Pitch_ApprovalDAO;
import data.Pitch_ApprovalHibernate;
import models.Pitch_Approval;

public class pitchhibernate {
	@Mock
	static Pitch_ApprovalDAO padao;
	static Pitch_ApprovalController c;
	@Mock
	static Pitch_ApprovalHibernate h;



	@Test
	public void testUpdate() {
		Pitch_Approval pa = h.getById(1);
			pa.setApproved(true);
			h.update(pa);
			verify(h).update(pa);
			
	}




}
