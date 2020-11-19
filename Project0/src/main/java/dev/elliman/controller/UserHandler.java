package dev.elliman.controller;

import dev.elliman.beans.Person;

/**
 * This will contain the methods that handle users interacting with the program
 * @author Will
 *
 */
public class UserHandler {
	
	public static void purchaseBike(Person user) {
		System.out.println("purchasing a bike");
	}
	
	public static void addBike(Person user) {
		System.out.println("adding a bike");
	}
	
	public static void removeBike(Person user) {
		System.out.println("removing a bike");
	}
	
	public static void promoteUser(Person user) {
		System.out.println("promoting a user");
	}
}
