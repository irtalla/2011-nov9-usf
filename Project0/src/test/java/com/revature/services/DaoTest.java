//package com.revature.services;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.Set;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import com.revature.beans.schemaClasses.Row;
//import com.revature.data.DAO;
//
//
//public abstract class DaoTest<T extends Row> {
//	protected DAO<T> dao;
//	protected T testSubject;
//	protected Set<T> testSubjectSet;
//	protected abstract DAO<T> createDao();
//	protected abstract T createObject();
//	protected abstract Set<T> createTestSubjectSet(); 
//	
//	@BeforeAll
//	public void initializeDaos() {
//		dao = createDao();
//	}
//	
//	@BeforeEach
//	public void initializeTestSubjects(){
//		testSubject = createObject();
//		testSubjectSet = createTestSubjectSet();
//	}
//	
//	@Test
//	public void testAdd() throws Exception{
//		dao.add(testSubject);
//		assertTrue(dao.getAll().contains(testSubject));
//	}
//	
//	@Test
//	public void testGetAll() throws Exception{
//		for(T addedObject : testSubjectSet){
//			dao.add(addedObject);
//		}
//		
//		for(T entry : dao.getAll()) {
//			T match = dao.getById(entry.getId());
//			assertEquals(entry, match);
//		}
////		assertEquals(addedObjects.length, dao.getAll().size());
//	}
//	
//	@Test
//	public void testGetById() {
//		T match = dao.getById(testSubject.getId());
//		assertEquals(match.getId(), testSubject.getId());
//	}
//	
//	@Test
//	public void update(T t) {
//		T match = dao.getById(t.getId());
//		match = dao.update(t);
//		assertEquals(t, match);
//	}
//}
