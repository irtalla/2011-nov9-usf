package data;

import models.Draft_Status_Article;
import models.Draft_Status_Other;
import models.Employee;

public interface Draft_Status_Other_Dao {
	public Draft_Status_Other getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Draft_Status_Other add(Draft_Status_Other e);
	public void update(Draft_Status_Other e);
}
