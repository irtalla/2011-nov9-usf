package dev.rev.beans;

public class benco {

	private int benco_id;
	private int form_id;
	private String status;
	
	public benco() {
		benco_id=form_id=0;
		status="";
		
	}

	public int getBenco_id() {
		return benco_id;
	}

	public void setBenco_id(int benco_id) {
		this.benco_id = benco_id;
	}

	public int getForm_id() {
		return form_id;
	}

	public void setForm_id(int form_id) {
		this.form_id = form_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + benco_id;
		result = prime * result + form_id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		benco other = (benco) obj;
		if (benco_id != other.benco_id)
			return false;
		if (form_id != other.form_id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "benco [benco_id=" + benco_id + ", form_id=" + form_id + ", status=" + status + "]";
	}
}
