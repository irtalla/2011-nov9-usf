package com.revature.beans;

//import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Pitch {
	@Id
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private Type type;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	private Person author;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genre_id")
	private GenreCommittee genre;
	private String title;
	//@Column(name = "completion_date")
	//private String completionDate;
	@Column(name = "tag_line")
	private String tagLine;
	private String description;
	@Column(name = "editor_notes")
	private String editorNotes;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	private Status status;
	private Integer priority;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pitch_approval",
			joinColumns = @JoinColumn(name = "pitch_id"),
			inverseJoinColumns = @JoinColumn(name = "person_id"))
	private Set<Person> approvals;

	public Pitch() {
		id = 0;
		type = new Type();
		author = new Person();
		genre = new GenreCommittee();
		title = "";
		//completionDate = "";
		tagLine = "";
		description = "";
		editorNotes = "";
		status = new Status();
		priority = 0;
		approvals = new HashSet<Person>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public GenreCommittee getGenre() {
		return genre;
	}

	public void setGenre(GenreCommittee genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

/*	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}*/

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEditorNotes() {
		return editorNotes;
	}

	public void setEditorNotes(String editorNotes) {
		this.editorNotes = editorNotes;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Set<Person> getApprovals() {
		return approvals;
	}

	public void setApprovals(Set<Person> approvals) {
		this.approvals = approvals;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvals == null) ? 0 : approvals.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((editorNotes == null) ? 0 : editorNotes.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tagLine == null) ? 0 : tagLine.hashCode());
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
		Pitch other = (Pitch) obj;
		if (approvals == null) {
			if (other.approvals != null)
				return false;
		} else if (!approvals.equals(other.approvals))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (editorNotes == null) {
			if (other.editorNotes != null)
				return false;
		} else if (!editorNotes.equals(other.editorNotes))
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
		if (tagLine == null) {
			if (other.tagLine != null)
				return false;
		} else if (!tagLine.equals(other.tagLine))
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
		return "Pitch [id=" + id + ", type=" + type + ", author=" + author + ", genre=" + genre + ", title=" + title
				+ ", tagLine=" + tagLine + ", description=" + description + ", editorNotes=" + editorNotes + ", status="
				+ status + ", priority=" + priority + ", approvals=" + approvals + "]";
	}

}
