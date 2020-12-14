package com.revature.beans;

import java.sql.Time;
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
	private double amount; 
	private Time event_time;
	private Integer location_id;
	private Integer grading_format_id;
	private String work_related_justification;
	private Integer passing_cutoff_grade_id;
	private Integer status;
	private String approver_username;
	
	public EvtReq() {
		id = 0;
		name = "";
		posting_date = new Date();
		direct_supervisor_approval_id = null;
		department_head_approval_id = null;
		benefits_coordinator_approval_id = null;	
		person_id = null;
		type_id = null;
		req_fr_cmnt_id = null;
		priority_id = null;
		start_date = new Date();
		amount = 0.00;
		event_time = null; 
		location_id = null;
		grading_format_id = null;
		work_related_justification = null;
		passing_cutoff_grade_id = null;
		
	}

	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getApprover_username() {
		return approver_username;
	}


	public void setApprover_username(String approver_username) {
		this.approver_username = approver_username;
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


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Time getEvent_time() {
		return event_time;
	}


	public void setEvent_time(Time event_time) {
		this.event_time = event_time;
	}


	public Integer getLocation_id() {
		return location_id;
	}


	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}


	public Integer getGrading_format_id() {
		return grading_format_id;
	}


	public void setGrading_format_id(Integer grading_format_id) {
		this.grading_format_id = grading_format_id;
	}


	public String getWork_related_justification() {
		return work_related_justification;
	}


	public void setWork_related_justification(String work_related_justification) {
		this.work_related_justification = work_related_justification;
	}


	public Integer getPassing_cutoff_grade_id() {
		return passing_cutoff_grade_id;
	}


	public void setPassing_cutoff_grade_id(Integer passing_cutoff_grade_id) {
		this.passing_cutoff_grade_id = passing_cutoff_grade_id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((benefits_coordinator_approval_id == null) ? 0 : benefits_coordinator_approval_id.hashCode());
		result = prime * result + ((department_head_approval_id == null) ? 0 : department_head_approval_id.hashCode());
		result = prime * result
				+ ((direct_supervisor_approval_id == null) ? 0 : direct_supervisor_approval_id.hashCode());
		result = prime * result + ((event_time == null) ? 0 : event_time.hashCode());
		result = prime * result + ((grading_format_id == null) ? 0 : grading_format_id.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location_id == null) ? 0 : location_id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passing_cutoff_grade_id == null) ? 0 : passing_cutoff_grade_id.hashCode());
		result = prime * result + ((person_id == null) ? 0 : person_id.hashCode());
		result = prime * result + ((posting_date == null) ? 0 : posting_date.hashCode());
		result = prime * result + ((priority_id == null) ? 0 : priority_id.hashCode());
		result = prime * result + ((req_fr_cmnt_id == null) ? 0 : req_fr_cmnt_id.hashCode());
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
		result = prime * result + ((type_id == null) ? 0 : type_id.hashCode());
		result = prime * result + ((work_related_justification == null) ? 0 : work_related_justification.hashCode());
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
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
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
		if (event_time == null) {
			if (other.event_time != null)
				return false;
		} else if (!event_time.equals(other.event_time))
			return false;
		if (grading_format_id == null) {
			if (other.grading_format_id != null)
				return false;
		} else if (!grading_format_id.equals(other.grading_format_id))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location_id == null) {
			if (other.location_id != null)
				return false;
		} else if (!location_id.equals(other.location_id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passing_cutoff_grade_id == null) {
			if (other.passing_cutoff_grade_id != null)
				return false;
		} else if (!passing_cutoff_grade_id.equals(other.passing_cutoff_grade_id))
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
		if (work_related_justification == null) {
			if (other.work_related_justification != null)
				return false;
		} else if (!work_related_justification.equals(other.work_related_justification))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "EvtReq [id=" + id + ", name=" + name + ", posting_date=" + posting_date
				+ ", direct_supervisor_approval_id=" + direct_supervisor_approval_id + ", department_head_approval_id="
				+ department_head_approval_id + ", benefits_coordinator_approval_id=" + benefits_coordinator_approval_id
				+ ", person_id=" + person_id + ", type_id=" + type_id + ", req_fr_cmnt_id=" + req_fr_cmnt_id
				+ ", priority_id=" + priority_id + ", start_date=" + start_date + ", amount=" + amount + ", event_time="
				+ event_time + ", location_id=" + location_id + ", grading_format_id=" + grading_format_id
				+ ", work_related_justification=" + work_related_justification + ", passing_cutoff_grade_id="
				+ passing_cutoff_grade_id + "]";
	}


}



