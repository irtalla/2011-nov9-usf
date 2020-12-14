package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.beans.Author;
import com.revature.beans.Editor;
import com.revature.beans.User;
import com.revature.utils.SessionUtil;

public class UserHibernate implements UserDAO {
	
	private SessionUtil su = SessionUtil.getHibernateUtil();

	@Override
	public int registerAUser(String username, String password) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeAUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object verifyAUser(String username, String password) {
		Session s = su.getSession();
		
		String query = "FROM User where username = :username and password = :password";
		Query<User> q = s.createQuery(query, User.class);
		q.setParameter("username", username);
		q.setParameter("password", password);
		List<User> userList = q.getResultList();
		
		if (userList.size() == 1) {	//yes, a cheap and hacky way to ensure there are no duplicates
									//but it works.
			User onlyUser = userList.get(0);
			int userId = onlyUser.getId();
			
			String roleQueryAuthor = "FROM Author where id = :id";
			String roleQueryEditor = "FROM Editor where id = :id";
			Query<Author> qAuthor = s.createQuery(roleQueryAuthor, Author.class);
			Query<Editor> qEditor = s.createQuery(roleQueryEditor, Editor.class);
			qAuthor.setParameter("id", userId);
			qEditor.setParameter("id", userId);
			
			//note: this is actually secure, because the assumption is that
			//a User is an author or an editor, and will be guaranteed
			//to have an associated author or editor
			
			List<Author> authorList = qAuthor.getResultList();
			if (authorList.size() == 1) {
				s.close();
				return authorList.get(0);
			}
			
			List<Editor> editorList = qEditor.getResultList();	
			if (editorList.size() == 1) {
				s.close();
				return editorList.get(0);
			}
			
		}
		
		s.close();
		return null;
	}
	
}
