package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

public class GenreCommittee {
	private Genre genre;
	private String name;
	private Set<Editor> editorsInTheCommittee;
	
	public GenreCommittee() {
		genre = new Genre();
		name = "";
		editorsInTheCommittee = new HashSet<Editor>();
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
	
	public void addAnEditor(Editor editor) {
		editorsInTheCommittee.add(editor);
	}
	
	public Editor retrieveAnEditor(int id) {
		for (Editor e: editorsInTheCommittee) {
			if (e.getId() == id) {
				return e;
			}
		}
		
		return null;
	}
	
	public boolean removeAnEditor(int id) {
		for (Editor e: editorsInTheCommittee) {
			if (e.getId() == id) {
				return editorsInTheCommittee.remove(e);
			}
		}
		
		return false;
	}

	/**
	 * @return the editorsInTheCommittee
	 */
	public Set<Editor> getEditorsInTheCommittee() {
		return editorsInTheCommittee;
	}

	/**
	 * @param editorsInTheCommittee the editorsInTheCommittee to set
	 */
	public void setEditorsInTheCommittee(Set<Editor> editorsInTheCommittee) {
		this.editorsInTheCommittee = editorsInTheCommittee;
	}
	
	@Override
	public String toString() {
		String allEditors = "";
		for (Editor e: editorsInTheCommittee) {
			allEditors += e.getName() + "\n";
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
