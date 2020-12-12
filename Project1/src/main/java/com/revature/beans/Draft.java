package com.revature.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="draft")
public class Draft {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(mappedBy="draft")
	private Pitch pitch;
	
	@Column(name="narrative")
	private String narrative;
	
	@OneToMany
	@JoinColumn(name="draft_id")
	private Set<DraftFeedback> draftFeedbackGiven;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pitch getPitch() {
		return pitch;
	}

	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}

	public String getNarrative() {
		return narrative;
	}

	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	public Set<DraftFeedback> getDraftFeedbackGiven() {
		return draftFeedbackGiven;
	}

	public void setDraftFeedbackGiven(Set<DraftFeedback> draftFeedbackGiven) {
		this.draftFeedbackGiven = draftFeedbackGiven;
	}
}
