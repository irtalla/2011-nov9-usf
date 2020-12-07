package com.cross.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Request {
	@Id
	private Integer id; 
	@Column(name="sender_id")
	private Integer senderId; 
	@Column(name="reciever_id")
	private Integer recieverId; 
	private String requestContent; 
	private String responseContent; 
	private Status status;
	private Timestamp creationTime; 
	
	public Request() {
		
		requestContent = "";
		responseContent = ""; 
		status = new Status(); 
		status.setName("open");
		status.setId(5);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(Integer recieverId) {
		this.recieverId = recieverId;
	}

	public String getRequestContent() {
		return requestContent;
	}

	public void setRequestContent(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(String responseContent) {
		this.responseContent = responseContent;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	
	
}
