package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.beans.Genre;
import com.revature.utils.HibernateUtil;

public class GenreHibernate implements GenreDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Genre add(Genre t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genre getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Genre t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Genre t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Genre> getAll() {
		Session s = hu.getSession();
		String query = "FROM Genre";
		Query<Genre> q = s.createQuery(query, Genre.class);
		List<Genre> genreList = q.getResultList();
		Set<Genre> genreSet = new HashSet<>();
		genreSet.addAll(genreList);
		s.close();
		return genreSet;
	}
}
