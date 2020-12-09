package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import com.revature.models.Genre;

@TestMethodOrder(OrderAnnotation.class)
class GenreDAOTest {
	private static GenreDAO genreDao;
	private static Genre sampleGenre;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		GenreDAOFactory genreFactory = new GenreDAOFactory();
		genreDao = genreFactory.getGenreDao();
		
		sampleGenre = new Genre();
		sampleGenre.setId(1);
		sampleGenre.setName("SampleGenre");
	}

	@Order(1)
	@Test
	void testAdd() {
		try {
			Integer newInt = genreDao.add(sampleGenre);
			assertNotEquals(newInt, 0);
			sampleGenre.setId(newInt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Order(2)
	@Test
	void testGetById() {
		Genre a = genreDao.getById(sampleGenre.getId());
		assertEquals(a, sampleGenre);
	}
	
	@Order(3)
	@Test
	void testGetAll() {
		Set<Genre> genres = genreDao.getAll();
		assertTrue(genres.contains(sampleGenre));
	}
	
	@Order(4)
	@Test
	void testUpdate() {
		Genre a = sampleGenre;
		a.setName("Updated Sample");
		try {
			genreDao.update(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Genre b = genreDao.getById(a.getId());
		assertEquals(a, b);
		sampleGenre = b;
	}
	
	@Order(5)
	@Test
	void testDelete() {
		Genre a = sampleGenre;
		genreDao.delete(a);
		
		assertFalse(genreDao.getAll().contains(sampleGenre));
	}
}
