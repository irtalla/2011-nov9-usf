package dev.warrington.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.time.LocalDate;
import java.util.Base64;
 
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import dev.warrington.exceptions.NonUniqueUsernameException;

import dev.warrington.beans.Person;

import dev.warrington.services.PersonService;
import dev.warrington.services.PersonServiceImpl;
import io.javalin.http.Context;

public class PersonController {
	
	private static PersonService personServ = new PersonServiceImpl();
	private static String algorithm = "PBKDF2WithHmacSHA1"; // hashing algorithm
	private static int derivedKeyLength = 160; // for SHA1
    private static int iterations = 20000; // NIST specifies 10000
	
	/*public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		String password = "password";
		
		//Generate a random salt
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[8];
        random.nextBytes(salt);
        String newSalt = Base64.getEncoder().encodeToString(salt);
        System.out.println(newSalt);
        
        //Generate password
        byte[] saltBytes = Base64.getDecoder().decode(newSalt);
        
        KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		
        byte[] encBytes = f.generateSecret(spec).getEncoded();
        String passHash = Base64.getEncoder().encodeToString(encBytes);
        System.out.println(passHash);
        System.out.println(passHash.length());
	}*/
	
	
	public static void checkLogin(Context ctx) {
		
		System.out.println("Checking login");
		Person p = ctx.sessionAttribute("user");
		if (p != null) {
			System.out.println("Logged in as " + p.getUsername());
			ctx.json(p);
			ctx.status(200);
		} else {
			System.out.println("Not logged in");
			ctx.status(400);
		}
		
	}
	
	public static void logIn(Context ctx) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		System.out.println("here log in");
		/*String username = ctx.queryParam("user");
		Person p = personServ.getPersonByUsername(username);
		
		if (p != null) {
			
			String password = getHash(ctx.queryParam("pass"), p.getSalt());
			
			if (p.getPassHash().equals(password))
			{
				System.out.println("Logged in as " + p.getUsername());
				ctx.status(200);
				ctx.json(p);
				ctx.sessionAttribute("user", p);
			}
			else
			{
				// password mismatch
				ctx.status(400);
			}
		}
		else
		{
			// username not found
			ctx.status(404);
		}*/
		
	}
	
	public static void registerUser(Context ctx) {
		
		Person newPerson = ctx.bodyAsClass(Person.class);
		try {
			personServ.addPerson(newPerson);
		}
		catch (NonUniqueUsernameException e) {
			System.out.println("Username already taken :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
		
	}
	
	public static String getHash(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		byte[] saltBytes = Base64.getDecoder().decode(salt);
		KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		byte[] encBytes = f.generateSecret(spec).getEncoded();
		return Base64.getEncoder().encodeToString(encBytes);
		
	}
}