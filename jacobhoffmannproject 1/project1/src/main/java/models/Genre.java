package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "genre",schema = "\"StoryBoard\"")
public class Genre {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String genre;
	private boolean nonfiction;
	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Genre(int id, String genre, boolean nonfiction) {
		super();
		this.id = id;
		this.genre = genre;
		this.nonfiction = nonfiction;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public boolean isNonfiction() {
		return nonfiction;
	}
	public void setNonfiction(boolean nonfiction) {
		this.nonfiction = nonfiction;
	}
	@Override
	public String toString() {
		return "Genre [id=" + id + ", genre=" + genre + ", nonfiction=" + nonfiction + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + id;
		result = prime * result + (nonfiction ? 1231 : 1237);
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
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (nonfiction != other.nonfiction)
			return false;
		return true;
	}
	
	
}
