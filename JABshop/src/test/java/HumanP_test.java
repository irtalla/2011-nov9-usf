import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Entity.Human;
import Providers.HumanPImp;

class HumanP_test {
	//testing some crud methods in my human services

	HumanPImp obj= new HumanPImp();
	
	@Test
	public void testaddHuman() {
		Human hu = new Human();
		hu.setUser("bobo");
		hu.setPass("lucky");
		
	}
	
	

}
