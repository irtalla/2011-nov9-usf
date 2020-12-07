package com.revature.beans;

public class Bicycle {
	private Integer id;
	private String modelName;
	private Category category;
	private Status avaliable;
	
	public Bicycle() {
		id = 0;
		modelName = "";
		category = new Category();
		avaliable = new Status();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Status getStatus() {
		return avaliable;
	}
	
	public void setStatus(Status status) {
		this.avaliable = status;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avaliable == null) ? 0 : avaliable.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
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
		Bicycle other = (Bicycle) obj;
		if (avaliable == null) {
			if (other.avaliable != null)
				return false;
		} else if (!avaliable.equals(other.avaliable))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bicycle [id=" + id +  ", modelName=" + modelName + ", category=" + category 
				+ ", Avaliability = " + avaliable.getStatus() + "]";
	}
	
}
