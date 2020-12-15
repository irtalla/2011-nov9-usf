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
@Table (name = "pitch_status",schema = "\"StoryBoard\"")
public class Pitch_Status {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="story_id")
	private Story story;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="genre_status")
	private Pitch_Approval genre_status;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="outside_status")
	private Pitch_Approval outside_status;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="assitant_status")
	private Pitch_Approval assitant_status;
	private boolean approved;
	private String description;
	private int priority;
	public Pitch_Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pitch_Status(int id, Story story, Pitch_Approval genre_status, Pitch_Approval outside_status,
			Pitch_Approval assitant_status, String description) {
		super();
		this.id = id;
		this.story = story;
		this.genre_status = genre_status;
		this.outside_status = outside_status;
		this.assitant_status = assitant_status;
		this.approved = false;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Story getStory() {
		return story;
	}
	public void setStory(Story story) {
		this.story = story;
	}
	public Pitch_Approval getGenre_status() {
		return genre_status;
	}
	public void setGenre_status(Pitch_Approval genre_status) {
		this.genre_status = genre_status;
	}
	public Pitch_Approval getOutside_status() {
		return outside_status;
	}
	public void setOutside_status(Pitch_Approval outside_status) {
		this.outside_status = outside_status;
	}
	public Pitch_Approval getAssitant_status() {
		return assitant_status;
	}
	public void setAssitant_status(Pitch_Approval assitant_status) {
		this.assitant_status = assitant_status;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + ((assitant_status == null) ? 0 : assitant_status.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((genre_status == null) ? 0 : genre_status.hashCode());
		result = prime * result + id;
		result = prime * result + ((outside_status == null) ? 0 : outside_status.hashCode());
		result = prime * result + priority;
		result = prime * result + ((story == null) ? 0 : story.hashCode());
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
		Pitch_Status other = (Pitch_Status) obj;
		if (approved != other.approved)
			return false;
		if (assitant_status == null) {
			if (other.assitant_status != null)
				return false;
		} else if (!assitant_status.equals(other.assitant_status))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (genre_status == null) {
			if (other.genre_status != null)
				return false;
		} else if (!genre_status.equals(other.genre_status))
			return false;
		if (id != other.id)
			return false;
		if (outside_status == null) {
			if (other.outside_status != null)
				return false;
		} else if (!outside_status.equals(other.outside_status))
			return false;
		if (priority != other.priority)
			return false;
		if (story == null) {
			if (other.story != null)
				return false;
		} else if (!story.equals(other.story))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Pitch_Status [id=" + id + ", story=" + story + ", genre_status=" + genre_status + ", outside_status="
				+ outside_status + ", assitant_status=" + assitant_status + ", approved=" + approved + ", description="
				+ description + ", priority=" + priority + "]";
	}
	
}
