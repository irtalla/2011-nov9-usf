package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;


public class FurtherInfoRequests {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="request_id")
	private int id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="approval_id")
	private StoryPitch originalPitch;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="stage")
	private Stage stageWhenRequested;
	
	@Column(name="request_description")
	private String requestDescription;
	
	@Column(name="information_url")
	private String path;
	
	public FurtherInfoRequests() {
		id = 0;
		originalPitch = null;
		stageWhenRequested = null;
		requestDescription = "";
		path = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StoryPitch getOriginalPitch() {
		return originalPitch;
	}

	public void setOriginalPitch(StoryPitch originalPitch) {
		this.originalPitch = originalPitch;
	}

	public Stage getStage() {
		return stageWhenRequested;
	}

	public void setStage(Stage stage) {
		stageWhenRequested = stage;
	}

	public String getRequestDescription() {
		return requestDescription;
	}

	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return "Request for info " + id + " is about story pitch for " + originalPitch.getProposedWork().getTitle() + " on " + stageWhenRequested.getStageName() + " stage asking for " + requestDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((originalPitch == null) ? 0 : originalPitch.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((requestDescription == null) ? 0 : requestDescription.hashCode());
		result = prime * result + ((stageWhenRequested== null) ? 0 : stageWhenRequested.hashCode());
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
		FurtherInfoRequests other = (FurtherInfoRequests) obj;
		if (id != other.id)
			return false;
		if (originalPitch == null) {
			if (other.originalPitch != null)
				return false;
		} else if (!originalPitch.equals(other.originalPitch))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (requestDescription == null) {
			if (other.requestDescription != null)
				return false;
		} else if (!requestDescription.equals(other.requestDescription))
			return false;
		if (stageWhenRequested == null) {
			if (other.stageWhenRequested != null)
				return false;
		} else if (!stageWhenRequested.equals(other.stageWhenRequested))
			return false;
		return true;
	}
	
	
}
