package com.revature.beans;

public class Draft {
	
	private Integer id; 
	private Integer pitchId; 
	private Integer authorId; 
	private String content;
	private Status status;
	
	public Draft() {}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPitchId() {
		return pitchId;
	}
	public void setPitchId(Integer pitchId) {
		this.pitchId = pitchId;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	} 
	
	

}
