package com.trms.beans;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table
public class Activity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="date")
	private LocalDateTime timestamp;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="event_id")
	private EventType event;



	@Override
	public String toString() {
		return "Activity{" +
				"id=" + id +
				", timestamp=" + timestamp +
				", event=" + event +
				", creator=" + creator +
				", requestId=" + requestId +
				", comment='" + comment + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="creator_id")
	private Person creator;
//	@ManyToOne
	@Column(name="request_id")
	private Integer requestId;
	private String comment;
	private String name;
	private String filepath;
	
	public Activity() {
		id = 0;
		timestamp = LocalDateTime.now();
		event = new EventType();
		creator = new Person();
		requestId = 0;
		comment = "";
		name = "";
		filepath = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public EventType getEvent() {
		return event;
	}

	public void setEvent(EventType event) {
		this.event = event;
	}

	public Person getCreator() {
		return creator;
	}

	public void setCreator(Person creator) {
		this.creator = creator;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
}
