package com.revature.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Customer;
import com.revature.beans.Employee;
import com.revature.beans.Offer;
import com.revature.services.EmployeeFunctions;

public class EmployeeFunctionsTest {
	
	@Test
	public void testAddingABicycleToTheShop() {
		EmployeeFunctions ef = new EmployeeFunctions();
		Employee employee = new Employee("santalegweak", "happyrest");
		Bicycle newBicycle = new Bicycle("SemiLunar", "Jacked Up U76", "a racing bike meant for the discerning", employee, 40.99);
		
		assertTrue(ef.addABicycle(newBicycle));
	}
	
	@Test
	public void testAcceptingAnExistingOffer() {
		EmployeeFunctions ef = new EmployeeFunctions();
		
		Employee bicycleOfferer = new Employee("sagradaFamilia", "vanessaBebo");
		Bicycle bicycle = new Bicycle("ENMakis", "Mobility Mountain Bike", "Somehow this bike is so comfortable that even on the longest trails you will feel as if you're just riding a stationary bicycle at home.", bicycleOfferer, 69.42);
		Customer customerOffering = new Customer("baileyPickett", "fatAndSkinnyEpisode");
		Offer offer = new Offer(customerOffering, bicycle, 70.00);
		
		assertNotNull(ef.acceptAnOffer(offer));
	}
	
	@Test
	public void testRejectingAnExistingOffer() {
		EmployeeFunctions ef = new EmployeeFunctions();
		
		Employee bicycleOfferer = new Employee("sagradaFamilia", "vanessaBebo");
		Bicycle bicycle = new Bicycle("ENMakis", "Mobility Mountain Bike", "Somehow this bike is so comfortable that even on the longest trails you will feel as if you're just riding a stationary bicycle at home.", bicycleOfferer, 69.42);
		Customer customerOffering = new Customer("baileyPickett", "fatAndSkinnyEpisode");
		Offer offer = new Offer(customerOffering, bicycle, 70.00);
		
		assertNotNull(ef.rejectAnOffer(offer));
	}
	
	@Test
	public void testAcceptingAndRejectingANonexistentOffer() {
		EmployeeFunctions ef = new EmployeeFunctions();
		
		Employee bicycleOfferer = new Employee("adsi", "sdji");
		Bicycle bicycle = new Bicycle("wejio", "cuyf", "jiohcmk", bicycleOfferer, 69.42);
		Customer customerOffering = new Customer("ctyuisw", "ridersOnTheStorm");
		Offer offer = new Offer(customerOffering, bicycle, 70.00);
		
		assertNull(ef.acceptAnOffer(offer));
		assertNull(ef.rejectAnOffer(offer));
	}
	
	@Test
	public void testRejectingSimilarBicycleOffers() {
		EmployeeFunctions ef = new EmployeeFunctions();
		
		Set<Offer> offers = ef.getOfferDAO().getAllOffers();
		int amountOfOffersBeforehand = offers.size();
		
		Employee bicycleOfferer = new Employee("sagradaFamilia", "vanessaBebo");
		Bicycle bicycle = new Bicycle("ENMakis", "Mobility Mountain Bike", "Somehow this bike is so comfortable that even on the longest trails you will feel as if you're just riding a stationary bicycle at home.", bicycleOfferer, 69.42);
		Customer deniedCustomer1 = new Customer("barrabas", "tenEgyptianPlagues");
		Customer deniedCustomer2 = new Customer("calem", "CharonAndJupiter");
		Offer offer1 = new Offer(deniedCustomer1, bicycle, 79.87);
		Offer offer2 = new Offer(deniedCustomer2, bicycle, 72.76);
		
		ef.acceptAnOffer(offer1);
		ef.acceptAnOffer(offer2);
		
		Offer randomOfferChosen = (Offer)(offers.toArray())[0];
		ef.acceptAnOffer(randomOfferChosen);
		offers = ef.getOfferDAO().getAllOffers();
		assertEquals(amountOfOffersBeforehand, offers.size());
	}
	
	@Test
	public void testAcceptedOfferMeansOwnedBike() {
		EmployeeFunctions ef = new EmployeeFunctions();
		
		Employee bicycleOfferer = new Employee("sagradaFamilia", "vanessaBebo");
		Bicycle bicycle = new Bicycle("ENMakis", "Mobility Mountain Bike", "Somehow this bike is so comfortable that even on the longest trails you will feel as if you're just riding a stationary bicycle at home.", bicycleOfferer, 69.42);
		Customer customerOffering = new Customer("baileyPickett", "fatAndSkinnyEpisode");
		Offer offer = new Offer(customerOffering, bicycle, 70.00);
		
		Offer acceptedOffer = ef.acceptAnOffer(offer);
		assertEquals("owned", acceptedOffer.getBicycleToBeSold().getStatus());
	}
	
}
