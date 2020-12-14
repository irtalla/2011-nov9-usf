package com.revature.beans;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "approval_file")
public class ApprovalFile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "reimbursement_form_id")
	private Integer formId;
	@Column(name = "approver_id")
	private Integer approverId;
	@Column(name = "file_name")
	private String name;
	@Column(name = "file_data")
	private byte[] data;
	
	public ApprovalFile()
	{
		id = -1;
		formId = -1;
		approverId = -1;
		name = "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approverId == null) ? 0 : approverId.hashCode());
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + ((formId == null) ? 0 : formId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ApprovalFile other = (ApprovalFile) obj;
		if (approverId == null) {
			if (other.approverId != null)
				return false;
		} else if (!approverId.equals(other.approverId))
			return false;
		if (!Arrays.equals(data, other.data))
			return false;
		if (formId == null) {
			if (other.formId != null)
				return false;
		} else if (!formId.equals(other.formId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApprovalFile [id=" + id + ", formId=" + formId + ", approverId=" + approverId + ", name=" + name
				+ ", data=" + Arrays.toString(data) + "]";
	}
	
	
}
