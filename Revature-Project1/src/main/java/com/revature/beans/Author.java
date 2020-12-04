package com.revature.beans;

public class Author extends User{
	private String name;
	private int pointsRemaining;
	
	public Author() {
		super("author");

		name = "";
		pointsRemaining = 100;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the pointsRemaining
	 */
	public int getPointsRemaining() {
		return pointsRemaining;
	}

	/**
	 * @param pointsRemaining the pointsRemaining to set
	 */
	public void setPointsRemaining(int pointsRemaining) {
		this.pointsRemaining = pointsRemaining;
	}
	
	@Override
	public String toString() {
		return "Author " + name + " has " + pointsRemaining + " points remaining";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pointsRemaining;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pointsRemaining != other.pointsRemaining)
			return false;
		return true;
	}
	
	

	
	
}
