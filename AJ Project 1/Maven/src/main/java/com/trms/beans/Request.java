package com.trms.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.OneToMany;

@Entity
@Table(name="request")
public class Request {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="requestor_id")
	private Person requestor;
	
	
//	@Column(name="requestor_id")
//	@ManyToOne
	@Column(name="manager_id")
	private Integer manager;
//	@ManyToOne
	@Column(name="dhead_id")
	private Integer dHead;
//	@ManyToOne
	@Column(name="department_id")
	private Integer department;
//	@JsonProperty("testDate")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonIgnore
	@Column(name="test_date")
	private String testDate;
	private LocalDateTime due;
//	private Time time;
	private String description;
	private Float cost;
	private Float amount;
	private Float projectedAmount;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="status_id")
	private ApprovalStatus status;
	
	
	private String location;
	@Column(name="hours_missed")
	private Integer hoursMissed;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="type_id")
	private RequestType requestType;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.EAGER)
//	@JoinTable(name="activity",
//	
	@JoinColumn(name="request_id")
	private List<Activity> activity;
	private LocalDateTime closed;
	
	public Request() {
		id = 0;
		requestor = new Person();
		manager = 0;
		dHead = 0;
		department = 0;
		testDate = null;

		description = "";
		cost = 0f;
		amount = 0f;
		projectedAmount = 0f;
		status = new ApprovalStatus();
		due = LocalDateTime.now().plusDays(7);
		location = "";
		hoursMissed = 0;
		requestType = new RequestType();
		activity = new ArrayList<Activity>();
		
	}

	public Person getRequestor() {
		return requestor;
	}

	public void setRequestor(Person requestor) {
		this.requestor = requestor;
	}

	public Integer getManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	public Integer getdHead() {
		return dHead;
	}

	public void setdHead(Integer dHead) {
		this.dHead = dHead;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getProjectedAmount() {
		return projectedAmount;
	}

	public void setProjectedAmount(Float projectedAmount) {
		this.projectedAmount = projectedAmount;
	}

	public ApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovalStatus status) {
		this.status = status;
	}

	public LocalDateTime getDue() {
		return due;
	}

	public void setDue(LocalDateTime due) {
		this.due = due;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getHoursMissed() {
		return hoursMissed;
	}

	public void setHoursMissed(Integer hoursMissed) {
		this.hoursMissed = hoursMissed;
	}

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public List<Activity> getActivity() {
		return activity;
	}

	public void setActivity(List<Activity> activity) {
		this.activity = activity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getClosed() {
		return closed;
	}

	public void setClosed(LocalDateTime closed) {
		this.closed = closed;
	}

}
