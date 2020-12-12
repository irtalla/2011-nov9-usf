package com.revature.beans;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.revature.exceptions.FeedbackAsAuthorException;
import com.revature.exceptions.NonAuthorHasPitchesException;
import com.revature.exceptions.RequestAsAuthorException;

@Entity
@Table(name="person")
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//columns:
	@Column(name="username")
	private String username;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="bio")
	private String bio;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	//associations:
	@OneToMany
	@JoinColumn(name="author_id")
	private Set<Pitch> pitches; //must be empty if role is not author
	
	@OneToMany
	@JoinColumn(name="editor_id")
	private Set<PitchFeedback> pitchFeedbackGiven; //as editor
	
	//pitch feedback received handled via pitch
	
	@OneToMany
	@JoinColumn(name="requesting_editor_id")
	private Set<PitchInfoRequest> pitchInfoRequestsMade;
	
	@OneToMany
	@JoinColumn(name="target_person_id")
	private Set<PitchInfoRequest> pitchInfoRequestsReceived;
	
	//authors have drafts through their pitches -> Dao
	
	@OneToMany
	@JoinColumn(name="editor_id")
	private Set<DraftFeedback> draftFeedbackGiven;
	//authors will receive draft feedback through their drafts -> Dao
	
	@ManyToMany(fetch=FetchType.EAGER, mappedBy = "members")
	private Set<GenreCommittee> genreCommittees;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Set<Pitch> getPitches() {
		return pitches;
	}
	public void setPitches(Set<Pitch> pitches) throws NonAuthorHasPitchesException {
		if(!this.role.equals(Role.AUTHOR)) {
			throw new NonAuthorHasPitchesException();
		}
		this.pitches = pitches;
	}
	public Set<PitchFeedback> getPitchFeedbackGiven() {
		return pitchFeedbackGiven;
	}
	public void setPitchFeedbackGiven(Set<PitchFeedback> pitchFeedbackGiven) throws FeedbackAsAuthorException {
		if(this.role.equals(Role.AUTHOR)) {
			throw new FeedbackAsAuthorException();
		}
		this.pitchFeedbackGiven = pitchFeedbackGiven;
	}
	public Set<DraftFeedback> getDraftFeedbackGiven() {
		return draftFeedbackGiven;
	}
	public void setDraftFeedbackGiven(Set<DraftFeedback> draftFeedbackGiven) throws FeedbackAsAuthorException {
		if(this.role.equals(Role.AUTHOR)) {
			throw new FeedbackAsAuthorException();
		}
		this.draftFeedbackGiven = draftFeedbackGiven;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<PitchInfoRequest> getPitchInfoRequestsMade() throws RequestAsAuthorException {
		if(this.role.equals(Role.AUTHOR)) {
			throw new RequestAsAuthorException();
		}
		return pitchInfoRequestsMade;
	}
	public void setPitchInfoRequestsMade(Set<PitchInfoRequest> pitchInfoRequestsMade) {
		this.pitchInfoRequestsMade = pitchInfoRequestsMade;
	}
	public Set<PitchInfoRequest> getPitchInfoRequestsReceived() {
		return pitchInfoRequestsReceived;
	}
	public void setPitchInfoRequestsReceived(Set<PitchInfoRequest> pitchInfoRequestsReceived) {
		this.pitchInfoRequestsReceived = pitchInfoRequestsReceived;
	}
}
