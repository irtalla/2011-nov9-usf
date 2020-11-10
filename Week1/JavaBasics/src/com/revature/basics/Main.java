package com.revature.basics;

public class Main {

	public static void main(String[] args) {
		// data types
		// primitives:
		byte byteVar; // 1 byte (numeric)
		short shortVar; // 2 bytes (numeric)
		char charVar = 'A'; // 2 bytes 'A' 'a' '4'
		int intVar = 24; // 4 bytes (numeric)
		float floatVar; // 4 bytes (numeric, decimal)
		long longVar; // 8 bytes (numeric)
		double doubleVar; // 8 bytes (numeric, decimal)
		boolean boolVar; // size is JVM-dependent

		// reference types/object types
		// references types for primitives are called Wrappers
		Byte b;
		Short s;
		Character c;
		Integer i = new Integer(24);
		Float f;
		Long l;
		Double d;
		Boolean bool;
		
		String myString = myMethod(24);
		System.out.println(myMethod(24));
		
		Main mainObj = new Main();
		System.out.println(mainObj.myMethod(36));
		
		// arrays are contiguous blocks of memory
		// for storing entities of the same type
		int[] intArray = new int[5];
		int[] intArray2 = {1, 2, 3, 4, 5};
		
		myMethod(intArray2[2]);
	}
	
	private static String myMethod(Integer i) {
		return i.toString();
	}

}
