package com.revature.services;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Author;
import com.revature.beans.Editor;
import com.revature.beans.FurtherInfoRequests;
import com.revature.beans.Genre;
import com.revature.beans.LengthOfWork;
import com.revature.beans.StoryPitch;
import com.revature.beans.Work;
import com.revature.dao.ApprovalDAO;
import com.revature.dao.ApprovalHibernate;
import com.revature.dao.FurtherInfoRequestsDAO;
import com.revature.dao.FurtherInfoRequestsHibernate;
import com.revature.dao.GenreCommitteeMemberDAO;
import com.revature.dao.GenreCommitteeMemberHibernate;
import com.revature.dao.GenreDAO;
import com.revature.dao.GenreHibernate;
import com.revature.dao.LengthOfWorkDAO;
import com.revature.dao.LengthOfWorkHibernate;
import com.revature.dao.ProposedWorkDAO;
import com.revature.dao.ProposedWorkHibernate;
import com.revature.dao.StoryPitchDAO;
import com.revature.dao.StoryPitchHibernate;

public class AuthorServicesImpl implements AuthorFunctions {
	//private static Set<Genre> genresFound = new HashSet<Genre>();
	//private static Set<LengthOfWork> lengthsOfWorkFound = new HashSet<LengthOfWork>();
	
	
	//private GenreDAO genreDAO = new GenreHibernate();
	//private LengthOfWorkDAO lengthOfWorkDAO = new LengthOfWorkHibernate();
	//private 
	private ProposedWorkDAO proposedWorkDAO = new ProposedWorkHibernate();
	private StoryPitchDAO storyPitchDAO = new StoryPitchHibernate();
	private GenreCommitteeMemberDAO gcmDAO = new GenreCommitteeMemberHibernate();
	private FurtherInfoRequestsDAO firDAO = new FurtherInfoRequestsHibernate();
	private ApprovalDAO approvalDAO = new ApprovalHibernate();

	@Override
	public StoryPitch addProposedWork(StoryPitch storyPitch) {
		Work work = storyPitch.getProposedWork();
		Genre genre = work.getGenre();
		Editor assistantEditor = gcmDAO.assignAnAssistantEditor(work);
		storyPitch.setAssignedEditor(assistantEditor);
		Date date = new Date(new java.util.Date().getTime());
		storyPitch.setDateWhenStageStarted(date);
		
		proposedWorkDAO.addProposedWork(storyPitch.getProposedWork());
		storyPitchDAO.addStoryPitch(storyPitch);

		return storyPitch;
	}

	@Override
	public Set<Work> getAllOfAuthorsWorks(Author author) {
		int authorId = author.getAuthorId();
		return proposedWorkDAO.getAllWorksOfAuthor(authorId);
		
		//return null;
	}

	@Override
	public void addRequestedInfo(FurtherInfoRequests furtherInfoRequests) {
		firDAO.addFurtherInfoToAPitch(furtherInfoRequests);		
	}

	@Override
	public void authorRemoveProposedWork(int userID, int workID) {
		StoryPitch removedPitch = storyPitchDAO.retrieveAPitch(userID, workID);
		storyPitchDAO.rejectAPitch(removedPitch);
	}
	
	

}
