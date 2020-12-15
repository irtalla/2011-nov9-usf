package servicespackage.Author;

import models.Author;

public interface Author_Service {
	public Author getAuthorById(Integer id);
	public Author getByUserId( Integer i);
	public Author add(Author e);
	public void updateAuthor(Author e);
}
