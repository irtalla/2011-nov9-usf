package com.revature.data;

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
import com.cross.utils.StringGenerator;

public class DecisionHibernateTest {
	
	private static DecisionDAO decisionDAO = new DecisionHibernate(); 
	private static Set<Decision> testDecisions = new HashSet<Decision>(); 
	
	/*
	 * Since we are using JavaBeans, we have to manually set 
	 * at least once. 
	 */
	private static void generateTestPitches() {
		Random rand = new Random(); 
		for (int i = 0; i < 5; ++i) {
			Decision ds = new Decision(); 
			// Dummy pitch IDs are negative 
			ds.setPitchId( (-1 * rand.nextInt(3)) - 1 );
			ds.setEditorId( 1 + rand.nextInt(2) );
			ds.setExplanation( StringGenerator.randomString(5) );
			DecisionType dst = new DecisionType(); 
			dst.setId( 1 + rand.nextInt(2) );
			dst.setName( UtilityDAO.getById(dst, dst.getId()).getName());
			ds.setDecisionType(dst);
			ds.setCreationTime( LocalDateTime.now() );
			testDecisions.add(ds);
		}
	}
	
	
	@DisplayName("addTest")
	@Test
	@Order(1) 
	public void addTest() {
		generateTestPitches(); 
		testDecisions.forEach( decision -> {
			Decision ds = null; 
			ds = decisionDAO.add(decision);
			assertTrue(ds != null);
			decision.setId( ds.getId());
		});
	}
	
	@DisplayName("getByIdTest")
	@Test
	@Order(2) 
	public void getByIdTest() {
		testDecisions.forEach( decision -> {
			Decision q = null; 
			q = decisionDAO.getById( decision.getId() );
			assertTrue(q != null && q.getId() == decision.getId() );
		});
		
		assertTrue( null == decisionDAO.getById(-1) );
	}
	
	@DisplayName("getByEditorIdTest")
	@Test
	@Order(3)
	public void getByEditorIdTest() {
		
		Map<Integer, Integer> idMap = new HashMap<Integer, Integer>(); 
		Integer editorIds[] = {1,2,3};
		for (Integer id : editorIds) { idMap.put(id, 0); }
		testDecisions.forEach(ds -> {
			Integer editorId = ds.getEditorId(); 
			idMap.put(editorId, idMap.get(editorId) + 1);
		});
		
		for (Integer id : editorIds) {
			Set<Decision> byEditorId = decisionDAO.getByEditorId(id);
			System.out.println("Query size: " + idMap.get(id));
			System.out.println("Map size: " + byEditorId.size());
			assertTrue( byEditorId.size() == idMap.get(id) );
		}
	}
	
	@DisplayName("getByPitchIdTest")
	@Test
	@Order(4)
	public void getByPitchIdTest() {
		
		Map<Integer, Integer> idMap = new HashMap<Integer, Integer>(); 
		Integer pitchIds[] = {-1,-2,-3};
		for (Integer id : pitchIds) { idMap.put(id, 0); }
		testDecisions.forEach(ds -> {
			Integer pitchId = ds.getPitchId(); 
			idMap.put(pitchId, idMap.get(pitchId) + 1);
		});
		
		for (Integer id : pitchIds) {
			Set<Decision> byPitchId = decisionDAO.getByPitchId(id);
			assertTrue( byPitchId.size() == idMap.get(id) );
		}
	}
	
	@DisplayName("deleteTest")
	@Test
	@Order(5)
	public void deleteTest() {
		testDecisions.forEach( decision -> {
			assertTrue( decisionDAO.delete(decision) );
		});
	}
	
	
	
}
