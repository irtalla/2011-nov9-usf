 package com.revature.beans;

 import java.util.Set;
 import javax.persistence.*;
 import java.util.HashSet;

 @Entity
 @Table
public class Person {
 	@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	@Column(name = "pass")
	private String password;
	private Integer points;
	@Column(name = "author_info")
	private String authorInfo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name="genre_committee_id"))
	private Set<GenreCommittee> genreCommittees;
	//@OneToMany(fetch = FetchType.EAGER)
	//@JoinColumn(name = "to_person_id")
	//private Set<Request> requests;

	public Person() {
		id = 0;
		username = "";
		password = "";
		points = 100;
		authorInfo = "";
		role = new Role();
		//requestInbox = new HashSet<Request>();
		//pitches = new HashSet<Pitch>();
		//drafts = new HashSet<Draft>();
		//requests = new HashSet<Request>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getAuthorInfo() {
		return authorInfo;
	}

	public void setAuthorInfo(String authorInfo) {
		this.authorInfo = authorInfo;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
/*
	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}
*/
	public Set<GenreCommittee> getGenreCommittees() {
		return genreCommittees;
	}

	public void setGenreCommittees(Set<GenreCommittee> genreCommittees) {
		this.genreCommittees = genreCommittees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorInfo == null) ? 0 : authorInfo.hashCode());
		result = prime * result + ((genreCommittees == null) ? 0 : genreCommittees.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		//result = prime * result + ((requests == null) ? 0 : requests.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		if (authorInfo == null) {
			if (other.authorInfo != null)
				return false;
		} else if (!authorInfo.equals(other.authorInfo))
			return false;
		if (genreCommittees == null) {
			if (other.genreCommittees != null)
				return false;
		} else if (!genreCommittees.equals(other.genreCommittees))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		/*if (requests == null) {
			if (other.requests != null)
				return false;
		} else if (!requests.equals(other.requests))
			return false;*/
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
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
		return "Person [id=" + id + ", username=" + username + ", password=" + password + ", points=" + points
				+ ", authorInfo=" + authorInfo + ", role=" + role + ", genreCommittees=" + genreCommittees + "]";
	}

 }
