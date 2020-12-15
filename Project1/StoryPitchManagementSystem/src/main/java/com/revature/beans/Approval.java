package com.revature.beans;

import java.sql.Date;

public class Approval {
	
	private Integer approvalID;
	private Date approvalDate;
	private Integer statusApproved;
	private Integer approverID;
	private Integer approvedID;
	private Integer storyApprovedID;
	
	public Approval(Integer approvalID, Date approvalDate, Integer statusApproved, Integer approverID,
			Integer approvedID, Integer storyApprovedID) {
		super();
		this.approvalID = approvalID;
		this.approvalDate = approvalDate;
		this.statusApproved = statusApproved;
		this.approverID = approverID;
		this.approvedID = approverID;
		this.storyApprovedID = storyApprovedID;
	}
	
	public Approval() {
		super();
		this.approvalID = 0;
		this.approvalDate = null;
		this.statusApproved = 0;
		this.approverID = 0;
		this.approvedID = 0;
		this.storyApprovedID = 0;
	}

	public Integer getApprovalID() {
		return approvalID;
	}

	public void setApprovalID(Integer approvalID) {
		this.approvalID = approvalID;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Integer getStatusApproved() {
		return statusApproved;
	}

	public void setStatusApproved(Integer statusApproved) {
		this.statusApproved = statusApproved;
	}

	public Integer getApproverID() {
		return approverID;
	}

	public void setApproverID(Integer approverID) {
		this.approverID = approverID;
	}

	public Integer getApprovedID() {
		return approvedID;
	}

	public void setApprovedID(Integer approvedID) {
		this.approvedID = approvedID;
	}

	public Integer getStoryApprovedID() {
		return storyApprovedID;
	}

	public void setStoryApprovedID(Integer storyApprovedID) {
		this.storyApprovedID = storyApprovedID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvalDate == null) ? 0 : approvalDate.hashCode());
		result = prime * result + ((approvalID == null) ? 0 : approvalID.hashCode());
		result = prime * result + ((approvedID == null) ? 0 : approvedID.hashCode());
		result = prime * result + ((approverID == null) ? 0 : approverID.hashCode());
		result = prime * result + ((statusApproved == null) ? 0 : statusApproved.hashCode());
		result = prime * result + ((storyApprovedID == null) ? 0 : storyApprovedID.hashCode());
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
		Approval other = (Approval) obj;
		if (approvalDate == null) {
			if (other.approvalDate != null)
				return false;
		} else if (!approvalDate.equals(other.approvalDate))
			return false;
		if (approvalID == null) {
			if (other.approvalID != null)
				return false;
		} else if (!approvalID.equals(other.approvalID))
			return false;
		if (approvedID == null) {
			if (other.approvedID != null)
				return false;
		} else if (!approvedID.equals(other.approvedID))
			return false;
		if (approverID == null) {
			if (other.approverID != null)
				return false;
		} else if (!approverID.equals(other.approverID))
			return false;
		if (statusApproved == null) {
			if (other.statusApproved != null)
				return false;
		} else if (!statusApproved.equals(other.statusApproved))
			return false;
		if (storyApprovedID == null) {
			if (other.storyApprovedID != null)
				return false;
		} else if (!storyApprovedID.equals(other.storyApprovedID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Approval [approvalID=" + approvalID + ", approvalDate=" + approvalDate + ", statusApproved="
				+ statusApproved + ", approverID=" + approverID + ", approvedID=" + approvedID + ", storyApprovedID="
				+ storyApprovedID + "]";
	}

	
}
