package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.ApprovalFile;

public class ApprovalFileDAOTest {

	@Test
	public void testAddGetDelete()
	{
		Path path = Paths.get("D:/Testing/Java/Hi.txt");
		ApprovalFile f = new ApprovalFile();
		f.setName("Hi.txt");
		f.setApproverId(2);
		f.setFormId(1);
		try {
			f.setData(Files.readAllBytes(path));
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue(false);
			return;
		}
		
		
		DAOFactory.getApprovalFileDAO().add(f);
		assertTrue(f.getId() != -1);
		
		Set<ApprovalFile> f3 = DAOFactory.getApprovalFileDAO().getApprovalFileByFormId(f.getFormId());
		assertTrue(f3.contains(f));
		
		DAOFactory.getApprovalFileDAO().delete(f);
		f3 = DAOFactory.getApprovalFileDAO().getApprovalFileByFormId(f.getFormId());
		assertFalse(f3.contains(f));
		
	}
}
