package com.revature.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pitch")
public class Pitch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name="author_pitch",
			joinColumns=@JoinColumn(name="pitch_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	private User author;
	@Column(name="title")
	private String title;
	@Column(name="tagline")
	private String tagline;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="type_id")
	private StoryType storyType;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="genre_id")
	private Genre genre;
	@Column(name="description")
	private String description;
	@Column(name="completion_date")
	private LocalDate completionDate;
	@Column(name="pitch_made_at")
	private LocalDateTime pitchMadeAt;
	@Column(name="pitch_arrived_at")
	private LocalDateTime pitchArrivedAt;
	@Transient
	private Priority priority;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="stage_id")
	private PitchStage pitchStage;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="status_id")
	private ReviewStatus reviewStatus;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="pitch_add_file",
			joinColumns=@JoinColumn(name="pitch_id"),
			inverseJoinColumns=@JoinColumn(name="add_file_id"))
	private Set<AdditionalFile> additionalFiles;

	public Pitch() {
		id = 0;
		author = new User();
		title = "";
		tagline = "";
		storyType = new StoryType();
		genre = new Genre();
		description = "";
		completionDate = LocalDate.now();
		pitchMadeAt = LocalDateTime.now();
		pitchArrivedAt = LocalDateTime.now();
		priority = Priority.NORMAL;
		pitchStage = new PitchStage();
		reviewStatus = new ReviewStatus();
		additionalFiles = new HashSet<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
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

	public StoryType getStoryType() {
		return storyType;
	}

	public void setStoryType(StoryType storyType) {
		this.storyType = storyType;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public LocalDateTime getPitchMadeAt() {
		return pitchMadeAt;
	}

	public void setPitchMadeAt(LocalDateTime pitchMadeAt) {
		this.pitchMadeAt = pitchMadeAt;
	}

	public LocalDateTime getPitchArrivedAt() {
		return pitchArrivedAt;
	}

	public void setPitchArrivedAt(LocalDateTime pitchArrivedAt) {
		this.pitchArrivedAt = pitchArrivedAt;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public PitchStage getPitchStage() {
		return pitchStage;
	}

	public void setPitchStage(PitchStage pitchStage) {
		this.pitchStage = pitchStage;
	}

	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public Set<AdditionalFile> getAdditionalFiles() {
		return additionalFiles;
	}

	public void setAdditionalFiles(Set<AdditionalFile> additionalFiles) {
		this.additionalFiles = additionalFiles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalFiles == null) ? 0 : additionalFiles.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((completionDate == null) ? 0 : completionDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pitchArrivedAt == null) ? 0 : pitchArrivedAt.hashCode());
		result = prime * result + ((pitchMadeAt == null) ? 0 : pitchMadeAt.hashCode());
		result = prime * result + ((pitchStage == null) ? 0 : pitchStage.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((reviewStatus == null) ? 0 : reviewStatus.hashCode());
		result = prime * result + ((storyType == null) ? 0 : storyType.hashCode());
		result = prime * result + ((tagline == null) ? 0 : tagline.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (additionalFiles == null) {
			if (other.additionalFiles != null)
				return false;
		} else if (!additionalFiles.equals(other.additionalFiles))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (completionDate == null) {
			if (other.completionDate != null)
				return false;
		} else if (!completionDate.equals(other.completionDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (pitchArrivedAt == null) {
			if (other.pitchArrivedAt != null)
				return false;
		} else if (!pitchArrivedAt.equals(other.pitchArrivedAt))
			return false;
		if (pitchMadeAt == null) {
			if (other.pitchMadeAt != null)
				return false;
		} else if (!pitchMadeAt.equals(other.pitchMadeAt))
			return false;
		if (pitchStage == null) {
			if (other.pitchStage != null)
				return false;
		} else if (!pitchStage.equals(other.pitchStage))
			return false;
		if (priority != other.priority)
			return false;
		if (reviewStatus == null) {
			if (other.reviewStatus != null)
				return false;
		} else if (!reviewStatus.equals(other.reviewStatus))
			return false;
		if (storyType == null) {
			if (other.storyType != null)
				return false;
		} else if (!storyType.equals(other.storyType))
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
		return true;
	}

	@Override
	public String toString() {
		return "Pitch [id=" + id + ", author=" + author + ", title=" + title + ", tagline=" + tagline + ", storyType="
				+ storyType + ", genre=" + genre + ", description=" + description + ", completionDate=" + completionDate
				+ ", pitchMadeAt=" + pitchMadeAt + ", pitchArrivedAt=" + pitchArrivedAt + ", priority=" + priority
				+ ", pitchStage=" + pitchStage + ", reviewStatus=" + reviewStatus + ", additionalFiles="
				+ additionalFiles + "]";
	}

}
