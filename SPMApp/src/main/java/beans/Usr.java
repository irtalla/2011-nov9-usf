package beans;
import javax.persistence.*;


@Entity
@Table(name="usr")
public class Usr {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer usr_id;
	@Override
	public String toString() {
		return "Usr [usr_id=" + usr_id + ", usrname=" + usrname + ", passwd=" + passwd + ", role=" + role + ", points="
				+ points + "]";
	}
	private String usrname;
	private String passwd;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="usr_role_id")
	private Role role;
	@Column(name="points")
	private Integer points;
	
	/*
	 * public Usr(Integer id) { usr_id = id; usrname =""; passwd =""; role = new
	 * Role(); points = 0; }
	 */
	public Usr() {
		usr_id = 0;
		usrname = "";
		passwd = "";
		role = new Role();
		points = 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((passwd == null) ? 0 : passwd.hashCode());
		result = prime * result + ((usr_id == null) ? 0 : usr_id.hashCode());
		result = prime * result + ((usrname == null) ? 0 : usrname.hashCode());
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
		Usr other = (Usr) obj;
		if (passwd == null) {
			if (other.passwd != null)
				return false;
		} else if (!passwd.equals(other.passwd))
			return false;
		if (usr_id == null) {
			if (other.usr_id != null)
				return false;
		} else if (!usr_id.equals(other.usr_id))
			return false;
		if (usrname == null) {
			if (other.usrname != null)
				return false;
		} else if (!usrname.equals(other.usrname))
			return false;
		return true;
	}
	public Integer getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(Integer usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}
