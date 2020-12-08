package dev.rev.beans;

public class directsupervisor {

	private int direct_id ;
	private int form_id;
	private String status;
	private String reason;
	
	public directsupervisor() {
		
		direct_id=form_id=0;
		status=reason="";
		
	}
	public int getDirect_id() {
		return direct_id;
	}
	public void setDirect_id(int direct_id) {
		this.direct_id = direct_id;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + direct_id;
		result = prime * result + form_id;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
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
		directsupervisor other = (directsupervisor) obj;
		if (direct_id != other.direct_id)
			return false;
		if (form_id != other.form_id)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
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
		return "directsupervisor [direct_id=" + direct_id + ", form_id=" + form_id + ", status=" + status + ", reason="
				+ reason + "]";
	}
	
	


}
