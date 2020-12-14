package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.beans.Genre;
import com.revature.beans.User;
import com.revature.utils.SessionUtil;

public class GenreHibernate implements GenreDAO {
	
	private SessionUtil su = SessionUtil.getHibernateUtil();

	@Override
	public Genre getGenreByName(String name) {
		Session s = su.getSession();
		
		String query = "FROM Genre where genreName = :genre";
		Query<Genre> q = s.createQuery(query, Genre.class);
		q.setParameter("genre", name);
		List<Genre> userList = q.getResultList();
		s.close();
		
		//assumption: there is only one genre to a name.
		//barring any strange errors, this is true.
		if (userList.size() > 0) {
			return userList.get(0);
		}
		
		return null;
	}

}
