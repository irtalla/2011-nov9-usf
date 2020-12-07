package dev.elliman.services;

import java.util.List;
import java.util.Set;

import dev.elliman.beans.Claim;
import dev.elliman.beans.Person;
import dev.elliman.data.ClaimDAO;
import dev.elliman.data.ClaimDAOFactory;

public class ClaimServiceImpl implements ClaimService {
	private ClaimDAO claimDAO;
	
	public ClaimServiceImpl() {
		claimDAO = ClaimDAOFactory.getClaimDAO();
	}

	@Override
	public List<Claim> getClaimsByPerson(Person person) {
		return claimDAO.getClaimsByPerson(person);
	}

	@Override
	public Claim makeClaim(Claim claim) {
		return claimDAO.makeClaim(claim);
	}

}
