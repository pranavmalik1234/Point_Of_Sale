package com.pos.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos.DAO.CustomerDao;
import com.pos.models.Customer;
import com.pos.models.Employee;
import com.pos.springconfigurations.SessionFactoryConfigurations;


@Repository
public class CustomerDaoImpl  implements CustomerDao{
	@Autowired
	SessionFactoryConfigurations sessionFactoryConfig;
	
	Configuration conf=new Configuration().configure().addAnnotatedClass(Customer.class);

	@Override
	public List<Customer> getAllCustomers() {
		Session session= opensession();
		List<Customer>databaselists = null;
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from customers");
			databaselists=q.list();
		}catch(Exception ex) {}
		closesession(tx,session);
		return databaselists;
	}
	
	
	@Override
	public void CreateCustomer() {
		System.out.println("customer dao");
		Session session= opensession();
		Customer c=new Customer();
		c.setEmailid("abc@xyz.com");
		c.setMobile("999999999");
		c.setName("abc xyz");
		c.setId(1);
		Transaction tx=session.beginTransaction();
		try {
			session.save(c);
		}catch(Exception ex) {}
		closesession(tx,session);		
	}
	
	
	public Session opensession() {
		return sessionFactoryConfig.HibernateInitialization(conf).openSession();
	
		
	}
	public void closesession(Transaction tx, Session session) {
		tx.commit();
		session.close();
		sessionFactoryConfig.closeSessionFactory();
	}
	public static void main(String args[]) {
		CustomerDaoImpl d= new CustomerDaoImpl();
		d.getAllCustomers();
	}

	

}
