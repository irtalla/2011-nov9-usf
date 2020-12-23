package com.revature.driver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.Flower;
import com.revature.services.FlowerService;
import com.revature.services.PurchaseService;

public class FlowerDriver {
	public static ApplicationContext ac;
	
	public static void main(String[] args) {
		ac = new ClassPathXmlApplicationContext("beans.xml");
		
		FlowerService fServ = (FlowerService) ac.getBean(FlowerService.class);
		PurchaseService pServ = (PurchaseService) ac.getBean(PurchaseService.class);
		System.out.println(fServ.getAll());
		
		Flower f = new Flower();
		f.setName("h");
		f.setCurrentPrice(2.99);
		fServ.addFlower(f);
		
		System.out.println(fServ.getAll());
		System.out.println(pServ.getPurchasesByFlower(fServ.getFlower(1)));
		
		fServ.throwExceptionFlower(null);
	}
}
