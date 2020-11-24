package com.revature.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Product;
import com.revature.beans.Purchase;
import com.revature.beans.Person;
import com.revature.data.ProductDAO;
import com.revature.data.ProductDAOFactory;
import com.revature.data.PurchaseDAO;
import com.revature.data.PurchasePostgres;
import com.revature.data.PersonDAO;

public class ProductServiceImpl implements ProductService {
	private ProductDAO productDao;
	private PersonDAO personDao;
	private PurchaseDAO purchaseDao; 
	
	
	public ProductServiceImpl() {
		ProductDAOFactory productDaoFactory = new ProductDAOFactory();
	
		try {
			productDao = productDaoFactory.getProductDAO();
			purchaseDao = new PurchasePostgres(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public Product addProduct(Product c) {
		this.productDao.add(c); 
		return this.productDao.getById(c.getId()); 
	}



	@Override
	public Product getProductById(Integer Id) {
		return this.productDao.getById(Id); 
	}



	@Override
	public Set<Product> getProductsByOwnerId(Integer customerId) {
		
		Set<Purchase> purchases = purchaseDao.getPurchasesByCustomerId(customerId);
		Set<Product> products = new HashSet<Product>(); 
		purchases.forEach( purchase -> products.add( productDao.getById(purchase.getProductId()) ) );
		return products; 
	}



	@Override
	public Set<Product> getProducts() {
		return this.productDao.getAll(); 
	}



	@Override
	public Set<Product> getAvailableProducts() {
		return this.productDao.getAvailableProducts(); 
	}



	@Override
	public void updateProduct(Product c) {
		this.productDao.update(c);
		
	}



	@Override
	public void setOwnerForProduct(Person p, Product c) {	
	}



	@Override
	public void removeProduct(Integer productId) {
		this.productDao.delete( this.productDao.getById(productId) );
	}


	@Override
	public void getRemainingPayments(Integer customerId) {
		
		Set<Product> myProducts = getProductsByOwnerId(customerId); 
		myProducts.forEach( product -> displayPayments(product));
	}
	
	private void displayPayments(Product product) {
 
		 Double minPayment = 150.00; 
	
		 System.out.printf("We hope you enjoy your new product: %s!\n"
		 		+ "We at Generic Product Inc. are happy to provide you "
		 		+ "with the following finance option:\n"
		 		+ "%d payments of %f\n 1 final payment of %f.\n",
		 		product.getName(),
				Math.floor( product.getPrice() / minPayment ), 
				minPayment,
				product.getPrice()% minPayment
				 ); 	
	}
}
