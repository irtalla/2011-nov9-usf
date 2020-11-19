package dev.elliman.controller;

import java.util.Scanner;

import dev.elliman.beans.Person;
import dev.elliman.beans.ScannerSingleton;

/**
 * This will contain the methods that handle users interacting with the program
 * @author Will
 *
 */
public class UserHandler {
	
	private static Scanner input = ScannerSingleton.getScanner();
	
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
