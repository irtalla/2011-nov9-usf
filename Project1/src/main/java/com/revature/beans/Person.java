package com.revature.beans;

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.exceptions.FeedbackAsAuthorException;
import com.revature.exceptions.NonAuthorHasPitchesException;
import com.revature.exceptions.RequestAsAuthorException;

@Entity
@Table(name="person")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//columns:
	@Column(name="email")
	private String email;
	
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
	@OneToMany(fetch=FetchType.EAGER, mappedBy="author")
	private Set<Pitch> pitches; //must be empty if role is not author
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="editor")
	private Set<PitchFeedback> pitchFeedbackGiven; //as editor
	
	//pitch feedback received (as author) handled via pitch
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="requestingEditor")
	private Set<PitchInfoRequest> pitchInfoRequestsMade;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="targetedPerson")
	private Set<PitchInfoRequest> pitchInfoRequestsReceived;
	
	//authors have drafts through their pitches -> Dao
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="editor")
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
		if(this.role.equals(Role.AUTHOR) && pitchFeedbackGiven.size() > 0) {
			throw new FeedbackAsAuthorException();
		}
		this.pitchFeedbackGiven = pitchFeedbackGiven;
	}
	public Set<DraftFeedback> getDraftFeedbackGiven() {
		return draftFeedbackGiven;
	}
	public void setDraftFeedbackGiven(Set<DraftFeedback> draftFeedbackGiven) throws FeedbackAsAuthorException {
		if(this.role.equals(Role.AUTHOR) && draftFeedbackGiven.size() > 0) {
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
	public Set<GenreCommittee> getGenreCommittees() {
		return genreCommittees;
	}
	public void setGenreCommittees(Set<GenreCommittee> genreCommittees) {
		this.genreCommittees = genreCommittees;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Transient
	public boolean genreIsWithinSpecialty(Genre g) {
		//already checking elsewhere that role is not that of an author
		for(GenreCommittee gc : this.genreCommittees) {
			if(gc.getGenre().equals(g)) {
				return true;
			}
		}
		return false;
	}
	@Transient
	public int getTotalStoryPoints() {
		int sum = 0;
		for(Pitch p : this.pitches) {
			Status status = p.getStatus();
			if(status.equals(Status.PENDING) || status.equals(Status.APPROVED)) {
				sum += p.getStoryType().getPoints();
			}
		}
		return sum;
	}
	
	public Set<Draft> getDrafts(){
		Set<Draft> drafts = new HashSet<>();
		for(Pitch p : this.getPitches()) {
			Draft d = p.getDraft();
			if(d != null && p.getStatus().equals(Status.APPROVED)) {
				drafts.add(d);
			}
		}
		
		return drafts;
	}
	
	@Transient
	public Set<Draft> getDraftsWhosePitchesIHaveReactedTo(){
		Set<Draft> drafts = new HashSet<>();
		for(PitchFeedback fp : this.pitchFeedbackGiven) {
			Draft d = fp.getPitch().getDraft();
			if(d != null) {
				drafts.add(d);
			}
		}
		
		for(PitchInfoRequest pir : this.pitchInfoRequestsMade) {
			Draft d = pir.getPitch().getDraft();
			if(d != null) {
				drafts.add(d);
			}
		}
		return drafts;
	}
}
