package com.revature.beans;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.type.LocalDateTimeType;
@Entity
@Table
public class Pitch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person author;
	private String story_title;
	private LocalDateTimeType finish_date;  //Date from java Util.  Might need to be Sql
	@ManyToOne
	@JoinColumn(name = "storytype_id")
	private StoryType story_type;
	@ManyToOne
	@JoinColumn(name = "genre_id")
	private Genre genre;
	
	private String description; //consider file type and adding files
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;
	@ManyToOne
	@JoinColumn(name = "pitch_priority_id")
	private PitchPriority priority;
	@ManyToOne
	@JoinColumn(name = "pitch_stage_id")
	private PitchStage stage;

	
	
	public Pitch() {
		id = 0;
		author = new Person();
		story_title ="";
		finish_date = new LocalDateTimeType();
		story_type = new StoryType();
		genre = new Genre();
		description = "";
		status = new Status();
		priority = new PitchPriority();
		stage = new PitchStage();
	}
	public Person getAuthor() {
		return author;
	}
	public void setAuthor(Person author) {
		this.author = author;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStory_title() {
		return story_title;
	}
	public void setStory_title(String story_title) {
		this.story_title = story_title;
	}

	public LocalDateTimeType getFinish_date() {
		return finish_date;
	}
	public void setFinish_date(LocalDateTimeType finish_date) {
		this.finish_date = finish_date;
	}
	public StoryType getStory_type() {
		return story_type;
	}
	public void setStory_type(StoryType story_type) {
		this.story_type = story_type;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public PitchPriority getPriority() {
		return priority;
	}
	public void setPriority(PitchPriority priority) {
		this.priority = priority;
	}
	public PitchStage getStage() {
		return stage;
	}
	public void setStage(PitchStage stage) {
		this.stage = stage;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((finish_date == null) ? 0 : finish_date.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((story_title == null) ? 0 : story_title.hashCode());
		result = prime * result + ((story_type == null) ? 0 : story_type.hashCode());
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
		Pitch other = (Pitch) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (finish_date == null) {
			if (other.finish_date != null)
				return false;
		} else if (!finish_date.equals(other.finish_date))
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
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (story_title == null) {
			if (other.story_title != null)
				return false;
		} else if (!story_title.equals(other.story_title))
			return false;
		if (story_type == null) {
			if (other.story_type != null)
				return false;
		} else if (!story_type.equals(other.story_type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pitch id:" + id + " by " + author +
				"\n story_title: " + story_title + ", projected finish date: " + finish_date + 
				"\n In the genre of " + genre.getName()
				+ "\n description=" + description;
	}
	
	
}
