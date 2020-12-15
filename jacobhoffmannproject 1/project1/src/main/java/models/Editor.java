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
@Table (name = "editor",schema = "\"StoryBoard\"")
public class Editor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private boolean is_assitant;
	private boolean is_senior;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="employee_id")
	private Employee employee;
	public Editor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Editor(int id, boolean is_assitant, boolean is_senior, Employee employee) {
		super();
		this.id = id;
		this.is_assitant = is_assitant;
		this.is_senior = is_senior;
		this.employee = employee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isIs_assitant() {
		return is_assitant;
	}
	public void setIs_assitant(boolean is_assitant) {
		this.is_assitant = is_assitant;
	}
	public boolean isIs_senior() {
		return is_senior;
	}
	public void setIs_senior(boolean is_senior) {
		this.is_senior = is_senior;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	@Override
	public String toString() {
		return "Editor [id=" + id + ", is_assitant=" + is_assitant + ", is_senior=" + is_senior + ", employee="
				+ employee + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + id;
		result = prime * result + (is_assitant ? 1231 : 1237);
		result = prime * result + (is_senior ? 1231 : 1237);
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
		Editor other = (Editor) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (id != other.id)
			return false;
		if (is_assitant != other.is_assitant)
			return false;
		if (is_senior != other.is_senior)
			return false;
		return true;
	}
	
	
}
