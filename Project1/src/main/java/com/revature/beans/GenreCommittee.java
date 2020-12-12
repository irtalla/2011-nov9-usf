package com.revature.beans;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="genre_committee")
public class GenreCommittee {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="genre_committee_membership",
		joinColumns= { @JoinColumn(name="genre_committee_id") },
		inverseJoinColumns= { @JoinColumn(name="editor_id") }
	)
	private Set<Person> members;
}
