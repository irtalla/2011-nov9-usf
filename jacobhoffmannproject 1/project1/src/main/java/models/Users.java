package models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "users",schema = "\"StoryBoard\"")
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String passwords;
	private boolean is_employee;
	private String email;
	private String cellphone;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(int id, String username, String passwords, String email, String cellphone) {
		super();
		this.id = id;
		this.username = username;
		this.passwords = passwords;
		this.is_employee = false;
		this.email = email;
		this.cellphone = cellphone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswords() {
		return passwords;
	}
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
	public boolean isIs_employee() {
		return is_employee;
	}
	public void setIs_employee(boolean is_employee) {
		this.is_employee = is_employee;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cellphone == null) ? 0 : cellphone.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + (is_employee ? 1231 : 1237);
		result = prime * result + ((passwords == null) ? 0 : passwords.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Users other = (Users) obj;
		if (cellphone == null) {
			if (other.cellphone != null)
				return false;
		} else if (!cellphone.equals(other.cellphone))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (is_employee != other.is_employee)
			return false;
		if (passwords == null) {
			if (other.passwords != null)
				return false;
		} else if (!passwords.equals(other.passwords))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", passwords=" + passwords + ", is_employee="
				+ is_employee + ", email=" + email + ", cellphone=" + cellphone + "]";
	}
	
	
}
