package com.revature.beans;

import java.util.Date;


public class EvtReq {
	private Integer id;
	private String name;
	private Date posting_date;
	private Integer grade_id;
	private Integer presentation_id;
	private Integer direct_supervisor_approval_id;
	private Integer department_head_approval_id;
	private Integer benefits_coordinator_approval_id;
	
	public EvtReq() {
		id = 0;
		name = "";
		posting_date = new Date();
		grade_id = 0;
		presentation_id = 0;
		direct_supervisor_approval_id = 0;
		department_head_approval_id = 0;
		benefits_coordinator_approval_id = 0;	
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

	public Integer getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(Integer grade_id) {
		this.grade_id = grade_id;
	}

	public Integer getPresentation_id() {
		return presentation_id;
	}

	public void setPresentation_id(Integer presentation_id) {
		this.presentation_id = presentation_id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((benefits_coordinator_approval_id == null) ? 0 : benefits_coordinator_approval_id.hashCode());
		result = prime * result + ((department_head_approval_id == null) ? 0 : department_head_approval_id.hashCode());
		result = prime * result
				+ ((direct_supervisor_approval_id == null) ? 0 : direct_supervisor_approval_id.hashCode());
		result = prime * result + ((grade_id == null) ? 0 : grade_id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((posting_date == null) ? 0 : posting_date.hashCode());
		result = prime * result + ((presentation_id == null) ? 0 : presentation_id.hashCode());
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
		if (grade_id == null) {
			if (other.grade_id != null)
				return false;
		} else if (!grade_id.equals(other.grade_id))
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
		if (posting_date == null) {
			if (other.posting_date != null)
				return false;
		} else if (!posting_date.equals(other.posting_date))
			return false;
		if (presentation_id == null) {
			if (other.presentation_id != null)
				return false;
		} else if (!presentation_id.equals(other.presentation_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EvtReq [id=" + id + ", name=" + name + ", posting_date=" + posting_date + ", grade_id=" + grade_id
				+ ", presentation_id=" + presentation_id + ", direct_supervisor_approval_id="
				+ direct_supervisor_approval_id + ", department_head_approval_id=" + department_head_approval_id
				+ ", benefits_coordinator_approval_id=" + benefits_coordinator_approval_id + "]";
	}

	

}



