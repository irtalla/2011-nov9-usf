package dev.rev.services;

import java.util.Set;

import dev.rev.beans.event;
import dev.rev.data.eventDAO;
import dev.rev.data.eventDAOfactory;

public class eventserviceimp implements eventservice  {
	
	private eventDAO eda;
	
	public eventserviceimp() {
		eventDAOfactory ef=new eventDAOfactory();
		eda=ef.geteventDAO();
	}
	
	@Override
	public Set<event> getallevent() {
		return eda.getallevent();
	}

	@Override
	public event getbyid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
