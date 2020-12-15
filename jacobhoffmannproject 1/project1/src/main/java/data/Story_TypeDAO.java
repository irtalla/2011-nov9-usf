package data;

import models.Request_Info;
import models.Story_Type;

public interface Story_TypeDAO {
	public Story_Type getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Story_Type add(Story_Type e);
	public void update(Story_Type e);
}
