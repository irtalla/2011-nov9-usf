package JDBC;

import Entity.Status;

public interface StatusJDBC extends GenericJDBC<Status> {
	public Status add(Status s);

}
