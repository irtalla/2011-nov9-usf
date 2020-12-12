package com.revature.beans;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="pitch")
public class Pitch {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//associations:
	@ManyToOne
	@JoinColumn(name="author_id")
	private Person author;
	
	@OneToOne @JoinColumn(name="draft_id", referencedColumnName="id")
	private Draft draft;
	
	@OneToMany
	@JoinColumn(name="pitch_id")
	private Set<PitchFeedback> feedback;
	
	//columns:
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="pseudo_first_name")
	private String authorPseudoFirstName;
	
	@Column(name="pseudo_last_name")
	private String authorPseudoLastName;
	
	@Column(name="bio")
	private String bio;
	
	@Column(name="tentative_title")
	private String tentativeTitle;
	
	@Column(name="description")
	private String description;
	
	@Column(name="tag_line")
	private String tagLine;
	
	@Column(name="tentative_completion_date")
	private Date tentativeCompletionDate; //sql date or util date????????
	
	//enum values:
	@Enumerated(EnumType.STRING)
	@Column(name="story_type")
	private StoryType storyType;
	
	@Enumerated(EnumType.STRING)
	private Genre genre;
	
	@Enumerated(EnumType.STRING)
	private Priority priority;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public Set<PitchFeedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(Set<PitchFeedback> feedback) {
		this.feedback = feedback;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getAuthorPseudoFirstName() {
		return authorPseudoFirstName;
	}

	public void setAuthorPseudoFirstName(String authorPseudoFirstName) {
		this.authorPseudoFirstName = authorPseudoFirstName;
	}

	public String getAuthorPseudoLastName() {
		return authorPseudoLastName;
	}

	public void setAuthorPseudoLastName(String authorPseudoLastName) {
		this.authorPseudoLastName = authorPseudoLastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getTentativeTitle() {
		return tentativeTitle;
	}

	public void setTentativeTitle(String tentativeTitle) {
		this.tentativeTitle = tentativeTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public Date getTentativeCompletionDate() {
		return tentativeCompletionDate;
	}

	public void setTentativeCompletionDate(Date tentativeCompletionDate) {
		this.tentativeCompletionDate = tentativeCompletionDate;
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

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
}
