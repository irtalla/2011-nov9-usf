package servicespackage.Author;

import data.AuthorDao;
import data.Author_DAOFactory;
import models.Author;

public class Author_ServiceImpl implements Author_Service{
	private AuthorDao adao;
	public Author_ServiceImpl() {
		Author_DAOFactory afactory = new Author_DAOFactory();
		adao = afactory.getApprovalOtherDao();
	}
	public Author getAuthorById(Integer id) {
		// TODO Auto-generated method stub
		return adao.getAuthorById(id);
	}

	public Author getByUserId(Integer i) {
		// TODO Auto-generated method stub
		return adao.getByUserId(i);
	}

	public Author add(Author e) {
		// TODO Auto-generated method stub
		return adao.add(e);
	}

	public void updateAuthor(Author e) {
		// TODO Auto-generated method stub
		adao.updateAuthor(e);
	}

}
