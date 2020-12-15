package com.revature.beans;

import java.util.HashSet;

public class Course {
	    private Integer id;
	    private Integer employeeId;
	    private String startDate;
		private String startTime;
		private String description;
		private float courseCost;
		private String gradingFormat;
		private String gradingMin;
		private Event eventType;
		public Event getEventType() {
			return eventType;
		}
		public void setEvenType(Event eventType) {
			this.eventType = eventType;
		}
		private String dirSup;
		private String deptHead;
		private String benCor;
		private String awardGranted;
		private Integer approverId;
		private String latestSubmitDate;
		private float reimburseAmt;
		
	
		public Course() {
			id = 0;
			employeeId = 0;
			startDate = "";
			startTime = "";
			description = "";
			courseCost = 0;
			gradingMin="";
			dirSup = "";
			deptHead = "";
			benCor = "";
			awardGranted = "";
			approverId = 0;
			latestSubmitDate = "";
			reimburseAmt=0f;
		}
		
		
		
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
		}
		public String getStartDate() {
			return startDate;
		}
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public float getCourseCost() {
			return courseCost;
		}
		public void setCourseCost(float courseCost) {
			this.courseCost = courseCost;
		}
		public String getGradingFormat() {
			return gradingFormat;
		}
		public void setGradingFormat(String gradingFormat) {
			this.gradingFormat = gradingFormat;
		}
		public String getDirSup() {
			return dirSup;
		}
		public void setDirSup(String dirSup) {
			this.dirSup = dirSup;
		}
		public String getDeptHead() {
			return deptHead;
		}
		public void setDeptHead(String deptHead) {
			this.deptHead = deptHead;
		}
		public String getBenCor() {
			return benCor;
		}
		public void setBenCor(String benCor) {
			this.benCor = benCor;
		}
		public String getAwardGranted() {
			return awardGranted;
		}
		public void setAwardGranted(String awardGranted) {
			this.awardGranted = awardGranted;
		}
		public void setEventType(Event eventType) {
			this.eventType = eventType;
		}
		
		public Integer getApproverId() {
			return approverId;
		}
		public void setApproverId(Integer approverId) {
			this.approverId = approverId;
		}
		public String getLatestSubmitDate() {
			return latestSubmitDate;
		}
		public void setLatestSubmitDate(String latestSubmitDate) {
			this.latestSubmitDate = latestSubmitDate;
		}
		public float getReimburseAmt() {
			return reimburseAmt;
		}
		public void setReimburseAmt(float reimburseAmt) {
			this.reimburseAmt = reimburseAmt;
		}
		
		public String getGradingMin() {
			return gradingMin;
		}
		public void setGradingMin(String gradingMin) {
			this.gradingMin = gradingMin;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((approverId == null) ? 0 : approverId.hashCode());
			result = prime * result + ((awardGranted == null) ? 0 : awardGranted.hashCode());
			result = prime * result + ((benCor == null) ? 0 : benCor.hashCode());
			result = prime * result + Float.floatToIntBits(courseCost);
			result = prime * result + ((deptHead == null) ? 0 : deptHead.hashCode());
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((dirSup == null) ? 0 : dirSup.hashCode());
			result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
			result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
			result = prime * result + ((gradingFormat == null) ? 0 : gradingFormat.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((latestSubmitDate == null) ? 0 : latestSubmitDate.hashCode());
			result = prime * result + Float.floatToIntBits(reimburseAmt);
			result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
			result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
			Course other = (Course) obj;
			if (approverId == null) {
				if (other.approverId != null)
					return false;
			} else if (!approverId.equals(other.approverId))
				return false;
			if (awardGranted == null) {
				if (other.awardGranted != null)
					return false;
			} else if (!awardGranted.equals(other.awardGranted))
				return false;
			if (benCor == null) {
				if (other.benCor != null)
					return false;
			} else if (!benCor.equals(other.benCor))
				return false;
			if (Float.floatToIntBits(courseCost) != Float.floatToIntBits(other.courseCost))
				return false;
			if (deptHead == null) {
				if (other.deptHead != null)
					return false;
			} else if (!deptHead.equals(other.deptHead))
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (dirSup == null) {
				if (other.dirSup != null)
					return false;
			} else if (!dirSup.equals(other.dirSup))
				return false;
			if (employeeId == null) {
				if (other.employeeId != null)
					return false;
			} else if (!employeeId.equals(other.employeeId))
				return false;
			if (eventType == null) {
				if (other.eventType != null)
					return false;
			} else if (!eventType.equals(other.eventType))
				return false;
			if (gradingFormat == null) {
				if (other.gradingFormat != null)
					return false;
			} else if (!gradingFormat.equals(other.gradingFormat))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (latestSubmitDate == null) {
				if (other.latestSubmitDate != null)
					return false;
			} else if (!latestSubmitDate.equals(other.latestSubmitDate))
				return false;
			if (Float.floatToIntBits(reimburseAmt) != Float.floatToIntBits(other.reimburseAmt))
				return false;
			if (startDate == null) {
				if (other.startDate != null)
					return false;
			} else if (!startDate.equals(other.startDate))
				return false;
			if (startTime == null) {
				if (other.startTime != null)
					return false;
			} else if (!startTime.equals(other.startTime))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Course [id=" + id + ", employeeId=" + employeeId + ", startDate=" + startDate + ", startTime="
					+ startTime + ", description=" + description + ", courseCost=" + courseCost + ", gradingFormat="
					+ gradingFormat + ", eventType=" + eventType + ", dirSup=" + dirSup + ", deptHead=" + deptHead
					+ ", benCor=" + benCor + ", awardGranted=" + awardGranted + ", approverId=" + approverId
					+ ", latestSubmitDate=" + latestSubmitDate + ", reimburseAmt=" + reimburseAmt + "]";
		}
	
		
}
