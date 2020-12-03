package dev.elliman.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;
import dev.elliman.beans.Stage;

public class ClaimDAOTest {
	
	private static ClaimDAO claimDAO;
	
	private static Claim testClaim;
	public static Person testPerson;

	@BeforeAll
	public static void beforeAll() {
		claimDAO = ClaimDAOFactory.getClaimDAO();
		
		testClaim = new Claim();
		testClaim.setId(1);
		testClaim.setPersonID(1);
		testClaim.setEventID(1);
		testClaim.setGradingID(1);
		
		LocalDateTime ldt = LocalDateTime.now();
		testClaim.setEventDate(ldt);
		
		testClaim.setEventLocation("test location");
		testClaim.setDescription("test description");
		testClaim.setPrice(100.00);
		testClaim.setJustification("test justification");
		testClaim.setHoursMissed(8);
		
		Stage s = new Stage();
		s.setId(1);
		s.setName("test stage");
		testClaim.setApprovalStage(s);
		
		testClaim.setDsaID(null);
		testClaim.setDhaID(null);
		testClaim.setBcaID(null);
		testClaim.setDenialReason("test denial");
		
		testPerson = new Person();
		testPerson.setId(1);
	}
	
	@Test
	public void getClaimsByPersonPerson() {
		Set<Claim> claims = claimDAO.getClaimsByPerson(testPerson);
		for(Claim c : claims) {
			//select the first and only item in a weird way
			assertTrue(testClaim.equals(c));
			break;
		}
	}
	
	@Test
	public void getClaimsByPersonID() {
		Set<Claim> claims = claimDAO.getClaimsByPerson(testPerson.getId());
		for(Claim c : claims) {
			//select the first and only item in a weird way
			assertTrue(testClaim.equals(c));
			break;
		}
	}
}
