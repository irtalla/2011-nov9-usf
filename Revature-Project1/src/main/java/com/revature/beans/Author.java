package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="authors")
public class Author extends User{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="author_id")
	@OneToOne(fetch=FetchType.LAZY)
	@JoinTable(name="all_users", 
			joinColumns=@JoinColumn(name="author_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	private int id;
	
	@Column(name="author_name")
	private String name;
	
	@Column(name="points_remaining")
	private int pointsRemaining;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		result = prime * result + id;
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
		if (id != other.id)
			return false;
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
