package com.revature.data;

public class PostgresRelation<LeftPostgresDao, RightPostgresDao> {
	public enum RelationType {
		HAS_MANY, HAS_ONE, BELONGS_TO;
	};
	
	private RelationType relationType; 
	private String getterName; //name of method with which LeftPosgresDao obtains relational data from RightPostgresDao
	private String setterName; //name of method with which LeftPosgresDao purveys relational data to RightPostgresDao
	private PostgresRelation reciprocalRelation;
	
	public String getLeftTableName() {
		return leftTableName;
	}
	public void setLeftTableName(String leftTableName) {
		this.leftTableName = leftTableName;
	}
	public String getRightTableName() {
		return rightTableName;
	}
	public void setRightTableName(String rightTableName) {
		this.rightTableName = rightTableName;
	}
	public RelationType getRelationType() {
		return relationType;
	}
	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}
	public String getLeftTableGetterName() {
		return leftTableGetterName;
	}
	public void setLeftTableGetterName(String leftTableGetterName) {
		this.leftTableGetterName = leftTableGetterName;
	}
	public String getLeftTableSetterName() {
		return leftTableSetterName;
	}
	public void setLeftTableSetterName(String leftTableSetterName) {
		this.leftTableSetterName = leftTableSetterName;
	}
	public PostgresRelation getReciprocalRelation() {
		return reciprocalRelation;
	}
	public void setReciprocalRelation(PostgresRelation reciprocalRelation) {
		this.reciprocalRelation = reciprocalRelation;
	}
}
