package com.revature.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="request")
public class Requests {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="question")
	private String question;
	@Column(name="answer")
	private String answer;
	@Column(name="request_time")
	private LocalDateTime requestMadeAt;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="requester_id")
	private User requester;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="requestee_id")
	private User requestee;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="request_status")
	private ReviewStatus requestStatus;

	public Requests() {
		id = 0;
		question = "";
		answer = "";
		requestMadeAt = LocalDateTime.now();
		requester = new User();
		requestee = new User();
		requestStatus = new ReviewStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public LocalDateTime getRequestMadeAt() {
		return requestMadeAt;
	}

	public void setRequestMadeAt(LocalDateTime requestMadeAt) {
		this.requestMadeAt = requestMadeAt;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public User getRequestee() {
		return requestee;
	}

	public void setRequestee(User requestee) {
		this.requestee = requestee;
	}

	public ReviewStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(ReviewStatus requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((requestMadeAt == null) ? 0 : requestMadeAt.hashCode());
		result = prime * result + ((requestStatus == null) ? 0 : requestStatus.hashCode());
		result = prime * result + ((requestee == null) ? 0 : requestee.hashCode());
		result = prime * result + ((requester == null) ? 0 : requester.hashCode());
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
		Requests other = (Requests) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (requestMadeAt == null) {
			if (other.requestMadeAt != null)
				return false;
		} else if (!requestMadeAt.equals(other.requestMadeAt))
			return false;
		if (requestStatus == null) {
			if (other.requestStatus != null)
				return false;
		} else if (!requestStatus.equals(other.requestStatus))
			return false;
		if (requestee == null) {
			if (other.requestee != null)
				return false;
		} else if (!requestee.equals(other.requestee))
			return false;
		if (requester == null) {
			if (other.requester != null)
				return false;
		} else if (!requester.equals(other.requester))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Requests [id=" + id + ", question=" + question + ", answer=" + answer + ", requestMadeAt="
				+ requestMadeAt + ", requester=" + requester + ", requestee=" + requestee + ", requestStatus="
				+ requestStatus + "]";
	}

}
