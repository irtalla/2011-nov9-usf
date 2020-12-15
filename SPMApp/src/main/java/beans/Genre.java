package beans;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name="genre")
public class Genre {
	public Integer getGenres_id() {
		return genres_id;
	}
	public void setGenres_id(Integer genres_id) {
		this.genres_id = genres_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Genre() {
		genres_id = 0;
		name = "";
	}
	@Id
	@Column(name="genres_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer genres_id;
	@Override
	public String toString() {
		return "Genre [genres_id=" + genres_id + ", name=" + name + "]";
	}
	private String name;
}
