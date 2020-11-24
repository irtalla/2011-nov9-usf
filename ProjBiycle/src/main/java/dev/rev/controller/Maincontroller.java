package dev.rev.controller;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.model.Bicycle;
import dev.rev.model.Offer;
import dev.rev.model.Person;
import dev.rev.model.Role;
import dev.rev.services.BicycleService;
import dev.rev.services.BicycleServiceImp;
import dev.rev.services.OfferServiceIm;
import dev.rev.services.OfferServices;
import dev.rev.services.PersonService;
import dev.rev.services.PersonServiceImpl;


public class Maincontroller {


	private static Scanner scan;
	private static PersonService pservice = new PersonServiceImpl();
	private static BicycleService bservice= new BicycleServiceImp();
	private static OfferServices oservice=new OfferServiceIm();
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
					
			if(logedinuser.getRole().getRole_name().equals("Customer")) {
			
				CustomerRoles(logedinuser);
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
	
	private static void CustomerRoles(Person log) {
		System.out.println("Choose the Option from below:"
				+ "\n1. See Avaiable Bicycles\t\t2.View My Bicycles\n3.Make Offer on Bicycle\t\t4.View Remaining Payments"
				+ "\t\t5.View my offers\t\t 0. Logout");
		int i = Integer.valueOf(scan.nextInt());
		switch(i) {
		case 1: See_Avaialable_Bicycles(log);
			break;
		case 2: View_My_bicycles();
			break;
		case 3: Make_an_offer();
			break;
		case 4: View_Remaining_Payment();
			break;
		case 5: viewmyoffers(log);
			break;
		case 0: logout();
		}
		
	}
	
	private static void viewmyoffers(Person log) {
		
		Set<Offer> offers=oservice.getofferbyPersonID(log.getId());
		System.out.println(offers);
		
		
		
	}

	private static void View_My_bicycles() {
		// TODO Auto-generated method stub
		
	}

	private static void View_Remaining_Payment() {
		// TODO Auto-generated method stub
		
	}

	private static void Make_an_offer() {
		// TODO Auto-generated method stub
		
	}

	private static void See_Avaialable_Bicycles(Person log) {
		int price;
		
		
			Set<Bicycle> allbicycles=bservice.getallBicyles();
			Bicycle bik1e=new Bicycle();
			for(Bicycle bike:allbicycles) {
				System.out.println("Bicycle Id: "+bike.getId()+"\nBrand: "+bike.getBrand()+"\t\t Color: "+bike.getColor()+"\n Price: "+bike.getPrice()
				+"Quantity: "+bike.getQuantity());
			}
			
			System.out.println("Would you like to Put offer on a biycle? 1 for yes, press anyother key for no");
			int input =Integer.valueOf(scan.next());
			if (input==1) {
				while (true) {
					System.out.println("Which Id? Enter its Id");
					input =Integer.valueOf(scan.next());
					Bicycle bicycle = bservice.getbyID(input);
					bicycle.setId(input);
					if (bicycle != null && bicycle.getQuantity()>0) {
						System.out.println(bicycle);
						System.out.println("You want to put offer on " + bicycle.getBrand()+ "Price: "+bicycle.getPrice() + "\n? 1 for yes, other for no");
						input = Integer.valueOf(scan.next());
						if (input == 1) {
							//bservice.
							System.out.println("Enter your Price! ");
							price=Integer.valueOf(scan.next());
							Offer offer=new Offer();
							offer.setOffer_price(price);
							offer.setOffer_Bicycle_id(bicycle.getId());
							offer.setOffer_Person_id(log.getId());
							offer.setStatus("Pending");
							try {
								offer.setOffer_id(oservice.putOffer(offer));
							//	System.out.println(oservice.getAll());
								System.out.println("Your offer on bike is placed.");
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							// get the person with their updated cat set
							
							break;
						} else {
							System.out.println("Okay, did you want to offer a different one? 1 for yes, other for no");
							input = Integer.valueOf(scan.nextLine());
							if (input != 1)
								break;
						}
					} else {
						System.out.println("Sorry,its out of stock. Do you want to try again?"
								+ " 1 for yes, other for no");
						input = Integer.valueOf(scan.nextLine());
						if (input != 1) {
							System.out.println("Okay, that's fine.");
							break;
						}
					}
				}
			} else {
				System.out.println("Okay, that's fine.");
			}

				
			
		
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
		Set<Offer> offers=oservice.getAll();
		for(Offer of:offers) {
			System.out.println(of+"\n");
		}
		System.out.println("Which Offer you want to see?\nEnter the Offer Id");
		int input=Integer.valueOf(scan.next());
		System.out.println("What you want to do? \n1.Accept\t\t2.Reject \t\t Press anyther key to exit");
		int in=Integer.valueOf(scan.next());
		switch(in) {
		case 1: 
			System.out.println("You have accepted the offer having id:"+input);
		//	Offer of=new Offer();
			
			int bid=oservice.bike_id_byofferid(input);
			int person_id=oservice.getpersonId(input,bid);
			System.out.println(input+"bikeid: "+bid +"personID "+person_id );
			
			oservice.rejectothers(bid);
			oservice.accept_reject_offer(input);
			changecyclestatus(bid,person_id);
			
			
			break;
		case 2: 
			oservice.rejectOffer(input);
			
			break;
		default:
			break;
		
		}
		EmployeeRoles();
		}

	private static void changecyclestatus(int id,int person_id) {
		// TODO Auto-generated method stub
		//bservice.updateBicycle(b);
		bservice.updateBikeStatus(id,person_id);
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
