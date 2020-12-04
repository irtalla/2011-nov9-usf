package dev.elliman.services;

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
	public Set<Claim> getClaimsByPerson(Person person) {
		return getClaimsByPerson(person.getId());
	}

	@Override
	public Set<Claim> getClaimsByPerson(Integer id) {
		Set<Claim> claims = null;
		
		claimDAO.getClaimsByPerson(id);
		
		return claims;
	}

	@Override
	public Integer makeClaim(Claim claim) {
		return claimDAO.makeClaim(claim);
	}

}
