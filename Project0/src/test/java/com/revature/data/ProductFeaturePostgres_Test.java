package com.revature.data;

import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.revature.beans.Feature;


public class ProductFeaturePostgres_Test {
	
	@DisplayName("Test getFeaturesByProductIds()") 
	@Test
	public void getFeaturesByProductId() {
		
		ProductFeaturePostgres testProductFeaturePostgres = new ProductFeaturePostgres();
		
		Set<Feature> featuresSet = testProductFeaturePostgres.getFeaturesByProductId(4); 
		Set<Feature> emptyFeaturesSet = testProductFeaturePostgres.getFeaturesByProductId(-1);

		Assertions.assertTrue( featuresSet.size() > 0);
		Assertions.assertTrue( emptyFeaturesSet.size() == 0);
	}
}
