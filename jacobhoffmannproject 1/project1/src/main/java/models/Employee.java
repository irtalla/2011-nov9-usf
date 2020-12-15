package models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name = "employee",schema = "\"StoryBoard\"")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private boolean is_editor;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private Users user;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int id, boolean is_editor, Users user) {
		super();
		this.id = id;
		this.is_editor = is_editor;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isIs_editor() {
		return is_editor;
	}
	public void setIs_editor(boolean is_editor) {
		this.is_editor = is_editor;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", is_editor=" + is_editor + ", user=" + user + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (is_editor ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		if (is_editor != other.is_editor)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
