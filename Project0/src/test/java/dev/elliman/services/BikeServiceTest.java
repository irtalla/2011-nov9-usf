package dev.elliman.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import dev.elliman.beans.Bike;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class BikeServiceTest {
	private static BikeService bs;
	private static Set<Bike> addedBikes;
	
	@BeforeAll
	public static void beforeAllTests() {
		bs = new BikeServiceImpl();
		//addedBikes = new HashSet<Bike>();
	}
	
	@Order(1)
	@Test
	public void addAndGet() {
		Bike b = new Bike("trike", "red");
		bs.addBike(b);
		
		Bike dbb = bs.getById(b.getId());
		
		assertTrue(dbb.getModel().equals(b.getModel()) && dbb.getColor().equals(b.getColor()));
	}
	
	@Order(2)
	@Test
	public void getByModel() {
		Bike b = new Bike("bmx", "red");
		bs.addBike(b);
		
		Bike dbb = bs.getByModel(b.getModel());
		
		assertTrue(dbb.getId().equals(b.getId()) && dbb.getColor().equals(b.getColor()));
	}
	
	@Order(3)
	@Test
	public void update() {
		Bike b = new Bike("trike","blue");
		b.setId(bs.getByModel("trike").getId());
		bs.update(b);
		assertEquals(b.getColor(), bs.getById(b.getId()).getColor());
	}
	
	@Order(4)
	@Test
	public void getAllAndDeleteAll() {
		addedBikes = bs.getAll();
		for(Bike b : addedBikes) {
			bs.delete(b);
		}
		addedBikes = bs.getAll();
		assertTrue(addedBikes.isEmpty());
	}
	
//	@Order(5)
//	@Test
//	public void test() {
//		
//	}
}
