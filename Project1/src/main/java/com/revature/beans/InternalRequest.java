package com.revature.beans;

public class InternalRequest {
	
	private Integer id; 
	private Integer editorId; 
	private Integer decisionId; 
	private String requestContent; 
	private String responseContent; 
	private Status status;
	
	public InternalRequest() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEditorId() {
		return editorId;
	}
	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}
	public Integer getDecisionId() {
		return decisionId;
	}
	public void setDecisionId(Integer decisionId) {
		this.decisionId = decisionId;
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
