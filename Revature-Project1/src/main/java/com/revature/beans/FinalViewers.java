package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Table
@Entity(name="final_approval_viewers")
public class FinalViewers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="viewer_id")
	private int id;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="editor_id")
	private Editor editor;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="approval_id")
	private StoryPitch storyPitch;
	
	public FinalViewers(){
		id = 0;
		editor = null;
		storyPitch = null;
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

	public StoryPitch getStoryPitch() {
		return storyPitch;
	}

	public void setStoryPitch(StoryPitch storyPitch) {
		this.storyPitch = storyPitch;
	}

	@Override
	public String toString() {
		return "FinalViewers [id=" + id + ", editor=" + editor + ", storyPitch=" + storyPitch + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((editor == null) ? 0 : editor.hashCode());
		result = prime * result + id;
		result = prime * result + ((storyPitch == null) ? 0 : storyPitch.hashCode());
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
		FinalViewers other = (FinalViewers) obj;
		if (editor == null) {
			if (other.editor != null)
				return false;
		} else if (!editor.equals(other.editor))
			return false;
		if (id != other.id)
			return false;
		if (storyPitch == null) {
			if (other.storyPitch != null)
				return false;
		} else if (!storyPitch.equals(other.storyPitch))
			return false;
		return true;
	}
	
	
}
