package models;

public class User {
	private int UserId;
	private String Fname;
	private String Lname;
	private String Username;
	private String Password;
	private boolean IsEmployee;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int userId, String fname, String lname, String username, String password, boolean isEmployee) {
		super();
		UserId = userId;
		Fname = fname;
		Lname = lname;
		Username = username;
		Password = password;
		IsEmployee = isEmployee;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getFname() {
		return Fname;
	}
	public void setFname(String fname) {
		Fname = fname;
	}
	public String getLname() {
		return Lname;
	}
	public void setLname(String lname) {
		Lname = lname;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public boolean isIsEmployee() {
		return IsEmployee;
	}
	public void setIsEmployee(boolean isEmployee) {
		IsEmployee = isEmployee;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Fname == null) ? 0 : Fname.hashCode());
		result = prime * result + (IsEmployee ? 1231 : 1237);
		result = prime * result + ((Lname == null) ? 0 : Lname.hashCode());
		result = prime * result + ((Password == null) ? 0 : Password.hashCode());
		result = prime * result + UserId;
		result = prime * result + ((Username == null) ? 0 : Username.hashCode());
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
		User other = (User) obj;
		if (Fname == null) {
			if (other.Fname != null)
				return false;
		} else if (!Fname.equals(other.Fname))
			return false;
		if (IsEmployee != other.IsEmployee)
			return false;
		if (Lname == null) {
			if (other.Lname != null)
				return false;
		} else if (!Lname.equals(other.Lname))
			return false;
		if (Password == null) {
			if (other.Password != null)
				return false;
		} else if (!Password.equals(other.Password))
			return false;
		if (UserId != other.UserId)
			return false;
		if (Username == null) {
			if (other.Username != null)
				return false;
		} else if (!Username.equals(other.Username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", Fname=" + Fname + ", Lname=" + Lname + ", Username=" + Username
				+ ", Password=" + Password + ", IsEmployee=" + IsEmployee + "]";
	}
	
	
}
