package com.revature.beans;

public class ExternalRequest {
	
	private Integer id; 
	private Integer editorId; 
	private Integer pitchId; 
	private String requestContent; 
	private String responseContent; 
	private Status status;
	
	public ExternalRequest() {}
	
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
	public Integer getPitchId() {
		return pitchId;
	}
	public void setPitchId(Integer pitchId) {
		this.pitchId = pitchId;
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
