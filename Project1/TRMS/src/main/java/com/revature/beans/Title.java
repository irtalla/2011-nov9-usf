package com.revature.beans;

public class Title {
	private Integer id;
	private String name;
	private Integer person_id;
	
	public Title() {
		id = 0;
		name = "";
		person_id = 0;
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

	public Integer getPerson_id() {
		return person_id;
	}




	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((person_id == null) ? 0 : person_id.hashCode());
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
		Title other = (Title) obj;
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
		if (person_id == null) {
			if (other.person_id != null)
				return false;
		} else if (!person_id.equals(other.person_id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Title [id=" + id + ", name=" + name + ", person_id=" + person_id + "]";
	}

	

}
