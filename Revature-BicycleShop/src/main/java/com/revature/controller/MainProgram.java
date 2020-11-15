package com.revature.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.revature.beans.Bicycle;
import com.revature.beans.Offer;
import com.revature.beans.User;

public class MainProgram {
	//note that as time goes on, this will be phased out for the database
	private static List<Bicycle> bicyclesForSale;
	private static Set<User> bicycleShopUsers;
	private static Set<Offer> offers;
	
	static {
		bicycleShopUsers = new HashSet<>();
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		loginLoop(scanner);
		
		scanner.close();
	}
	
	private static void loginLoop(Scanner scanner) {
		
		while (true) {
			System.out.println("Welcome to Punk-Sure, the best one around!");
			System.out.println("Please enter your username and password.");
			System.out.print("Username: ");
			String username = scanner.nextLine();
			System.out.print("Password: ");
			String password = scanner.nextLine();
			
			
		}
	}
	
}
