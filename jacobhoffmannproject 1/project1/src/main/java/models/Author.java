package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "author",schema = "\"StoryBoard\"")
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int current_weight;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private Users users;
	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Author(int id,Users users) {
		super();
		this.id = id;
		this.current_weight = 0;
		this.users = users;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCurrent_weight() {
		return current_weight;
	}
	public void setCurrent_weight(int current_weight) {
		this.current_weight = current_weight;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + current_weight;
		result = prime * result + id;
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		if (current_weight != other.current_weight)
			return false;
		if (id != other.id)
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", current_weight=" + current_weight + ", users=" + users + "]";
	}
	
}
