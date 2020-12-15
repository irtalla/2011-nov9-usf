package com.revature.beans;

import java.time.LocalDate;
import java.sql.Date;

public class InfoResponse {
	
	private Integer responseID;
	private Date responseDate;
	private Integer requestID;
	private String message;
	private Integer storyID;
	private Integer personRespondingID;
	private Integer personRespondedID;
	
	public InfoResponse(Integer responseID, Date responseDate, Integer requestID, String message, Integer storyID,
			Integer personRespondingID, Integer personRespondedID) {
		super();
		this.responseID = responseID;
		this.responseDate = responseDate;
		this.requestID = requestID;
		this.message = message;
		this.storyID = storyID;
		this.personRespondingID = personRespondingID;
		this.personRespondedID = personRespondedID;
	}
	public InfoResponse() {
		super();
		this.responseID = 0;
		this.responseDate = null;
		this.requestID = 0;
		this.message = "";
		this.storyID = 0;
		this.personRespondingID = 0;
		this.personRespondedID = 0;
	}
	public Integer getResponseID() {
		return responseID;
	}
	public void setResponseID(Integer responseID) {
		this.responseID = responseID;
	}
	public Date getResponseDate() {
		return responseDate;
	}
	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}
	public Integer getRequestID() {
		return requestID;
	}
	public void setRequestID(Integer requestID) {
		this.requestID = requestID;
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
	public Integer getPersonRespondingID() {
		return personRespondingID;
	}
	public void setPersonRespondingID(Integer personRespondingID) {
		this.personRespondingID = personRespondingID;
	}
	public Integer getPersonRespondedID() {
		return personRespondedID;
	}
	public void setPersonRespondedID(Integer personRespondedID) {
		this.personRespondedID = personRespondedID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((personRespondedID == null) ? 0 : personRespondedID.hashCode());
		result = prime * result + ((personRespondingID == null) ? 0 : personRespondingID.hashCode());
		result = prime * result + ((requestID == null) ? 0 : requestID.hashCode());
		result = prime * result + ((responseDate == null) ? 0 : responseDate.hashCode());
		result = prime * result + ((responseID == null) ? 0 : responseID.hashCode());
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
		InfoResponse other = (InfoResponse) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (personRespondedID == null) {
			if (other.personRespondedID != null)
				return false;
		} else if (!personRespondedID.equals(other.personRespondedID))
			return false;
		if (personRespondingID == null) {
			if (other.personRespondingID != null)
				return false;
		} else if (!personRespondingID.equals(other.personRespondingID))
			return false;
		if (requestID == null) {
			if (other.requestID != null)
				return false;
		} else if (!requestID.equals(other.requestID))
			return false;
		if (responseDate == null) {
			if (other.responseDate != null)
				return false;
		} else if (!responseDate.equals(other.responseDate))
			return false;
		if (responseID == null) {
			if (other.responseID != null)
				return false;
		} else if (!responseID.equals(other.responseID))
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
		return "InfoResponse [responseID=" + responseID + ", responseDate=" + responseDate + ", requestID=" + requestID
				+ ", message=" + message + ", storyID=" + storyID + ", personRespondingID=" + personRespondingID
				+ ", personRespondedID=" + personRespondedID + "]";
	}
	
	

}
