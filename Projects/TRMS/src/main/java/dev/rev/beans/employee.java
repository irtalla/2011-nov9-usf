package dev.rev.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class employee {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
 private int emp_id;
 private String emp_name;
 private String emp_email;
 private String password;
 private String role;
 private int max_claim;
 private int awardedclaim;
 private int pendingclaim;
 
 public employee() {
	 
	 emp_id=0;
	 emp_name=role="";
	 emp_email="";
	 password="";
	 max_claim=1000;
	 awardedclaim=pendingclaim=0;
 }
 
 
public int getEmp_id() {
	return emp_id;
}
public void setEmp_id(int emp_id) {
	this.emp_id = emp_id;
}
public String getEmp_name() {
	return emp_name;
}
public void setEmp_name(String emp_name) {
	this.emp_name = emp_name;
}
public String getEmp_email() {
	return emp_email;
}
public void setEmp_email(String emp_email) {
	this.emp_email = emp_email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getMax_claim() {
	return max_claim;
}
public void setMax_claim(int max_claim) {
	this.max_claim = max_claim;
}


public String getRole() {
	return role;
}


public void setRole(String role) {
	this.role = role;
}


public int getAwardedclaim() {
	return awardedclaim;
}


public void setAwardedclaim(int awardedclaim) {
	this.awardedclaim = awardedclaim;
}


public int getPendingclaim() {
	return pendingclaim;
}


public void setPendingclaim(int pendingclaim) {
	this.pendingclaim = pendingclaim;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + awardedclaim;
	result = prime * result + ((emp_email == null) ? 0 : emp_email.hashCode());
	result = prime * result + emp_id;
	result = prime * result + ((emp_name == null) ? 0 : emp_name.hashCode());
	result = prime * result + max_claim;
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + pendingclaim;
	result = prime * result + ((role == null) ? 0 : role.hashCode());
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
	employee other = (employee) obj;
	if (awardedclaim != other.awardedclaim)
		return false;
	if (emp_email == null) {
		if (other.emp_email != null)
			return false;
	} else if (!emp_email.equals(other.emp_email))
		return false;
	if (emp_id != other.emp_id)
		return false;
	if (emp_name == null) {
		if (other.emp_name != null)
			return false;
	} else if (!emp_name.equals(other.emp_name))
		return false;
	if (max_claim != other.max_claim)
		return false;
	if (password == null) {
		if (other.password != null)
			return false;
	} else if (!password.equals(other.password))
		return false;
	if (pendingclaim != other.pendingclaim)
		return false;
	if (role == null) {
		if (other.role != null)
			return false;
	} else if (!role.equals(other.role))
		return false;
	return true;
}


@Override
public String toString() {
	return "employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_email=" + emp_email + ", password="
			+ password + ", role=" + role + ", max_claim=" + max_claim + ", awardedclaim=" + awardedclaim
			+ ", pendingclaim=" + pendingclaim + "]";
}

}
