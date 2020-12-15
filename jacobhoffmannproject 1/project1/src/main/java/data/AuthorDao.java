package data;

import models.Author;
import models.Employee;

public interface AuthorDao {
	public Author getAuthorById(Integer id);
	public Author getByUserId( Integer i);
	public Author add(Author e);
	public void updateAuthor(Author e);
}
