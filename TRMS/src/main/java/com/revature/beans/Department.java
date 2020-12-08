package com.revature.beans;

public class Department {
	private int id;
	private String name;
	private int departmentHeadId;
	private int bencoId;
	
	public Department()
	{
		id = 1;
		name = "Galactic Core";
		departmentHeadId = 3;
		bencoId = 4;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDepartmentHeadId() {
		return departmentHeadId;
	}
	public void setDepartmentHeadId(int departmentHeadId) {
		this.departmentHeadId = departmentHeadId;
	}
	public int getBencoId() {
		return bencoId;
	}
	public void setBencoId(int bencoId) {
		this.bencoId = bencoId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bencoId;
		result = prime * result + departmentHeadId;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Department other = (Department) obj;
		if (bencoId != other.bencoId)
			return false;
		if (departmentHeadId != other.departmentHeadId)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
