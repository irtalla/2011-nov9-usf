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
@Table (name = "draft_status_other",schema = "\"StoryBoard\"")
public class Draft_Status_Other {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="editor_id")
	private Editor editor;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="approval_id")
	private Approval_Other approval_other;
	private boolean approved;
	public Draft_Status_Other() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Draft_Status_Other(int id, Editor editor, Approval_Other approval_other) {
		super();
		this.id = id;
		this.editor = editor;
		this.approval_other = approval_other;
		this.approved = false;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Editor getEditor() {
		return editor;
	}
	public void setEditor(Editor editor) {
		this.editor = editor;
	}
	public Approval_Other getApproval_other() {
		return approval_other;
	}
	public void setApproval_other(Approval_Other approval_other) {
		this.approval_other = approval_other;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "Draft_Status_Other [id=" + id + ", editor=" + editor + ", approval_other=" + approval_other
				+ ", approved=" + approved + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approval_other == null) ? 0 : approval_other.hashCode());
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + ((editor == null) ? 0 : editor.hashCode());
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
		Draft_Status_Other other = (Draft_Status_Other) obj;
		if (approval_other == null) {
			if (other.approval_other != null)
				return false;
		} else if (!approval_other.equals(other.approval_other))
			return false;
		if (approved != other.approved)
			return false;
		if (editor == null) {
			if (other.editor != null)
				return false;
		} else if (!editor.equals(other.editor))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
