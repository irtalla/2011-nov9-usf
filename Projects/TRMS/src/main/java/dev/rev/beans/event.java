package dev.rev.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event")
public class event {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int event_id;
	private int event_coverage;
	private String event_name;
	private String passing_grade;
	
	public event() {
		event_id=event_coverage=0;
		event_name=passing_grade="";
	}
	
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	
	public int getEvent_coverage() {
		return event_coverage;
	}
	public void setEvent_coverage(int event_coverage) {
		this.event_coverage = event_coverage;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + event_coverage;
		result = prime * result + event_id;
		result = prime * result + ((event_name == null) ? 0 : event_name.hashCode());
		
		result = prime * result + ((passing_grade == null) ? 0 : passing_grade.hashCode());
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
		event other = (event) obj;
		if (event_coverage != other.event_coverage)
			return false;
		if (event_id != other.event_id)
			return false;
		if (event_name == null) {
			if (other.event_name != null)
				return false;
		} else if (!event_name.equals(other.event_name))
			return false;
		if (passing_grade == null) {
			if (other.passing_grade != null)
				return false;
		} else if (!passing_grade.equals(other.passing_grade))
			return false;
		return true;
	}

	public String getPassing_grade() {
		return passing_grade;
	}

	public void setPassing_grade(String passing_grade) {
		this.passing_grade = passing_grade;
	}

	@Override
	public String toString() {
		return "event [event_id=" + event_id +", event_coverage=" + event_coverage
				+ ", event_name=" + event_name + ", passing_grade=" + passing_grade + "]";
	}	
	
	
}
