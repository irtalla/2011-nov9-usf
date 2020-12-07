package com.cross.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Draft {
	@Id
	private Integer id; 
	@Column(name="pitch_id")
	private Integer pitchId; 
	@Column(name="author_id")
	private Integer authorId; 
	@Column(name="draft_content")
	private String content;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="status_id")
	private Status status;
	private Timestamp createdTime; 
	private Timestamp lastModifiedTime; 
	
	
	
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
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getLastModifiedTime() {
		return lastModifiedTime;
	}
	public void setLastModifiedTime(Timestamp lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	} 
}

