package com.revature.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="draft")
public class Draft {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="time_submitted")
	private LocalDateTime timeSubmitted;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pitch_id")
	private Pitch pitch;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="draft_status")
	private ReviewStatus draftStatus;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="draft_add_file",
			joinColumns=@JoinColumn(name="draft_id"),
			inverseJoinColumns=@JoinColumn(name="add_file_id"))
	private Set<AdditionalFile> draftFiles;
	
	public Draft() {
		id = 0;
		timeSubmitted = LocalDateTime.now();
		pitch = new Pitch();
		draftStatus = new ReviewStatus();
		draftFiles = new HashSet<AdditionalFile>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<AdditionalFile> getDraftFiles() {
		return draftFiles;
	}

	public void setDraftFiles(Set<AdditionalFile> draftFiles) {
		this.draftFiles = draftFiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((draftFiles == null) ? 0 : draftFiles.hashCode());
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
		if (draftFiles == null) {
			if (other.draftFiles != null)
				return false;
		} else if (!draftFiles.equals(other.draftFiles))
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
		return "Draft [id=" + id + ", timeSubmitted=" + timeSubmitted + ", pitch=" + pitch + ", draftStatus="
				+ draftStatus + ", draftFiles=" + draftFiles + "]";
	}

}
