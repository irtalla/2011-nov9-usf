package com.revature.beans;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pitch")
public class Pitch /*implements GenericBean*/{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//associations:
//	@ManyToOne(fetch=FetchType.EAGER)
//	@JoinColumn(name="author_id")
	@Transient
	private Person author;
	@Column(name="author_id")
	private Integer authorId;
	
//	@OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="draft_id", referencedColumnName="id")
	@Transient
	private Draft draft;
	@Column(name="draft_id")
	private Integer draftId;
	
//	@OneToMany(fetch=FetchType.EAGER, mappedBy="pitch")
	@Transient
	private Set<PitchFeedback> feedback;
	
	
//	@OneToMany(fetch=FetchType.EAGER, mappedBy="pitch")
	@Transient
	private Set<PitchInfoRequest> infoRequests;
	
//	@Override
//	@Transient
//	public Set<AssociationMap<?>> foreignKeysForPrimaryKeyToClasses(){
//		//where these foreign keys go in predicates of criteria queries
//		Set<AssociationMap<?>> mapSet = new HashSet<>();
//		mapSet.add(new AssociationMap<PitchFeedback>(PitchFeedback.class, "pitchId", true));
//		mapSet.add(new AssociationMap<PitchInfoRequest>(PitchInfoRequest.class, "pitchId", true));
//		return mapSet;
//	}
	
	//belongs to associations:
//	@Override
//	@Transient
//	public Set<AssociationMap<?>> owningForeignKeysToClasses(){
//		//where these foreign keys go in predicates of criteria queries
//		Set<AssociationMap<?>> mapSet = new HashSet<>();
//		mapSet.add(new AssociationMap<Person>(Person.class, "authorId", false));
//		mapSet.add(new AssociationMap<Draft>(Draft.class, "pitchId", false));
//		return mapSet;
//	}
	
	//columns:
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="pseudo_first_name")
	private String pseudoFirstName;
	
	@Column(name="pseudo_last_name")
	private String pseudoLastName;
	
	@Column(name="pseudo_bio")
	private String pseudoBio;
	
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

	@Enumerated(EnumType.STRING)
	private Status status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuthorId() {
		return authorId;
	}
	
	@Transient
	public void setAuthorId(Integer id) {
		this.authorId = id;
	}
	@Transient
	public Person getAuthor() {
		return author;
	}

	@Transient
	public void setAuthor(Person author) {
		this.author = author;
	}

	@Transient
	public Set<PitchFeedback> getFeedback() {
		return feedback;
	}
	
	@Transient
	public void setFeedback(Set<PitchFeedback> feedback) {
		this.feedback = feedback;
	}
	
	@Transient
	public Set<Person> getEditorsThatHaveReacted(){
		Set<Person> reactingEditors = new HashSet<>();
		for(PitchFeedback pf : this.feedback) {
			reactingEditors.add(pf.getEditor());
		}
		
		for(PitchInfoRequest pir : this.infoRequests) {
			reactingEditors.add(pir.getRequestingEditor());
		}
		return reactingEditors;
	}
	

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPseudoFirstName() {
		return pseudoFirstName;
	}

	public void setPseudoFirstName(String authorPseudoFirstName) {
		this.pseudoFirstName = authorPseudoFirstName;
	}

	public String getPseudoLastName() {
		return pseudoLastName;
	}

	public void setPseudoLastName(String authorPseudoLastName) {
		this.pseudoLastName = authorPseudoLastName;
	}

	public String getPseudoBio() {
		return pseudoBio;
	}

	public void setPseudoBio(String bio) {
		this.pseudoBio= bio;
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

	@Transient
	public Draft getDraft() {
		return draft;
	}
	@Transient
	public void setDraft(Draft draft) {
		this.draft = draft;
	}
//
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	@Transient
	public Set<PitchInfoRequest> getInfoRequests() {
		return infoRequests;
	}
	@Transient
	public void setInfoRequests(Set<PitchInfoRequest> infoRequests) {
		this.infoRequests = infoRequests;
	}

	public Integer getDraftId() {
		return draftId;
	}

	public void setDraftId(Integer draftId) {
		this.draftId = draftId;
	}

}
