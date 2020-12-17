package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="draft")
public class Draft {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
//	@OneToOne(fetch=FetchType.EAGER, mappedBy="draft")
	@Transient
	private Pitch pitch;
	
	@Column(name="pitch_id")
	private Integer pitchId;
	
	@Column(name="narrative")
	private String narrative;
	
//	@OneToMany(fetch=FetchType.EAGER, mappedBy="draft")
	@Transient
	private Set<DraftFeedback> feedback;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Transient
	public Pitch getPitch() {
		return pitch;
	}
	//has one through:
	@Transient
	public Genre getGenre() {
		return this.pitch.getGenre();
	}
	
	@Transient
	public StoryType getStoryType() {
		return this.pitch.getStoryType();
	}
	
	@Transient
	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}

	public Integer getPitchId() {
		return pitchId;
	}

	public void setPitchId(Integer pitchId) {
		this.pitchId = pitchId;
	}
	
	@Transient
	public String getNarrative() {
		return narrative;
	}
	@Transient
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	@Transient
	public Set<DraftFeedback> getFeedback() {
		return feedback;
	}

	@Transient
	public void setFeedback(Set<DraftFeedback> feedback) {
		this.feedback = feedback;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	@Transient
	public Set<DraftFeedback> getApprovals(){
		Set<DraftFeedback> approvals = new HashSet<>();
		for(DraftFeedback df : this.feedback) {
			if(df.getStatus().equals(Status.APPROVED)) {
				approvals.add(df);
			}
		}
		
		return approvals;
	}
	@Transient
	public Set<DraftFeedback> getDenials(){
		Set<DraftFeedback> denials = new HashSet<>();
		for(DraftFeedback df : this.feedback) {
			if(df.getStatus().equals(Status.DENIED)) {
				denials.add(df);
			}
		}
		
		return denials;
	}
	@Transient
	public boolean getHasBeenApprovedBySeniorEditors(){
		for(DraftFeedback df : this.getApprovals()) {
			if(df.getEditor().getRole().equals(Role.SENIOR_EDITOR)) {
				return true;
			}
		}
		
		return false;
	}
	@Transient
	public boolean getHasBeenDeniedBySeniorEditor() {
		for(DraftFeedback df : this.getDenials()) {
			if(df.getEditor().getRole().equals(Role.SENIOR_EDITOR)) {
				return true;
			}
		}
		
		return false;
	}
}
