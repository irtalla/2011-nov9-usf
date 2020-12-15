package beans;

import java.util.Set;

import javax.persistence.*;

public class Committee {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCom_genre() {
		return com_genre;
	}
	public void setCom_genre(Integer com_genre) {
		this.com_genre = com_genre;
	}
	@Column(name="name")
	private String name;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="genres_id")
	private Integer com_genre;
}
