package com.revature.data;

import java.util.Set;

public abstract class PostgresDao<T> implements GenericDao<T> {
	public abstract String tableName();
	public abstract Set<PostgresRelation> hasManyRelations();
	public abstract Set<PostgresRelation> belongsToRelations();
}
