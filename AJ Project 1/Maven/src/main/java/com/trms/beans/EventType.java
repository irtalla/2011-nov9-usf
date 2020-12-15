package com.trms.beans;

import javax.persistence.*;

@Entity
@Table(name="event_type")
public class EventType {
	@Id
	private Integer id;
	private String name;
	
	public EventType() {
		id = 0;
		name = "";
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
