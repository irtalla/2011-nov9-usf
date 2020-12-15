package servicespackage.pitch_status;

import java.util.Set;

import models.Pitch_Status;

public interface Pitch_Status_Service {
	public Pitch_Status getById(Integer id);
	public Set<Pitch_Status> getByEditor(Integer id);
	//public Draft_Status_Article getByUserId( Integer i);
	public Pitch_Status add(Pitch_Status e);
	public void update(Pitch_Status e);
	public Set<Pitch_Status> getAll();
	public Set<Pitch_Status> getByAuthorId(Integer id);
}
