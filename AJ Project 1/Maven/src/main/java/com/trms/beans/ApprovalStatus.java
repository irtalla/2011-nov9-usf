package com.trms.beans;

import javax.persistence.*;

@Entity
@Table(name="status")
public class ApprovalStatus {
	@Id
//	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	public ApprovalStatus() {
		id = 1;
		name = "Created, awaiting manager";
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
