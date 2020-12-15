package com.revature.beans;

public class GenreSpecialization {
	
	private Integer genreSpecID;
	private String genreName;
	private Integer genreID;
	private Integer personID;
	
	public GenreSpecialization() {
		super();
		this.genreSpecID = 0;
		this.genreName = "";
		this.genreID = 0;
		this.personID = 0;
	}

	public GenreSpecialization(Integer genreSpecID, String genreName, Integer genreID, Integer personID) {
		super();
		this.genreSpecID = genreSpecID;
		this.genreName = genreName;
		this.genreID = genreID;
		this.personID = personID;
	}

	public Integer getGenreSpecID() {
		return genreSpecID;
	}

	public void setGenreSpecID(Integer genreSpecID) {
		this.genreSpecID = genreSpecID;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Integer getGenreID() {
		return genreID;
	}

	public void setGenreID(Integer genreID) {
		this.genreID = genreID;
	}

	public Integer getPersonID() {
		return personID;
	}

	public void setPersonID(Integer personID) {
		this.personID = personID;
	}
	
	

}
