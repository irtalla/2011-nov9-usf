package com.revature.data;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.revature.beans.Product;

public class ProductPostgres_Test {
	
	@DisplayName("Test getbyId()")
	@Test
	public void getByIdTest() {
		
		ProductPostgres testProductPostgres = new ProductPostgres(); 
		Product testProduct = new Product(); 
		
		testProduct = testProductPostgres.getById(3); 
		Assertions.assertNotEquals(null, testProduct);
		
		System.out.println( testProduct.getName() ); 
		System.out.println( testProduct.getPrice() ); 
		System.out.println( testProduct.getCategory().getName() ); 
		System.out.println( testProduct.getStatus().getName() ); 
		
		testProduct = testProductPostgres.getById(-1);
		Assertions.assertEquals(null, testProduct);
		
	}
	
	@DisplayName("Test getAvailableProducts()") 
	@Test
	public void getAvailableProductsTest() {
		
		ProductPostgres testProductPostgres = new ProductPostgres(); 
		Set<Product> availableProducts = testProductPostgres.getAvailableProducts(); 
		
		Assertions.assertTrue( availableProducts.size() > 0);
		availableProducts.forEach( 
				product -> Assertions.assertEquals("available", product.getStatus().getName())
				);	
	}
	
	@DisplayName("Test getAll()") 
	@Test
	public void getAll() {
		
		ProductPostgres testProductPostgres = new ProductPostgres(); 
		Set<Product> availableProducts = testProductPostgres.getAll(); 
		Set<Integer> categoryIds = new HashSet<Integer>(); 
		Assertions.assertTrue( availableProducts.size() > 0);
		availableProducts.forEach( product -> categoryIds.add(product.getCategory().getId()) ); 
		Assertions.assertTrue( categoryIds.size() > 1 );
	}
	
//	@DisplayName("Test delete()") 
//	@Test
//	public void deleteTest() {
//				
//		ProductPostgres testProductPostgres = new ProductPostgres(); 
//		Product presentProduct, notPresentProduct; 
//		presentProduct = new Product();
//		presentProduct.setId(1);
//		notPresentProduct = new Product();
//		notPresentProduct.setId(-1);		
//		Assertions.assertEquals(true, testProductPostgres.delete(presentProduct) );
//		Assertions.assertEquals(false, testProductPostgres.delete(notPresentProduct) );
//	}
	
	@DisplayName("Test add()")
	@Test
	public void addTest() {
		ProductPostgres testProductPostgres = new ProductPostgres(); 
		Product newProduct = new Product(); 
		newProduct.setName("Step Above"); 
		newProduct.setPrice(30.00);
		newProduct.getCategory().setId(3);
		
		Product returnedProduct = testProductPostgres.add(newProduct); 
		
		Assertions.assertEquals(30.00, returnedProduct.getPrice()); 
		Assertions.assertEquals("Step Above", returnedProduct.getName());
		Assertions.assertEquals("Pegs", returnedProduct.getCategory().getName());
	}
}
