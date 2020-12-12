package com.cross.data;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.cross.beans.Decision;
import com.cross.beans.DecisionType;
import com.cross.beans.Pitch;
import com.cross.beans.Stage;
import com.cross.data.DecisionDAO;
import com.cross.data.UtilityDAO;
import com.cross.exceptions.InvalidGeneralEditorException;
import com.cross.utils.StringGenerator;

/*
 * 
 * Testing the decisionHibernateDAO is tricky because we cannot
 * simply generate and hash a large volume of random data. Since
 * each decision requires a transaction that could effect the
 * status of a pitch and/or draft, we should try to move a 
 * pitch through the pipeline, checking that is has the correct
 * status value after each decision. To facilitate testing, the 
 * database contains a dummy pitch with id = 0; 
 * 
 */

public class DecisionHibernateTest {
	
	private static DecisionDAO decisionDAO = new DecisionHibernate(); 
	private static Pitch dummyPitch = new PitchHibernate().getById(0);
	private static Set<Decision> dSet = new HashSet<Decision>(); 
	
	@DisplayName("addTest")
	@Test
	@Order(1) 
	public void addTest() {
		assertTrue( dummyPitch != null); 
	}
	
	@DisplayName("approvePitchInGenreStageTest")
	@Test
	@Order(2) 
	public void approvePitchInGenreStageTest() {
		
		System.out.println( dummyPitch.getStage().getName() );
		
		assertTrue(dummyPitch.getStage().getName().equalsIgnoreCase("GENRE REVIEW") );		
		Decision d = new Decision(); 
		d.setPitchId(0);
		d.setEditorId(2);
		d.setDecisionType(UtilityDAO.getByName(new DecisionType(), "pitch-approval") );
		d.setCreationTime( LocalDateTime.now() );
		d.setExplanation("dfjiapf sadfjpds");
		d = decisionDAO.add(d); 
		assertTrue(d != null);
		Pitch p = new PitchHibernate().getById(0);
		assertTrue( p.getStage().getName().equalsIgnoreCase("GENERAL REVIEW") );
	}
	
	@DisplayName("approvePitchInGeneralStageTest")
	@Test
	@Order(2) 
	public void approvePitchInGeneralStageTest() {
		
		System.out.println( dummyPitch.getStage().getName() );
		
		assertTrue(dummyPitch.getStage().getName().equalsIgnoreCase("GENERAL REVIEW") );		
		final Decision d = new Decision(); 
		d.setPitchId(0);
		d.setEditorId(2);
		d.setDecisionType(UtilityDAO.getByName(new DecisionType(), "pitch-approval") );
		d.setCreationTime( LocalDateTime.now() );
		d.setExplanation("dfjiapf sadfjpds");
		
		assertThrows(InvalidGeneralEditorException.class, () -> {
			decisionDAO.add(d);
		});

		
//		d.setEditorId(3);
//		Decision returned = decisionDAO.add(d);
//		assertTrue(returned != null);
//		Pitch p = new PitchHibernate().getById(0);
//		assertTrue( p.getStage().getName().equalsIgnoreCase("SENIOR REVIEW") );
	}
	
	
	
	
		
}
