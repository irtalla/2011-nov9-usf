package models;

public class Status {
	private int id;
	private String avalibilty;
	
	
	public Status() {
		super();
	}
	public Status(int id, String avalibilty) {
		super();
		this.id = id;
		this.avalibilty = avalibilty;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAvalibilty() {
		return avalibilty;
	}
	public void setAvalibilty(String avalibilty) {
		this.avalibilty = avalibilty;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avalibilty == null) ? 0 : avalibilty.hashCode());
		result = prime * result + id;
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
		Status other = (Status) obj;
		if (avalibilty == null) {
			if (other.avalibilty != null)
				return false;
		} else if (!avalibilty.equals(other.avalibilty))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Status [id=" + id + ", avalibilty=" + avalibilty + "]";
	}
	
	
}
