import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Entity.Bike;
import Entity.Brand;
import Entity.Status;
import Providers.BikePImp;

// testing crud methods in my services 

class BikeP_test {
	BikePImp obj = new BikePImp();
	/*@Test
	public void testaddBike() {
		
		Bike bi = new Bike();
		Brand br = new Brand();
		Status st = new Status();
		br.setId(3);
		st.setId(1);
		bi.setStatus(st);
		bi.setBrand(br);
		
		
		Integer output =obj.addBike(bi);
		//System.out.println(output);
		assertEquals(4,output);
		
	}*/
	/*@Test
	public void testgetBikeById() {
		Bike bi=obj.getBikeById(1);
		String output = bi.getName();
		
		assertEquals("unicorn",output);
	}*/
	/*@Test
	public void testupdateBike() {
		Bike bi = obj.getBikeById(1);
		bi.setName("thunder");
		obj.updateBike(bi);
		String output = bi.getName();
		
		assertEquals("thunder",output);
	}*/
	

}
