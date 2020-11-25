package com.revature.utils;
import java.util.Scanner;

public class SingletonScanner {
	// private static object
	private static SingletonScanner scannerSingleton;
	private Scanner scan;
	
	// private constructor
	private SingletonScanner() {
		scan = new Scanner(System.in);
	}
	
	// public static synchronized accessor method
	public static synchronized SingletonScanner getScannerSingleton() {
		if (scannerSingleton == null)
			scannerSingleton = new SingletonScanner();
		return scannerSingleton;
	}
	
	public Scanner getScanner() {
		return scan;
	}
	
	public void close() {
		scan.close();
	}
}
