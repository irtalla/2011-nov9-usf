package com.revature.beans;

public class Person {
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Person other = (Person) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	private String userName;
    private String type;
    private int id;
    private String password;

	public void setId(int id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public Person() {
    	userName="";
    	type="";
    	id = 0;
    	password="";
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Person(String userName, String type) {
        this.userName = userName;
        this.type = type;
    }

    public String getName() {
        return userName;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return String.format("{ id: %s, type: %s }", userName, type);
    }

}
