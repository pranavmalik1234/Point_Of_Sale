package com.pos.DAOImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pos.DAO.SaveOrderDao;
import com.pos.models.SavedOrder;
import com.pos.springconfigurations.SessionFactoryConfigurations;

@Repository
public class SaveOrderDaoImpl implements SaveOrderDao{
	
	@Autowired
	SessionFactoryConfigurations sessionFactoryConfig;
	
	Configuration conf=new Configuration().configure().addAnnotatedClass(SavedOrder.class);

	@Override
	public boolean saveOrder(ArrayList<Object> orderDetails) {
		
		return false;
	}

	@Override
	public void addOrder(SavedOrder newOrder, String neworderid) {
		Session session= opensession();

		Transaction tx=session.beginTransaction();
		try {
			session.save(newOrder);
		}catch(Exception ex) {}
		closesession(tx,session);		
		
	}
	
	@Override
	public List<SavedOrder> getOrderDetail(String employeeName) {
		Session session= opensession();
		List<SavedOrder>databaselists = null;
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("from SavedOrder where employee=:employee");
			q.setParameter("employee", employeeName);
			databaselists=q.list();
		}catch(Exception ex) {}
		closesession(tx,session);
		return databaselists;
	}
	
	@Override
	public void deleteSavedOrder(String orderid) {
		Session session= opensession();
		Transaction tx=session.beginTransaction();
		try {
			Query q= session.createQuery("delete from SavedOrder where orderid=:orderid");
			q.setParameter("orderid", orderid);
			q.executeUpdate();
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




}
