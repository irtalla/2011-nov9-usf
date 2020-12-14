package com.revature.services;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Individual;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.ReimbursementDAO;
import com.revature.data.ReimbursementDAOFactory;

public class ReimbursementServiceImpl implements ReimbursementService {
	ReimbursementDAOFactory reimbursementDaoFactory = new ReimbursementDAOFactory();
	ReimbursementDAO reimbursementDao = reimbursementDaoFactory.getReimbursementDAO();

	@Override
	public Integer addReimbursement(Reimbursement r) throws Exception {
		// TODO Auto-generated method stub
		Reimbursement re = reimbursementDao.add(r);
		if(re.getId() != null)
			return re.getId();
		else
			return 0;
	}

	@Override
	public Set<Reimbursement> getReimbursementById(Integer id) {
		Set<Reimbursement> rSet = new HashSet<>();
		rSet = reimbursementDao.getReimbursementsByUserId(id);
		return rSet;
	}
	@Override
	public Set<String> getAttachmentsById(Integer id){
		Set<String> attachments = new HashSet<>();
		
		String directory = "src/main/resources/static/attachments/" + id;
		File[] attachmentArray = new File(directory).listFiles();

		if(attachmentArray != null) {
			for(int i = 0; i < attachmentArray.length; i++) {
				if(attachmentArray[i].getPath() == null) {
					attachments.add("");
				}else
				attachments.add(attachmentArray[i].getName());
			}
			return attachments;
		}

		else {
			attachments.add("");
			return attachments;
		}
	}

	@Override
	public Set<Reimbursement> getReimbursementByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub

	}
	@Override
	public Set<Reimbursement> getAll() {
		Set<Reimbursement> rSet = new HashSet<>();
		rSet = reimbursementDao.getAll();
		return rSet;
		// TODO Auto-generated method stub

	}
	
	public Reimbursement getSingleReimbursementById(Integer id) {
		Reimbursement r = new Reimbursement();
		
		r = reimbursementDao.getById(id);
		
		return r;
	}
	public Reimbursement addMessageToReimbursement(Integer id, String message) {
		Reimbursement nR = new Reimbursement();
		
		Reimbursement r = reimbursementDao.getById(id);
		
		r.setMessage(message);
		
		nR = reimbursementDao.updateReimbursement(r);
		
		return nR;
	}
	public Integer approve(Integer id, Integer n) {
		Integer retValue = 0;
		Reimbursement nR = new Reimbursement();
		Reimbursement r = reimbursementDao.getById(id);
		
		switch(n) {
		case 1://supervisor
			r.setSupervisor_approved(true);
			break;
		
		case 2://benco
			r.setBenco_approved(true);
			break;
			
		case 3://department head
			r.setDepartment_head_approved(true);
			break;	
			
		default:
			return -1;
		}
		if(r.getBenco_approved() && r.getDepartment_head_approved() && r.getSupervisor_approved()) {
			Status status  = new Status();
			status.setId(3);
			status.setName("approved");
			r.setStatus(status);
		}
		nR = reimbursementDao.updateReimbursement(r);
		retValue = nR.getId();
		return retValue;
	}
}
