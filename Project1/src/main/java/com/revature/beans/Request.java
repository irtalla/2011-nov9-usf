package com.revature.beans;

public class Request {
	
	private Integer id; 
	private Integer requestorId; 
	private Integer requesteeId; 
	private String requestContent; 
	private String responseContent; 
	private Status status;
	
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

	public Integer getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(Integer requestorId) {
		this.requestorId = requestorId;
	}

	public Integer getRequesteeId() {
		return requesteeId;
	}

	public void setRequesteeId(Integer requesteeId) {
		this.requesteeId = requesteeId;
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
}
