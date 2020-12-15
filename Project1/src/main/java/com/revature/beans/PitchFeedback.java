package com.revature.beans;

import java.sql.Date;

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
@Table(name="pitch_feedback")
public class PitchFeedback {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
//	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="pitch_id")
	@Transient
	private Pitch pitch;
	@Column(name="pitch_id")
	private Integer pitchId;
	
//	@ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name="editor_id")
	@Transient
	private Person editor; //person who is giving feedback; role must be that of an editor,
	//with authority of greater scope than the editor who most recently approved this pitch, if any
	@Column(name="editor_id")
	private Integer editorId;
	
	@Enumerated(EnumType.STRING)
	private Status status; //the status being conferred to this pitch by an editor
	//this must be null unless editor is a senior editor
	
	//columns:
	@Column(name="explanation")
	private String explanation;
	
	@Column(name="new_tag_line")
	private String newTagLine; 
	
	@Column(name="new_tentative_title")
	private String newTentativeTitle;
	
	@Column(name="new_tentative_completion_date")
	private Date newTentativeCompletionDate;

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
	@Transient
	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
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

	public Integer getPitchId() {
		return pitchId;
	}

	public void setPitchId(Integer pitchId) {
		this.pitchId = pitchId;
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

	public String getNewTagLine() {
		return newTagLine;
	}

	public void setNewTagLine(String newTagLine) {
		this.newTagLine = newTagLine;
	}

	public String getNewTentativeTitle() {
		return newTentativeTitle;
	}

	public void setNewTentativeTitle(String newTentativeTitle) {
		this.newTentativeTitle = newTentativeTitle;
	}

	public Date getNewTentativeCompletionDate() {
		return newTentativeCompletionDate;
	}

	public void setNewTentativeCompletionDate(Date newTentativeCompletionDate) {
		this.newTentativeCompletionDate = newTentativeCompletionDate;
	}
}
