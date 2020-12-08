package dev.rev.beans;

public class reimbForm {

		private int from_id;
		private int emp_id;
		private int event_id;
		private String dates;
		private String timet;
		private String location;
		private String description;
		private int cost;
		private String grading_format;
		private String type_of_event;
		private String form_status;
		private String type_of_approval;
		private String work_time_missed;
		
		public reimbForm() {
			from_id=emp_id=event_id=cost=0;
			dates=timet=location=description=grading_format=type_of_event=form_status=type_of_approval=work_time_missed="";
			
			
		}
		
		
		public int getFrom_id() {
			return from_id;
		}
		public void setFrom_id(int from_id) {
			this.from_id = from_id;
		}
		public int getEmp_id() {
			return emp_id;
		}
		public void setEmp_id(int emp_id) {
			this.emp_id = emp_id;
		}
		public int getEvent_id() {
			return event_id;
		}
		public void setEvent_id(int event_id) {
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
		public String getType_of_event() {
			return type_of_event;
		}
		public void setType_of_event(String type_of_event) {
			this.type_of_event = type_of_event;
		}
		public String getForm_status() {
			return form_status;
		}
		public void setForm_status(String form_status) {
			this.form_status = form_status;
		}
		public String getType_of_approval() {
			return type_of_approval;
		}
		public void setType_of_approval(String type_of_approval) {
			this.type_of_approval = type_of_approval;
		}
		public String getWork_time_missed() {
			return work_time_missed;
		}
		public void setWork_time_missed(String work_time_missed) {
			this.work_time_missed = work_time_missed;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + cost;
			result = prime * result + ((dates == null) ? 0 : dates.hashCode());
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + emp_id;
			result = prime * result + event_id;
			result = prime * result + ((form_status == null) ? 0 : form_status.hashCode());
			result = prime * result + from_id;
			result = prime * result + ((grading_format == null) ? 0 : grading_format.hashCode());
			result = prime * result + ((location == null) ? 0 : location.hashCode());
			result = prime * result + ((timet == null) ? 0 : timet.hashCode());
			result = prime * result + ((type_of_approval == null) ? 0 : type_of_approval.hashCode());
			result = prime * result + ((type_of_event == null) ? 0 : type_of_event.hashCode());
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
			if (emp_id != other.emp_id)
				return false;
			if (event_id != other.event_id)
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
			if (timet == null) {
				if (other.timet != null)
					return false;
			} else if (!timet.equals(other.timet))
				return false;
			if (type_of_approval == null) {
				if (other.type_of_approval != null)
					return false;
			} else if (!type_of_approval.equals(other.type_of_approval))
				return false;
			if (type_of_event == null) {
				if (other.type_of_event != null)
					return false;
			} else if (!type_of_event.equals(other.type_of_event))
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
					+ dates + ", timet=" + timet + ", location=" + location + ", description=" + description + ", cost="
					+ cost + ", grading_format=" + grading_format + ", type_of_event=" + type_of_event
					+ ", form_status=" + form_status + ", type_of_approval=" + type_of_approval + ", work_time_missed="
					+ work_time_missed + "]";
		}
		
		
}
