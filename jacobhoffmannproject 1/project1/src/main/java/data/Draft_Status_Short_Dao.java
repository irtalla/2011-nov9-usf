package data;

import models.Draft_Status_Article;
import models.Draft_Status_Other;
import models.Draft_Status_Short;

public interface Draft_Status_Short_Dao {
	public Draft_Status_Short getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Draft_Status_Short add(Draft_Status_Short e);
	public void update(Draft_Status_Short e);
}
