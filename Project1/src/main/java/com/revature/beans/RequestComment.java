package com.revature.beans;

public class RequestComment {
	private Integer id;
	private String comment;
	
	public RequestComment() {
		id = 0;
		comment = "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
