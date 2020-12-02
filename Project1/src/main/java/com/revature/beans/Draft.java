package com.revature.beans;

public class Draft {
	private Integer id;
	private String draft;
	
	public Draft() {
		id = 0;
		draft = "";
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDraft() {
		return draft;
	}

	public void setDraft(String draft) {
		this.draft = draft;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((draft == null) ? 0 : draft.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (draft == null) {
			if (other.draft != null)
				return false;
		} else if (!draft.equals(other.draft))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Draft [id=" + id + ", draft=" + draft + "]";
	}
	
	
	
	 
}
