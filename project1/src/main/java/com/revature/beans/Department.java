package com.revature.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table
public class Department {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;

private String name;

@Transient
private Individual head;

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
public Individual getHead() {
	return head;
}
public void setHead(Individual head) {
	this.head = head;
}
}
