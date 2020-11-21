package com.revature.data;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.revature.beans.Feature;
import com.revature.beans.Offer;

public class ProductFeaturePostgres_Test {
	
	@DisplayName("Test getFeaturesByProductIds()") 
	@Test
	public void getFeaturesByProductId() {
		
		ProductFeaturePostgres testProductFeaturePostgres = new ProductFeaturePostgres();
		
		Set<Feature> featuresSet = testProductFeaturePostgres.getFeaturesByProductId(1); 
		Set<Feature> emptyFeaturesSet = testProductFeaturePostgres.getFeaturesByProductId(-1);

		Assertions.assertTrue( featuresSet.size() > 0);
		Assertions.assertTrue( emptyFeaturesSet.size() == 0);
	}
	
//	@DisplayName("Test delete()") 
//	@Test
//	public void deleteTest() {
//		
//		ProductFeaturePostgres testProductFeaturePostgres = new ProductFeaturePostgres();
//		Feature featureToDelete = new Feature(); 
//		featureToDelete.set
//		
//		offerToDelete.setId(1);
//		boolean didDelete = testOfferPostgres.update(offerToDelete);
//		Assertions.assertEquals(true, didDelete );
//	}
	
	

}
