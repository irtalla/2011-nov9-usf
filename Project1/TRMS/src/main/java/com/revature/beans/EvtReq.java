package com.revature.beans;

import java.util.Date;


public class EvtReq {
	private Integer id;
	private String name;
	private Date posting_date;
	private Integer direct_supervisor_approval_id;
	private Integer department_head_approval_id;
	private Integer benefits_coordinator_approval_id;
	private Integer person_id;
	private Integer type_id;
	private Integer req_fr_cmnt_id;
	private Integer priority_id;
	private Date start_date;
	
	
	public EvtReq() {
		id = 0;
		name = "";
		posting_date = new Date();
		direct_supervisor_approval_id = 0;
		department_head_approval_id = 0;
		benefits_coordinator_approval_id = 0;	
		person_id = 0;
		type_id = 0;
		req_fr_cmnt_id = 0;
		priority_id = 0;
		start_date = new Date();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getPosting_date() {
		return posting_date;
	}


	public void setPosting_date(Date posting_date) {
		this.posting_date = posting_date;
	}


	public Integer getDirect_supervisor_approval_id() {
		return direct_supervisor_approval_id;
	}


	public void setDirect_supervisor_approval_id(Integer direct_supervisor_approval_id) {
		this.direct_supervisor_approval_id = direct_supervisor_approval_id;
	}


	public Integer getDepartment_head_approval_id() {
		return department_head_approval_id;
	}


	public void setDepartment_head_approval_id(Integer department_head_approval_id) {
		this.department_head_approval_id = department_head_approval_id;
	}


	public Integer getBenefits_coordinator_approval_id() {
		return benefits_coordinator_approval_id;
	}


	public void setBenefits_coordinator_approval_id(Integer benefits_coordinator_approval_id) {
		this.benefits_coordinator_approval_id = benefits_coordinator_approval_id;
	}


	public Integer getPerson_id() {
		return person_id;
	}


	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}


	public Integer getType_id() {
		return type_id;
	}


	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}


	public Integer getReq_fr_cmnt_id() {
		return req_fr_cmnt_id;
	}


	public void setReq_fr_cmnt_id(Integer req_fr_cmnt_id) {
		this.req_fr_cmnt_id = req_fr_cmnt_id;
	}


	public Integer getPriority_id() {
		return priority_id;
	}


	public void setPriority_id(Integer priority_id) {
		this.priority_id = priority_id;
	}


	public Date getStart_date() {
		return start_date;
	}


	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((benefits_coordinator_approval_id == null) ? 0 : benefits_coordinator_approval_id.hashCode());
		result = prime * result + ((department_head_approval_id == null) ? 0 : department_head_approval_id.hashCode());
		result = prime * result
				+ ((direct_supervisor_approval_id == null) ? 0 : direct_supervisor_approval_id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((person_id == null) ? 0 : person_id.hashCode());
		result = prime * result + ((posting_date == null) ? 0 : posting_date.hashCode());
		result = prime * result + ((priority_id == null) ? 0 : priority_id.hashCode());
		result = prime * result + ((req_fr_cmnt_id == null) ? 0 : req_fr_cmnt_id.hashCode());
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
		result = prime * result + ((type_id == null) ? 0 : type_id.hashCode());
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
		EvtReq other = (EvtReq) obj;
		if (benefits_coordinator_approval_id == null) {
			if (other.benefits_coordinator_approval_id != null)
				return false;
		} else if (!benefits_coordinator_approval_id.equals(other.benefits_coordinator_approval_id))
			return false;
		if (department_head_approval_id == null) {
			if (other.department_head_approval_id != null)
				return false;
		} else if (!department_head_approval_id.equals(other.department_head_approval_id))
			return false;
		if (direct_supervisor_approval_id == null) {
			if (other.direct_supervisor_approval_id != null)
				return false;
		} else if (!direct_supervisor_approval_id.equals(other.direct_supervisor_approval_id))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (person_id == null) {
			if (other.person_id != null)
				return false;
		} else if (!person_id.equals(other.person_id))
			return false;
		if (posting_date == null) {
			if (other.posting_date != null)
				return false;
		} else if (!posting_date.equals(other.posting_date))
			return false;
		if (priority_id == null) {
			if (other.priority_id != null)
				return false;
		} else if (!priority_id.equals(other.priority_id))
			return false;
		if (req_fr_cmnt_id == null) {
			if (other.req_fr_cmnt_id != null)
				return false;
		} else if (!req_fr_cmnt_id.equals(other.req_fr_cmnt_id))
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (type_id == null) {
			if (other.type_id != null)
				return false;
		} else if (!type_id.equals(other.type_id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "EvtReq [id=" + id + ", name=" + name + ", posting_date=" + posting_date
				+ ", direct_supervisor_approval_id=" + direct_supervisor_approval_id + ", department_head_approval_id="
				+ department_head_approval_id + ", benefits_coordinator_approval_id=" + benefits_coordinator_approval_id
				+ ", person_id=" + person_id + ", type_id=" + type_id + ", req_fr_cmnt_id=" + req_fr_cmnt_id
				+ ", priority_id=" + priority_id + ", start_date=" + start_date + "]";
	}
	
	
	

}



