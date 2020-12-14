package com.revature.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class Reimbursement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="event")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name="employee")
	private Individual employee;
	
	private Integer amount;
	private Boolean benco_approved;
	private Boolean supervisor_approved;
	private Boolean head_approved;
	private String message;
	
	public Boolean getHead_approved() {
		return head_approved;
	}
	public void setHead_approved(Boolean head_approved) {
		this.head_approved = head_approved;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getBenco_approved() {
		return benco_approved;
	}
	public void setBenco_approved(Boolean benco_approved) {
		this.benco_approved = benco_approved;
	}
	public Boolean getSupervisor_approved() {
		return supervisor_approved;
	}
	public void setSupervisor_approved(Boolean supervisor_approved) {
		this.supervisor_approved = supervisor_approved;
	}
	public Boolean getDepartment_head_approved() {
		return head_approved;
	}
	public void setDepartment_head_approved(Boolean department_head_approved) {
		this.head_approved = department_head_approved;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Individual getEmployee() {
		return employee;
	}
	public void setEmployee(Individual employee) {
		this.employee = employee;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
