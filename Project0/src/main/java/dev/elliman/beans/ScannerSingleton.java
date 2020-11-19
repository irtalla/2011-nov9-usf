package dev.elliman.beans;

import java.util.Scanner;

public class ScannerSingleton {
	private static Scanner scanner = null;
	
	public static Scanner getScanner() {
		if(scanner == null) {
			scanner = new Scanner(System.in);
		}
		return scanner;
	}
}
