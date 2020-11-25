package com.revature.beans;

public class User {
	private Integer userID;
	private String username;
	private String password;
	private Integer roleID;
	private Double accountBalance;
	private Integer paymentsRemaining;
	
	
	
	public User(Integer userID, String username, String password, Integer roleID, Double accountBalance, Integer paymentsRemaining) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.roleID = roleID;
		this.accountBalance = accountBalance;
		this.paymentsRemaining = paymentsRemaining;
	}
	public User() {
		this.userID = 0;
		this.username = "";
		this.password = "";
		this.roleID = 0;
		this.accountBalance = 0.0;
		this.paymentsRemaining = 0;
	}
	public Integer getPaymentsRemaining() {
		return paymentsRemaining;
	}
	public void setPaymentsRemaining(Integer paymentsRemaining) {
		this.paymentsRemaining = paymentsRemaining;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getRoleID() {
		return roleID;
	}
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
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
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", roleID=" + roleID
				+ ", accountBalance=" + accountBalance + ", paymentsRemaining=" + paymentsRemaining + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountBalance == null) ? 0 : accountBalance.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((paymentsRemaining == null) ? 0 : paymentsRemaining.hashCode());
		result = prime * result + ((roleID == null) ? 0 : roleID.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
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
		User other = (User) obj;
		if (accountBalance == null) {
			if (other.accountBalance != null)
				return false;
		} else if (!accountBalance.equals(other.accountBalance))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (paymentsRemaining == null) {
			if (other.paymentsRemaining != null)
				return false;
		} else if (!paymentsRemaining.equals(other.paymentsRemaining))
			return false;
		if (roleID == null) {
			if (other.roleID != null)
				return false;
		} else if (!roleID.equals(other.roleID))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	

	
}
