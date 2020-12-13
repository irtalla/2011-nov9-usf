package dev.rev.services;

import java.util.Set;

import dev.rev.beans.event;

public interface eventservice {
	
	public Set<event> getallevent();
	public event getbyid(int id);
	

}
