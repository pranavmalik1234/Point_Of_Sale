package com.pos.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos.DAO.EmployeeDao;
import com.pos.models.Employee;
import com.pos.springconfigurations.SessionFactoryConfigurations;


@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	

	
	@Autowired
	SessionFactoryConfigurations sessionFactoryConfig;
	
	Configuration conf=new Configuration().configure().addAnnotatedClass(Employee.class);
	
	
	@Override
	public void CreateEmployee(Employee e) {	
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		try {
			session.save(e);
		}catch(Exception ex) {}
		closesession(tx,session);
	}

	@Override
	public void UpdateEmployee(Employee e) {		
	}

	@Override
	public void edit(Employee ee) {		
	}

	@Override
	public void DeleteEmployee(Employee e) {		
	}

	@Override
	public List<Employee> find(Employee e) {	
		Session session= opensession();
		List<Employee>databaselists = null;
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from employee where username=:username and password=:password");
			q.setParameter("username", e.getUsername());
			q.setParameter("password", e.password);
			databaselists=q.list();
		}catch(Exception ex) {}
		closesession(tx,session);
		return databaselists;
	}

	@Override
	public void getAllEmployee() {		
	}
	
	public Session opensession() {
		return sessionFactoryConfig.HibernateInitialization(conf).openSession();
	
		
	}
	public void closesession(Transaction tx, Session session) {
		tx.commit();
		session.close();
		sessionFactoryConfig.closeSessionFactory();
	}

}
