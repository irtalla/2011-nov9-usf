package dev.rev.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reimbformtable")
public class reimbForm {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="form_id")
		private int from_id;
		@OneToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="emp_id")
		private employee emp_id;
		@ManyToOne
		@JoinColumn(name="event_id")
		private event event_id;
		private String dates;
		private String timet;
		private String priority;
		private String location;
		private String description;
		private int cost;
		private String grading_format;
		//private String type_of_event;
		private String form_status;
		@OneToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="toa_id")
		private toa toa_id;
		private String work_time_missed;
		
		public reimbForm() {
			toa_id=null;
			from_id=cost=0;
			dates=timet=location=description=grading_format=form_status=work_time_missed="";
			emp_id=null;
			event_id=null;
			priority="";
			
		}
		
		
		public String getPriority() {
			return priority;
		}


		public void setPriority(String priority) {
			this.priority = priority;
		}


		public int getFrom_id() {
			return from_id;
		}
		public void setFrom_id(int from_id) {
			this.from_id = from_id;
		}
		public employee getEmp_id() {
			return emp_id;
		}
		public void setEmp_id(employee emp_id) {
			this.emp_id = emp_id;
		}
		public event getEvent_id() {
			return event_id;
		}
		public void setEvent_id(event event_id) {
			this.event_id = event_id;
		}
		public String getDates() {
			return dates;
		}
		public void setDates(String dates) {
			this.dates = dates;
		}
		public String getTimet() {
			return timet;
		}
		public void setTimet(String timet) {
			this.timet = timet;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getCost() {
			return cost;
		}
		public void setCost(int cost) {
			this.cost = cost;
		}
		public String getGrading_format() {
			return grading_format;
		}
		public void setGrading_format(String grading_format) {
			this.grading_format = grading_format;
		}
		public String getForm_status() {
			return form_status;
		}
		public void setForm_status(String form_status) {
			this.form_status = form_status;
		}

		public String getWork_time_missed() {
			return work_time_missed;
		}


		public void setWork_time_missed(String work_time_missed) {
			this.work_time_missed = work_time_missed;
		}


		public toa getToa_id() {
			return toa_id;
		}


		public void setToa_id(toa toa_id) {
			this.toa_id = toa_id;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + cost;
			result = prime * result + ((dates == null) ? 0 : dates.hashCode());
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((emp_id == null) ? 0 : emp_id.hashCode());
			result = prime * result + ((event_id == null) ? 0 : event_id.hashCode());
			result = prime * result + ((form_status == null) ? 0 : form_status.hashCode());
			result = prime * result + from_id;
			result = prime * result + ((grading_format == null) ? 0 : grading_format.hashCode());
			result = prime * result + ((location == null) ? 0 : location.hashCode());
			result = prime * result + ((priority == null) ? 0 : priority.hashCode());
			result = prime * result + ((timet == null) ? 0 : timet.hashCode());
			result = prime * result + ((toa_id == null) ? 0 : toa_id.hashCode());
			result = prime * result + ((work_time_missed == null) ? 0 : work_time_missed.hashCode());
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
			reimbForm other = (reimbForm) obj;
			if (cost != other.cost)
				return false;
			if (dates == null) {
				if (other.dates != null)
					return false;
			} else if (!dates.equals(other.dates))
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (emp_id == null) {
				if (other.emp_id != null)
					return false;
			} else if (!emp_id.equals(other.emp_id))
				return false;
			if (event_id == null) {
				if (other.event_id != null)
					return false;
			} else if (!event_id.equals(other.event_id))
				return false;
			if (form_status == null) {
				if (other.form_status != null)
					return false;
			} else if (!form_status.equals(other.form_status))
				return false;
			if (from_id != other.from_id)
				return false;
			if (grading_format == null) {
				if (other.grading_format != null)
					return false;
			} else if (!grading_format.equals(other.grading_format))
				return false;
			if (location == null) {
				if (other.location != null)
					return false;
			} else if (!location.equals(other.location))
				return false;
			if (priority == null) {
				if (other.priority != null)
					return false;
			} else if (!priority.equals(other.priority))
				return false;
			if (timet == null) {
				if (other.timet != null)
					return false;
			} else if (!timet.equals(other.timet))
				return false;
			if (toa_id == null) {
				if (other.toa_id != null)
					return false;
			} else if (!toa_id.equals(other.toa_id))
				return false;
			if (work_time_missed == null) {
				if (other.work_time_missed != null)
					return false;
			} else if (!work_time_missed.equals(other.work_time_missed))
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "reimbForm [from_id=" + from_id + ", emp_id=" + emp_id + ", event_id=" + event_id + ", dates="
					+ dates + ", timet=" + timet + ", priority=" + priority + ", location=" + location
					+ ", description=" + description + ", cost=" + cost + ", grading_format=" + grading_format
					+ ", form_status=" + form_status + ", toa_id=" + toa_id + ", work_time_missed=" + work_time_missed
					+ "]";
		}


		
	
}
