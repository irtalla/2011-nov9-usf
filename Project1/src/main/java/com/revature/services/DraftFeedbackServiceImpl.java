package com.revature.services;

import com.revature.beans.Draft;
import com.revature.beans.DraftFeedback;
import com.revature.beans.GenreCommittee;
import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.beans.StoryType;
import com.revature.data.DraftDAO;
import com.revature.data.DraftDAOFactory;
import com.revature.data.DraftFeedbackDAO;
import com.revature.data.DraftFeedbackDAOFactory;
import com.revature.data.GenreCommitteeDAO;
import com.revature.data.GenreCommitteeDAOFactory;

public class DraftFeedbackServiceImpl extends GenericServiceImpl<DraftFeedback> implements DraftFeedbackService{
	private GenreCommitteeServiceImpl gcServ;
	private DraftServiceImpl draftServ;
	
	public DraftFeedbackServiceImpl() {
		super(new DraftFeedbackDAOFactory());
		gcServ = new GenreCommitteeServiceImpl();
		draftServ = new DraftServiceImpl();
	}
	
	@Override
	public DraftFeedbackDAO getDao() {
		return (DraftFeedbackDAO) this.dao;
	}
	
	@Override
	public DraftFeedback add(DraftFeedback df) {
		df = getDao().add(df);
		
		if(df.getStatus().equals(Status.APPROVED)) {
			this.approveDraftVia(df);
		}
		else if(df.getStatus().equals(Status.DENIED)) {
			this.denyDraftVia(df);
		}
		return df;
	}

	public void approveDraftVia(DraftFeedback df) {
		Draft draft = df.getDraft();
		Person editor = df.getEditor();
		
		int totalApprovals = draft.getApprovals().size();
		
		totalApprovals += 1;
		GenreCommittee gc = gcServ.getByGenre(draft.getGenre());
		StoryType st = draft.getStoryType();
		boolean fullyApproved = false;
		switch(st) {
			case ARTICLE:
				fullyApproved = editor.getRole().equals(Role.SENIOR_EDITOR);
				break;
			case SHORT_STORY:
				boolean alreadyApprovedBySeniorEditor = draft.getHasBeenApprovedBySeniorEditors();
				int priorApprovalTotal = draft.getApprovals().size();
				if(alreadyApprovedBySeniorEditor) {
					if(priorApprovalTotal >= 1) {
						fullyApproved = true;
					}
				}else {
					if(priorApprovalTotal >= 1 && editor.getRole().equals(Role.SENIOR_EDITOR)) {
						fullyApproved = true;
					}
				}
				break;
			case NOVELLA:
			case NOVEL:
				int totalApprovalsNeeded = (int) Math.ceil(gc.getMembers().size()/2);
				fullyApproved = totalApprovals >= totalApprovalsNeeded;
				//fall-through, meaning same procedure for novella
				break;	
			default:
				System.out.println(st + "is not a valid type for StoryType.");
				break;
		}
		if(fullyApproved) {
			draft.setStatus(Status.APPROVED);
			draftServ.update(draft);
		}
	}

	public void denyDraftVia(DraftFeedback df) {
		Draft draft = df.getDraft();
		Person editor = df.getEditor();
		StoryType st = draft.getStoryType();
		GenreCommittee gc = gcServ.getByGenre(draft.getGenre());
		
		boolean fullyDenied = false;
		int totalDenials= draft.getDenials().size();
		
		totalDenials += 1;
		
		
		switch(st) {
			case ARTICLE:
				fullyDenied= editor.getRole().equals(Role.SENIOR_EDITOR);
				break;
			case SHORT_STORY:
				boolean alreadyDeniedBySeniorEditor = draft.getHasBeenDeniedBySeniorEditor();
				int priorDenialTotal = draft.getDenials().size();
				
				if(alreadyDeniedBySeniorEditor) {
					if(priorDenialTotal >= 1) {
						fullyDenied = true;
					}
				}else {
					if(priorDenialTotal >= 1 && editor.getRole().equals(Role.SENIOR_EDITOR)) {
						fullyDenied = true;
					}
				}
				break;
			case NOVELLA:
			case NOVEL:
				int totalDenialsNeeded = (int) Math.ceil(gc.getMembers().size()/2);
				fullyDenied = totalDenials >= totalDenialsNeeded;
				//fall-through, meaning same procedure for novella
				break;	
			default:
				System.out.println(st + "is not a valid type for StoryType.");
				break;
		}
		if(fullyDenied) {
			draft.setStatus(Status.DENIED);
			this.draftServ.update(draft);
			
		}
	}
}
