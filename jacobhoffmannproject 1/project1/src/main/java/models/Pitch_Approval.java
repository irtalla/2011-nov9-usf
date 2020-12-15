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
@Table (name = "pitch_approval",schema = "\"StoryBoard\"")
public class Pitch_Approval {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="editor")
	private Editor editor;
	private boolean approved;
	public Pitch_Approval() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pitch_Approval(int id, Editor editor) {
		super();
		this.id = id;
		this.editor = editor;
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
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "Pitch_Approval [id=" + id + ", editor=" + editor + ", approved=" + approved + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Pitch_Approval other = (Pitch_Approval) obj;
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
