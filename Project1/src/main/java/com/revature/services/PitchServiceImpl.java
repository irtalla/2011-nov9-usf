package com.revature.services;

import com.revature.beans.Role;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.GenreCommittee;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.Status;
import com.revature.data.PitchDAO;
import com.revature.data.PitchDAOFactory;

public class PitchServiceImpl implements PitchService {
	private PitchDAO pitchDAO;

	public PitchServiceImpl() {
		PitchDAOFactory pitchDAOFactory = new PitchDAOFactory();
		pitchDAO = pitchDAOFactory.getPitchDAO();
	}

	public Integer addPitch(Pitch pitch, Person author) {
		pitch.setAuthor(author);
		return pitchDAO.add(pitch).getId();
	}

	public Pitch getPitchById(Integer id) {
		return pitchDAO.getById(id);
	}

	public Set<Pitch> getPitches() {
		return pitchDAO.getAll();
	}

	public Set<Pitch> getPitchesByStatus(String status) {
		return pitchDAO.getByStatus(status);
	}

	public Set<Pitch> getPitchesByAuthor(Person author){
		return pitchDAO.getByAuthor(author);
	}

	public void updatePitch(Pitch p) {
		pitchDAO.update(p);
	}

	public void approvePitch(Pitch pitch, Person person) {
		Set<Person> approvals = new HashSet<>();
		approvals.add(person);
		pitch.setApprovals(approvals);
		if(reviewApprovals(pitch)){
			Status s = new Status();
			s.setId(2);
			s.setName("Approved");
			pitch.setStatus(s);
			updatePitch(pitch);
		}

		updatePitch(pitch);
	}

	public void rejectPitch(Pitch pitch, Person person, String editorNotes) {
		Status s = new Status();
		s.setId(3);
		s.setName("Rejected");
		pitch.setStatus(s);
		pitch.setEditorNotes("Rejected by: " + person + "\n Their notes: " + editorNotes);
		updatePitch(pitch);
	}

	public void removePitch(Pitch p) {
		pitchDAO.delete(p);
	}

@Override
public Boolean reviewApprovals(Pitch pitch){
      Set<Person> approvals = pitch.getApprovals();

      Integer assistantInCount = 0;
      Integer generalInCount = 0;
      Integer seniorInCount = 0;
      Integer assistantOutCount = 0;
      Integer generalOutCount = 0;
      Integer seniorOutCount = 0;

      for(Person person : approvals){
    	 Role role = person.getRole();
         Set<GenreCommittee> committees = person.getGenreCommittees();
         for(GenreCommittee committee : committees){

            if(committee == pitch.getGenre()){
               if(role.getName() == "Assistant Editor"){
                  assistantInCount++;
               }
               if(role.getName() == "General Editor"){
                  generalInCount++;
               }
               if(role.getName() == "Senior Editor"){
                  seniorInCount++;
               }
            }

            else{
               if(role.getName() == "Assistant Editor"){
                  assistantOutCount++;
               }
               if(role.getName() == "General Editor"){
                  seniorOutCount++;
               }
               if(role.getName() == "Senior Editor"){
                  seniorOutCount++;
               }
            }
         }
      }
		if(assistantInCount > 0 && generalOutCount > 0 && seniorInCount > 0){
			return true;
		}
		else{
			return false;
		}
	}
}
