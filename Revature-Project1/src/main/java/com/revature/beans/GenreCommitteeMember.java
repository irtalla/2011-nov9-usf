package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="genre_committee_members")
public class GenreCommitteeMember {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="genre_committee_member_id")
	private int id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="genre_committee_id")
	private GenreCommittee genreCommittee;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="editor_id")
	private Editor editor;
	
	@Column(name="editor_position")
	private String editorType;
	
	public GenreCommitteeMember() {
		genreCommittee = null;
		editor = null;
		editorType = "";
	}

	public GenreCommittee getGenreCommittee() {
		return genreCommittee;
	}

	public void setGenreCommittee(GenreCommittee genreCommittee) {
		this.genreCommittee = genreCommittee;
	}

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	public String getEditorType() {
		return editorType;
	}

	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}
	
	@Override
	public String toString() {
		return editor.getName() + " is in " + genreCommittee.getName() + " as a " + editorType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((editor == null) ? 0 : editor.hashCode());
		result = prime * result + ((editorType == null) ? 0 : editorType.hashCode());
		result = prime * result + ((genreCommittee == null) ? 0 : genreCommittee.hashCode());
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
		GenreCommitteeMember other = (GenreCommitteeMember) obj;
		if (editor == null) {
			if (other.editor != null)
				return false;
		} else if (!editor.equals(other.editor))
			return false;
		if (editorType == null) {
			if (other.editorType != null)
				return false;
		} else if (!editorType.equals(other.editorType))
			return false;
		if (genreCommittee == null) {
			if (other.genreCommittee != null)
				return false;
		} else if (!genreCommittee.equals(other.genreCommittee))
			return false;
		return true;
	}
	
	
}
