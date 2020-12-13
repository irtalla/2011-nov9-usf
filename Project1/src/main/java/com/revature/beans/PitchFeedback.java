package com.revature.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pitch_feedback")
public class PitchFeedback {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne @JoinColumn(name="pitch_id")
	private Pitch pitch;
	
	@ManyToOne @JoinColumn(name="editor_id")
	private Person editor; //person who is giving feedback; role must be that of an editor,
	//with authority of greater scope than the editor who most recently approved this pitch, if any
	
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

	public Pitch getPitch() {
		return pitch;
	}

	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}

	public Person getEditor() {
		return editor;
	}

	public void setEditor(Person editor) {
		this.editor = editor;
	}

	public Status getStatus() {
		return status;
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
