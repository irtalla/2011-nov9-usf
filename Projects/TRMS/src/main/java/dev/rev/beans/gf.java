package dev.rev.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class gf {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int gf_id;
	private String gf_name;
	public int getGf_id() {
		return gf_id;
	}
	public void setGf_id(int gf_id) {
		this.gf_id = gf_id;
	}
	public String getGf_name() {
		return gf_name;
	}
	public void setGf_name(String gf_name) {
		this.gf_name = gf_name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gf_id;
		result = prime * result + ((gf_name == null) ? 0 : gf_name.hashCode());
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
		gf other = (gf) obj;
		if (gf_id != other.gf_id)
			return false;
		if (gf_name == null) {
			if (other.gf_name != null)
				return false;
		} else if (!gf_name.equals(other.gf_name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "gf [gf_id=" + gf_id + ", gf_name=" + gf_name + "]";
	}
	
	

}
