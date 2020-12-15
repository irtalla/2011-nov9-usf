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
@Table (name = "employee",schema = "\"StoryBoard\"")
public class Draft_Status_Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="approval")
	private Draft_Approval approval;
	private boolean complete;
	public Draft_Status_Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Draft_Status_Article(int id, Draft_Approval approval) {
		super();
		this.id = id;
		this.approval = approval;
		this.complete = false;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Draft_Approval getApproval() {
		return approval;
	}
	public void setApproval(Draft_Approval approval) {
		this.approval = approval;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	@Override
	public String toString() {
		return "Draft_Status_Article [id=" + id + ", approval=" + approval + ", complete=" + complete + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approval == null) ? 0 : approval.hashCode());
		result = prime * result + (complete ? 1231 : 1237);
		result = prime * result + id;
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
		Draft_Status_Article other = (Draft_Status_Article) obj;
		if (approval == null) {
			if (other.approval != null)
				return false;
		} else if (!approval.equals(other.approval))
			return false;
		if (complete != other.complete)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
}
