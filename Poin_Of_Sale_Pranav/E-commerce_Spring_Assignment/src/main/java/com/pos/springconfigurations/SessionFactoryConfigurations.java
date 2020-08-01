package com.pos.springconfigurations;

import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hibernate.cfg.Configuration;

@Component
public class SessionFactoryConfigurations {
	
	static SessionFactory sf;
	
	public SessionFactory HibernateInitialization(Configuration conf) {
		ServiceRegistryBuilder registry = new ServiceRegistryBuilder();
		registry.applySettings(conf.getProperties());
		ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
		sf= conf.buildSessionFactory(serviceRegistry);
		return sf;
	}
	
	public void closeSessionFactory() {
		sf.close();
	}

}