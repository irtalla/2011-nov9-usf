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
public class Author{
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User userInfo;

	@Id
	@Column(name="author_id")
	private int authorId;
	
	@Column(name="author_name")
	private String name;
	
	@Column(name="points_remaining")
	private int pointsRemaining;
	
	public Author() {
		userInfo = null;
		name = "";
		authorId = 0;
		pointsRemaining = 100;
	}
	
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int id) {
		this.authorId = id;
	}
	
	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
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
		int result = 1;
		result = prime * result + authorId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pointsRemaining;
		result = prime * result + ((userInfo == null) ? 0 : userInfo.hashCode());
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
		Author other = (Author) obj;
		if (authorId != other.authorId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pointsRemaining != other.pointsRemaining)
			return false;
		if (userInfo == null) {
			if (other.userInfo != null)
				return false;
		} else if (!userInfo.equals(other.userInfo))
			return false;
		return true;
	}
	
	

	
	
}
