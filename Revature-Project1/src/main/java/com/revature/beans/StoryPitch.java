package com.revature.beans;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="approvals")
public class StoryPitch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="approval_id")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="proposed_work_id")
	private Work proposedWork;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="approval_stage_id")
	private Stage stage;
	
	@Column(name="denial_reason")
	private String denialReason;
	
	@Column(name="high_priority_marker")
	private boolean highPriority;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="editor_id")
	private Editor assignedEditor;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Status status;
	
	@Column(name="senior_editor_change")
	private boolean seniorEditorChange;
	
	@Column(name="stage_start")
	private Date dateWhenStageStarted;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="second_stage_checker")
	private Editor secondStageChecker;

	/*
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="approval_proofreading",
			joinColumns=@JoinColumn(name="approval_id"),
			inverseJoinColumns=@JoinColumn(name="editor_id"))
	private List<Editor> thoseWhoMightProofreadYourFinalWork;
	
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="final_approval_viewing",
			joinColumns=@JoinColumn(name="approval_id"),
			inverseJoinColumns=@JoinColumn(name="editor_id"))
	private List<Editor> thoseWhoMightViewYourFinalWork;
	*/
	
	public StoryPitch() {
		proposedWork = null;
		denialReason = "";
		stage = null;
		highPriority = false;
		status = null;
		assignedEditor = null;
		seniorEditorChange = false;
		//thoseWhoMightProofreadYourFinalWork = null;
	}
	
	
	/*	
	public List<Editor> getThoseWhoMightViewYourFinalWork() {
		return thoseWhoMightViewYourFinalWork;
	}



	public void setThoseWhoMightViewYourFinalWork(List<Editor> thoseWhoMightViewYourFinalWork) {
		this.thoseWhoMightViewYourFinalWork = thoseWhoMightViewYourFinalWork;
	}



	public List<Editor> getThoseWhoMightProofreadYourFinalWork() {
		return thoseWhoMightProofreadYourFinalWork;
	}

	public void setThoseWhoMightProofreadYourFinalWork(List<Editor> thoseWhoMightProofreadYourFinalWork) {
		this.thoseWhoMightProofreadYourFinalWork = thoseWhoMightProofreadYourFinalWork;
	}
	 */


	public Editor getSecondStageChecker() {
		return secondStageChecker;
	}


	public void setSecondStageChecker(Editor secondStageChecker) {
		this.secondStageChecker = secondStageChecker;
	}


	public Editor getAssignedEditor() {
		return assignedEditor;
	}

	public void setAssignedEditor(Editor assignedEditor) {
		this.assignedEditor = assignedEditor;
	}

	public Date getDateWhenStageStarted() {
		return dateWhenStageStarted;
	}

	public void setDateWhenStageStarted(Date dateWhenStageStarted) {
		this.dateWhenStageStarted = dateWhenStageStarted;
	}

	/**
	 * @return the proposedWork
	 */
	public Work getProposedWork() {
		return proposedWork;
	}

	/**
	 * @param proposedWork the proposedWork to set
	 */
	public void setProposedWork(Work proposedWork) {
		this.proposedWork = proposedWork;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the denialReason
	 */
	public String getDenialReason() {
		return denialReason;
	}

	/**
	 * @param denialReason the denialReason to set
	 */
	public void setDenialReason(String denialReason) {
		this.denialReason = denialReason;
	}

	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * @return the highPriority
	 */
	public boolean isHighPriority() {
		return highPriority;
	}

	/**
	 * @param highPriority the highPriority to set
	 */
	public void setHighPriority(boolean highPriority) {
		this.highPriority = highPriority;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the seniorEditorChange
	 */
	public boolean isSeniorEditorChange() {
		return seniorEditorChange;
	}

	/**
	 * @param seniorEditorChange the seniorEditorChange to set
	 */
	public void setSeniorEditorChange(boolean seniorEditorChange) {
		this.seniorEditorChange = seniorEditorChange;
	}
	
	@Override
	public String toString() {
		String denialString = new String("");
		String seniorEditor = new String("");
		
		if (status != null && status.getStatus().equals("rejected")) {
			denialString += "Denied because: " + denialReason;
		}
		else if (stage != null && (stage.getStageName().equals("senior editor")  || stage.getStageName().equals("final check"))) {
			seniorEditor += "Senior editor has edited your work: " + seniorEditorChange; 
		}
		
		return "The work " + (proposedWork != null ? proposedWork.getTitle() : "N/A") 
				+ " is being reviewed.\nIt is being reviewed by " + (assignedEditor != null ? assignedEditor.getName() : "N/A")
				+ "Status: " + status + " " + denialString + "\n"
				+ "High priority: " + highPriority + " " + seniorEditor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assignedEditor == null) ? 0 : assignedEditor.hashCode());
		result = prime * result + ((dateWhenStageStarted == null) ? 0 : dateWhenStageStarted.hashCode());
		result = prime * result + ((denialReason == null) ? 0 : denialReason.hashCode());
		result = prime * result + (highPriority ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((proposedWork == null) ? 0 : proposedWork.hashCode());
		result = prime * result + ((secondStageChecker == null) ? 0 : secondStageChecker.hashCode());
		result = prime * result + (seniorEditorChange ? 1231 : 1237);
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		/*
		result = prime * result
				+ ((thoseWhoMightProofreadYourFinalWork == null) ? 0 : thoseWhoMightProofreadYourFinalWork.hashCode());
		result = prime * result
				+ ((thoseWhoMightViewYourFinalWork == null) ? 0 : thoseWhoMightViewYourFinalWork.hashCode());
		*/
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StoryPitch other = (StoryPitch) obj;
		if (assignedEditor == null) {
			if (other.assignedEditor != null)
				return false;
		} else if (!assignedEditor.equals(other.assignedEditor))
			return false;
		if (dateWhenStageStarted == null) {
			if (other.dateWhenStageStarted != null)
				return false;
		} else if (!dateWhenStageStarted.equals(other.dateWhenStageStarted))
			return false;
		if (denialReason == null) {
			if (other.denialReason != null)
				return false;
		} else if (!denialReason.equals(other.denialReason))
			return false;
		if (highPriority != other.highPriority)
			return false;
		if (id != other.id)
			return false;
		if (proposedWork == null) {
			if (other.proposedWork != null)
				return false;
		} else if (!proposedWork.equals(other.proposedWork))
			return false;
		if (secondStageChecker == null) {
			if (other.secondStageChecker != null)
				return false;
		} else if (!secondStageChecker.equals(other.secondStageChecker))
			return false;
		if (seniorEditorChange != other.seniorEditorChange)
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		/*
		if (thoseWhoMightProofreadYourFinalWork == null) {
			if (other.thoseWhoMightProofreadYourFinalWork != null)
				return false;
		} else if (!thoseWhoMightProofreadYourFinalWork.equals(other.thoseWhoMightProofreadYourFinalWork))
			return false;
		if (thoseWhoMightViewYourFinalWork == null) {
			if (other.thoseWhoMightViewYourFinalWork != null)
				return false;
		} else if (!thoseWhoMightViewYourFinalWork.equals(other.thoseWhoMightViewYourFinalWork))
			return false;
		*/
		return true;
	}


	/*
	public void setThoseWhoMightProofreadYourFinalWork(List<Editor> allPossibleEditorsToCheckWork) {
		
	}


	public void setThoseWhoMightViewYourFinalWork(List<Editor> thoseWhoCanView) {
		// TODO Auto-generated method stub
		
	}
	*/

}
