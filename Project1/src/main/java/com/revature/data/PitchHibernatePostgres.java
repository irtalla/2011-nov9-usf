package com.revature.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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
		Pitch p = null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			p = s.get(Pitch.class, id);
			p.setPriority(checkPriority(p));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public Set<Pitch> getByAuthor(User author) {
		Set<Pitch> pitches = null;
		
		if (author == null)  return null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "From Pitch where user_id = :author_id";
			Query<Pitch> q = s.createQuery(hql, Pitch.class);
			q.setParameter("author_id", author.getId());
			List<Pitch> resultList = q.getResultList();
			if (resultList.size() > 0) {
				pitches = new HashSet<Pitch>(resultList);
				for (Pitch p : pitches) {
					p.setPriority(checkPriority(p));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pitches;
	}

	@Override
	public Set<Pitch> getByGenre(Genre genre, Boolean withinGenre) {
		Set<Pitch> pitches = null;
		
		if (genre == null)  return null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql;
			if (withinGenre) {
				hql = "from Pitch where genre_id = :genre_id";
			} else {
				hql = "from Pitch where genre_id <> :genre_id";
			}
			Query<Pitch> q = s.createQuery(hql, Pitch.class);
			q.setParameter("genre_id", genre.getId());
			List<Pitch> resultList = q.getResultList();
			if (resultList.size() > 0) {
				pitches = new HashSet<Pitch>(resultList);
				for (Pitch p : pitches) {
					p.setPriority(checkPriority(p));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pitches;
	}

	@Override
	public Set<Pitch> getByStoryType(StoryType type) {
		Set<Pitch> pitches = null;
		
		if (type == null)  return null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "From Pitch where type_id = :type_id";
			Query<Pitch> q = s.createQuery(hql, Pitch.class);
			q.setParameter("type_id", type.getId());
			List<Pitch> resultList = q.getResultList();
			if (resultList.size() > 0) {
				pitches = new HashSet<Pitch>(resultList);
				for (Pitch p : pitches) {
					p.setPriority(checkPriority(p));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pitches;
	}

	@Override
	public Set<Pitch> getByPitchStage(PitchStage stage) {
		Set<Pitch> pitches = null;
		
		if (stage == null)  return null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "From Pitch where stage_id = :stage_id";
			Query<Pitch> q = s.createQuery(hql, Pitch.class);
			q.setParameter("stage_id", stage.getId());
			List<Pitch> resultList = q.getResultList();
			if (resultList.size() > 0) {
				pitches = new HashSet<Pitch>(resultList);
				for (Pitch p : pitches) {
					p.setPriority(checkPriority(p));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pitches;
	}

	@Override
	public Set<Pitch> getByReviewStatus(ReviewStatus status) {
		Set<Pitch> pitches = null;
		
		if (status == null)  return null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "From Pitch where status_id = :status_id";
			Query<Pitch> q = s.createQuery(hql, Pitch.class);
			q.setParameter("status_id", status.getId());
			List<Pitch> resultList = q.getResultList();
			if (resultList.size() > 0) {
				pitches = new HashSet<Pitch>(resultList);
				for (Pitch p : pitches) {
					p.setPriority(checkPriority(p));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pitches;
	}

	@Override
	public Set<Pitch> getByPriority(Priority priority) {
		Set<Pitch> pitches = this.getAll();
		pitches.removeIf( p -> p.getPriority() != priority);
		return pitches;
	}
	@Override
	public Set<Pitch> getAll() {
		Set<Pitch> pitches = null;
		
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			String hql = "From Pitch";
			Query<Pitch> q = s.createQuery(hql, Pitch.class);
			List<Pitch> resultList = q.getResultList();
			if (resultList.size() > 0) {
				pitches = new HashSet<Pitch>(resultList);
				for (Pitch p : pitches) {
					p.setPriority(checkPriority(p));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pitches;
	}

	@Override
	public void update(Pitch t) throws Exception {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.update(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Pitch t) {
		try (Session s = sessionFactory.getCurrentSession()) {
			s.beginTransaction();
			s.delete(t);
			s.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Priority checkPriority(Pitch t) {
		Priority p = Priority.NORMAL;
		LocalDate prioritySwitch = t.getPitchMadeAt().toLocalDate().plusDays(5);
		LocalDate now = LocalDate.now();
		if (now.isEqual(prioritySwitch) || now.isAfter(prioritySwitch)) {
			p = Priority.HIGH;
		}
		
		return p;
	}
}
