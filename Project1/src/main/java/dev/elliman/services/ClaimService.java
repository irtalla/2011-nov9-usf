package dev.elliman.services;

import java.util.List;
import java.util.Set;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Event;
import dev.elliman.beans.Person;

public interface ClaimService {
	
	//read
	public List<Claim> getClaimsByPerson(Person person);
	public List<Claim> getDSUnapprovedClaims();
	public List<Claim> getDHUnapprovedClaims();
	public List<Claim> getBCUnapprovedClaims();
	public Claim getClaimByID(Integer id);
	
	//related read
	public List<Event> getEventTypes();
	
	//write
	public Claim makeClaim(Claim claim);
	public boolean accept(Claim claim);
}
