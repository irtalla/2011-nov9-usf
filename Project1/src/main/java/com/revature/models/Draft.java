package com.revature.models;

import java.time.LocalDateTime;

public class Draft {
	private Integer id;
	private String path; // TODO: find a better data type/structure to store large file
	private LocalDateTime timeSubmitted;
	private Pitch pitch;
	private ReviewStatus draftStatus;
	
	public Draft() {
		id = 0;
		path = "";
		timeSubmitted = LocalDateTime.now();
		pitch = new Pitch();
		draftStatus = new ReviewStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContents() {
		return path;
	}

	public void setContents(String contents) {
		this.path = contents;
	}

	public LocalDateTime getTimeSubmitted() {
		return timeSubmitted;
	}

	public void setTimeSubmitted(LocalDateTime timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}

	public Pitch getPitch() {
		return pitch;
	}

	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}

	public ReviewStatus getDraftStatus() {
		return draftStatus;
	}

	public void setDraftStatus(ReviewStatus draftStatus) {
		this.draftStatus = draftStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((draftStatus == null) ? 0 : draftStatus.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pitch == null) ? 0 : pitch.hashCode());
		result = prime * result + ((timeSubmitted == null) ? 0 : timeSubmitted.hashCode());
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
		Draft other = (Draft) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (draftStatus == null) {
			if (other.draftStatus != null)
				return false;
		} else if (!draftStatus.equals(other.draftStatus))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pitch == null) {
			if (other.pitch != null)
				return false;
		} else if (!pitch.equals(other.pitch))
			return false;
		if (timeSubmitted == null) {
			if (other.timeSubmitted != null)
				return false;
		} else if (!timeSubmitted.equals(other.timeSubmitted))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Draft [id=" + id + ", contents=" + path + ", timeSubmitted=" + timeSubmitted + ", pitch=" + pitch
				+ ", draftStatus=" + draftStatus + "]";
	}

}
