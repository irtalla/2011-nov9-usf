package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "story_type",schema = "\"StoryBoard\"")
public class Story_Type {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int cost;
	private String type_name;
	public Story_Type(int id, int cost, String type_name) {
		super();
		this.id = id;
		this.cost = cost;
		this.type_name = type_name;
	}
	public Story_Type() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	@Override
	public String toString() {
		return "Story_Type [id=" + id + ", cost=" + cost + ", type_name=" + type_name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + id;
		result = prime * result + ((type_name == null) ? 0 : type_name.hashCode());
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
		Story_Type other = (Story_Type) obj;
		if (cost != other.cost)
			return false;
		if (id != other.id)
			return false;
		if (type_name == null) {
			if (other.type_name != null)
				return false;
		} else if (!type_name.equals(other.type_name))
			return false;
		return true;
	}
	
}
