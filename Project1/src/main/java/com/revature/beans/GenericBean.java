package com.revature.beans;

import java.util.Map;
import java.util.Set;

public interface  GenericBean {
	//has many relationships:
//	abstract Map<String, Class<? extends GenericBean>> foreignKeysForPrimaryKeyToClasses();
	abstract Set<AssociationMap<?>> foreignKeysForPrimaryKeyToClasses();
	//belongs to relationships:
	abstract Set<AssociationMap<?>> owningForeignKeysToClasses();

}
