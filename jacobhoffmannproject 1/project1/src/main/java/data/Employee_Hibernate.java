package data;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import exceptions.NonUniqueUsernameException;
import models.Employee;
import models.Users;
import utils.HibernateUtil;
public class Employee_Hibernate implements Employee_Dao{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	public Employee getEmployeeById(Integer id) {
		Session s = hu.getSession();
		Employee e = s.get(Employee.class,id);
		s.close();
		return e;
	}
	public Employee getByUserId( Integer i) {
		Session s = hu.getSession();
		String query = "FROM Employee where user_id = :status";
		Query<Employee> q = s.createQuery(query, Employee.class);
		q.setParameter("status", i);
		Employee emp = q.getSingleResult();
		s.close();
		return emp;
	}
	public Employee add(Employee e) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(e);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return e;
	}
	public void updateEmployee(Employee e) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(e);
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}
}
