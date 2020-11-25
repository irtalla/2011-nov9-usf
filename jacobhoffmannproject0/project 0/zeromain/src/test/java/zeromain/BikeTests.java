package zeromain;

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

import data.BikeDao;
import data.OfferDao;
import data.PaymentDAO;
import data.UserDao;
import models.Bike;
import models.Model;
import models.Offer;
import models.Payment;
import models.Status;
import models.User;
import services.BikeService;
import services.BikeServiceImpl;
import services.OfferService;
import services.OfferServiceImpl;
import services.PaymentServiceImpl;
@ExtendWith(MockitoExtension.class)
public class BikeTests {
	@Mock
	static BikeDao bikeDao;
	@Mock
	static OfferDao offerDao;
	@Mock
	static PaymentDAO paymentDao;
	@Mock
	static UserDao userDao;
	
	

	@InjectMocks
	static BikeServiceImpl bikeServ;
	static OfferServiceImpl offerServ;
	static PaymentServiceImpl paymentServ;
	static Set<Bike> bikesMock = new HashSet<>();
	static Set<Offer> offersMock = new HashSet<>();
	static Set<Payment> payMock = new HashSet<>();
	static Integer bikeSequenceMock = 1;
	static Integer offersSequenceMock =1;
	static Integer paySequenceMock = 1;
	@Test
	public void testAddValidBike() {
		Bike c = new Bike();
		Model b = new Model();
		b.setId(1);
		b.setBrand("a");
		b.setName("yo");
		b.setType("aaa");
		b.setYear(33333);
		c.setId(1);
		c.setColor("hhh");
		c.setModeltype(b);
		c.setSize("aaa");
		Status s = new Status();
		s.setId(1);
		s.setAvalibilty("do");
		c.setStatus(s);
		
		bikesMock.add(c);
		Bike c2 = new Bike();
		c2.setId(bikeSequenceMock++);
		c2.setStatus(c.getStatus());
		c2.setModeltype(b);
	//	when(bikeDao.add(c)).thenReturn(c2);
		//assertEquals(c.getId(), bikeServ.addBike(c).intValue());
	//assertNotEquals(c.getId().intValue(), bikeServ.addBike(c).intValue());
		when(bikeDao.add(c)).thenReturn(c2);
		assertEquals(c.getId(), bikeServ.addBike(c).intValue());
	verify(bikeDao).add(c);
	}
	@Test
	public void testAddOffer() {
		Offer o = new Offer();
		User u = new User();
		Bike c = new Bike();
		Model b = new Model();
		b.setId(1);
		b.setBrand("a");
		b.setName("yo");
		b.setType("aaa");
		b.setYear(33333);
		c.setId(1);
		c.setColor("hhh");
		c.setModeltype(b);
		c.setSize("aaa");
		Status s = new Status();
		s.setId(1);
		s.setAvalibilty("do");
		c.setStatus(s);
		o.setBikeId(c);
		o.setCustomerId(u);
		bikesMock.add(c);
		Offer c2 = new Offer();
		c2.setOfferId(offersSequenceMock++);
		c2.setBikeId(o.getBikeId());
		c2.setCustomerId(u);
	//	when(bikeDao.add(c)).thenReturn(c2);
		//assertEquals(c.getId(), bikeServ.addBike(c).intValue());
	//assertNotEquals(c.getId().intValue(), bikeServ.addBike(c).intValue());
		when(offerDao.add(o)).thenReturn(c2);
		assertEquals(o.getOfferId(), offerServ.addOffer(o).intValue());
	verify(offerDao).add(o);
	}
	@Test
	public void paymentAdd() throws Exception {
		Payment p = new Payment();
		Offer o = new Offer();
		User u = new User();
		Bike c = new Bike();
		Model b = new Model();
		b.setId(1);
		b.setBrand("a");
		b.setName("yo");
		b.setType("aaa");
		b.setYear(33333);
		c.setId(1);
		c.setColor("hhh");
		c.setModeltype(b);
		c.setSize("aaa");
		Status s = new Status();
		s.setId(1);
		s.setAvalibilty("do");
		c.setStatus(s);
		o.setBikeId(c);
		o.setCustomerId(u);
		p.setBike(c);
		p.setCustomer(u);
		payMock.add(p);
		Payment c2 = new Payment();
		c2.setPaymentId(offersSequenceMock++);
		c2.setBike(o.getBikeId());
		c2.setCustomer(u);
	//	when(bikeDao.add(c)).thenReturn(c2);
		//assertEquals(c.getId(), bikeServ.addBike(c).intValue());
	//assertNotEquals(c.getId().intValue(), bikeServ.addBike(c).intValue());
		when(paymentDao.add(p)).thenReturn(c2);
		assertEquals(p.getPaymentId(), paymentServ.addPayment(p).intValue());
	verify(paymentDao).add(p);
	}
	@Test
	public void testGetBikes() {
		when(bikeDao.getAll()).thenReturn(bikesMock);
		assertEquals(bikesMock, bikeServ.getBikes());
		verify(bikeDao).getAll();
	}
	@Test
	public void testUpdateBike() {
		Bike c = new Bike();
		bikeServ.updateBike(c);
		verify(bikeDao).update(c);
	}
	@Test
	public void testRemoveBike() {
		Bike c = new Bike();
		bikeServ.removeBike(c);
		verify(bikeDao).delete(c);
	}
	@Test
	public void testGetOffers() {
		when(offerDao.getAll()).thenReturn(offersMock);
		assertEquals(offersMock, offerServ.getAllOffers());
		verify(offerDao).getAll();
	}
	@Test
	public void testUpdateOffers() {
		Offer c = new Offer();
		offerServ.updateOffer(c);
		verify(offerDao).update(c);
	}
	@Test
	public void testRemoveOffer() {
		Offer c = new Offer();
		offerServ.removeOffer(c);
		verify(offerDao).delete(c);
	}
	@Test
	public void testGetPayments() {
		when(paymentDao.getAll()).thenReturn(payMock);
		assertEquals(payMock, paymentServ.getAll());
		verify(paymentDao).getAll();
	}
	@Test
	public void testUpdatePayments() {
		Payment c = new Payment();
		paymentServ.updatePayment(c);
		verify(paymentDao).update(c);
	}
	@Test
	public void testRemovePayment() {
		Payment c = new Payment();
		paymentServ.deletePayment(c);
		verify(paymentDao).delete(c);
	}
	@Test
	public void testGetValidBikes(){
		Bike b = new Bike();
		Status s = new Status();
		//Model m = new Model();
		s.setId(2);
		s.setAvalibilty("For Sale");
		b.setStatus(s);
		//b.setModeltype(m);
		Set<Bike> avalbikesMock = bikeServ.getAvailableBikes();
		avalbikesMock.add(b);
		bikesMock.add(b);
		when(bikeDao.getAvailableBikes()).then(invocation -> {
			Set<Bike> aval = new HashSet<>();
			for (Bike bike : bikesMock) {
				if (bike.getStatus().getAvalibilty().equals("For Sale")) {
					aval.add(bike);
				}
			}
			return aval;
		});
		assertEquals(avalbikesMock,bikeServ.getAvailableBikes());
		verify(bikeDao).getAvailableBikes();
	}

	@Test
	public void testPlaceOffer() {
		User p = new User();
		Bike c = new Bike();
		p.setUserId(1);
		p.setPassword("123");
		p.setUsername("joe");
		p.setIsEmployee(true);
		c.setId(1);
		Model b = new Model();
		b.setId(1);
		Status s = new Status();
		s.setAvalibilty("For Sale");
		s.setId(1);
		c.setModeltype(b);
		c.setStatus(s);
		Offer o = new Offer(200,p,c);
		o.setOfferId(1);
		o.setStatus("Pending");
		offerServ.addOffer(o);
		
		assertEquals(c.getStatus().getAvalibilty(),"For Sale");
		assertTrue(o.getBikeId().equals(c));
		verify(bikeDao).update(c);
		verify(userDao).update(p);
	}

	
}
