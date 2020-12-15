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
@Table (name = "approval_other",schema = "\"StoryBoard\"")
public class Approval_Other {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="draft_id")
	private Draft draft;
	private boolean approved;
	private String reject_reason;
	public Approval_Other() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Approval_Other(int id, Draft draft, String reject_reason) {
		super();
		this.id = id;
		this.draft = draft;
		this.approved = false;
		this.reject_reason = reject_reason;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Draft getDraft() {
		return draft;
	}
	public void setDraft(Draft draft) {
		this.draft = draft;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getReject_reason() {
		return reject_reason;
	}
	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}
	@Override
	public String toString() {
		return "Approval_Other [id=" + id + ", draft=" + draft + ", approved=" + approved + ", reject_reason="
				+ reject_reason + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + ((draft == null) ? 0 : draft.hashCode());
		result = prime * result + id;
		result = prime * result + ((reject_reason == null) ? 0 : reject_reason.hashCode());
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
		Approval_Other other = (Approval_Other) obj;
		if (approved != other.approved)
			return false;
		if (draft == null) {
			if (other.draft != null)
				return false;
		} else if (!draft.equals(other.draft))
			return false;
		if (id != other.id)
			return false;
		if (reject_reason == null) {
			if (other.reject_reason != null)
				return false;
		} else if (!reject_reason.equals(other.reject_reason))
			return false;
		return true;
	}
	
}
