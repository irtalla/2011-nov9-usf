package services;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import beans.Committee;
import beans.Genre;
import beans.Role;
import beans.Usr;
import beans.Story_type;
import exceptions.NonUniqueUsernameException;

public interface UsrService {

	public Set<Genre> getGenres();
	public Usr getUsrbyUsrname(String username);
	public Integer addUsr(Usr newUsr) throws NonUniqueUsernameException;
	public Usr getUsrById(Integer id);
	public void updateUsr(Usr u);
	public void deleteUsr(Usr u);
	public Set<Usr> getUsrsByCommittee(Committee c);
	public Set<Role> getRoles();
	public Set<Story_type> getTypes();
}
