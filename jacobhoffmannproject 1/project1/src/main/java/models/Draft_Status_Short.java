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
@Table (name = "draft_status_short",schema = "\"StoryBoard\"")
public class Draft_Status_Short {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="senior_approval")
	private Draft_Approval senior_draft;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="other_approval")
	private Draft_Approval other_draft;
	private boolean complete;
	public Draft_Status_Short() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Draft_Status_Short(int id, Draft_Approval senior_draft, Draft_Approval other_draft) {
		super();
		this.id = id;
		this.senior_draft = senior_draft;
		this.other_draft = other_draft;
		this.complete = false;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Draft_Approval getSenior_draft() {
		return senior_draft;
	}
	public void setSenior_draft(Draft_Approval senior_draft) {
		this.senior_draft = senior_draft;
	}
	public Draft_Approval getOther_draft() {
		return other_draft;
	}
	public void setOther_draft(Draft_Approval other_draft) {
		this.other_draft = other_draft;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	@Override
	public String toString() {
		return "Draft_Status_Short [id=" + id + ", senior_draft=" + senior_draft + ", other_draft=" + other_draft
				+ ", complete=" + complete + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (complete ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((other_draft == null) ? 0 : other_draft.hashCode());
		result = prime * result + ((senior_draft == null) ? 0 : senior_draft.hashCode());
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
		Draft_Status_Short other = (Draft_Status_Short) obj;
		if (complete != other.complete)
			return false;
		if (id != other.id)
			return false;
		if (other_draft == null) {
			if (other.other_draft != null)
				return false;
		} else if (!other_draft.equals(other.other_draft))
			return false;
		if (senior_draft == null) {
			if (other.senior_draft != null)
				return false;
		} else if (!senior_draft.equals(other.senior_draft))
			return false;
		return true;
	}
	
	
}
