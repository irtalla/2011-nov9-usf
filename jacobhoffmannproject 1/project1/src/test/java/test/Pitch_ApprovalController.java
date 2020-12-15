package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import data.Pitch_ApprovalDAO;
import data.Pitch_ApprovalHibernate;
import models.Pitch_Approval;
public class Pitch_ApprovalController {
@Mock
static Pitch_ApprovalDAO padao;
static Pitch_ApprovalController c;
static Pitch_ApprovalHibernate h;
Pitch_Approval pa = h.getById(1);



@Test
public void testUpdate() {
	
}
}
