package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Draft;
import com.revature.beans.DraftFeedback;
import com.revature.beans.Pitch;

public class PersonPostgres extends PostgresDao<T> {

	public Set<Draft> getDrafts(){
		Set<Draft> drafts = new HashSet<>();
		for(Pitch pitch : this.pitches) {
			Draft draft = pitch.getDraft();
			if(draft != null) 
				drafts.add(draft);
		}
		
		return drafts;
	}
	
//	public Set<DraftFeedback> getFeedbackForDrafts(){
//		Set<DraftFeedback> allFeedback = new HashSet<>();
//		for(Pitch pitch : this.pitches) {
//			Draft draft = pitch.getDraft();
//			if(draft != null)
//			drafts.add(pitch.getDraft());
//		}
//		
//		return allFeedback;
//	}
	
	public Set<DraftFeedback> getFeedbackForDraft(){
		Set<DraftFeedback> allFeedback = new HashSet<>();
		for(Pitch pitch : this.pitches) {
			Draft draft = pitch.getDraft();
			if(draft != null)
			drafts.add(pitch.getDraft());
		}
		
		return allFeedback;
	}
}
