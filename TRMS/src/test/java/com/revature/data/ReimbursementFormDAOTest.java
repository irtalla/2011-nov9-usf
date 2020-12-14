package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.GradePresentationFile;
import com.revature.beans.GradingFormat;
import com.revature.beans.ReimbursementForm;

public class ReimbursementFormDAOTest {

	@Test
	public void testGetById()
	{
		ReimbursementForm form = DAOFactory.getReimbursementFormDAO().getById(1);
		//System.out.println(form);
		assertTrue(form.getId() == 1);
	}
	
	@Test
	public void testAddDelete()
	{
		ReimbursementForm form = new ReimbursementForm();
		form.setEmployeeId(1);
		form.setEventId(1);
		GradingFormat format = new GradingFormat();
		format.setId(1);
		format.setName("Exam");
		form.setGradingFormat(format);
		form.setStage(DAOFactory.getReimbursementFormDAO().getStageById(1));
		form.setStatus(DAOFactory.getReimbursementFormDAO().getStatusById(1));
		
		form = DAOFactory.getReimbursementFormDAO().add(form);
		ReimbursementForm form2 = DAOFactory.getReimbursementFormDAO().getById(form.getId());
		System.out.println(form);
		System.out.println(form2);
		assertEquals(form,form2);
		DAOFactory.getReimbursementFormDAO().delete(form2);
		form2 = DAOFactory.getReimbursementFormDAO().getById(form.getId());
		System.out.println(form2);
		assertEquals(form2, null);
	}
	
	@Test
	public void testUpdate()
	{
		ReimbursementForm form = new ReimbursementForm();
		form.setEmployeeId(1);
		form.setEventId(1);
		GradingFormat format = new GradingFormat();
		format.setId(1);
		format.setName("Exam");
		form.setGradingFormat(format);
		form.setStage(DAOFactory.getReimbursementFormDAO().getStageById(1));
		form.setStatus(DAOFactory.getReimbursementFormDAO().getStatusById(1));
		
		form = DAOFactory.getReimbursementFormDAO().add(form);
		form.setStatus(DAOFactory.getReimbursementFormDAO().getStatusById(2));
		DAOFactory.getReimbursementFormDAO().update(form);
		form = DAOFactory.getReimbursementFormDAO().getById(form.getId());
		assertTrue(form.getStatus().getId() == 2);
		DAOFactory.getReimbursementFormDAO().delete(form);
	}
	
	@Test
	public void testGetAll()
	{
		Set<ReimbursementForm> s = DAOFactory.getReimbursementFormDAO().getAll();
		assertTrue(s.size() > 0);
	}
	
	@Test
	public void testPresentationUploadDownload()
	{
		Path path = Paths.get("D:/Testing/Java/Hi.txt");
		GradePresentationFile f = new GradePresentationFile();
		
	}
}
