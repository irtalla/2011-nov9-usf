package com.revature.services;

import java.util.List;
import java.util.Set;

import com.revature.beans.Offer;
import com.revature.beans.Product;
import com.revature.beans.Person;
import com.revature.data.ProductDAO;
import com.revature.data.ProductDAOFactory;
import com.revature.data.PersonDAO;

public class ProductServiceImpl implements ProductService {
	private ProductDAO productDao;
	private PersonDAO userDao;
	
	
	
	public ProductServiceImpl() {
		ProductDAOFactory productDaoFactory = new ProductDAOFactory();
		try {
			productDao = productDaoFactory.getProductDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		
		Set<Product> products = productDao.getAll(); 
		
		try {
			products.removeIf( p -> p.getOwerId() != customerId );
			return products; 
		} catch (NullPointerException e) {
			System.out.println(e);
			return products; 
		}
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
		
		c.setOwnerId(p.getId());
		
		p.getOwnProducts().add(c); 
		
		this.productDao.update(c);
		
	}



	@Override
	public void removeProduct(Integer productId) {
		this.productDao.delete( this.productDao.getById(productId) );
	}



	@Override
	public void addOfferForProduct(Integer customerId, Integer productId, Double offerPrice) {
		
		Offer newOffer = new Offer(); 
		newOffer.setProductId(productId);
		newOffer.setCustomerId(customerId);
		newOffer.setAmount(offerPrice);
		
		Product product = this.productDao.getById(productId); 
		Set<Offer> offers = product.getOffers();
		offers.add(newOffer); 
		product.setOffers(offers);
		
		// TODO update offers collection. product service must call offer service... 
	}



	@Override
	public void getRemainingPaymentsForProduct(Integer productId) {
		
		 Double price = this.productDao.getById(productId).getPrice(); 
		 Double minPayment = 150.00; 
		 
		 System.out.printf("%d payments of %f \n 1 final payment of %f",
				 price / minPayment, 
				 minPayment,
				 price % minPayment
				 ); 	
	}
}
