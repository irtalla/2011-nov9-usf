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
@Table (name = "draft",schema = "\"StoryBoard\"")
public class Draft {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="story_id")
	private Story story_id;
	private String draft;
	private boolean approved;
	public Draft() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Draft(int id, Story story_id, String draft) {
		super();
		this.id = id;
		this.story_id = story_id;
		this.draft = draft;
		this.approved = false;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Story getStory_id() {
		return story_id;
	}
	public void setStory_id(Story story_id) {
		this.story_id = story_id;
	}
	public String getDraft() {
		return draft;
	}
	public void setDraft(String draft) {
		this.draft = draft;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "Draft [id=" + id + ", story_id=" + story_id + ", draft=" + draft + ", approved=" + approved + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + ((draft == null) ? 0 : draft.hashCode());
		result = prime * result + id;
		result = prime * result + ((story_id == null) ? 0 : story_id.hashCode());
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
		Draft other = (Draft) obj;
		if (approved != other.approved)
			return false;
		if (draft == null) {
			if (other.draft != null)
				return false;
		} else if (!draft.equals(other.draft))
			return false;
		if (id != other.id)
			return false;
		if (story_id == null) {
			if (other.story_id != null)
				return false;
		} else if (!story_id.equals(other.story_id))
			return false;
		return true;
	}
	
	
}
