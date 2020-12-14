package com.revature.dao;

import java.io.Closeable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.beans.Editor;
import com.revature.beans.Genre;
import com.revature.beans.GenreCommittee;
import com.revature.beans.GenreCommitteeMember;
import com.revature.beans.Work;
import com.revature.utils.SessionUtil;

public class GenreCommitteeMemberHibernate implements GenreCommitteeMemberDAO {
	
	private SessionUtil su = SessionUtil.getHibernateUtil();
	
	private static Set<GenreCommittee> allKnownGenreCommittees = new HashSet<GenreCommittee>();

	@Override
	public Editor assignAnAssistantEditor(Work w) {
		Genre g = w.getGenre();
		
		Session s = su.getSession();
		String genreCommitteeQuery = "FROM GenreCommittee where genre_id=:id";
		String workQuery = "FROM GenreCommitteeMember where genre_committee_id=:id and editor_position='assistant editor'";
		
		Query<GenreCommittee> qGenreCommittee = s.createQuery(genreCommitteeQuery, GenreCommittee.class);
		qGenreCommittee.setParameter("id", g.getId());
		
		List<GenreCommittee> gcList = null;
		GenreCommittee gcObject = null;
		
		if (allKnownGenreCommittees.size() == 0) {
			gcList = qGenreCommittee.getResultList();
			if (gcList.size() > 0) {
				allKnownGenreCommittees.add(gcList.get(0));
			}
			else {
				s.close();
				return null;
			}
		}
		else {
			for (GenreCommittee gc: allKnownGenreCommittees) {
				if (gc.getGenre().equals(g)) {
					gcObject = gc;
					break;
				}
			}
			
			if (gcObject == null) {
				gcList = qGenreCommittee.getResultList();
				if (gcList.size() > 0) {
					allKnownGenreCommittees.add(gcList.get(0));
				}
				else {
					s.close();
					return null;
				}
			}
		}
		
		//This is just taking a GenreCommitteeMember after getting a Genre
		//necessary to
		List<GenreCommitteeMember> gcmList = null;
		Query<GenreCommitteeMember> qWork = s.createQuery(workQuery, GenreCommitteeMember.class);
		qWork.setParameter("id", g.getId());
		gcmList = qWork.getResultList();
		s.close();
		
		int randomization = gcmList.size();
		int chosenIndex = (int)(Math.random() * randomization);
		return gcmList.get(chosenIndex).getEditor();		
	}

	@Override
	public Editor assignEditorNotInGenre(Work w) {
		Genre g = w.getGenre();
		
		Session s = su.getSession();
		String genreCommitteeQuery = "FROM GenreCommittee where genre_id=:id";
		String workQuery = "FROM GenreCommitteeMember where genre_committee_id != :id";
		
		Query<GenreCommittee> qGenreCommittee = s.createQuery(genreCommitteeQuery, GenreCommittee.class);
		qGenreCommittee.setParameter("id", g.getId());
		
		List<GenreCommittee> gcList = null;
		GenreCommittee gcObject = null;
		
		if (allKnownGenreCommittees.size() == 0) {
			gcList = qGenreCommittee.getResultList();
			if (gcList.size() > 0) {
				allKnownGenreCommittees.add(gcList.get(0));
			}
			else {
				s.close();
				return null;
			}
		}
		else {
			for (GenreCommittee gc: allKnownGenreCommittees) {
				if (gc.getGenre().equals(g)) {
					gcObject = gc;
					break;
				}
			}
			
			if (gcObject == null) {
				gcList = qGenreCommittee.getResultList();
				if (gcList.size() > 0) {
					allKnownGenreCommittees.add(gcList.get(0));
				}
				else {
					s.close();
					return null;
				}
			}
		}
		
		//This is just taking a GenreCommitteeMember after getting a Genre
		//necessary to
		List<GenreCommitteeMember> gcmList = null;
		Query<GenreCommitteeMember> qWork = s.createQuery(workQuery, GenreCommitteeMember.class);
		qWork.setParameter("id", g.getId());
		gcmList = qWork.getResultList();
		s.close();
		
		int randomization = gcmList.size();
		int chosenIndex = (int)(Math.random() * randomization);
		return gcmList.get(chosenIndex).getEditor();
	}

