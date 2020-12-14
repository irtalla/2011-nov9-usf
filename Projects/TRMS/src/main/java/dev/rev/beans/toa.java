package dev.rev.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table()

public class toa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int toa_id;
	private String stage;
	
	public toa() {
		toa_id=0;
		stage="";
	}
	public int getToa_id() {
		return toa_id;
	}
	public void setToa_id(int toa_id) {
		this.toa_id = toa_id;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result + toa_id;
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
		toa other = (toa) obj;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (toa_id != other.toa_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "toa [toa_id=" + toa_id + ", stage=" + stage + "]";
	}
	

}
