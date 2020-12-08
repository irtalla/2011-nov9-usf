package dev.rev.beans;

public class department {

	
		private int dep_id;
		private int emp_id;
		private String status;
		
	public department() {
		dep_id=emp_id=0;
		status="";
		
	}

	public int getDep_id() {
		return dep_id;
	}

	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
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
		result = prime * result + dep_id;
		result = prime * result + emp_id;
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
		department other = (department) obj;
		if (dep_id != other.dep_id)
			return false;
		if (emp_id != other.emp_id)
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
		return "department [dep_id=" + dep_id + ", emp_id=" + emp_id + ", status=" + status + "]";
	}
	
	
}
