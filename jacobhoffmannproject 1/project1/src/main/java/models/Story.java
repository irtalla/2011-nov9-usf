package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "story",schema = "\"StoryBoard\"")
public class Story {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="stype")
	private Story_Type stype;
	private String completion_date;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="genre_type")
	private Genre genre_type;
	private String tag_line;
	private String description;
	private String title;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="author_id")
	private Author author;

	public Story() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Story(int id, Story_Type stype, String completion_date, Genre genre_type, String tag_line,
			String description, String title, Author author) {
		super();
		this.id = id;
		this.stype = stype;
		this.completion_date = completion_date;
		this.genre_type = genre_type;
		this.tag_line = tag_line;
		this.description = description;
		this.title = title;
		this.author = author;
		//this.status_id = status_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Story_Type getStype() {
		return stype;
	}
	public void setStype(Story_Type stype) {
		this.stype = stype;
	}
	public String getCompletion_date() {
		return completion_date;
	}
	public void setCompletion_date(String completion_date) {
		this.completion_date = completion_date;
	}
	public Genre getGenre_type() {
		return genre_type;
	}
	public void setGenre_type(Genre genre_type) {
		this.genre_type = genre_type;
	}
	public String getTag_line() {
		return tag_line;
	}
	public void setTag_line(String tag_line) {
		this.tag_line = tag_line;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Story [id=" + id + ", stype=" + stype + ", completion_date=" + completion_date + ", genre_type="
				+ genre_type + ", tag_line=" + tag_line + ", description=" + description + ", title=" + title
				+ ", author=" + author + ", status_id="  + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((completion_date == null) ? 0 : completion_date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((genre_type == null) ? 0 : genre_type.hashCode());
		result = prime * result + id;
		result = prime * result + ((stype == null) ? 0 : stype.hashCode());
		result = prime * result + ((tag_line == null) ? 0 : tag_line.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Story other = (Story) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (completion_date == null) {
			if (other.completion_date != null)
				return false;
		} else if (!completion_date.equals(other.completion_date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (genre_type == null) {
			if (other.genre_type != null)
				return false;
		} else if (!genre_type.equals(other.genre_type))
			return false;
		if (id != other.id)
			return false;
		if (stype == null) {
			if (other.stype != null)
				return false;
		} else if (!stype.equals(other.stype))
			return false;
		if (tag_line == null) {
			if (other.tag_line != null)
				return false;
		} else if (!tag_line.equals(other.tag_line))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}
