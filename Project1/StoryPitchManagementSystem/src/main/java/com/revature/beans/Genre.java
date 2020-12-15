package com.revature.beans;

public class Genre {
	private Integer genreID;
	private String genreName;
	
	
	
	public Genre() {
		super();
		this.genreID = 0;
		this.genreName = "";
	}



	public Genre(Integer genreID, String genreName) {
		super();
		this.genreID = genreID;
		this.genreName = genreName;
	}



	public Integer getGenreID() {
		return genreID;
	}



	public void setGenreID(Integer genreID) {
		this.genreID = genreID;
	}



	public String getGenreName() {
		return genreName;
	}



	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genreID == null) ? 0 : genreID.hashCode());
		result = prime * result + ((genreName == null) ? 0 : genreName.hashCode());
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
		Genre other = (Genre) obj;
		if (genreID == null) {
			if (other.genreID != null)
				return false;
		} else if (!genreID.equals(other.genreID))
			return false;
		if (genreName == null) {
			if (other.genreName != null)
				return false;
		} else if (!genreName.equals(other.genreName))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Genre [genreID=" + genreID + ", genreName=" + genreName + "]";
	}
	
	

}
