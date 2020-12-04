package dev.elliman.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;
import dev.elliman.beans.Stage;

@TestMethodOrder(OrderAnnotation.class)
public class ClaimDAOTest {
	
	private static ClaimDAO claimDAO;
	
	private static Claim testClaim;
	public static Person testPerson;

	@BeforeAll
	public static void beforeAll() {
		claimDAO = ClaimDAOFactory.getClaimDAO();
		
		testClaim = new Claim();
		testClaim.setId(null);
		testClaim.setPersonID(1);
		testClaim.setEventID(1);
		testClaim.setGradingID(1);
		
//		Date d = new Date(119, 12, 14);
//		testClaim.setEventDate(d);
//		Time t = new Time(2,0,0);
//		testClaim.setEventTime(t);
		
		testClaim.setEventDate(LocalDateTime.of(2020, 12, 14, 2, 0));
		
		testClaim.setEventLocation("test location");
		testClaim.setDescription("test description");
		testClaim.setPrice(100.00);
		testClaim.setJustification("test justification");
		testClaim.setHoursMissed(8);
		
		Stage s = new Stage();
		s.setId(1);
		s.setName("Pending direct supervisor review");
		testClaim.setApprovalStage(s);
		
		testClaim.setDsaID(0);
		testClaim.setDhaID(0);
		testClaim.setBcaID(0);
		testClaim.setDenialReason("test denial");
		
		testPerson = new Person();
		testPerson.setId(1);
	}
	

	@Order(1)
	@Test
	public void makeClaim() {
		assertTrue(claimDAO.makeClaim(testClaim) == 1);
	}
	
	@Order(2)
	@Test
	public void getClaimsByPersonID() {
		Set<Claim> claims = claimDAO.getClaimsByPerson(testPerson.getId());
		for(Claim c : claims) {
			System.out.println(c);
		}
		assertTrue(claims.contains(testClaim));
	}
}
