package dev.warrington.beans;

import java.util.Set;

public class Committee {

	private Set<Person> employees;
	private Genre genre;
	
	public Set<Person> getEmployees() {
		return employees;
	}
	
	public void setEmployees(Set<Person> employees) {
		this.employees = employees;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
}