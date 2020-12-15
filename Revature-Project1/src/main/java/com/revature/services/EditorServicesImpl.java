package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.revature.beans.Editor;
import com.revature.beans.FurtherInfoRequests;
import com.revature.beans.Genre;
import com.revature.beans.GenreCommitteeMember;
import com.revature.beans.Stage;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.beans.Work;
import com.revature.dao.ApprovalDAO;
import com.revature.dao.ApprovalHibernate;
import com.revature.dao.ApprovalProofreadDAO;
import com.revature.dao.ApprovalProofreadHibernate;
import com.revature.dao.FinalViewersDAO;
import com.revature.dao.FinalViewersHibernate;
import com.revature.dao.FurtherInfoRequestsDAO;
import com.revature.dao.FurtherInfoRequestsHibernate;
import com.revature.dao.GenreCommitteeMemberDAO;
import com.revature.dao.GenreCommitteeMemberHibernate;
import com.revature.dao.StoryPitchDAO;
import com.revature.dao.StoryPitchHibernate;

public class EditorServicesImpl implements EditorFunctions {
	
	//private ApprovalDAO approvalDAO = new ApprovalHibernate();
	private StoryPitchDAO spDAO = new StoryPitchHibernate();
	private GenreCommitteeMemberDAO gcmDAO = new GenreCommitteeMemberHibernate();
	private FurtherInfoRequestsDAO firDAO = new FurtherInfoRequestsHibernate();
	private ApprovalProofreadDAO apDAO = new ApprovalProofreadHibernate();
	private FinalViewersDAO fvDAO = new FinalViewersHibernate();

	@Override
	public void rejectAPitch(StoryPitch rejectedStoryPitch) {
		spDAO.rejectAPitch(rejectedStoryPitch);
		apDAO.removeProofreaders(rejectedStoryPitch.getId());
		fvDAO.removeViewers(rejectedStoryPitch.getId());
	}

	@Override
	public void acceptAPitch(StoryPitch acceptedStoryPitch) {
		Stage currentStage = acceptedStoryPitch.getStage();
		Status currentStatus = acceptedStoryPitch.getStatus();
		int stageNumber = acceptedStoryPitch.getStage().getId();
		
		Work work = acceptedStoryPitch.getProposedWork();
		Genre genre = work.getGenre();
		
		switch(stageNumber) {
			case 1:
				currentStage.setId(2);
				currentStage.setStageName("general editor");
				currentStatus.setId(1);
				currentStatus.setStatus("pending");
				
				Editor editor2 = gcmDAO.assignEditorNotInGenre(work);
				acceptedStoryPitch.setAssignedEditor(editor2);
				acceptedStoryPitch.setSecondStageChecker(editor2);
				break;
			case 2:
				currentStage.setId(3);
				currentStage.setStageName("senior editor");
				currentStatus.setId(1);
				currentStatus.setStatus("pending");
				
				Editor editor3 = gcmDAO.assignSeniorEditor(work);
				acceptedStoryPitch.setAssignedEditor(editor3);
				break;
			case 3:
				currentStage.setId(4);
				currentStage.setStageName("final check");
				currentStatus.setId(1);
				currentStatus.setStatus("pending");
				
				List<GenreCommitteeMember> gcm = gcmDAO.getAllEditorsInAGenre(work);
				List<Editor> allPossibleEditorsToCheckWork = new ArrayList<Editor>();
				List<Editor> thoseWhoCanView = new ArrayList<Editor>();
				
				String lengthOfWorkName = work.getLengthOfWork().getName();
				
				if (lengthOfWorkName.equals("article")) {
					for(GenreCommitteeMember member: gcm) {
						if (member.getEditorType().equals("senior editor")) {
							allPossibleEditorsToCheckWork.add(member.getEditor());
							break;
						}
					}
				}
				else if (lengthOfWorkName.equals("short story")) {
					boolean oneAssistant = false;
					boolean oneSenior = false;
					
					for(GenreCommitteeMember member: gcm) {
						if (member.getEditorType().equals("senior editor")) {
							allPossibleEditorsToCheckWork.add(member.getEditor());
							oneSenior = true;
						}
						else {
							allPossibleEditorsToCheckWork.add(member.getEditor());
							oneAssistant = true;
						}
					}
				}
				else {
					for(GenreCommitteeMember member: gcm) {
						allPossibleEditorsToCheckWork.add(member.getEditor());
					}
				}
				
				for(GenreCommitteeMember member: gcm) {
					thoseWhoCanView.add(member.getEditor());
				}
				
				apDAO.addProofreaders(allPossibleEditorsToCheckWork, acceptedStoryPitch);
				fvDAO.addViewers(thoseWhoCanView, acceptedStoryPitch);
				break;
			case 4:
				currentStatus.setId(6);
				currentStatus.setStatus("approved");
				apDAO.removeProofreaders(acceptedStoryPitch.getId());
				fvDAO.removeViewers(acceptedStoryPitch.getId());
				break;
				
		}
		
		
		spDAO.acceptAPitch(acceptedStoryPitch);
	}

	@Override
	public Set<FurtherInfoRequests> getRequestedInfo(int approvalID, int approvalStage) {
		return firDAO.getFurtherInfo(approvalID, approvalStage);
	}

	@Override
	public Set<StoryPitch> getPitchesByEditorApproval(int editorID) {
		return spDAO.retrievePitchesByEditorApproval(editorID);
	}

}
