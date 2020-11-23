package dev.rev.controller;

import java.util.Scanner;

import dev.rev.customException.NonUniqueUsernameException;
import dev.rev.model.Person;
import dev.rev.model.Role;
import dev.rev.services.PersonService;
import dev.rev.services.PersonServiceImpl;


public class Maincontroller {


	private static Scanner scan;
	private static PersonService pservice = new PersonServiceImpl();
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
		System.out.println(logedinuser);
		while(activeuser) {
			
			if(logedinuser.getRole().getRole_name()=="Customer") {
			
				CustomerRoles();
				
			}else if(logedinuser.getRole().getRole_name()=="Employee") {
				
				ManagerRoles();
			}else if(logedinuser.getRole().getRole_name()=="Manager") {
				EmployeeRoles();
			}
			
		}
		

	}
	
	private static void CustomerRoles() {
		
	}
	
	private static void ManagerRoles() {
		
	}
	
	private static void  EmployeeRoles() {
		
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
