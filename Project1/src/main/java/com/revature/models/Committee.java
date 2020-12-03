package com.revature.models;

import java.util.HashSet;
import java.util.Set;

public class Committee {
	private Integer id;
	private String name;
	private Genre genre;
	private Set<User> editors;
	
	public Committee() {
		id = 0;
		name = " Comittee";
		genre = new Genre();
		editors = new HashSet<>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Genre getGenre() {
		return genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public Set<User> getEditors() {
		return editors;
	}
	
	public void setEditors(Set<User> editors) {
		this.editors = editors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((editors == null) ? 0 : editors.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Committee other = (Committee) obj;
		if (editors == null) {
			if (other.editors != null)
				return false;
		} else if (!editors.equals(other.editors))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Committee [editors=" + editors + ", genre=" + genre + ", id=" + id + ", name=" + name + "]";
	}
	
}
