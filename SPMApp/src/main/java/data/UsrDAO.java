package data;
import java.util.Set;

import beans.Story_type;
import beans.Committee;
import beans.Genre;
import beans.Usr;
import exceptions.NonUniqueUsernameException;

public interface UsrDAO extends GenericDAO<Usr> {
	public Usr add(Usr u) throws NonUniqueUsernameException ;
	public Usr getByUsrname(String username);
	public Set<Story_type> getTypes();	public Set<Usr> getUsrsByCommittee(Committee c);
	public Set<Genre> getGenres();
}
