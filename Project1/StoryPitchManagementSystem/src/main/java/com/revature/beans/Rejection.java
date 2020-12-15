package com.revature.beans;

import java.sql.Date;

public class Rejection {
	
	private Integer rejectionID;
	private Date rejectionDate;
	private String reason;
	private Integer rejectorID;
	private Integer rejectedID;
	private Integer storyRejectedID;
	
	public Rejection(Integer rejectionID, Date rejectionDate, String reason, Integer rejectorID,
			 Integer rejectedID, Integer storyRejectedID) {
		super();
		this.rejectionID = rejectionID;
		this.rejectionDate = rejectionDate;
		this.reason = reason;
		this.rejectorID = rejectorID;
		this.storyRejectedID = storyRejectedID;
	}
	
	public Rejection() {
		super();
		this.rejectionID = 0;
		this.rejectionDate = null;
		this.reason = "";
		this.rejectorID = 0;
		this.rejectedID = 0;
		this.storyRejectedID = 0;
	}

	public Integer getRejectionID() {
		return rejectionID;
	}

	public void setRejectionID(Integer rejectionID) {
		this.rejectionID = rejectionID;
	}

	public Date getRejectionDate() {
		return rejectionDate;
	}

	public void setRejectionDate(Date rejectionDate) {
		this.rejectionDate = rejectionDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getRejectorID() {
		return rejectorID;
	}

	public void setRejectorID(Integer rejectorID) {
		this.rejectorID = rejectorID;
	}

	public Integer getRejectedID() {
		return rejectedID;
	}

	public void setRejectedID(Integer rejectedID) {
		this.rejectedID = rejectedID;
	}

	public Integer getStoryRejectedID() {
		return storyRejectedID;
	}

	public void setStoryRejectedID(Integer storyRejectedID) {
		this.storyRejectedID = storyRejectedID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((rejectedID == null) ? 0 : rejectedID.hashCode());
		result = prime * result + ((rejectionDate == null) ? 0 : rejectionDate.hashCode());
		result = prime * result + ((rejectionID == null) ? 0 : rejectionID.hashCode());
		result = prime * result + ((rejectorID == null) ? 0 : rejectorID.hashCode());
		result = prime * result + ((storyRejectedID == null) ? 0 : storyRejectedID.hashCode());
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
		Rejection other = (Rejection) obj;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (rejectedID == null) {
			if (other.rejectedID != null)
				return false;
		} else if (!rejectedID.equals(other.rejectedID))
			return false;
		if (rejectionDate == null) {
			if (other.rejectionDate != null)
				return false;
		} else if (!rejectionDate.equals(other.rejectionDate))
			return false;
		if (rejectionID == null) {
			if (other.rejectionID != null)
				return false;
		} else if (!rejectionID.equals(other.rejectionID))
			return false;
		if (rejectorID == null) {
			if (other.rejectorID != null)
				return false;
		} else if (!rejectorID.equals(other.rejectorID))
			return false;
		if (storyRejectedID == null) {
			if (other.storyRejectedID != null)
				return false;
		} else if (!storyRejectedID.equals(other.storyRejectedID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rejection [rejectionID=" + rejectionID + ", rejectionDate=" + rejectionDate + ", reason=" + reason
				+ ", rejectorID=" + rejectorID + ", rejectedID=" + rejectedID + ", storyRejectedID=" + storyRejectedID
				+ "]";
	}
	
	
	
}
