package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

import com.revature.exceptions.FeedbackAsAuthorException;

public class Person {
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String bio;
	private Genre genreSpecialty;
	private Role role;
	
	private Set<Pitch> pitches;
	private Set<PitchFeedback> pitchFeedbackGiven; //as editor
	private Set<PitchFeedback> pitchFeedbackReceived; //as editor
	//authors receive pitch feedback through their pitches -> Dao
	
	//authors have drafts through their pitches -> Dao
	private Set<DraftFeedback> draftFeedbackGiven;
	//authors will receive draft feedback through their drafts -> Dao
	
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
	public Genre getGenreSpecialty() {
		return genreSpecialty;
	}
	public void setGenreSpecialty(Genre genreSpecialty) {
		this.genreSpecialty = genreSpecialty;
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
	public void setPitches(Set<Pitch> pitches) {
		this.pitches = pitches;
	}
	public Set<PitchFeedback> getPitchFeedbackGiven() {
		return pitchFeedbackGiven;
	}
	public void setPitchFeedbackGiven(Set<PitchFeedback> pitchFeedbackGiven) {
		this.pitchFeedbackGiven = pitchFeedbackGiven;
	}
	public Set<PitchFeedback> getPitchFeedbackReceived() {
		return pitchFeedbackReceived;
	}
	public void setPitchFeedbackReceived(Set<PitchFeedback> pitchFeedbackReceived) {
		if(this.role.getName().equals("author")) {
			throw new FeedbackAsAuthorException();
		}
		this.pitchFeedbackReceived = pitchFeedbackReceived;
	}
	public Set<DraftFeedback> getDraftFeedbackGiven() {
		return draftFeedbackGiven;
	}
	public void setDraftFeedbackGiven(Set<DraftFeedback> draftFeedbackGiven) {
		if(this.role.getName().equals("author")) {
			throw new FeedbackAsAuthorException();
		}
		this.draftFeedbackGiven = draftFeedbackGiven;
	}
}
