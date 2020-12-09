package com.revature.data;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.models.Genre;
import com.revature.models.Pitch;
import com.revature.models.PitchStage;
import com.revature.models.Priority;
import com.revature.models.ReviewStatus;
import com.revature.models.StoryType;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class PitchHibernatePostgres implements PitchDAO {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Override
	public Integer add(Pitch t) throws Exception {
		Integer newId = 0;
		
		try (Session s = sessionFactory.getCurrentSession()){
			s.beginTransaction();
			newId = (Integer) s.save(t);
			s.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newId;
	}

	@Override
	public Pitch getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Pitch> getByAuthor(User author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Pitch> getByGenre(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Pitch> getByStoryType(StoryType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Pitch> getByPitchStage(PitchStage stage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Pitch> getByReviewStatus(ReviewStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Pitch> getByPriority(Priority priority) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Pitch> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Pitch t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Pitch t) {
		// TODO Auto-generated method stub

	}

}
