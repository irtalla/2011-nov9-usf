package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="pitches")
public class Pitch {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="author")
	private Integer authorId;
	
	private String authorname;
	@ManyToOne
	@JoinColumn(name="genre")
	private Genre genre;
	@ManyToOne
	@JoinColumn(name="pitchtype")
	private Storytype pitchtype;
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	private String title;
	private String tagline;
	private Integer compldate;
	private String description;
	private String extrainfo;
	private Integer votes;
	private String priority;
	
	public Pitch() {
		id = 0;
		authorId = 0;
		authorname = "";
		genre = new Genre();
		pitchtype = new Storytype();
		status = new Status();
		title = "";
		tagline = "";
		compldate = 0;
		description = "";
		extrainfo = "";
		votes = 0;
		priority = "Low";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Storytype getPitchtype() {
		return pitchtype;
	}

	public void setPitchtype(Storytype pitchtype) {
		this.pitchtype = pitchtype;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public Integer getCompldate() {
		return compldate;
	}

	public void setCompldate(Integer compldate) {
		this.compldate = compldate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtrainfo() {
		return extrainfo;
	}

	public void setExtrainfo(String extrainfo) {
		this.extrainfo = extrainfo;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorId == null) ? 0 : authorId.hashCode());
		result = prime * result + ((authorname == null) ? 0 : authorname.hashCode());
		result = prime * result + ((compldate == null) ? 0 : compldate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((extrainfo == null) ? 0 : extrainfo.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pitchtype == null) ? 0 : pitchtype.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tagline == null) ? 0 : tagline.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((votes == null) ? 0 : votes.hashCode());
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
		Pitch other = (Pitch) obj;
		if (authorId == null) {
			if (other.authorId != null)
				return false;
		} else if (!authorId.equals(other.authorId))
			return false;
		if (authorname == null) {
			if (other.authorname != null)
				return false;
		} else if (!authorname.equals(other.authorname))
			return false;
		if (compldate == null) {
			if (other.compldate != null)
				return false;
		} else if (!compldate.equals(other.compldate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (extrainfo == null) {
			if (other.extrainfo != null)
				return false;
		} else if (!extrainfo.equals(other.extrainfo))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pitchtype == null) {
			if (other.pitchtype != null)
				return false;
		} else if (!pitchtype.equals(other.pitchtype))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
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
		if (votes == null) {
			if (other.votes != null)
				return false;
		} else if (!votes.equals(other.votes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pitch [id=" + id + ", authorId=" + authorId + ", authorname=" + authorname + ", genre=" + genre
				+ ", pitchtype=" + pitchtype + ", status=" + status + ", title=" + title + ", tagline=" + tagline
				+ ", compldate=" + compldate + ", description=" + description + ", extrainfo=" + extrainfo + ", votes="
				+ votes + ", priority=" + priority + "]";
	}
	
}
