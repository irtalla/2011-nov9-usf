package dev.rev.data;

import java.util.Set;

import dev.rev.beans.event;

public interface eventDAO  {
	
	public Set<event> getallevent();
	public event getbyId(int id);
}
