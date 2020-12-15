package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pitch_info_request")
public class PitchInfoRequest {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
//	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="pitch_id")
	@Transient
	private Pitch pitch;
	@Column(name="pitch_id")
	private Integer pitchId;
	
//	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="requesting_editor_id")
	@Transient
	private Person requestingEditor;
	@Column(name="requesting_editor_id")
	private Integer requestingEditorId;
	
//	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="target_person_id")
	@Transient
	private Person targetedPerson;
	@Column(name="targeted_person")
	private Integer targetedPersonId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPitchId() {
		return pitchId;
	}

	public void setPitchId(Integer pitchId) {
		this.pitchId = pitchId;
	}

	public Integer getRequestingEditorId() {
		return requestingEditorId;
	}

	public void setRequestingEditorId(Integer requestingEditorId) {
		this.requestingEditorId = requestingEditorId;
	}

	public Integer getTargetedPersonId() {
		return targetedPersonId;
	}

	public void setTargetedPersonId(Integer targetedPersonId) {
		this.targetedPersonId = targetedPersonId;
	}

	public Pitch getPitch() {
		return pitch;
	}

	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}

	public Person getRequestingEditor() {
		return requestingEditor;
	}

	public void setRequestingEditor(Person requestingEditor) {
		this.requestingEditor = requestingEditor;
	}

	public Person getTargetedPerson() {
		return targetedPerson;
	}

	public void setTargetedPerson(Person targetedPerson) {
		this.targetedPerson = targetedPerson;
	}
}
