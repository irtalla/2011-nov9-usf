package myTests;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Request;
import com.revature.data.RequestDAO;
import com.revature.services.RequestServiceImpl;




public class TRMSServiceTests {
	
	@Mock
	static RequestDAO  requestDao;	
	
	
	@InjectMocks
	static RequestServiceImpl RequestServ;
	
	static Set<Request> RequestMock =  new HashSet<>();
	
	static Integer RequestSequenceMock = 1;
	
	@Test 
	public void testGetReeuests() {
		when(requestDao.getAll()).thenReturn(RequestMock);
		assertEquals(RequestMock, RequestServ.getRequests());		
		verify(requestDao).getAll();
	}
}
