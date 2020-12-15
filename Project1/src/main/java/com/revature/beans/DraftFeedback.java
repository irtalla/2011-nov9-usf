package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="draft_feedback")
public class DraftFeedback {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="draft_id")
	@Transient
	private Draft draft;
	@Column(name="draft_id")
	private Integer draftId;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="editor_id")
	@Transient
	private Person editor;
	@Column(name="editor_id")
	private Integer editorId;
	
	@Enumerated(EnumType.STRING)
	private Status status; //"pending" by default
	
	@Column(name="explanation")
	private String explanation; //not null if denial

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Transient
	public Draft getDraft() {
		return draft;
	}

	@Transient
	public void setDraft(Draft draft) {
		this.draft = draft;
	}

	public Integer getDraftId() {
		return draftId;
	}

	public void setDraftId(Integer id) {
		this.draftId = id;
	}
	@Transient
	public Person getEditor() {
		return editor;
	}
	@Transient
	public void setEditor(Person editor) {
		this.editor = editor;
	}

	public Status getStatus() {
		return status;
	}

	public Integer getEditorId() {
		return editorId;
	}

	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
}
