package data;

import java.util.Set;

import models.Draft_Status_Article;
import models.Draft_Status_Other;
import models.Pitch_Status;

public interface Pitch_StatusDAO {
	public Pitch_Status getById(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Set<Pitch_Status> getByEditorId(Integer id);
	public Set<Pitch_Status> getByUserId(Integer id);
	public Pitch_Status add(Pitch_Status e);
	public void update(Pitch_Status e);
	public Set<Pitch_Status> getAll();
}
