package com.revature.thursday;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.basics.Bean;
import com.revature.gc.Garbage;

public class ExampleApp {

	public static void main(String[] args) {
		Chef<Bean> beanChef = new Chef<>();
		Bean b = new Bean("kidney", "red", 4, false);
		beanChef.cookFood(b);
		System.out.println(b);
		
		Chef<Garbage> gourmetChef = new Chef<>();
		Garbage g = new Garbage("fresh");
		gourmetChef.cookFood(g);
		
		Chef<Object> objectChef = new Chef<>();
		objectChef.cookFood(new ExampleApp());
		
		
		List<Bean> beanList = new ArrayList<>();
		
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "hello");
		map.put(2, "world");
		map.put(2, "!");
		
		Set<Integer> keySet = map.keySet();
		
		for (Integer i : keySet) {
			System.out.println(map.get(i));
		}
	}

}
