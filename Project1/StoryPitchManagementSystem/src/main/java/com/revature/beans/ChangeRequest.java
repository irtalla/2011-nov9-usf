package com.revature.beans;

import java.sql.Date;
import java.time.LocalDate;

public class ChangeRequest {

	private Integer requestID;
	private Date requestDate;
	private String message;
	private Integer storyID;
	private Integer personRequestingID;
	private Integer personRequestedID;
	
	public ChangeRequest(Integer requestID, Date requestDate, String message, Integer storyID,
			Integer personRequestingID, Integer personRequestedID) {
		super();
		this.requestID = requestID;
		this.requestDate = requestDate;
		this.message = message;
		this.storyID = storyID;
		this.personRequestingID = personRequestingID;
		this.personRequestedID = personRequestedID;
	}
	public ChangeRequest() {
		super();
		this.requestID = 0;
		this.requestDate = null;
		this.message = "";
		this.storyID = 0;
		this.personRequestingID = 0;
		this.personRequestedID = 0;
	}
	public Integer getRequestID() {
		return requestID;
	}
	public void setRequestID(Integer requestID) {
		this.requestID = requestID;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date date) {
		this.requestDate = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStoryID() {
		return storyID;
	}
	public void setStoryID(Integer storyID) {
		this.storyID = storyID;
	}
	public Integer getPersonRequestingID() {
		return personRequestingID;
	}
	public void setPersonRequestingID(Integer personRequestingID) {
		this.personRequestingID = personRequestingID;
	}
	public Integer getPersonRequestedID() {
		return personRequestedID;
	}
	public void setPersonRequestedID(Integer personRequestedID) {
		this.personRequestedID = personRequestedID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((personRequestedID == null) ? 0 : personRequestedID.hashCode());
		result = prime * result + ((personRequestingID == null) ? 0 : personRequestingID.hashCode());
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
		result = prime * result + ((requestID == null) ? 0 : requestID.hashCode());
		result = prime * result + ((storyID == null) ? 0 : storyID.hashCode());
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
		ChangeRequest other = (ChangeRequest) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (personRequestedID == null) {
			if (other.personRequestedID != null)
				return false;
		} else if (!personRequestedID.equals(other.personRequestedID))
			return false;
		if (personRequestingID == null) {
			if (other.personRequestingID != null)
				return false;
		} else if (!personRequestingID.equals(other.personRequestingID))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		if (requestID == null) {
			if (other.requestID != null)
				return false;
		} else if (!requestID.equals(other.requestID))
			return false;
		if (storyID == null) {
			if (other.storyID != null)
				return false;
		} else if (!storyID.equals(other.storyID))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ChangeRequest [requestID=" + requestID + ", requestDate=" + requestDate + ", message=" + message
				+ ", storyID=" + storyID + ", personRequestingID=" + personRequestingID + ", personRequestedID="
				+ personRequestedID + "]";
	}
	
	
	
}
