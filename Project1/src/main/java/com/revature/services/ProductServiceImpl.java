package com.revature.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.data.ProductDAO;
import com.revature.data.ProductDAOFactory;
import com.revature.data.PurchaseDAO;
import com.revature.data.PurchasePostgres;
import com.cross.beans.Offer;
import com.cross.beans.Person;
import com.cross.beans.Pitch;
import com.cross.beans.Purchase;
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
	public Pitch addProduct(Pitch c) {
		this.productDao.add(c); 
		return this.productDao.getById(c.getId()); 
	}



	@Override
	public Pitch getProductById(Integer Id) {
		return this.productDao.getById(Id); 
	}



	@Override
	public Set<Pitch> getProductsByOwnerId(Integer customerId) {
		
		Set<Purchase> purchases = purchaseDao.getPurchasesByCustomerId(customerId);
		Set<Pitch> products = new HashSet<Pitch>(); 
		purchases.forEach( purchase -> products.add( productDao.getById(purchase.getProductId()) ) );
		return products; 
	}



	@Override
	public Set<Pitch> getProducts() {
		return this.productDao.getAll(); 
	}



	@Override
	public Set<Pitch> getAvailableProducts() {
		return this.productDao.getAvailableProducts(); 
	}



	@Override
	public void updateProduct(Pitch c) {
		this.productDao.update(c);
		
	}


	@Override
	public void removeProduct(Integer productId) {
		this.productDao.delete( this.productDao.getById(productId) );
	}


	@Override
	public void getRemainingPayments(Integer customerId) {
		
		Set<Pitch> myProducts = getProductsByOwnerId(customerId); 
		
		if ( myProducts.size() > 0 ) {
			myProducts.forEach( product -> displayPayments(product));
		} else {
			System.out.printf("No payment information to display\n"); 
		}
		
	}
	
	private void displayPayments(Pitch product) {
 
		 Double minPayment = 150.00; 
	
		 System.out.printf("We hope you enjoy your new product: %s!\n"
		 		+ "We at Generic Product Inc. are happy to provide you "
		 		+ "with the following finance option:\n"
		 		+ "%f payments of %f\n 1 final payment of %f.\n",
		 		product.getName(),
				Math.floor( product.getPrice() / minPayment ), 
				minPayment,
				product.getPrice()% minPayment
				 ); 	
	}



	@Override
	public Set<Pitch> getProductByCategory(String userChoice) {
		Set<Pitch> products = getProducts(); 
		products.removeIf( product -> ! product.getCategory().getName().equalsIgnoreCase(userChoice) );
		return products;
	}
}
