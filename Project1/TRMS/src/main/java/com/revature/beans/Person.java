package com.revature.beans;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.*;

public class Person {
	private Integer id;
	private String username;
	private String password;
	private Set<Title> titles;
	private Set<EvtReq> evtReqs;
	private Set<EvtReq> evtReqsToApprove;
	private Role role;
	private ReqFrCmnt reqFrCmnt;

	private String title;
	private Integer role_id; 
	private LocalTime event_time;
	
	@JsonFormat(pattern = "hh:mm")
	public LocalTime getEvent_time() {
		return event_time;
	}


	public void setEvent_time(LocalTime event_time) {
		this.event_time = event_time;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Set<EvtReq> getEvtReqsToApprove() {
		return evtReqsToApprove;
	}


	public void setEvtReqsToApprove(Set<EvtReq> evtReqsToApprove) {
		this.evtReqsToApprove = evtReqsToApprove;
	}


	public Integer getRole_id() {
		return role_id;
	}


	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Person() {
		id = 0;
		username = "";
		password = "";
		titles = new HashSet<Title>();
		evtReqs = new HashSet<EvtReq>();
		role = new Role();
		reqFrCmnt = new ReqFrCmnt();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Title> getTitles() {
		return titles;
	}


	public void setTitles(Set<Title> titles) {
		this.titles = titles;
	}


	public Set<EvtReq> getEvtReqs() {
		return evtReqs;
	}


	public void setEvtReqs(Set<EvtReq> evtReqs) {
		this.evtReqs = evtReqs;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public ReqFrCmnt getReqFrCmnt() {
		return reqFrCmnt;
	}


	public void setReqFrCmnt(ReqFrCmnt reqFrCmnt) {
		this.reqFrCmnt = reqFrCmnt;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evtReqs == null) ? 0 : evtReqs.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((reqFrCmnt == null) ? 0 : reqFrCmnt.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((titles == null) ? 0 : titles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Person other = (Person) obj;
		if (evtReqs == null) {
			if (other.evtReqs != null)
				return false;
		} else if (!evtReqs.equals(other.evtReqs))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (reqFrCmnt == null) {
			if (other.reqFrCmnt != null)
				return false;
		} else if (!reqFrCmnt.equals(other.reqFrCmnt))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (titles == null) {
			if (other.titles != null)
				return false;
		} else if (!titles.equals(other.titles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", password=" + password + ", titles=" + titles
				+ ", evtReqs=" + evtReqs + ", role=" + role + ", reqFrCmnt=" + reqFrCmnt + "]";
	}	

}
