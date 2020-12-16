package com.revature.beans;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Draft {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private Type type;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	private Person author;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genre_committee_id")
	private GenreCommittee genre;
	private String title;
	private Date completionDate;
	private String tagLine;
	private String description;
	private String editorNotes;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	private Status status;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pitch_id")
	private Pitch pitch;
	private Integer priority;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "draft_approval",
			joinColumns = @JoinColumn(name = "draft_id"),
			inverseJoinColumns = @JoinColumn(name = "person_id"))
	private Set<Person> approvals;

	public Draft() {
		id = 0;
		type = new Type();
		author = new Person();
		genre = new GenreCommittee();
		title = "";
		completionDate = new Date();
		tagLine = "";
		description = "";
		editorNotes = "";
		status = new Status();
		pitch = new Pitch();
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

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

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

	public Pitch getPitch() {
		return pitch;
	}

	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Draft draft = (Draft) o;

		if (id != null ? !id.equals(draft.id) : draft.id != null) return false;
		if (type != null ? !type.equals(draft.type) : draft.type != null) return false;
		if (author != null ? !author.equals(draft.author) : draft.author != null) return false;
		if (genre != null ? !genre.equals(draft.genre) : draft.genre != null) return false;
		if (title != null ? !title.equals(draft.title) : draft.title != null) return false;
		if (completionDate != null ? !completionDate.equals(draft.completionDate) : draft.completionDate != null)
			return false;
		if (tagLine != null ? !tagLine.equals(draft.tagLine) : draft.tagLine != null) return false;
		if (description != null ? !description.equals(draft.description) : draft.description != null) return false;
		if (editorNotes != null ? !editorNotes.equals(draft.editorNotes) : draft.editorNotes != null) return false;
		if (status != null ? !status.equals(draft.status) : draft.status != null) return false;
		if (pitch != null ? !pitch.equals(draft.pitch) : draft.pitch != null) return false;
		if (priority != null ? !priority.equals(draft.priority) : draft.priority != null) return false;
		return approvals != null ? approvals.equals(draft.approvals) : draft.approvals == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (author != null ? author.hashCode() : 0);
		result = 31 * result + (genre != null ? genre.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (completionDate != null ? completionDate.hashCode() : 0);
		result = 31 * result + (tagLine != null ? tagLine.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (editorNotes != null ? editorNotes.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (pitch != null ? pitch.hashCode() : 0);
		result = 31 * result + (priority != null ? priority.hashCode() : 0);
		result = 31 * result + (approvals != null ? approvals.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Draft{" +
				"id=" + id +
				", type=" + type +
				", author=" + author +
				", genre=" + genre +
				", title='" + title + '\'' +
				", completionDate=" + completionDate +
				", tagLine='" + tagLine + '\'' +
				", description='" + description + '\'' +
				", editorNotes='" + editorNotes + '\'' +
				", status=" + status +
				", pitch=" + pitch +
				", priority=" + priority +
				", approvals=" + approvals +
				'}';
	}
}
