package spencer.revature.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BicycleTest {

	@Test
	public void ConstructorTest1() {
		Bicycle Bike = new Bicycle();
		Bike.setModel("apache");
		assertEquals("apache", Bike.getModel());
		assertEquals(1, Bike.getId());
		assertEquals(true, Bike.getAvailable());
	}
	
	

}
