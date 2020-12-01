package com.revature.models;

public class StoryType {
	private Integer id;
	private String name;
	private Integer numEditorApprovalRequired;
	
	public StoryType() {
		id = 0;
		name = "";
		numEditorApprovalRequired = 1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumEditorApprovalRequired() {
		return numEditorApprovalRequired;
	}

	public void setNumEditorApprovalRequired(Integer numEditorApprovalRequired) {
		this.numEditorApprovalRequired = numEditorApprovalRequired;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numEditorApprovalRequired == null) ? 0 : numEditorApprovalRequired.hashCode());
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
		StoryType other = (StoryType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numEditorApprovalRequired == null) {
			if (other.numEditorApprovalRequired != null)
				return false;
		} else if (!numEditorApprovalRequired.equals(other.numEditorApprovalRequired))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StoryType [id=" + id + ", name=" + name + ", numEditorApprovalRequired=" + numEditorApprovalRequired
				+ "]";
	}
	
}
