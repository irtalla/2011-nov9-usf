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
	private GenreCommitteeDAO gcDao;
	private DraftDAO draftDao;
	
	public DraftFeedbackServiceImpl() {
		super(new DraftFeedbackDAOFactory());
		gcDao = new GenreCommitteeDAOFactory().getDAO();
		draftDao = new DraftDAOFactory().getDAO();
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
		return df;
	}

	private void approveDraftVia(DraftFeedback df) {
		Draft draft = df.getDraft();
		Person editor = df.getEditor();
		
		
		int totalApprovals = draft.getApprovals().size();
		
		totalApprovals += 1;
		GenreCommittee gc = gcDao.getByGenre(draft.getGenre());
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
			draftDao.update(draft);
		}
	}

//	private void denyDraftVia(DraftFeedback df) {
//		
//	}
}
