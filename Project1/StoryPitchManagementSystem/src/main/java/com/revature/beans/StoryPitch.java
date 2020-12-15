package com.revature.beans;

import java.sql.Date;
import java.time.LocalDate;

public class StoryPitch {

	private Integer storyID;
	private String title;
	private Date submissionDate;
	private Date completionDate;
	private String tagline;
	private String detailedDescription;
	private Integer personID;
	private Genre genre;
	private Type type;
	private Status status;
	private Integer priorityID;
	private Integer changesRequested;
	private String draft;
	
	public StoryPitch() {
		super();
		this.storyID = 0;
		this.title = "";
		this.submissionDate = null;
		this.completionDate = null;
		this.tagline = "";
		this.detailedDescription = "";
		this.personID = 0;
		this.type = new Type();
		this.genre = new Genre();
		this.status = new Status();
		this.priorityID = 0;
		this.changesRequested = 0;
		this.draft = "";
	}

	public StoryPitch(Integer storyID, String title, Date submissionDate, Date completionDate,
			String tagline, String detailedDescription, Integer personID, Genre genre, Type type, Status status,
			Integer priorityID, Integer changesRequested, String draft) {
		super();
		this.storyID = storyID;
		this.title = title;
		this.submissionDate = submissionDate;
		this.completionDate = completionDate;
		this.tagline = tagline;
		this.detailedDescription = detailedDescription;
		this.personID = personID;
		this.genre = genre;
		this.type = type;
		this.status = status;
		this.priorityID = priorityID;
		this.changesRequested = changesRequested;
		this.draft = draft;
	}

	public Integer getStoryID() {
		return storyID;
	}

	public void setStoryID(Integer storyID) {
		this.storyID = storyID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public Integer getPersonID() {
		return personID;
	}

	public void setPersonID(Integer personID) {
		this.personID = personID;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getPriorityID() {
		return priorityID;
	}

	public void setPriorityID(Integer priorityID) {
		this.priorityID = priorityID;
	}

	public Integer getChangesRequested() {
		return changesRequested;
	}

	public void setChangesRequested(Integer changesRequested) {
		this.changesRequested = changesRequested;
	}

	public String getDraft() {
		return draft;
	}

	public void setDraft(String draft) {
		this.draft = draft;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((changesRequested == null) ? 0 : changesRequested.hashCode());
		result = prime * result + ((completionDate == null) ? 0 : completionDate.hashCode());
		result = prime * result + ((detailedDescription == null) ? 0 : detailedDescription.hashCode());
		result = prime * result + ((draft == null) ? 0 : draft.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((personID == null) ? 0 : personID.hashCode());
		result = prime * result + ((priorityID == null) ? 0 : priorityID.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((storyID == null) ? 0 : storyID.hashCode());
		result = prime * result + ((submissionDate == null) ? 0 : submissionDate.hashCode());
		result = prime * result + ((tagline == null) ? 0 : tagline.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (changesRequested == null) {
			if (other.changesRequested != null)
				return false;
		} else if (!changesRequested.equals(other.changesRequested))
			return false;
		if (completionDate == null) {
			if (other.completionDate != null)
				return false;
		} else if (!completionDate.equals(other.completionDate))
			return false;
		if (detailedDescription == null) {
			if (other.detailedDescription != null)
				return false;
		} else if (!detailedDescription.equals(other.detailedDescription))
			return false;
		if (draft == null) {
			if (other.draft != null)
				return false;
		} else if (!draft.equals(other.draft))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (personID == null) {
			if (other.personID != null)
				return false;
		} else if (!personID.equals(other.personID))
			return false;
		if (priorityID == null) {
			if (other.priorityID != null)
				return false;
		} else if (!priorityID.equals(other.priorityID))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (storyID == null) {
			if (other.storyID != null)
				return false;
		} else if (!storyID.equals(other.storyID))
			return false;
		if (submissionDate == null) {
			if (other.submissionDate != null)
				return false;
		} else if (!submissionDate.equals(other.submissionDate))
			return false;
		if (tagline == null) {
			if (other.tagline != null)
				return false;
		} else if (!tagline.equals(other.tagline))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StoryPitch [storyID=" + storyID + ", title=" + title + ", submissionDate=" + submissionDate
				+ ", completionDate=" + completionDate + ", tagline=" + tagline + ", detailedDescription="
				+ detailedDescription + ", personID=" + personID + ", genre=" + genre + ", type=" + type + ", status="
				+ status + ", priorityID=" + priorityID + ", changesRequested=" + changesRequested + ", draft=" + draft
				+ "]";
	}

	
	
	
	
}
