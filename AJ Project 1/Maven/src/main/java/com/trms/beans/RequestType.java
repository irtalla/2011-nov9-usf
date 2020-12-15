package com.trms.beans;

import javax.persistence.*;

@Entity
@Table(name="request_type")
public class RequestType {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Float percentage;
	
	public RequestType() {
		id = 0;
		name = "";
		percentage = 0f;
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

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}
}
