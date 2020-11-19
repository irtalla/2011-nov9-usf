package dev.elliman.data;

import java.util.Set;

import dev.elliman.beans.Item;

public interface ItemDAO {
	
	public Integer add(Item p);
	
	public Item getByID(Integer id);
	
	public Set<Item> getAll();
	
	public void update(Item p);
	
	public void delete(Item p);
}
