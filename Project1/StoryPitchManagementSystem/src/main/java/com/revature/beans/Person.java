package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

public class Person {
	private Integer personID;
	private String name;
	private String password;
	private Integer roleID;
	private Integer points;
	private String username;
	private Set<StoryPitch> storyPitches;
	private Set<Approval> approvals;
	private Set<Rejection> rejections;
	private Set<InfoRequest> infoRequests;
	private Set<InfoResponse> infoResponses;
	private Set<ChangeRequest> changeRequests;
	
	public Person() {
		super();
		this.personID = 0;
		this.name = "";
		this.password ="";
		this.roleID = 0;
		this.points = 0;
		this.username = "";
		this.storyPitches = new HashSet<StoryPitch>();
		this.approvals = new HashSet<Approval>();
		this.rejections = new HashSet<Rejection>();
		this.infoRequests = new HashSet<InfoRequest>();
		this.infoResponses = new HashSet<InfoResponse>();
		this.changeRequests = new HashSet<ChangeRequest>();
	}

	public Person(Integer personID, String name, String password, Integer roleID, Integer points, String username,
			Set<StoryPitch> storyPitches, Set<Approval> approvals, Set<Rejection> rejections,
			Set<InfoRequest> infoRequests, Set<InfoResponse> infoResponses, Set<ChangeRequest> changeRequests) {
		super();
		this.personID = personID;
		this.name = name;
		this.password = password;
		this.roleID = roleID;
		this.points = points;
		this.username = username;
		this.storyPitches = storyPitches;
		this.approvals = approvals;
		this.rejections = rejections;
		this.infoRequests = infoRequests;
		this.infoResponses = infoResponses;
		this.changeRequests = changeRequests;
	}

	public Integer getPersonID() {
		return personID;
	}

	public void setPersonID(Integer personID) {
		this.personID = personID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<StoryPitch> getStoryPitches() {
		return storyPitches;
	}

	public void setStoryPitches(Set<StoryPitch> storyPitches) {
		this.storyPitches = storyPitches;
	}

	public Set<Approval> getApprovals() {
		return approvals;
	}

	public void setApprovals(Set<Approval> approvals) {
		this.approvals = approvals;
	}

	public Set<Rejection> getRejections() {
		return rejections;
	}

	public void setRejections(Set<Rejection> rejections) {
		this.rejections = rejections;
	}

	public Set<InfoRequest> getInfoRequests() {
		return infoRequests;
	}

	public void setInfoRequests(Set<InfoRequest> infoRequests) {
		this.infoRequests = infoRequests;
	}

	public Set<InfoResponse> getInfoResponses() {
		return infoResponses;
	}

	public void setInfoResponses(Set<InfoResponse> infoResponses) {
		this.infoResponses = infoResponses;
	}

	public Set<ChangeRequest> getChangeRequests() {
		return changeRequests;
	}

	public void setChangeRequests(Set<ChangeRequest> changeRequests) {
		this.changeRequests = changeRequests;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvals == null) ? 0 : approvals.hashCode());
		result = prime * result + ((changeRequests == null) ? 0 : changeRequests.hashCode());
		result = prime * result + ((infoRequests == null) ? 0 : infoRequests.hashCode());
		result = prime * result + ((infoResponses == null) ? 0 : infoResponses.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((personID == null) ? 0 : personID.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((rejections == null) ? 0 : rejections.hashCode());
		result = prime * result + ((roleID == null) ? 0 : roleID.hashCode());
		result = prime * result + ((storyPitches == null) ? 0 : storyPitches.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Person other = (Person) obj;
		if (approvals == null) {
			if (other.approvals != null)
				return false;
		} else if (!approvals.equals(other.approvals))
			return false;
		if (changeRequests == null) {
			if (other.changeRequests != null)
				return false;
		} else if (!changeRequests.equals(other.changeRequests))
			return false;
		if (infoRequests == null) {
			if (other.infoRequests != null)
				return false;
		} else if (!infoRequests.equals(other.infoRequests))
			return false;
		if (infoResponses == null) {
			if (other.infoResponses != null)
				return false;
		} else if (!infoResponses.equals(other.infoResponses))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (personID == null) {
			if (other.personID != null)
				return false;
		} else if (!personID.equals(other.personID))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (rejections == null) {
			if (other.rejections != null)
				return false;
		} else if (!rejections.equals(other.rejections))
			return false;
		if (roleID == null) {
			if (other.roleID != null)
				return false;
		} else if (!roleID.equals(other.roleID))
			return false;
		if (storyPitches == null) {
			if (other.storyPitches != null)
				return false;
		} else if (!storyPitches.equals(other.storyPitches))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [personID=" + personID + ", name=" + name + ", password=" + password + ", roleID=" + roleID
				+ ", points=" + points + ", username=" + username + ", storyPitches=" + storyPitches + ", approvals="
				+ approvals + ", rejections=" + rejections + ", infoRequests=" + infoRequests + ", infoResponses="
				+ infoResponses + ", changeRequests=" + changeRequests + "]";
	}

	

}
