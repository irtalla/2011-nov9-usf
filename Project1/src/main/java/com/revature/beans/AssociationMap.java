package com.revature.beans;

public class AssociationMap<T> {
	private Class<T> type;
	private String foreignKeyName;
	private boolean isHasMany;
	public AssociationMap(Class<T> type, String foreignKeyName, boolean isHasMany) {
		super();
		this.type = type;
		this.foreignKeyName = foreignKeyName;
		this.isHasMany = isHasMany;
	}
	public Class<T> getType() {
		return type;
	}
	public void setType(Class<T> type) {
		this.type = type;
	}
	public String getForeignKeyName() {
		return foreignKeyName;
	}
	public void setForeignKeyName(String foreignKeyName) {
		this.foreignKeyName = foreignKeyName;
	}
	public boolean isHasMany() {
		return isHasMany;
	}
	public void setHasMany(boolean isHasMany) {
		this.isHasMany = isHasMany;
	}
	
	
}
