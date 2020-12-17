package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="genre_committee")
public class GenreCommittee {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private Genre genre;
	
//	@ManyToMany(fetch=FetchType.LAZY)
//	@JoinTable(
//		name="genre_committee_membership",
//		joinColumns= { @JoinColumn(name="genre_committee_id") },
//		inverseJoinColumns= { @JoinColumn(name="editor_id") }
//	)
	@Transient
	private Set<Person> members;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Transient
	public Set<Person> getMembers() {
		return members;
	}
	@Transient
	public void setMembers(Set<Person> members) {
		this.members = members;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public Set<Person> getSeniorEditors(){
		Set<Person> seniorEditors = new HashSet<>();
		for(Person p : this.members) {
			if(p.getRole().equals(Role.SENIOR_EDITOR)) {
				seniorEditors.add(p);
			}
		}
		
		return seniorEditors;
	}
}
