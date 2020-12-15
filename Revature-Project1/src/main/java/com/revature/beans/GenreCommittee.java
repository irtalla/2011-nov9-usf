package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="genre_committee")
public class GenreCommittee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="genre_committee_id")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="genre_id")
	private Genre genre;
	
	@Column(name="genre_committee_name")
	private String name;
	
	/*
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="genre_committee_table",
			joinColumns=@JoinColumn(name="genre_committee_id"),
			inverseJoinColumns=@JoinColumn(name="genre_commitee_id"))
	*/
	@Transient
	private Set<GenreCommitteeMember> editorsInTheCommittee;
	
	public GenreCommittee() {
		genre = new Genre();
		name = "";
		editorsInTheCommittee = new HashSet<GenreCommitteeMember>();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * @param genre the genre to set
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public void addAnEditor(GenreCommitteeMember editor) {
		editorsInTheCommittee.add(editor);
	}
	
	public GenreCommitteeMember retrieveAnEditor(int id) {
		for (GenreCommitteeMember gcm: editorsInTheCommittee) {
			if (gcm.getEditor().getEditorId() == id) {
				return gcm;
			}
		}
		
		return null;
	}
	
	public boolean removeAnEditor(int id) {
		for (GenreCommitteeMember gcm: editorsInTheCommittee) {
			if (gcm.getEditor().getEditorId() == id) {
				return editorsInTheCommittee.remove(gcm);
			}
		}
		
		return false;
	}

	/**
	 * @return the editorsInTheCommittee
	 */
	public Set<GenreCommitteeMember> getEditorsInTheCommittee() {
		return editorsInTheCommittee;
	}

	/**
	 * @param editorsInTheCommittee the editorsInTheCommittee to set
	 */
	public void setEditorsInTheCommittee(Set<GenreCommitteeMember> editorsInTheCommittee) {
		this.editorsInTheCommittee = editorsInTheCommittee;
	}
	
	@Override
	public String toString() {
		String allEditors = "";
		for (GenreCommitteeMember gcm: editorsInTheCommittee) {
			allEditors += gcm.getEditor().getName() + "\n";
		}
		return "The genre committee " + name + " has these editors: \n" + allEditors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((editorsInTheCommittee == null) ? 0 : editorsInTheCommittee.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		GenreCommittee other = (GenreCommittee) obj;
		if (editorsInTheCommittee == null) {
			if (other.editorsInTheCommittee != null)
				return false;
		} else if (!editorsInTheCommittee.equals(other.editorsInTheCommittee))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
