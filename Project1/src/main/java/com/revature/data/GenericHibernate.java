package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.Map;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.revature.beans.AssociationMap;
import com.revature.beans.Attachment;
import com.revature.beans.Draft;
import com.revature.beans.Person;
import com.revature.utils.HibernateUtil;

public abstract class GenericHibernate<T> implements GenericDAO<T>{
	protected Class<T> type;
	protected String tableName;
	protected HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public GenericHibernate(Class<T> type, String tableName) {
		this.type = type;
		this.tableName = tableName;
	}
	
	@Override
	public T getByIdEagerly(Integer id) {
		return this.getByIdLazily(id);
	}
//	public abstract Set<AssociationMap<T>> getMaps(); 
//	@Override
//	public T getByIdEagerly(Integer id) {
//		Session s = hu.getSession();
//		
//		try {
//			T t = s.get(this.type, id);
//			return t;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			s.close();
//		}
//		return null;
//	}
//	@Override
//	public T getByIdEagerly(Integer id) {
//		Session s = hu.getSession();
//		
//		String sql = "SELECT * from " + this.tableName + " where id = " + id;
//        NativeQuery<T> query = s.createNativeQuery(sql, this.type);
//        T match = query.getSingleResult();
//
//        for(AssociationMap<T> map : this.getHasManyMaps()) {
//			
//        	CriteriaBuilder cb = s.getCriteriaBuilder();
//			CriteriaQuery<T> cq = cb.createQuery(this.type);
//			Root<T> root = cq.from(this.type);
//			
//			Predicate predicate = cb.equal(root.get(map.getForeignKeyName()), id);
//			
//			cq.select(root).where(predicate);
//		}
//        
//        return match;
//		try {
//			T t = s.get(this.type, id);
//			return t;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			s.close();
//		}
//		return null;
//	}

	@Override
	public T getByIdLazily(Integer id) {
		Session s = hu.getSession();
//		try {
//			T t = s.get(this.type, id);
//			return t;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			s.close();
//		}
//		return null;
//		for(AssociationMap<T> map : this.getMaps()) {
//			CriteriaBuilder cb = s.getCriteriaBuilder();
//			CriteriaQuery<T> cq = cb.createQuery(this.type);
//			Root<T> root = cq.from(this.type);
//			
//			Predicate predicate = cb.equal(root.get(map.getForeignKeyName()), id);
//			
//			cq.select(root).where(predicate);
//		}
//		
//		Transaction tx = null;
	      
//	      try {
//	         tx = s.beginTransaction();
	         String sql = "SELECT * from " + this.tableName + " where id = " + id;
	         NativeQuery<T> query = s.createNativeQuery(sql, this.type);
	         return query.getSingleResult();

	         
//	         tx.commit();
//	      } catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      } finally {
//	         s.close(); 
//	      }
	}

	@Override
	public Set<T> getAllLazily() {
		Session s = hu.getSession();
		String sql = "SELECT * from " + this.tableName;
        NativeQuery<T> query = s.createNativeQuery(sql, this.type);
        List<T> matches = query.getResultList();
        return new HashSet<>(matches);
	}
	
	@Override
	public Set<T> getAllLazilyWhereOwnerIdIs(String ownerIdName, Integer ownerId) {
		Session s = hu.getSession();
		String sql = "SELECT * from " + this.tableName + " where " + ownerIdName + " = " + ownerId;
        NativeQuery<T> query = s.createNativeQuery(sql, this.type);
        List<T> matches = query.getResultList();
        return new HashSet<>(matches);
	}
	
//	public Set<T> getAllEagerly() {
//		Session s = hu.getSession();
//		CriteriaBuilder cb = s.getCriteriaBuilder();
//		CriteriaQuery<T> criteria = cb.createQuery(this.type);
//		Root<T> root = criteria.from(this.type);
//		
//		criteria.select(root);
//		
//		List<T> tList = s.createQuery(criteria).getResultList();
//		s.close();
//		return new HashSet<T>(tList);
//	}
	
	@Override
	public Set<T> getAllEagerly() {
		return this.getAllLazily();
	}

	public void update(T t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	public void delete(T t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	public Integer add(T t) {
		Session s = hu.getSession();
		Transaction tx = null;
		Integer id = null;
		try {
			tx = s.beginTransaction();
			id = (Integer) s.save(t);
			return id;
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return null;
	}

//	@Override
//	public T getById(Integer id) {
//		Session s = hu.getSession();
//		for(AssociationMap<T> map : this.getMaps()) {
//			CriteriaBuilder cb = s.getCriteriaBuilder();
//			CriteriaQuery<T> cq = cb.createQuery(this.type);
//			Root<T> root = cq.from(this.type);
//			
//			Predicate predicate = cb.equal(root.get(map.getForeignKeyName()), id);
//			
//			cq.select(root).where(predicate);
//			
//			List<T> list = s.createQuery(cq).getResultList();
//			return new HashSet<T>(list);
//		}
//		return null;
//	}
}
