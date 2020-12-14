package com.revature.beans;

import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tuition_reimbursement_form")
public class ReimbursementForm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "file_date")
	private Date fileDate;
	@Column(name = "employee_id")
	private int employeeId;
	@Column(name = "event_id")
	private int eventId;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="grading_format_id")
	private GradingFormat gradingFormat;
	@Column(name = "passing_grade")
	private String passingGrade;
	private String justification;
	@Column(name = "reimbursement_amount")
	private double reimbursementAmount;
	@Column(name = "time_missed_hours")
	private int hoursMissed;
	@ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="stage_id")
	private Stage stage;
	@Column(name = "stage_entry_date")
	private Date stageEntryDate;
	@ManyToOne(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="status_id")
	private Status status;
	
	@Override
	public String toString() {
		return "ReimbursementForm [id=" + id + ", fileDate=" + fileDate + ", employeeId=" + employeeId + ", eventId="
				+ eventId + ", gradingFormat=" + gradingFormat + ", passingGrade=" + passingGrade + ", justification="
				+ justification + ", reimbursementAmount=" + reimbursementAmount + ", hoursMissed=" + hoursMissed
				+ ", stage=" + stage + ", stageEntryDate=" + stageEntryDate + ", status=" + status + "]";
	}

	public ReimbursementForm()
	{
		id = -1;
		Calendar c = new GregorianCalendar(2000,1,31,12,59);
		fileDate = new Date(c.getTime().getTime());
		employeeId = -1;
		eventId = -1;
		gradingFormat = new GradingFormat();
		passingGrade = "";
		justification = "";
		reimbursementAmount = 0;
		hoursMissed = 0;
		stage = new Stage();
		status = new Status();
		stageEntryDate = new Date(c.getTime().getTime());
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFileDate() {
		return fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public String getPassingGrade() {
		return passingGrade;
	}

	public void setPassingGrade(String passingGrade) {
		this.passingGrade = passingGrade;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public double getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public int getHoursMissed() {
		return hoursMissed;
	}

	public void setHoursMissed(int hoursMissed) {
		this.hoursMissed = hoursMissed;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Date getStageEntryDate() {
		return stageEntryDate;
	}

	public void setStageEntryDate(Date stageEntryDate) {
		this.stageEntryDate = stageEntryDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + eventId;
		result = prime * result + ((fileDate == null) ? 0 : fileDate.hashCode());
		result = prime * result + ((gradingFormat == null) ? 0 : gradingFormat.hashCode());
		result = prime * result + hoursMissed;
		result = prime * result + id;
		result = prime * result + ((justification == null) ? 0 : justification.hashCode());
		result = prime * result + ((passingGrade == null) ? 0 : passingGrade.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reimbursementAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((stage == null) ? 0 : stage.hashCode());
		result = prime * result + ((stageEntryDate == null) ? 0 : stageEntryDate.hashCode());
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
		ReimbursementForm other = (ReimbursementForm) obj;
		if (employeeId != other.employeeId)
			return false;
		if (eventId != other.eventId)
			return false;
		if (fileDate == null) {
			if (other.fileDate != null)
				return false;
		} else if (!fileDate.toString().equals(other.fileDate.toString()))
			return false;
		if (gradingFormat == null) {
			if (other.gradingFormat != null)
				return false;
		} else if (!gradingFormat.equals(other.gradingFormat))
			return false;
		if (hoursMissed != other.hoursMissed)
			return false;
		if (id != other.id)
			return false;
		if (justification == null) {
			if (other.justification != null)
				return false;
		} else if (!justification.equals(other.justification))
			return false;
		if (passingGrade == null) {
			if (other.passingGrade != null)
				return false;
		} else if (!passingGrade.equals(other.passingGrade))
			return false;
		if (Double.doubleToLongBits(reimbursementAmount) != Double.doubleToLongBits(other.reimbursementAmount))
			return false;
		if (stage == null) {
			if (other.stage != null)
				return false;
		} else if (!stage.equals(other.stage))
			return false;
		if (stageEntryDate == null) {
			if (other.stageEntryDate != null)
				return false;
		} else if (!stageEntryDate.toString().equals(other.stageEntryDate.toString()))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
}