	@Override
	public Editor assignSeniorEditor(Work w) {
		Genre g = w.getGenre();
		
		
		Session s = su.getSession();
		String genreCommitteeQuery = "FROM GenreCommittee where genre_id=:id";
		String workQuery = "FROM GenreCommitteeMember where genre_committee_id=:id and editor_position='senior editor'";
		
		Query<GenreCommittee> qGenreCommittee = s.createQuery(genreCommitteeQuery, GenreCommittee.class);
		qGenreCommittee.setParameter("id", g.getId());
		
		List<GenreCommittee> gcList = null;
		GenreCommittee gcObject = null;
		
		if (allKnownGenreCommittees.size() == 0) {
			gcList = qGenreCommittee.getResultList();
			if (gcList.size() > 0) {
				allKnownGenreCommittees.add(gcList.get(0));
			}
			else {
				s.close();
				return null;
			}
		}
		else {
			for (GenreCommittee gc: allKnownGenreCommittees) {
				if (gc.getGenre().equals(g)) {
					gcObject = gc;
					break;
				}
			}
			
			if (gcObject == null) {
				gcList = qGenreCommittee.getResultList();
				if (gcList.size() > 0) {
					allKnownGenreCommittees.add(gcList.get(0));
				}
				else {
					s.close();
					return null;
				}
			}
		}
		
		//This is just taking a GenreCommitteeMember after getting a Genre
		//necessary to
		List<GenreCommitteeMember> gcmList = null;
		Query<GenreCommitteeMember> qWork = s.createQuery(workQuery, GenreCommitteeMember.class);
		qWork.setParameter("id", g.getId());
		gcmList = qWork.getResultList();
		s.close();
		
		int randomization = gcmList.size();
		int chosenIndex = (int)(Math.random() * randomization);
		return gcmList.get(chosenIndex).getEditor();
	}

	@Override
	public List<GenreCommitteeMember> getAllEditorsInAGenre(Work w) {
		Genre g = w.getGenre();
		
		Session s = su.getSession();
		String genreCommitteeQuery = "FROM GenreCommittee where genre_id=:id";
		String workQuery = "FROM GenreCommitteeMember where genre_committee_id=:id";
		
		Query<GenreCommittee> qGenreCommittee = s.createQuery(genreCommitteeQuery, GenreCommittee.class);
		qGenreCommittee.setParameter("id", g.getId());
		
		List<GenreCommittee> gcList = null;
		GenreCommittee gcObject = null;
		
		if (allKnownGenreCommittees.size() == 0) {
			gcList = qGenreCommittee.getResultList();
			if (gcList.size() > 0) {
				allKnownGenreCommittees.add(gcList.get(0));
			}
			else {
				s.close();
				return null;
			}
		}
		else {
			for (GenreCommittee gc: allKnownGenreCommittees) {
				if (gc.getGenre().equals(g)) {
					gcObject = gc;
					break;
				}
			}
			
			if (gcObject == null) {
				gcList = qGenreCommittee.getResultList();
				if (gcList.size() > 0) {
					allKnownGenreCommittees.add(gcList.get(0));
				}
				else {
					s.close();
					return null;
				}
			}
		}
		
		//This is just taking a GenreCommitteeMember after getting a Genre
		//necessary to
		List<GenreCommitteeMember> gcmList = null;
		Query<GenreCommitteeMember> qWork = s.createQuery(workQuery, GenreCommitteeMember.class);
		qWork.setParameter("id", g.getId());
		gcmList = qWork.getResultList();
		s.close();
		
		int randomization = gcmList.size();
		int chosenIndex = (int)(Math.random() * randomization);
		return gcmList;
	}
	
	

}
