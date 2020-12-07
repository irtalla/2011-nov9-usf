package com.revature.data;

import org.hibernate.Session;

import com.cross.beans.Pitch;
import com.cross.utils.HibernateUtil;

public class PitchDAO {
	
	
	public static void addPitch(Pitch p) {
		
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        try {
            session.save(p);	
        } catch (Exception e) {
        	session.getTransaction().rollback();
        	e.printStackTrace();
        }
 
        //Commit the transaction
        session.getTransaction().commit();
        session.clear();
	}

}
