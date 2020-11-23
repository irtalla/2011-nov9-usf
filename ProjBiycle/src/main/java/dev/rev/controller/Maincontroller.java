package dev.rev.controller;

import java.util.Scanner;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.model.Bicycle;
import dev.rev.model.Person;
import dev.rev.model.Role;
import dev.rev.services.BicycleService;
import dev.rev.services.BicycleServiceImp;
import dev.rev.services.PersonService;
import dev.rev.services.PersonServiceImpl;


public class Maincontroller {


	private static Scanner scan;
	private static PersonService pservice = new PersonServiceImpl();
	private static BicycleService bservice= new BicycleServiceImp();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean activeuser=true;
		scan=new Scanner(System.in);
		
		System.out.println("***********************************************");
		System.out.println("*************** Welcome To Shop ***************");
		System.out.println("***********************************************");

		
		Person logedinuser=null;
		while(logedinuser==null) {
			System.out.println("What would you like to do?");
			System.out.println("1. Register\n2. Log in\nother. Exit");
			int userInput = Integer.valueOf(scan.nextLine());
			
			switch (userInput) {
			case 1:
				logedinuser = registeruser();
				break;
			case 2:
				logedinuser = loginuser();
				break;
			default:
				activeuser = false;
				break;
			}
		}
		System.out.println(activeuser);
		
		while(activeuser) {
			System.out.println(logedinuser.getRole().getRole_name());
					
			if(logedinuser.getRole().getRole_name()=="Customer") {
			
				CustomerRoles();
				//activeuser=false;
		}else if(logedinuser.getRole().getRole_name().equals("Employee")) {
				EmployeeRoles();
				activeuser=false;		
			}else if(logedinuser.getRole().getRole_name()=="Manager") {
				ManagerRoles();
				activeuser=false;
			}
			
		}
		

	}
	
	private static void CustomerRoles() {
		
	}
	
	private static void ManagerRoles() {
		
	}
	
	private static void  EmployeeRoles() {
		
		System.out.println("Choose the Option from below:"
				+ "\n1. Add Bicycle\t\t2.Delete Bicycle\t\t3.Update Bicycle\n4.Check Offers on Bicycle\t\t5.View All Payments"
				+ "\t\t 0. Logout");
		int i = Integer.valueOf(scan.nextInt());
		switch (i){
		case 1: addbicycle();
				break;
		case 2: deletebicycle();
				break;
				
		case 3: updatebicycle();
				break;
		case 4: checkoffers();
				break;
		case 5: Viewallpayments();
				break;
		case 0: logout();
				break;
		}
	}
	private static void deletebicycle() {
		// TODO Auto-generated method stub
		
		
	}

	private static void logout() {
		// TODO Auto-generated method stub
		
	}

	private static void Viewallpayments() {
		// TODO Auto-generated method stub
		
	}

	private static void checkoffers() {
		// TODO Auto-generated method stub
		
	}

	private static void updatebicycle() {
		// TODO Auto-generated method stub
		
	}

	private static void addbicycle() {
		
		System.out.println("Enter the information of the Bicycle Below");
		System.out.println("Bicycle Brand:\n");
		Bicycle b=new Bicycle();
		b.setBrand(scan.next());
		System.out.println("Price:\n");
		b.setPrice(Integer.valueOf(scan.next()));
		System.out.println("Enter the color of Cycle");
		b.setColor(scan.next());
		System.out.println("Enter the Quantity");
		b.setQuantity(Integer.valueOf(scan.nextInt()));
		
		System.out.println("Review the Information:"
				+ "Brand: \t "+b.getBrand()+"\t Price: "+b.getPrice()+"\nColor:"+
				b.getColor()+"\t"+"Quantity: "+b.getQuantity());
		
		try {
			b.setId(bservice.addBicycle(b));
			System.out.println("Bicycle added succesfully ");
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

	private static Person registeruser() {
		Role role=registeras();
		System.out.println("Enter the Username:");
		String username=scan.next();
		
		if(pservice.checkusername(username)=="0") {
			System.out.println("Username Available");
		}else if(pservice.checkusername(username)=="!0" ){
			System.out.println(" Username taken, Register again");
			registeruser();
		}
		Person newuser=new Person();
	
		System.out.println(role);
		// TODO Auto-generated method stub
		
		
		newuser.setUsername(username);
		System.out.println("Enter the Name:");
		newuser.setName(scan.next());
	
		System.out.println("Enter the Password:");
		newuser.setPassword(scan.next());
		newuser.setRole(role);
		
		System.out.println("Review your information");
		System.out.println("Name: "+newuser.getName()+"\t Username: "+newuser.getUsername()+"\t Password:"
				+ " "+newuser.getPassword()+"\t Role: "+newuser.getRole());
		System.out.println("1. Confirm\t\t2.Re-enter the information\t\tPress anyother key to return");
		int input=Integer.valueOf(scan.next());
		switch(input) {
		case 1:
			try {
				newuser.setId(pservice.addPerson(newuser));
				System.out.println("Registerd Successfully");
				return newuser;
			}catch(NonUniqueUsernameException e) {
				System.out.println("Usernmae is already taken. Choose other");
			}
			break;
		case 2:
			break;
		default:
			return null;
				
		}
		
		
		return newuser;
	}
	private static Person loginuser() {
		String username,password;
		System.out.println("Enter the Username:");
		
		username=scan.nextLine();

		if(pservice.checkusername(username)=="0") {
			System.out.println("Username not avaialbe");
			loginuser();
		}else if(pservice.checkusername(username)=="!0" ){
			System.out.println(" Username matched");
			
		}
		System.out.println("Enter the Password:");
		password=scan.nextLine();
		
		System.out.println(username+"      "+password);

		Person p= pservice.getPersonByUsername(username, password);
		if(p==null) {
			System.out.println("Password not matched");
			
		}else {
			System.out.println("You are logged in");
			return p;
			
		}
		return null;
		}
	
	private static Role registeras() {
		Role role=new Role();
		System.out.println("Choose the Option from Below:");

		System.out.println("1. Register as Customer \t\t 2. Register as Employee");
		if(scan.nextInt()==1) {
			role.setId(1);
			role.setRole_name("Customer");
		}else if(scan.nextInt()==2) {
			role.setId(2);
			role.setRole_name("Employee");
		}
		else if(scan.nextLine()=="") {
			registeras();
		}else {
			registeras();
		}
				
				
				
				return role;
	}
	

}
