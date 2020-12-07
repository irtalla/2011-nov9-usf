package com.revature.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionUtil {
	private static SessionUtil hu;
	private static SessionFactory sessionFactory;
	
	private SessionUtil() {
		super();
	}
	
	public synchronized static SessionUtil getHibernateUtil() {
		if (hu == null)
			hu = new SessionUtil();
		return hu;
	}
	
	public synchronized static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			StandardServiceRegistry standardRegistry = 
					new StandardServiceRegistryBuilder().configure().build();
			Metadata meta = new MetadataSources(standardRegistry)
					.getMetadataBuilder()
					.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
					.build();
			sessionFactory = meta.getSessionFactoryBuilder().build();
		}
		return sessionFactory;
	}
	
	public Session getSession() {
		return this.getSessionFactory().openSession();
	}
}

