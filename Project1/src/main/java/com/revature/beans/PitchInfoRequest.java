package com.revature.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pitch_info_request")
public class PitchInfoRequest {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne @JoinColumn(name="pitch_id")
	private Pitch pitch;
	
	@ManyToOne @JoinColumn(name="requesting_editor_id")
	private Person requestingEditor;
	
	@ManyToOne @JoinColumn(name="target_person_id")
	private Person targetedPerson;

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
