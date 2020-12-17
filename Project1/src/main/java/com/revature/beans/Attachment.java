package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="attachment")
public class Attachment {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="file_body")
	private byte[] fileBody;
	
//	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="pitch_id")
	@Transient
	private Pitch pitch;
	
	@Column(name="pitch_id")
	private Integer pitchId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileBody() {
		return fileBody;
	}

	public void setFileBody(byte[] fileBody) {
		this.fileBody = fileBody;
	}

	public Integer getPitchId() {
		return pitchId;
	}

	public void setPitchId(Integer pitchId) {
		this.pitchId = pitchId;
	}

	@Transient
	public Pitch getPitch() {
		return pitch;
	}

	@Transient
	public void setPitch(Pitch pitch) {
		this.pitch = pitch;
	}
	
	
}
