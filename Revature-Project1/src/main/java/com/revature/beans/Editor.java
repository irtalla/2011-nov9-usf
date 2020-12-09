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
@Table(name="editors")
public class Editor extends User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="editor_id")
	@OneToOne(fetch=FetchType.LAZY)
	@JoinTable(name="all_users", 
			joinColumns=@JoinColumn(name="editor_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	private int id;
	
	@Column(name="editor_name")
	private String name;
	
	@JoinTable
	private String committeePosition;
	
	public Editor() {
		super("editor");
		name = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommitteePosition() {
		return committeePosition;
	}

	public void setCommitteePosition(String committeePosition) {
		this.committeePosition = committeePosition;
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
	
	@Override
	public String toString() {
		return "Editor " + name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((committeePosition == null) ? 0 : committeePosition.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Editor other = (Editor) obj;
		if (committeePosition == null) {
			if (other.committeePosition != null)
				return false;
		} else if (!committeePosition.equals(other.committeePosition))
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
